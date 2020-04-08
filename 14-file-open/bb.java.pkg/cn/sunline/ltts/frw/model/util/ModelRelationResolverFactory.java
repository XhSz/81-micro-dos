// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModelRelationResolverFactory.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.base.odb.ModelRelationResolver;
import cn.sunline.ltts.core.api.util.ProfileSwitcher;
import cn.sunline.ltts.core.api.util.ProfileUtil;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            DefaultModelRelationResolver2, DefaultModelRelationResolver

public class ModelRelationResolverFactory
{

    public ModelRelationResolverFactory()
    {
    }

    public static ModelRelationResolver getModelRelationResolver()
    {
        if(ProfileUtil.get().useRelationResolver2)
            return DefaultModelRelationResolver2.instance;
        else
            return DefaultModelRelationResolver.instance;
    }
}
