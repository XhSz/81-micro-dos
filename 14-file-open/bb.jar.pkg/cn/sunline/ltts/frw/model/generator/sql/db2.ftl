<#--
	功能：用于生成db2表结构创建语句
	@author：Humy,Yexh
-->

<#-- 用户自定义类型 -->
<#assign userTypeMap = { 
"fixString":"char","dateString":"varchar","string":"varchar","boolean":"char","int":"int","long":"bigint","dataTime":"timestamp","date":"date",
"time":"timestamp","decimal":"decimal","amount":"decimal","schema":"varchar"
	}> 
<#-- 用户自定义的类型对应的默认长度  -->	
<#assign userTypeDefaultLengthMap ={"dateString": "(8)", "schema":"(255)", "boolean":"(1)", "amount":"(20,2)"}> 



<#-- {"time":"timestamp", "text":"varchar(20000)", 
	"boolean":"char(1)", "integer":"int",
	"seqno":"varchar(25)",
	"amount":"decimal(15,2)",
	"date":"date",
	"datetime":"timestamp",
	"rate":"decimal(15,5)",
	"enumer1":"char(1)",
	"enumer30":"varchar(30)",
	"transname":"varchar(100)",
	"loginid":"varchar(30)",
	"binary": "blob",
	"clob":"clob"
	} -->
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
create table ${table.name} (
	<#-- 每个数据库表字段 -->
	<#list fields as field>
		<#-- 获取表主键 -->
		<#if field.primarykey!true >
			<#assign hasKey = true/>
			<#if keys == ""><#assign keys = field.id/><#else><#assign keys = keys + ", " + field.id/></#if>
		</#if>
		<#-- xml自定义类型到数据库类型转换 -->
		<#assign dbType = help.getDbType(field.typeObj)!""/>
		<#list userTypeMap?keys as type>
			<#if type == dbType>
				<#assign dbType = userTypeMap[type]/>
				<#break/>
			</#if>
		</#list>
		<#assign fieldLength=help.getFieldLengthString(field, userTypeDefaultLengthMap)!""/>
   ${field.id} ${dbType} ${fieldLength} <#compress>
		<#compress>
		<#if field.defaultValue??>
			default '${field.defaultValue!}'
		</#if>
		</#compress> <#compress>
   		<#if field.nullable! == false>
   			not null
   		</#if></#compress> <#compress>
   		<#if field_has_next>
   			,
   		</#if></#compress> <#compress>
   		<#if hasDesc && (help.isExists(field.longname)||help.isExists(field.description))>
                 --${field.longname!""} ${field.description!""}
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
)<#compress>
<@generateDdl table/>
</#compress>
</#if>
</#list>
--删除表
<#list tables as table>
drop table ${table.name};
</#list>