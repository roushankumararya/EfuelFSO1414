// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddFuelStation_ViewBinding implements Unbinder {
  private AddFuelStation target;

  private View view2131362060;

  @UiThread
  public AddFuelStation_ViewBinding(final AddFuelStation target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.fabAddFuelStation, "field 'fabAddFuelStation' and method 'onClick'");
    target.fabAddFuelStation = Utils.castView(view, R.id.fabAddFuelStation, "field 'fabAddFuelStation'", FloatingActionButton.class);
    view2131362060 = view;
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
    AddFuelStation target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.fabAddFuelStation = null;

    view2131362060.setOnClickListener(null);
    view2131362060 = null;
  }
}
