// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultModelRelationResolver.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.base.odb.ModelRelationResolver;
import java.util.Set;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            ModelFactoryUtil

public class DefaultModelRelationResolver
    implements ModelRelationResolver
{

    public DefaultModelRelationResolver()
    {
    }

    public void resolve(Object model, Set resolved)
    {
        ModelFactoryUtil.resolveModelRelation(model, resolved);
    }

    static final DefaultModelRelationResolver instance = new DefaultModelRelationResolver();

}
