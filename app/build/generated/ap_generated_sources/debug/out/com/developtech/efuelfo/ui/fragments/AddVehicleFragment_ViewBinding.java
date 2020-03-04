// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddVehicleFragment_ViewBinding implements Unbinder {
  private AddVehicleFragment target;

  private View view2131362065;

  @UiThread
  public AddVehicleFragment_ViewBinding(final AddVehicleFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.fabAddVehicle, "field 'fabAddVehicle' and method 'onClick'");
    target.fabAddVehicle = Utils.castView(view, R.id.fabAddVehicle, "field 'fabAddVehicle'", FloatingActionButton.class);
    view2131362065 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.recycleVehicle = Utils.findRequiredViewAsType(source, R.id.recycleVehicle, "field 'recycleVehicle'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddVehicleFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.fabAddVehicle = null;
    target.recycleVehicle = null;

    view2131362065.setOnClickListener(null);
    view2131362065 = null;
  }
}
