<#--
	功能：用于生成oracle表结构创建索引语句
	@author：caiqq
-->
<#include "sql_macro.ftl"/> 
<#list tables as table>
--${table.longname}(${table.name})
	<@generateIndex table/>
	
</#list>