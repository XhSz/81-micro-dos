// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModelIntroductUtil.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.core.api.model.dm.ComplexType;
import cn.sunline.ltts.core.api.model.dm.Rule;
import cn.sunline.ltts.frw.model.ModelFactory;
import cn.sunline.ltts.frw.model.dm.*;
import cu.sunline.ltts.ModelRtConst;
import java.util.*;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            ModelUtil

public class ModelIntroductUtil
{

    public ModelIntroductUtil()
    {
    }

    public static void introductProcessor(ModelFactory mf)
    {
        List types = mf.getModels(cn/sunline/ltts/frw/model/dm/ComplexTypeEx);
        if(ModelUtil.isCollectionEmpty(types))
            return;
        Iterator i$ = types.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            ComplexTypeEx ct = (ComplexTypeEx)i$.next();
            if(ct.getIntroduct() != null && ct.getIntroduct().booleanValue())
            {
                ComplexTypeEx destCt;
                for(Iterator i$ = getIntroductedModel(ct).iterator(); i$.hasNext(); merge(ct, destCt))
                    destCt = (ComplexTypeEx)i$.next();

                if(ct.getOwner() instanceof Schema)
                    ((Schema)ct.getOwner()).getTypes().remove(ct);
                else
                    throw new IllegalArgumentException(String.format(ModelRtConst.ModelIntroductUtil01, new Object[] {
                        ct.getFullId()
                    }));
            }
        } while(true);
    }

    private static void merge(ComplexTypeEx src, ComplexTypeEx dest)
    {
        if(ModelUtil.isCollectionEmpty(src.getValidations()))
            return;
        if(ModelUtil.isCollectionEmpty(dest.getValidations()))
            dest.setValidations(src.getValidations());
        List newValidation = new ArrayList();
        newValidation.addAll(dest.getValidations());
        Iterator i$ = src.getValidations().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Rule srcValidation = (Rule)i$.next();
            boolean isDefine = false;
            Iterator i$ = dest.getValidations().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                Rule destValidation = (Rule)i$.next();
                if(!getId(srcValidation).equals(getId(destValidation)))
                    continue;
                newValidation.set(dest.getValidations().indexOf(destValidation), srcValidation);
                isDefine = true;
                break;
            } while(true);
            if(!isDefine)
                newValidation.add(srcValidation);
        } while(true);
        dest.setValidations(newValidation);
    }

    private static List getIntroductedModel(ComplexTypeEx ct)
    {
        ComplexType extensionTypes[] = ct.getExtensionType();
        if(ct.getExtensionType() == null || ct.getExtensionType().length == 0)
            throw new IllegalArgumentException(ModelRtConst.ModelIntroductUtil02);
        List ret = new ArrayList();
        ComplexType arr$[] = extensionTypes;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            ComplexType superCt = arr$[i$];
            if(superCt == null)
                throw new IllegalArgumentException(String.format(ModelRtConst.ModelIntroductUtil03, new Object[] {
                    ct.getFullId()
                }));
            if(superCt.getIntroduct() == null || !superCt.getIntroduct().booleanValue())
                ret.add((ComplexTypeEx)superCt);
            else
                ret.addAll(getIntroductedModel((ComplexTypeEx)superCt));
        }

        return ret;
    }

    public static String getId(Rule rule)
    {
        if(rule instanceof Validation)
            return ((Validation)rule).getId();
        if(rule instanceof ExpressionRule)
            return ((ExpressionRule)rule).getId();
        else
            return "";
    }
}
