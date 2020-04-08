<#--
	功能：用于生成sybase表结构创建语句，支持用户自定义类型转换、唯一性索引、一般性索引、联合主键
	@author：Humy
-->

<#-- 用户自定义类型 -->
<#assign userTypeMap ={ 
"fixString":"char","dateString":"varchar","string":"varchar","boolean":"char","int":"int","long":"decimal","dataTime":"timestamp","date":"date",
"time":"datetime","decimal":"decimal","amount":"decimal","schema":"varchar"
	}> 
<#-- 用户自定义的类型对应的默认长度  -->	
<#assign userTypeDefaultLengthMap ={"dateString": "(8)", "schema":"(255)", "boolean":"(1)", "amount":"(20,2)"}> 
	
<#--
 {"time":"datetime", "boolean":"char(1)", 
	"integer":"int", "binary":"image",
	"seqno":"varchar(25)",
	"amount":"decimal(15,2)",
	"date":"date",
	"datetime":"timestamp",
	"rate":"decimal(15,5)",
	"integer":"int",
	"enumer1":"char(1)",
	"enumer30":"varchar(30)",
	"transname":"varchar(100)",
	"loginid":"varchar(30)",
	"binary": "binary",
	"clob":"CLOB"
	}/
-->
<#-- 每个数据库表 -->
<#list tables as table>
<#if !table.isAbstract()?? || !table.isAbstract()>
	<#assign fields = table.getTrueFieldWithoutCache()!>
<#include "sql_macro.ftl"/> 
	<#assign hasKey = false/>
	<#assign keys = ""/>
	<#assign uniques = [] />
	<#assign indexs = [] />
--${table.longname!}
if exists (select 1 from sysobjects where type = 'U' and name = '${table.name}') drop table ${table.name}
go

create table dbo.${table.name} (
	<#-- 每个数据库表字段 -->
	<#list fields as field>
		<#-- 获取表主键 -->
		<#if field.primarykey == true>
			<#assign hasKey = true/>
			<#if keys == ""><#assign keys = field.id/><#else><#assign keys = keys + ", " + field.id/></#if>
		</#if>
		<#-- 获取唯一性索引 -->
		<#if field.index! == "unique">
			<#assign uniques = uniques + ["${field.id}"]>
		<#-- 获取一般性索引 -->
		<#elseif field.index! == "index">
			<#assign indexs = indexs + ["${field.id}"]>
		</#if>
		<#-- xml自定义类型到数据库类型转换 -->		
		<#assign dbType = help.getDbType(field.typeObj)!""/>
		<#list userTypeMap?keys as type>
			<#if type?lower_case == dbType?lower_case>
				<#assign dbType = userTypeMap[type]/>
				<#break/>
			</#if>
		</#list>
		<#assign fieldLength=help.getFieldLengthString(field, userTypeDefaultLengthMap)!""/>
  ${field.id}  ${dbType} ${fieldLength} <#compress>
		<#compress>
		<#if field.defaultValue??>
			default '${field.defaultValue!}'
		</#if>
		</#compress> <#compress>		
  		<#if field.identity==true>
  			identity
  		</#if></#compress> <#compress>
  		<#if field.nullable == false>
  			not
  		</#if></#compress> 
  		<#compress>null</#compress><#compress>
  		<#if field_has_next || hasKey == true>
  			,
  		</#if></#compress>	<#compress>
  		<#if hasDesc && (help.isExists(field.longname)||help.isExists(field.description))>--${field.longname!""} 
  			${field.description!""}
  		</#if></#compress></#compress>
  
  		<#if hasFieldList && help.hasEnumeration(field.typeObj)>
               --字段值选项:${field.typeObj.id!""}，${field.typeObj.longname!""}
			<#if !help.isEmpty(help.getEnumerationList(field.typeObj))>
				<#assign enumerationList=help.getEnumerationList(field.typeObj)>
				<#list enumerationList as enumeration>
                   --选项值    :${enumeration.value!""}  ${enumeration.longname!""}
                </#list>
            </#if>
        </#if>

    </#list>
	<#-- 创建主键 -->
	<#if hasKey == true>
  constraint pk_${table.name} primary key nonclustered (${keys})
	</#if>
)
<#compress><#t>
		  <#list table.ddlfrags! as ddlfrags>
		  	<#if ddlfrags.dbType == DbType>
		  		<#t>${ddlfrags.sql!}<#t>
		  	</#if>
		  </#list>
 </#compress>
 
lock <#if table.locktype?exists == false>allpages<#else>${table.locktype}</#if>
on 'default'
go

<#-- 创建唯一性索引 -->
<#list uniques as unique>
create unique nonclustered index uidx_${uniques[unique_index]} on dbo.${table.name}(${uniques[unique_index]}) on 'default'
go

</#list>
<#-- 创建一般性索引 -->
<#list indexs as index>
create nonclustered index idx_${indexs[index_index]} on ${table.id}(${indexs[index_index]}) on 'default'
go
</#list>
</#if>
</#list>