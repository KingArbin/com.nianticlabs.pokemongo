package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Process;
import com.google.android.gms.common.internal.zzd;

public class zzmm
{
  public static boolean zzjA()
  {
    return (zzd.zzaeK) && (zzlr.isInitialized()) && (zzlr.zzoo() == Process.myUid());
  }
  
  public static boolean zzl(Context paramContext, String paramString)
  {
    boolean bool = false;
    paramContext = paramContext.getPackageManager();
    try
    {
      int i = getApplicationInfo0flags;
      if ((i & 0x200000) != 0) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzmm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */