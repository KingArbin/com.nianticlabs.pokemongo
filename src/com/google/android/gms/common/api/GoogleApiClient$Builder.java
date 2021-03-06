package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzf.zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzlp;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzqu;
import com.google.android.gms.internal.zzqw;
import com.google.android.gms.internal.zzqx;
import com.google.android.gms.internal.zzqx.zza;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class GoogleApiClient$Builder
{
  private final Context mContext;
  private Account zzQd;
  private String zzRq;
  private final Set<Scope> zzaaF = new HashSet();
  private int zzaaG;
  private View zzaaH;
  private String zzaaI;
  private final Map<Api<?>, zzf.zza> zzaaJ = new zzme();
  private final Map<Api<?>, Api.ApiOptions> zzaaK = new zzme();
  private FragmentActivity zzaaL;
  private int zzaaM = -1;
  private GoogleApiClient.OnConnectionFailedListener zzaaN;
  private Looper zzaaO;
  private GoogleApiAvailability zzaaP = GoogleApiAvailability.getInstance();
  private Api.zza<? extends zzqw, zzqx> zzaaQ = zzqu.zzRl;
  private final ArrayList<GoogleApiClient.ConnectionCallbacks> zzaaR = new ArrayList();
  private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zzaaS = new ArrayList();
  private zzqx zzaaT;
  
  public GoogleApiClient$Builder(Context paramContext)
  {
    mContext = paramContext;
    zzaaO = paramContext.getMainLooper();
    zzRq = paramContext.getPackageName();
    zzaaI = paramContext.getClass().getName();
  }
  
  public GoogleApiClient$Builder(Context paramContext, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this(paramContext);
    zzx.zzb(paramConnectionCallbacks, "Must provide a connected listener");
    zzaaR.add(paramConnectionCallbacks);
    zzx.zzb(paramOnConnectionFailedListener, "Must provide a connection failed listener");
    zzaaS.add(paramOnConnectionFailedListener);
  }
  
  private <O extends Api.ApiOptions> void zza(Api<O> paramApi, O paramO, int paramInt, Scope... paramVarArgs)
  {
    boolean bool = true;
    int i = 0;
    if (paramInt == 1) {}
    for (;;)
    {
      paramO = new HashSet(paramApi.zznv().zzm(paramO));
      int j = paramVarArgs.length;
      paramInt = i;
      while (paramInt < j)
      {
        paramO.add(paramVarArgs[paramInt]);
        paramInt += 1;
      }
      if (paramInt != 2) {
        break;
      }
      bool = false;
    }
    throw new IllegalArgumentException("Invalid resolution mode: '" + paramInt + "', use a constant from GoogleApiClient.ResolutionMode");
    zzaaJ.put(paramApi, new zzf.zza(paramO, bool));
  }
  
  private void zza(zzlp paramzzlp, GoogleApiClient paramGoogleApiClient)
  {
    paramzzlp.zza(zzaaM, paramGoogleApiClient, zzaaN);
  }
  
  private GoogleApiClient zznC()
  {
    final zzli localzzli = new zzli(mContext.getApplicationContext(), zzaaO, zznB(), zzaaP, zzaaQ, zzaaK, zzaaR, zzaaS, zzaaM);
    zzlp localzzlp = zzlp.zza(zzaaL);
    if (localzzlp == null)
    {
      new Handler(mContext.getMainLooper()).post(new Runnable()
      {
        public void run()
        {
          if ((GoogleApiClient.Builder.zza(GoogleApiClient.Builder.this).isFinishing()) || (GoogleApiClient.Builder.zza(GoogleApiClient.Builder.this).getSupportFragmentManager().isDestroyed())) {
            return;
          }
          GoogleApiClient.Builder.zza(GoogleApiClient.Builder.this, zzlp.zzb(GoogleApiClient.Builder.zza(GoogleApiClient.Builder.this)), localzzli);
        }
      });
      return localzzli;
    }
    zza(localzzlp, localzzli);
    return localzzli;
  }
  
  public Builder addApi(Api<? extends Api.ApiOptions.NotRequiredOptions> paramApi)
  {
    zzx.zzb(paramApi, "Api must not be null");
    zzaaK.put(paramApi, null);
    zzaaF.addAll(paramApi.zznv().zzm(null));
    return this;
  }
  
  public <O extends Api.ApiOptions.HasOptions> Builder addApi(Api<O> paramApi, O paramO)
  {
    zzx.zzb(paramApi, "Api must not be null");
    zzx.zzb(paramO, "Null options are not permitted for this Api");
    zzaaK.put(paramApi, paramO);
    zzaaF.addAll(paramApi.zznv().zzm(paramO));
    return this;
  }
  
  public <O extends Api.ApiOptions.HasOptions> Builder addApiIfAvailable(Api<O> paramApi, O paramO, Scope... paramVarArgs)
  {
    zzx.zzb(paramApi, "Api must not be null");
    zzx.zzb(paramO, "Null options are not permitted for this Api");
    zzaaK.put(paramApi, paramO);
    zza(paramApi, paramO, 1, paramVarArgs);
    return this;
  }
  
  public Builder addApiIfAvailable(Api<? extends Api.ApiOptions.NotRequiredOptions> paramApi, Scope... paramVarArgs)
  {
    zzx.zzb(paramApi, "Api must not be null");
    zzaaK.put(paramApi, null);
    zza(paramApi, null, 1, paramVarArgs);
    return this;
  }
  
  public Builder addConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    zzx.zzb(paramConnectionCallbacks, "Listener must not be null");
    zzaaR.add(paramConnectionCallbacks);
    return this;
  }
  
  public Builder addOnConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zzx.zzb(paramOnConnectionFailedListener, "Listener must not be null");
    zzaaS.add(paramOnConnectionFailedListener);
    return this;
  }
  
  public Builder addScope(Scope paramScope)
  {
    zzx.zzb(paramScope, "Scope must not be null");
    zzaaF.add(paramScope);
    return this;
  }
  
  public GoogleApiClient build()
  {
    if (!zzaaK.isEmpty()) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "must call addApi() to add at least one API");
      if (zzaaM < 0) {
        break;
      }
      return zznC();
    }
    return new zzli(mContext, zzaaO, zznB(), zzaaP, zzaaQ, zzaaK, zzaaR, zzaaS, -1);
  }
  
  public Builder enableAutoManage(FragmentActivity paramFragmentActivity, int paramInt, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    if (paramInt >= 0) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "clientId must be non-negative");
      zzaaM = paramInt;
      zzaaL = ((FragmentActivity)zzx.zzb(paramFragmentActivity, "Null activity is not permitted."));
      zzaaN = paramOnConnectionFailedListener;
      return this;
    }
  }
  
  public Builder enableAutoManage(FragmentActivity paramFragmentActivity, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return enableAutoManage(paramFragmentActivity, 0, paramOnConnectionFailedListener);
  }
  
  public Builder requestServerAuthCode(String paramString, GoogleApiClient.ServerAuthCodeCallbacks paramServerAuthCodeCallbacks)
  {
    zzaaT = new zzqx.zza().zza(paramString, paramServerAuthCodeCallbacks).zzCi();
    return this;
  }
  
  public Builder setAccountName(String paramString)
  {
    if (paramString == null) {}
    for (paramString = null;; paramString = new Account(paramString, "com.google"))
    {
      zzQd = paramString;
      return this;
    }
  }
  
  public Builder setGravityForPopups(int paramInt)
  {
    zzaaG = paramInt;
    return this;
  }
  
  public Builder setHandler(Handler paramHandler)
  {
    zzx.zzb(paramHandler, "Handler must not be null");
    zzaaO = paramHandler.getLooper();
    return this;
  }
  
  public Builder setViewForPopups(View paramView)
  {
    zzx.zzb(paramView, "View must not be null");
    zzaaH = paramView;
    return this;
  }
  
  public Builder useDefaultAccount()
  {
    return setAccountName("<<default account>>");
  }
  
  public zzf zznB()
  {
    boolean bool;
    Account localAccount;
    Set localSet;
    Map localMap;
    int i;
    View localView;
    String str1;
    String str2;
    if (zzaaK.containsKey(zzqu.API))
    {
      if (zzaaT == null)
      {
        bool = true;
        zzx.zza(bool, "SignIn.API can't be used in conjunction with requestServerAuthCode.");
        zzaaT = ((zzqx)zzaaK.get(zzqu.API));
      }
    }
    else
    {
      localAccount = zzQd;
      localSet = zzaaF;
      localMap = zzaaJ;
      i = zzaaG;
      localView = zzaaH;
      str1 = zzRq;
      str2 = zzaaI;
      if (zzaaT == null) {
        break label130;
      }
    }
    label130:
    for (zzqx localzzqx = zzaaT;; localzzqx = zzqx.zzaUZ)
    {
      return new zzf(localAccount, localSet, localMap, i, localView, str1, str2, localzzqx);
      bool = false;
      break;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.api.GoogleApiClient.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */