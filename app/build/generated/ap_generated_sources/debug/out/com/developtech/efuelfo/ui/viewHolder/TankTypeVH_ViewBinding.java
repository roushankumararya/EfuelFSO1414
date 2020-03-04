// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.viewHolder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TankTypeVH_ViewBinding implements Unbinder {
  private TankTypeVH target;

  @UiThread
  public TankTypeVH_ViewBinding(TankTypeVH target, View source) {
    this.target = target;

    target.tvTankName = Utils.findRequiredViewAsType(source, R.id.tvTankName, "field 'tvTankName'", TextView.class);
    target.etOFQty = Utils.findRequiredViewAsType(source, R.id.etOFQty, "field 'etOFQty'", EditText.class);
    target.etCFQty = Utils.findRequiredViewAsType(source, R.id.etCFQty, "field 'etCFQty'", EditText.class);
    target.ivEditTank = Utils.findRequiredViewAsType(source, R.id.ivEditTankType, "field 'ivEditTank'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TankTypeVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTankName = null;
    target.etOFQty = null;
    target.etCFQty = null;
    target.ivEditTank = null;
  }
}
