// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.viewHolder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomTextView;
import com.developtech.efuelfo.customs.RoundedImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TotalSaleOperatorVH_ViewBinding implements Unbinder {
  private TotalSaleOperatorVH target;

  @UiThread
  public TotalSaleOperatorVH_ViewBinding(TotalSaleOperatorVH target, View source) {
    this.target = target;

    target.ivOperator = Utils.findRequiredViewAsType(source, R.id.ivOperator, "field 'ivOperator'", RoundedImageView.class);
    target.tvOperatorName = Utils.findRequiredViewAsType(source, R.id.tvOperatorName, "field 'tvOperatorName'", CustomTextView.class);
    target.tvOperatorMobile = Utils.findRequiredViewAsType(source, R.id.tvOperatorMobile, "field 'tvOperatorMobile'", CustomTextView.class);
    target.tvTotalSale = Utils.findRequiredViewAsType(source, R.id.tvTotalSale, "field 'tvTotalSale'", CustomTextView.class);
    target.lytFuelPrices = Utils.findRequiredViewAsType(source, R.id.lytFuelPrices, "field 'lytFuelPrices'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TotalSaleOperatorVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivOperator = null;
    target.tvOperatorName = null;
    target.tvOperatorMobile = null;
    target.tvTotalSale = null;
    target.lytFuelPrices = null;
  }
}
