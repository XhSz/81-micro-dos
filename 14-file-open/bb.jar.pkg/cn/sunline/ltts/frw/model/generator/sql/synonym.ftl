<#--
	功能：用于生成oracle表同义词语句
-->
<#include "sql_macro.ftl"/> 
<#list tables as table>
--${table.longname}(${table.name})
	<@generateSynonym table username/>
	
</#list>