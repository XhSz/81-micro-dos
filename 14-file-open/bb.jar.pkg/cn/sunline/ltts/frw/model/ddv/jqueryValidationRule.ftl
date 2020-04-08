<#macro generateElement element>
	{
		<#if element.required!false>required: true,</#if><#rt/>
		<#assign rt=help.getRestrictionType(element)! />
		<#if rt??>
			<#if rt.minLength??>minlength: ${rt.minLength},</#if><#rt/>
		</#if>
	}<#rt/>
</#macro>
<#macro generateElementMessage element>
	{
		<#if element.required!false>required: "必须输入",</#if><#rt/>
		<#assign rt=help.getRestrictionType(element)! />
		<#if rt??>
			<#if rt.minLength??>minlength: "长度必须大于${rt.minLength}",</#if><#rt/>
		</#if>
	}<#rt/>
</#macro>
var jqueryValidationRule;
$().ready(function() {

	// validate signup form on keyup and submit
	jqueryValidationRule = {
		rules: {
		<#list config.elements as element>
			"${element.id}": <@generateElement element/>,
		</#list>
		},
		messages: {
		<#list config.elements as element>
			"${element.id}": <@generateElementMessage element/>,
		</#list>
		}
	};

	$.validator.addMethod("calcMode_expr1", function(value, element, params) {
		return eval('value != 3');
	}, '#calcMode != 3');

	$.validator.addMethod("topFee_expr1", function(value, element, params) {
		return eval('$("#currency").val() == "UST" || value < 100');
	}, 'currency== "UST" || topFee < 100');

	$.validator.addMethod("calcMode_rule", function(value, element, params) {
		return eval('$("#calcMode").val() >=1 && $("#calcMode").val() <= 3');
	}, 'calcMode要在1-3之间！');

	$.validator.addMethod("card_rule", function(value, element, params) {
		var reg = /[a-z|A-Z|0-9]{5,18}/;
		return reg.test(value);
	}, '证件号码只能由字母、数字组成，且长度介于5到18个字符');
	
});

