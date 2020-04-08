<#--
	功能：用于生成oracle表序列语句
-->
<#include "sql_macro.ftl"/> 
<#list tables as table>
--${table.longname}(${table.name})
<#if DbType.toString() == "mysql">
	<@generateSeq table "mysql"/>
</#if>
<#if DbType.toString() == "oracle">
<@generateSeq table "oracle"/>
</#if>
</#list>