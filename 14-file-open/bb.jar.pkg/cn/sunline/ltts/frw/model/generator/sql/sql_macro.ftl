<#-- 创建自定义ddl语句 -->
<#macro generateDdl table>
	<#compress><#t>
	<#if !table.virtual>
	   <#if table.ddlfrags??>
 		<@genddlfrag table.ddlfrags/>
 	   </#if>
 	<#elseif DbType.toString() != "mysql">
 	  on commit delete rows
 	</#if><#t>;</#compress>
</#macro>

<#--创建同义词-->
<#macro generateSynonym table username>
--drop public synonym ${table.name!}
create public synonym ${table.name!} for ${username}.${table.name!}
</#macro>

<#-- 创建索引 -->
<#macro generateIndex table>
	<#if (table.index?? && table.index?size >0) >
		<#list table.index as index>
			<#compress>
			<#if index.type.id == "index" || index.type.id == "unique">
<#-- 创建索引 -->
<#if DbType.toString() != "mysql">drop index ${index.id};</#if>
create <#if index.type.id == "unique">${index.type.id}</#if> index ${index.id} on ${table.name} (${index.fields})
			<#else>	
<#-- 创建主键、唯一、外键索引  -->				
alter table ${table.name}
add constraint ${index.id} primary key (${index.fields})
using index 
			</#if></#compress> <#compress><#t>
  			<#if index.ddlfrags??>
 <@genddlfrag index.ddlfrags/>
			</#if><#t>;</#compress>	
		</#list>	
	</#if>
</#macro>

<#macro genddlfrag ddlfrags>
<#compress><#t>
	<#if (ddlfrags?? && ddlfrags?size >0) >
		<#list ddlfrags as ddlfrag>
	  		<#if DbType == ddlfrag.dbType>
	  			<#if ddlfrag.independence><#t>;</#if>
  ${ddlfrag.sql!}
	  		</#if>
	  	</#list>
	</#if>
</#compress>
</#macro>

<#macro generateSeq table dbtype>
<#if dbtype =="mysql">
/* sequence */
<#-- 生成序号 -->
	<#if table.dbSequence??>
delete from ksys_liusdy where liusbm='${table.dbSequence.id!""}';
insert ksys_liusdy(liusbm, liusmc, huancdx, buchang, liuszdz, liuszxz, dangqzh) 
values('${table.dbSequence.id!""}', '${table.dbSequence.longname!""}', ${table.dbSequence.cache!""}, ${table.dbSequence.incrementBy!""}, ${table.dbSequence.maxValue!""}, ${table.dbSequence.minValue!""}, 0);
	</#if>
</#if>
<#if dbtype =="oracle">
	<#if table.dbSequence??>

drop SEQUENCE ${table.dbSequence.id!"ERROR"};
CREATE SEQUENCE ${table.dbSequence.id!"ERROR"}
START WITH ${table.dbSequence.startWith!"ERROR"}
INCREMENT BY ${table.dbSequence.incrementBy!"ERROR"}
MINVALUE ${table.dbSequence.startWith!"ERROR"}
		<#if table.dbSequence.maxValue??>
MAXVALUE ${table.dbSequence.maxValue!"ERROR"}
		</#if>
CACHE ${table.dbSequence.cache!"ERROR"}
		<#if table.dbSequence.cycle?? && table.dbSequence.cycle>
CYCLE
		<#else> 
NOCYCLE
		</#if> 
NOORDER;
	</#if>
</#if>
</#macro>