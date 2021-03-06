package rx.internal.operators;

import java.util.ArrayList;
import java.util.Map;
import rx.Subscriber;

final class OnSubscribeGroupJoin$ResultManager$RightObserver
  extends Subscriber<T2>
{
  OnSubscribeGroupJoin$ResultManager$RightObserver(OnSubscribeGroupJoin.ResultManager paramResultManager) {}
  
  public void onCompleted()
  {
    ArrayList localArrayList = null;
    synchronized (this$1.guard)
    {
      this$1.rightDone = true;
      if (this$1.leftDone) {
        localArrayList = new ArrayList(this$1.leftMap.values());
      }
    }
    throw ((Throwable)localObject1);
  }
  
  public void onError(Throwable paramThrowable)
  {
    this$1.errorAll(paramThrowable);
  }
  
  /* Error */
  public void onNext(T2 paramT2)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 17	rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver:this$1	Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
    //   4: getfield 26	rx/internal/operators/OnSubscribeGroupJoin$ResultManager:guard	Ljava/lang/Object;
    //   7: astore_3
    //   8: aload_3
    //   9: monitorenter
    //   10: aload_0
    //   11: getfield 17	rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver:this$1	Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
    //   14: astore 4
    //   16: aload 4
    //   18: getfield 71	rx/internal/operators/OnSubscribeGroupJoin$ResultManager:rightIds	I
    //   21: istore_2
    //   22: aload 4
    //   24: iload_2
    //   25: iconst_1
    //   26: iadd
    //   27: putfield 71	rx/internal/operators/OnSubscribeGroupJoin$ResultManager:rightIds	I
    //   30: aload_0
    //   31: getfield 17	rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver:this$1	Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
    //   34: getfield 54	rx/internal/operators/OnSubscribeGroupJoin$ResultManager:rightMap	Ljava/util/Map;
    //   37: iload_2
    //   38: invokestatic 77	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   41: aload_1
    //   42: invokeinterface 81 3 0
    //   47: pop
    //   48: aload_3
    //   49: monitorexit
    //   50: aload_0
    //   51: getfield 17	rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver:this$1	Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
    //   54: getfield 85	rx/internal/operators/OnSubscribeGroupJoin$ResultManager:this$0	Lrx/internal/operators/OnSubscribeGroupJoin;
    //   57: getfield 89	rx/internal/operators/OnSubscribeGroupJoin:rightDuration	Lrx/functions/Func1;
    //   60: aload_1
    //   61: invokeinterface 95 2 0
    //   66: checkcast 97	rx/Observable
    //   69: astore_3
    //   70: new 99	rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightDurationObserver
    //   73: dup
    //   74: aload_0
    //   75: getfield 17	rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver:this$1	Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
    //   78: iload_2
    //   79: invokespecial 102	rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightDurationObserver:<init>	(Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;I)V
    //   82: astore 4
    //   84: aload_0
    //   85: getfield 17	rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver:this$1	Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
    //   88: getfield 106	rx/internal/operators/OnSubscribeGroupJoin$ResultManager:group	Lrx/subscriptions/CompositeSubscription;
    //   91: aload 4
    //   93: invokevirtual 112	rx/subscriptions/CompositeSubscription:add	(Lrx/Subscription;)V
    //   96: aload_3
    //   97: aload 4
    //   99: invokevirtual 116	rx/Observable:unsafeSubscribe	(Lrx/Subscriber;)Lrx/Subscription;
    //   102: pop
    //   103: aload_0
    //   104: getfield 17	rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver:this$1	Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
    //   107: getfield 26	rx/internal/operators/OnSubscribeGroupJoin$ResultManager:guard	Ljava/lang/Object;
    //   110: astore_3
    //   111: aload_3
    //   112: monitorenter
    //   113: new 35	java/util/ArrayList
    //   116: dup
    //   117: aload_0
    //   118: getfield 17	rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver:this$1	Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
    //   121: getfield 39	rx/internal/operators/OnSubscribeGroupJoin$ResultManager:leftMap	Ljava/util/Map;
    //   124: invokeinterface 45 1 0
    //   129: invokespecial 48	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   132: astore 4
    //   134: aload_3
    //   135: monitorexit
    //   136: aload 4
    //   138: invokeinterface 122 1 0
    //   143: astore_3
    //   144: aload_3
    //   145: invokeinterface 128 1 0
    //   150: ifeq +27 -> 177
    //   153: aload_3
    //   154: invokeinterface 132 1 0
    //   159: checkcast 134	rx/Observer
    //   162: aload_1
    //   163: invokeinterface 136 2 0
    //   168: goto -24 -> 144
    //   171: astore_1
    //   172: aload_0
    //   173: aload_1
    //   174: invokevirtual 138	rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver:onError	(Ljava/lang/Throwable;)V
    //   177: return
    //   178: astore_1
    //   179: aload_3
    //   180: monitorexit
    //   181: aload_1
    //   182: athrow
    //   183: astore_1
    //   184: aload_3
    //   185: monitorexit
    //   186: aload_1
    //   187: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	188	0	this	RightObserver
    //   0	188	1	paramT2	T2
    //   21	58	2	i	int
    //   14	123	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   0	10	171	java/lang/Throwable
    //   50	113	171	java/lang/Throwable
    //   136	144	171	java/lang/Throwable
    //   144	168	171	java/lang/Throwable
    //   181	183	171	java/lang/Throwable
    //   186	188	171	java/lang/Throwable
    //   10	50	178	finally
    //   179	181	178	finally
    //   113	136	183	finally
    //   184	186	183	finally
  }
}

/* Location:
 * Qualified Name:     rx.internal.operators.OnSubscribeGroupJoin.ResultManager.RightObserver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */