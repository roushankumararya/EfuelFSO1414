// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.viewHolder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FuelPricesVH_ViewBinding implements Unbinder {
  private FuelPricesVH target;

  @UiThread
  public FuelPricesVH_ViewBinding(FuelPricesVH target, View source) {
    this.target = target;

    target.spinnerFuelType = Utils.findRequiredViewAsType(source, R.id.spinnerFuelType, "field 'spinnerFuelType'", Spinner.class);
    target.etPrice = Utils.findRequiredViewAsType(source, R.id.etPrice, "field 'etPrice'", EditText.class);
    target.ivDelete = Utils.findRequiredViewAsType(source, R.id.ivDelete, "field 'ivDelete'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FuelPricesVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spinnerFuelType = null;
    target.etPrice = null;
    target.ivDelete = null;
  }
}
