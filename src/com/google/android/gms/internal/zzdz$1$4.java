package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;

class zzdz$1$4
  implements Runnable
{
  zzdz$1$4(zzdz.1 param1, zzbb paramzzbb) {}
  
  public void run()
  {
    synchronized (zzdz.zzc(zzyw.zzyu))
    {
      if ((zzyw.zzyt.getStatus() == -1) || (zzyw.zzyt.getStatus() == 1)) {
        return;
      }
      zzyw.zzyt.reject();
      zzid.runOnUiThread(new Runnable()
      {
        public void run()
        {
          zzyv.destroy();
        }
      });
      zzb.v("Could not receive loaded message in a timely manner. Rejecting.");
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzdz.1.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */