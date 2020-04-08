// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeclarativeDataValidationManager.java

package cn.sunline.ltts.frw.model.ddv;

import cn.sunline.common.util.StringUtil;
import cn.sunline.ltts.base.expression.ExpressionEvaluator;
import cn.sunline.ltts.base.expression.ExpressionEvaluatorFactory;
import cn.sunline.ltts.base.util.CommUtil_;
import cn.sunline.ltts.core.api.exception.LttsBusinessException;
import cn.sunline.ltts.core.api.model.dm.*;
import cn.sunline.ltts.frw.model.ModelFactory;
import cn.sunline.ltts.frw.model.dm.ExpressionRule;
import cn.sunline.ltts.frw.model.dm.Validation;
import cn.sunline.ltts.frw.model.util.ModelFactoryUtil;
import cn.sunline.ltts.frw.model.util.ModelUtil;
import java.util.*;

// Referenced classes of package cn.sunline.ltts.frw.model.ddv:
//            DataMapWithContext, BaseProfileValidator

public class DeclarativeDataValidationManager
{

    public DeclarativeDataValidationManager()
    {
    }

    public static DeclarativeDataValidationManager get()
    {
        return instance;
    }

    public List validate(ComplexType config, Map data)
    {
        notNeedValidate.clear();
        return ModelUtil.mapToList(_validate(config, data));
    }

    public List validate(String configId, Map data)
    {
        ComplexType config = (ComplexType)ModelFactoryUtil.getModelFactory().getModel(cn/sunline/ltts/core/api/model/dm/ComplexType, configId);
        return validate(config, data);
    }

    public LttsBusinessException validate(ComplexType config, String fieldId, String exprId, Map data)
    {
        if(config == null)
            return null;
        Rule rule = getRule(config, fieldId, exprId);
        if(rule == null)
            return null;
        DataMapWithContext dataMap = new DataMapWithContext(data);
        String target = StringUtil.isNotEmpty(fieldId) ? fieldId : exprId;
        try
        {
            ruleProcessor(target, rule, dataMap);
        }
        catch(LttsBusinessException e)
        {
            return e;
        }
        return null;
    }

    private Rule getRule(ComplexType config, String fieldId, String exprId)
    {
        List elements = config.getAllElements();
        if(ModelUtil.isCollectionEmpty(elements))
            return null;
        for(Iterator i$ = elements.iterator(); i$.hasNext();)
        {
            Element e = (Element)i$.next();
            if(StringUtil.isNotEmpty(fieldId))
            {
                if(e.getId().equals(fieldId))
                    return getRule(exprId, e.getValidations());
            } else
            {
                return getRule(exprId, e.getValidations());
            }
        }

        return getRule(exprId, config.getValidations());
    }

    private Rule getRule(String exprId, List validations)
    {
label0:
        {
            if(ModelUtil.isCollectionEmpty(validations))
                return null;
            if(StringUtil.isEmpty(exprId))
                return null;
            Iterator i$ = validations.iterator();
            Rule rule;
label1:
            do
            {
                do
                {
                    if(!i$.hasNext())
                        break label0;
                    rule = (Rule)i$.next();
                    if(!(rule instanceof Validation))
                        continue label1;
                } while(!exprId.equals(((Validation)rule).getId()));
                return rule;
            } while(!(rule instanceof ExpressionRule) || !exprId.equals(((ExpressionRule)rule).getId()));
            return rule;
        }
        return null;
    }

    private Map _validate(ComplexType config, Map data)
    {
        if(data == null || config == null)
            return null;
        List elements = config.getAllElements();
        if(ModelUtil.isCollectionEmpty(elements))
            return null;
        Map ret = new LinkedHashMap();
        DataMapWithContext dataMap = new DataMapWithContext(data);
        Map resultMap = validateElement(elements, dataMap);
        if(ModelUtil.isMapNotEmpty(resultMap))
            ret.putAll(resultMap);
        Map rMap = validate(config.getValidations(), dataMap);
        if(ModelUtil.isMapNotEmpty(rMap))
            ret.putAll(rMap);
        return ret;
    }

