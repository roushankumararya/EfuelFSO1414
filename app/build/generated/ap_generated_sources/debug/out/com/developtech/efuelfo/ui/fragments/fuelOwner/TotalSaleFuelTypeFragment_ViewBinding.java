// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.fuelOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TotalSaleFuelTypeFragment_ViewBinding implements Unbinder {
  private TotalSaleFuelTypeFragment target;

  @UiThread
  public TotalSaleFuelTypeFragment_ViewBinding(TotalSaleFuelTypeFragment target, View source) {
    this.target = target;

    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", CoordinatorLayout.class);
    target.rvTotalSale = Utils.findRequiredViewAsType(source, R.id.rvTotalSale, "field 'rvTotalSale'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TotalSaleFuelTypeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rootLayout = null;
    target.rvTotalSale = null;
  }
}
