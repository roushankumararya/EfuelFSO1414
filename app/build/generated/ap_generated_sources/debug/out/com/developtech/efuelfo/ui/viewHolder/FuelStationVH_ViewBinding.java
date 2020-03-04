// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.viewHolder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.RoundedImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FuelStationVH_ViewBinding implements Unbinder {
  private FuelStationVH target;

  @UiThread
  public FuelStationVH_ViewBinding(FuelStationVH target, View source) {
    this.target = target;

    target.ivStationLogo = Utils.findRequiredViewAsType(source, R.id.ivStationLogo, "field 'ivStationLogo'", RoundedImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tvStationName, "field 'tvName'", TextView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tvStationAddress, "field 'tvAddress'", TextView.class);
    target.switchCreditAgreement = Utils.findRequiredViewAsType(source, R.id.switchCreditAgreement, "field 'switchCreditAgreement'", Switch.class);
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.item_fuel_station, "field 'rootLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FuelStationVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivStationLogo = null;
    target.tvName = null;
    target.tvAddress = null;
    target.switchCreditAgreement = null;
    target.rootLayout = null;
  }
}