    private Map validateElement(List elements, DataMapWithContext dataMap)
    {
        Map ret;
        Iterator i$;
        ret = new LinkedHashMap();
        i$ = elements.iterator();
_L2:
        Element element;
        if(!i$.hasNext())
            break; /* Loop/switch isn't completed */
        element = (Element)i$.next();
        if(!dependProcessor(element.getId(), element.getDepends(), dataMap))
            continue; /* Loop/switch isn't completed */
        Object value;
        value = dataMap.get(element.getId());
        BaseProfileValidator.checkFieldValidity(element, value);
        if(ModelUtil.isCollectionNotEmpty(element.getValidations()))
        {
            String target = element.getId();
            Rule rule;
            for(Iterator i$ = element.getValidations().iterator(); i$.hasNext(); ruleProcessor(target, rule, dataMap))
                rule = (Rule)i$.next();

        }
        if(value != null)
            try
            {
                ComplexType ct = ModelUtil.getComplexType(element.getTypeObj());
                if(ct != null)
                {
                    String currentContext = dataMap.getCurrentContext();
                    dataMap.setCurrentContext(dataMap.getKey(element.getId()).toString());
                    Map innerValidate = _validate(ct, dataMap);
                    if(ModelUtil.isMapNotEmpty(innerValidate))
                        ret.putAll(innerValidate);
                    dataMap.setCurrentContext(currentContext);
                }
            }
            catch(LttsBusinessException e)
            {
                ret.put(dataMap.getKey(element.getId()).toString(), e);
            }
        if(true) goto _L2; else goto _L1
_L1:
        return ret;
    }

    private Map validate(List validations, DataMapWithContext dataMap)
    {
        if(ModelUtil.isCollectionEmpty(validations))
            return null;
        Map ret = new LinkedHashMap();
        for(Iterator i$ = validations.iterator(); i$.hasNext();)
        {
            Rule validation = (Rule)i$.next();
            String target = null;
            try
            {
                if(validation instanceof Validation)
                    target = ((Validation)validation).getTarget();
                else
                if(validation instanceof ExpressionRule)
                    target = ((ExpressionRule)validation).getTarget();
                ruleProcessor(target, validation, dataMap);
            }
            catch(LttsBusinessException e)
            {
                if(StringUtil.isEmpty(target))
                {
                    ret.put(GLOBAL, e);
                    return ret;
                }
                ret.put(dataMap.getKey(target).toString(), e);
            }
        }

        return ret;
    }

    private boolean dependProcessor(String fieldName, List depends, DataMapWithContext dataMap)
    {
        String key;
        Iterator i$;
        if(ModelUtil.isCollectionEmpty(depends))
            return true;
        key = dataMap.getKey(fieldName).toString();
        i$ = depends.iterator();
_L2:
        Depend depend;
        if(!i$.hasNext())
            break MISSING_BLOCK_LABEL_93;
        depend = (Depend)i$.next();
        if(performRule(depend, dataMap)) goto _L2; else goto _L1
_L1:
        notNeedValidate.add(key);
        return false;
        Exception e;
        e;
        notNeedValidate.add(key);
        return false;
        return true;
    }

    private void ruleProcessor(String target, Rule rule, DataMapWithContext dataMap)
        throws LttsBusinessException
    {
        if(rule instanceof Validation)
        {
            Validation validation = (Validation)rule;
            if(notNeedValidate(dataMap, target, validation.getDepends()))
                return;
            validationProcessor(target, validation, dataMap);
        } else
        if(rule instanceof ExpressionRule)
        {
            ExpressionRule er = (ExpressionRule)rule;
            if(notNeedValidate(dataMap, target, er.getDepends()))
                return;
            expressionRuleProcessor(target, er, dataMap);
        }
    }

    private void validationProcessor(String target, Validation validation, DataMapWithContext dataMap)
        throws LttsBusinessException
    {
        try
        {
            performRule(validation, dataMap);
        }
        catch(Exception e)
        {
            if(e instanceof LttsBusinessException)
                throw (LttsBusinessException)e;
            else
                throw new LttsBusinessException(validation.getErrorCode(), e.getMessage(), e);
        }
    }

    private void expressionRuleProcessor(String target, ExpressionRule expressionRule, DataMapWithContext dataMap)
        throws LttsBusinessException
    {
        try
        {
            if(!performRule(expressionRule, dataMap))
                return;
        }
        catch(Exception e)
        {
            if(e instanceof LttsBusinessException)
                throw (LttsBusinessException)e;
            else
                throw new LttsBusinessException(expressionRule.getErrorCode(), e.getMessage(), e);
        }
        if(!getExprValue(target, expressionRule.getThisVal(), expressionRule.getThisOp(), dataMap))
            throw new LttsBusinessException(expressionRule.getErrorCode(), expressionRule.getMessage());
    }

