<#--
	功能：用于生成mysql表结构创建语句，支持联合主键
	@author：jxb
-->
<#-- 用户自定义类型 -->
<#assign userTypeMap = { 
"fixString":"char","dateString":"varchar","string":"varchar","boolean":"char","int":"int","long":"bigint","dataTime":"timestamp","date":"date",
"time":"timestamp","decimal":"decimal","amount":"decimal","schema":"varchar"
	}> 
<#-- 用户自定义的类型对应的默认长度  -->	
<#assign userTypeDefaultLengthMap ={"dateString": "(8)", "schema":"(255)", "boolean":"(1)", "amount":"(20,2)"}> 
<#--  原来的类型处理
 {"time":"timestamp", "smalldatetime":"date", 
	"text":"text", "long": "bigint", 
	"boolean":"char(1)", "binary":"binary",
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
	"clob":"text"
	}-->

<#include "sql_macro.ftl"/>
<#-- 每个数据库表 -->
<#list tables as table>
<#if !table.isAbstract()?? || !table.isAbstract()>
	<#assign fields = table.getTrueFieldWithoutCache()!>
	<#assign keys = ""/>
	<#assign uniques = [] />
	<#assign indexs = [] />
-- ${table.longname!}
drop table if exists ${table.name};
create table ${table.name} (
	<#assign tableSuffix=false />
	<#-- 每个数据库表字段 -->
	<#list fields as field>
		<#-- 获取表主键 -->
		<#if field.primarykey == true>
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
			<#if type?lower_case == dbType?lower_case >
				<#assign dbType = userTypeMap[type]/>
				<#break/>
			</#if>
		</#list>
		<#assign fieldLength=help.getFieldLengthString(field, userTypeDefaultLengthMap)!""/>
		<#assign dbType=help.getMysqlDbType(dbType, fieldLength)!""/>
		<#if dbType == "text"> 
			<#assign tableSuffix=true/>
		</#if>
   ${field.id}  ${dbType} <#if dbType != "text">${fieldLength}</#if> <#compress>
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
	</#if></#compress> null<#compress>
	<#if field_has_next >
		,
	</#if></#compress>  <#compress>
	<#if hasDesc && (help.isExists(field.longname)||help.isExists(field.description))>
	     -- ${field.longname!""}  ${field.description!""}
	</#if></#compress></#compress>
	
		<#if hasFieldList && help.hasEnumeration(field.typeObj)>
                 -- 字段值选项:${field.typeObj.id!""}，${field.typeObj.longname!""}
			<#if !help.isEmpty(help.getEnumerationList(field.typeObj))>
				<#assign enumerationList=help.getEnumerationList(field.typeObj)>
				<#list enumerationList as enumeration>
                     -- 选项值    :${enumeration.value!""}  ${enumeration.longname!""}
                </#list>
            </#if>
        </#if>
    </#list>
)<#if tableSuffix>ENGINE=InnoDB DEFAULT CHARSET=UTF8 ROW_FORMAT=DYNAMIC collate=utf8_bin</#if> 
<#-- 创建自定义ddl语句 -->
<@generateDdl table/>

    <#assign hasKey = help.hasDefinePrimaryKeys(table, keys) />
    <#if !hasKey>
		<#assign keys=help.getKeys(table)!""/>
	</#if>
  	<#if keys != "">
    <#-- 创建主键 -->
alter table ${table.name} add constraint pk_${table.name} primary key (${keys});
    </#if>
	<#-- 创建索引 -->
	<#if (table.index?? && table.index?size >0) >
		<#assign indexes = help.getTableIndex(table, hasKey) />
		<#list indexes as index>
			<#-- 创建唯一索引、普通索引 -->
			<#if help.isExists(index.type) >
			<#if index.type.id == "index" || index.type.id == "unique" >
create <#if index.type.id == "unique">${index.type.id}</#if> index ${index.id} on ${table.name} (${index.fields});
			</#if>
			</#if>
		</#list>	
	</#if>
	<#-- 生成序号 -->
	<@generateSeq table "mysql"/>
</#if>

</#list>