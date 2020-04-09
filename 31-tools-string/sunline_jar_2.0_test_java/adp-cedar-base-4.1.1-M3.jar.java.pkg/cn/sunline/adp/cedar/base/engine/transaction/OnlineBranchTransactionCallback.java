// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OnlineBranchTransactionCallback.java

package cn.sunline.adp.cedar.base.engine.transaction;

import cn.sunline.adp.cedar.base.engine.transaction.distributed.bean.BranchAttachment;

public interface OnlineBranchTransactionCallback
{

    public abstract void confirm(BranchAttachment branchattachment);

    public abstract void cancel(BranchAttachment branchattachment);
}