    private boolean performRule(Rule rule, DataMapWithContext dataMap)
    {
        boolean ret = getExprValue(rule.getWhen(), rule.getVal(), rule.getOp(), dataMap);
        if(rule.getNot().booleanValue())
            ret = !ret;
        return performLogics(ret, rule.getComposite(), dataMap);
    }

    private boolean performLogics(boolean lastResult, Logic composite, DataMapWithContext dataMap)
    {
        if(composite == null)
            return lastResult;
        boolean ret = lastResult;
        if(composite instanceof And)
        {
            And and = (And)composite;
            if(ModelUtil.isCollectionEmpty(and.getValidations()))
                return lastResult;
            for(Iterator i$ = and.getValidations().iterator(); i$.hasNext();)
            {
                Logic lg = (Logic)i$.next();
                ret = performLogics(ret, lg, dataMap) && ret;
            }

        } else
        if(composite instanceof Or)
        {
            Or or = (Or)composite;
            if(ModelUtil.isCollectionEmpty(or.getValidations()))
                return lastResult;
            for(Iterator i$ = or.getValidations().iterator(); i$.hasNext();)
            {
                Logic lg = (Logic)i$.next();
                ret = performLogics(ret, lg, dataMap) || ret;
            }

        } else
        {
            if(composite instanceof MvelExpr)
                return getMvelValue(((MvelExpr)composite).getText(), dataMap);
            if(composite instanceof Rule)
                return performRule((Rule)composite, dataMap);
        }
        return ret;
    }

    private Object getExprValue(String expression, Map context)
    {
        ExpressionEvaluator ee = ExpressionEvaluatorFactory.getInstance();
        return ee.findValue(expression, null, context);
    }

    private boolean getExprValue(String leftValue, String rightValue, cn.sunline.ltts.core.api.model.dm.Rule.OperateType op, DataMapWithContext dataMap)
    {
        if(op == null)
            return true;
        Object lValue = dataMap.get(leftValue);
        Object rValue = rightValue;
        if(rightValue != null && rightValue.startsWith("#"))
            rValue = getExprValue(rightValue, ((Map) (dataMap)));
        if(cn.sunline.ltts.core.api.model.dm.Rule.OperateType.gt.equals(op))
        {
            int ret = CommUtil_.compare(lValue, rValue);
            return ret > 0;
        }
        if(cn.sunline.ltts.core.api.model.dm.Rule.OperateType.lt.equals(op))
        {
            int ret = CommUtil_.compare(lValue, rValue);
            return ret < 0;
        }
        if(cn.sunline.ltts.core.api.model.dm.Rule.OperateType.eq.equals(op))
        {
            int ret = CommUtil_.compare(lValue, rValue);
            return ret == 0;
        }
        if(cn.sunline.ltts.core.api.model.dm.Rule.OperateType.neq.equals(op))
        {
            int ret = CommUtil_.compare(lValue, rValue);
            return ret != 0;
        }
        if(cn.sunline.ltts.core.api.model.dm.Rule.OperateType.in.equals(op))
            if(rValue == null)
                return lValue == null;
            else
                return CommUtil_.in(lValue, rValue.toString().split("[\\s,]+"));
        if(cn.sunline.ltts.core.api.model.dm.Rule.OperateType.notIn.equals(op))
        {
            if(rValue == null)
                return lValue != null;
            else
                return !CommUtil_.in(lValue, rValue.toString().split("[\\s,]+"));
        } else
        {
            return false;
        }
    }

    private boolean getMvelValue(String expr, Map context)
    {
        ExpressionEvaluator ee = ExpressionEvaluatorFactory.getMvelInstanceNoExcepion();
        return ((Boolean)ee.findValue(expr, java/lang/Boolean, context, context)).booleanValue();
    }

    private boolean notNeedValidate(DataMapWithContext dataMap, String target, String depends[])
    {
        if(StringUtil.isNotEmpty(target) && notNeedValidate.contains(dataMap.getKey(target).toString()))
            return true;
        if(depends == null || depends.length == 0)
            return false;
        String arr$[] = depends;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            String depend = arr$[i$];
            if(notNeedValidate.contains(depend))
                return true;
        }

        return false;
    }

    private static String GLOBAL = "_global";
    private static Set notNeedValidate = new HashSet();
    private static DeclarativeDataValidationManager instance = new DeclarativeDataValidationManager();

}
