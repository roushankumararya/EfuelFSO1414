// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.fuelOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FuelStationFragment_ViewBinding implements Unbinder {
  private FuelStationFragment target;

  private View view2131362057;

  @UiThread
  public FuelStationFragment_ViewBinding(final FuelStationFragment target, View source) {
    this.target = target;

    View view;
    target.rvFuelStaton = Utils.findRequiredViewAsType(source, R.id.rvFuelStation, "field 'rvFuelStaton'", RecyclerView.class);
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.fabAdd, "method 'onClick'");
    view2131362057 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    FuelStationFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvFuelStaton = null;
    target.rootLayout = null;

    view2131362057.setOnClickListener(null);
    view2131362057 = null;
  }
}
