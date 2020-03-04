// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.fuelOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TankTypeFragment_ViewBinding implements Unbinder {
  private TankTypeFragment target;

  private View view2131361905;

  private View view2131362064;

  @UiThread
  public TankTypeFragment_ViewBinding(final TankTypeFragment target, View source) {
    this.target = target;

    View view;
    target.recycleTankType = Utils.findRequiredViewAsType(source, R.id.recycleTankType, "field 'recycleTankType'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.btnUpdate, "field 'btnUpdate' and method 'onClick'");
    target.btnUpdate = Utils.castView(view, R.id.btnUpdate, "field 'btnUpdate'", Button.class);
    view2131361905 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.fabAddTankType, "field 'fabAddTankType' and method 'onClick'");
    target.fabAddTankType = Utils.castView(view, R.id.fabAddTankType, "field 'fabAddTankType'", FloatingActionButton.class);
    view2131362064 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", CoordinatorLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TankTypeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recycleTankType = null;
    target.btnUpdate = null;
    target.fabAddTankType = null;
    target.rootLayout = null;

    view2131361905.setOnClickListener(null);
    view2131361905 = null;
    view2131362064.setOnClickListener(null);
    view2131362064 = null;
  }
}
