package com.google.android.gms.ads;

import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.client.zzy.zza;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.common.internal.zzx;
import java.util.Date;

public final class AdRequest$Builder
{
  private final zzy.zza zznP = new zzy.zza();
  
  public AdRequest$Builder()
  {
    zznP.zzG(AdRequest.DEVICE_ID_EMULATOR);
  }
  
  public Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> paramClass, Bundle paramBundle)
  {
    zznP.zzb(paramClass, paramBundle);
    return this;
  }
  
  public Builder addKeyword(String paramString)
  {
    zznP.zzF(paramString);
    return this;
  }
  
  public Builder addNetworkExtras(NetworkExtras paramNetworkExtras)
  {
    zznP.zza(paramNetworkExtras);
    return this;
  }
  
  public Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> paramClass, Bundle paramBundle)
  {
    zznP.zza(paramClass, paramBundle);
    if ((paramClass.equals(AdMobAdapter.class)) && (paramBundle.getBoolean("_emulatorLiveAds"))) {
      zznP.zzH(AdRequest.DEVICE_ID_EMULATOR);
    }
    return this;
  }
  
  public Builder addTestDevice(String paramString)
  {
    zznP.zzG(paramString);
    return this;
  }
  
  public AdRequest build()
  {
    return new AdRequest(this, null);
  }
  
  public Builder setBirthday(Date paramDate)
  {
    zznP.zza(paramDate);
    return this;
  }
  
  public Builder setContentUrl(String paramString)
  {
    zzx.zzb(paramString, "Content URL must be non-null.");
    zzx.zzh(paramString, "Content URL must be non-empty.");
    if (paramString.length() <= 512) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "Content URL must not exceed %d in length.  Provided length was %d.", new Object[] { Integer.valueOf(512), Integer.valueOf(paramString.length()) });
      zznP.zzI(paramString);
      return this;
    }
  }
  
  public Builder setGender(int paramInt)
  {
    zznP.zzm(paramInt);
    return this;
  }
  
  public Builder setLocation(Location paramLocation)
  {
    zznP.zzb(paramLocation);
    return this;
  }
  
  public Builder setRequestAgent(String paramString)
  {
    zznP.zzK(paramString);
    return this;
  }
  
  public Builder tagForChildDirectedTreatment(boolean paramBoolean)
  {
    zznP.zzj(paramBoolean);
    return this;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.AdRequest.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */