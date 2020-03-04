// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.dialogFragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.RoundedImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FuelStationDetailDialog_ViewBinding implements Unbinder {
  private FuelStationDetailDialog target;

  @UiThread
  public FuelStationDetailDialog_ViewBinding(FuelStationDetailDialog target, View source) {
    this.target = target;

    target.tvName = Utils.findRequiredViewAsType(source, R.id.tvName, "field 'tvName'", TextView.class);
    target.tvDealerCode = Utils.findRequiredViewAsType(source, R.id.tvDealerCode, "field 'tvDealerCode'", TextView.class);
    target.tvFuelStationId = Utils.findRequiredViewAsType(source, R.id.tvFuelStationId, "field 'tvFuelStationId'", TextView.class);
    target.tvMobile = Utils.findRequiredViewAsType(source, R.id.tvMobile, "field 'tvMobile'", TextView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tvAddress, "field 'tvAddress'", TextView.class);
    target.tvTransactionDate = Utils.findRequiredViewAsType(source, R.id.tvTransactionDate, "field 'tvTransactionDate'", TextView.class);
    target.ivImg = Utils.findRequiredViewAsType(source, R.id.ivImg, "field 'ivImg'", RoundedImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FuelStationDetailDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvName = null;
    target.tvDealerCode = null;
    target.tvFuelStationId = null;
    target.tvMobile = null;
    target.tvAddress = null;
    target.tvTransactionDate = null;
    target.ivImg = null;
  }
}
