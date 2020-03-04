// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.dialogFragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectFuelStation_ViewBinding implements Unbinder {
  private SelectFuelStation target;

  @UiThread
  public SelectFuelStation_ViewBinding(SelectFuelStation target, View source) {
    this.target = target;

    target.recycleStationList = Utils.findRequiredViewAsType(source, R.id.recycleStationList, "field 'recycleStationList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectFuelStation target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recycleStationList = null;
  }
}
