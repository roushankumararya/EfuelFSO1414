// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.dialogFragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VehicleOwnerProfileDialog_ViewBinding implements Unbinder {
  private VehicleOwnerProfileDialog target;

  @UiThread
  public VehicleOwnerProfileDialog_ViewBinding(VehicleOwnerProfileDialog target, View source) {
    this.target = target;

    target.tvMobile = Utils.findRequiredViewAsType(source, R.id.tvMobile, "field 'tvMobile'", TextView.class);
    target.ivImg = Utils.findRequiredViewAsType(source, R.id.ivImg, "field 'ivImg'", ImageView.class);
    target.tvEmail = Utils.findRequiredViewAsType(source, R.id.tvEmail, "field 'tvEmail'", TextView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tvAddress, "field 'tvAddress'", TextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tvName, "field 'tvName'", TextView.class);
    target.tvCompanyNameLabel = Utils.findRequiredViewAsType(source, R.id.tvCompanyNameLabel, "field 'tvCompanyNameLabel'", TextView.class);
    target.tvCompanyName = Utils.findRequiredViewAsType(source, R.id.tvCompanyName, "field 'tvCompanyName'", TextView.class);
    target.viewCompany = Utils.findRequiredView(source, R.id.viewCompany, "field 'viewCompany'");
  }

  @Override
  @CallSuper
  public void unbind() {
    VehicleOwnerProfileDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvMobile = null;
    target.ivImg = null;
    target.tvEmail = null;
    target.tvAddress = null;
    target.tvName = null;
    target.tvCompanyNameLabel = null;
    target.tvCompanyName = null;
    target.viewCompany = null;
  }
}
