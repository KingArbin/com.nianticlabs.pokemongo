package com.upsight.android.marketing.internal.content;

import com.fasterxml.jackson.databind.JsonNode;
import com.upsight.android.analytics.internal.action.Action;

class MarketingContentActions$1$12
  implements MarketingContentActions.InternalFactory
{
  MarketingContentActions$1$12(MarketingContentActions.1 param1) {}
  
  public Action<MarketingContent, MarketingContentActions.MarketingContentActionContext> create(MarketingContentActions.MarketingContentActionContext paramMarketingContentActionContext, String paramString, JsonNode paramJsonNode)
  {
    return new MarketingContentActions.NotifyPurchases(paramMarketingContentActionContext, paramString, paramJsonNode, null);
  }
}

/* Location:
 * Qualified Name:     com.upsight.android.marketing.internal.content.MarketingContentActions.1.12
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */