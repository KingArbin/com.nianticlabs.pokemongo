package com.google.android.gms.location;

import com.google.android.gms.common.api.Status;

@Deprecated
public final class LocationStatusCodes
{
  public static final int ERROR = 1;
  public static final int GEOFENCE_NOT_AVAILABLE = 1000;
  public static final int GEOFENCE_TOO_MANY_GEOFENCES = 1001;
  public static final int GEOFENCE_TOO_MANY_PENDING_INTENTS = 1002;
  public static final int SUCCESS = 0;
  
  public static int zzgV(int paramInt)
  {
    if (((paramInt >= 0) && (paramInt <= 1)) || ((1000 <= paramInt) && (paramInt <= 1002))) {
      return paramInt;
    }
    return 1;
  }
  
  public static Status zzgW(int paramInt)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      return new Status(paramInt);
      paramInt = 13;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.location.LocationStatusCodes
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */