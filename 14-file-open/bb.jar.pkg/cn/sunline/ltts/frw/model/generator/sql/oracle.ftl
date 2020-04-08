<#--
	功能：用于生成oracle表结构创建语句
	@author：Jxb,caiqq
-->
<#-- 用户自定义类型 -->
<#assign userTypeMap ={
"fixString":"char","dateString":"varchar2","string":"varchar2",
"boolean":"char","int":"number","long":"number","dataTime":"date","date":"date",
"timestamp":"timestamp","time":"date","decimal":"number","amount":"number","schema":"varchar2"
	}> 
<#-- 用户自定义的类型对应的默认长度  -->	
<#assign userTypeDefaultLengthMap ={"int": "(10)", "long":"(16)", "dateString": "(8)", "schema":"(255)", "boolean":"(1)", "amount":"(20,2)"}> 
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
--begin execute immediate 'drop table ${table.name}'; exception when others then null; end;
drop table ${table.name};

create <#if table.virtual>global temporary </#if>table ${table.name} (
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
				<#if dbType?lower_case?contains(type?lower_case)>
					<#assign dbType = dbType?lower_case?replace(type?lower_case, userTypeMap[type]) />
					<#break/>
				</#if>
			</#list>
			<#-- 获取字段的长度 -->
			<#assign fieldLength=help.getFieldLengthString(field, userTypeDefaultLengthMap)!""/>
	<#-- 显示字段的详细信息  -->
  ${field.id} ${dbType}${fieldLength} <#compress>
			<#compress>
			<#if field.defaultValue??>
				default '${field.defaultValue!}'
			</#if>
			</#compress>
	    	<#compress>
	    	<#if field.identity==true>
	    		identity
	    	</#if></#compress> <#compress>	
	    	<#if field.nullable == false>
	    		not null
	    	</#if></#compress> <#compress>
	    	<#if field_has_next || (keyProcess && !help.hasDefindPrimaryKeyAfter(table) && hasKey == true) || (keyProcess && !help.hasDefindIndexAfter(table) && (uniques?? && uniques?size > 0))>
	    		,
	    	</#if></#compress>    <#compress>    
	    	<#if hasDesc && (help.isExists(field.longname)||help.isExists(field.description))>
	    		--${field.longname!""}  ${field.description!""}
	    	</#if></#compress></#compress> 
   			<#if hasFieldList && help.hasEnumeration(field.typeObj)>
                       --字段值选项:${field.typeObj.id!""},${field.typeObj.longname!""}
   				<#if !help.isEmpty(help.getEnumerationList(field.typeObj))>
   					<#assign enumerationList=help.getEnumerationList(field.typeObj)>
   					<#list enumerationList as enumeration>
                          --选项值    :${enumeration.value!""}  ${enumeration.longname!""}
                    </#list>
               </#if>
             <#else>
               
            </#if>
   		</#list>
   		<#if keyProcess && !help.hasDefindPrimaryKeyAfter(table)>
	<#-- 创建主键 -->
			<#if hasKey == true>
  constraint pk_${table.name!} primary key (${keys})<#compress>
				<#if !help.hasDefindIndexAfter(table) && (uniques?? && uniques?size > 0) >
					,
				</#if></#compress>
				
			</#if>
		</#if>
		<#if keyProcess && !help.hasDefindIndexAfter(table)>
	<#-- 创建唯一性索引 -->
			<#list uniques as unique>
	unique(${uniques[unique_index]})<#if unique_has_next>,</#if>
			</#list>
		</#if>
) <@generateDdl table/>
<@generateIndex table/>
<#-- <@generateSeq table oracle/>-->
<#if !help.isEmpty(username)>
<@generateSynonym table username/>
</#if>
comment on table ${table.name!} is '${table.longname!}'; 
	 <#list fields as field>
	  comment on column ${table.name}.${field.id} is <#compress>'${field.longname}<#t>
	 	<#if haseCommList && !help.isEmpty(help.getEnumerationList(field.typeObj))>
	    				<#assign enumerationList=help.getEnumerationList(field.typeObj)/>
	    				<#if (enumerationList?size > 0)>:</#if><#compress>
	   					<#list enumerationList as enumeration>
	   					    <#if enumeration_index < 50 >
	   					${enumeration.value!""}-${enumeration.longname!""}<#if enumeration_has_next>,</#if><#t>
	   					    </#if>
	   					</#list><#if (enumerationList?size > 50) >...</#if></#compress><#t>
	   				</#if>';</#compress>
	 </#list>
	 </#if>
   </#list>
