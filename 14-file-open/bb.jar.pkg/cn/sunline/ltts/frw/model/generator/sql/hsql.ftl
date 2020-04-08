<#--
	功能：用于生成hsql表结构创建语句，支持联合主键
	@author：Humy
-->

<#-- 用户自定义类型 -->
<#assign userTypeMap = {
"fixString":"char","dateString":"varchar","string":"varchar","boolean":"char","int":"int","long":"bigint","bigint":"number","dataTime":"timestamp","date":"date",
"time":"timestamp","decimal":"decimal","amount":"decimal","schema":"varchar"
	}>
<#-- 用户自定义的类型对应的默认长度  -->	
<#assign userTypeDefaultLengthMap ={"string": "(255)", "dateString": "(8)", "schema":"(255)", "boolean":"(1)", "amount":"(20,2)"}> 
	
<#-- 每个数据库表 -->
<#list tables as table>
<#assign fields = table.getTrueFieldWithoutCache()!>
 <#if !table.isAbstract()?? || !table.isAbstract()>
 <#include "sql_macro.ftl"/> 
	<#assign hasKey = false/>
	<#assign keys = ""/>
	<#assign uniques = [] />
	<#assign indexs = [] />
--${table.longname!}
drop table ${table.name} if exists;
create table ${table.name} (
	<#-- 每个数据库表字段 -->
	<#list fields as field>
		<#-- 获取表主键 -->
		<#if field.primarykey == true && field.identity == false>
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
			<#if type?lower_case == dbType?lower_case >
				<#assign dbType = userTypeMap[type]/>
				<#break/>
			</#if>
		</#list>
		<#assign fieldLength=help.getFieldLengthString(field, userTypeDefaultLengthMap)!""/>
   ${field.id}  ${dbType} ${fieldLength} <#compress>
		<#compress>
		<#if field.defaultValue??>
			<#-- default '${field.defaultValue!}' -->
		</#if>
		</#compress> <#compress>
        <#if field.identity==true>
        	identity
        </#if></#compress> <#compress>	
        <#if field.nullable == false>
        	not
        </#if> 
        </#compress> null <#compress>
        <#if field_has_next || (!help.hasDefindPrimaryKeyAfter(table) && hasKey == true)>
        	,
        </#if> </#compress>		<#compress>    	
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
    <#if !help.hasDefindPrimaryKeyAfter(table)>
	 	<#-- 创建主键 -->
		<#if hasKey == true>
  constraint pk_${table.name} primary key (${keys})
		</#if>
	</#if>
)<#compress>
;
</#compress>
 </#if>
</#list>