// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.dialogFragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectVehicleOwnerDialog_ViewBinding implements Unbinder {
  private SelectVehicleOwnerDialog target;

  @UiThread
  public SelectVehicleOwnerDialog_ViewBinding(SelectVehicleOwnerDialog target, View source) {
    this.target = target;

    target.recycleStationList = Utils.findRequiredViewAsType(source, R.id.recycleStationList, "field 'recycleStationList'", RecyclerView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectVehicleOwnerDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recycleStationList = null;
    target.tvTitle = null;
  }
}
