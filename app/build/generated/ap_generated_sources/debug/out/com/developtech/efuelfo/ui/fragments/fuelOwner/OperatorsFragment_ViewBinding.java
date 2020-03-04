// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.fuelOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OperatorsFragment_ViewBinding implements Unbinder {
  private OperatorsFragment target;

  private View view2131362062;

  @UiThread
  public OperatorsFragment_ViewBinding(final OperatorsFragment target, View source) {
    this.target = target;

    View view;
    target.rvOperator = Utils.findRequiredViewAsType(source, R.id.rvOperator, "field 'rvOperator'", RecyclerView.class);
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", CoordinatorLayout.class);
    view = Utils.findRequiredView(source, R.id.fabAddOperator, "method 'onClick'");
    view2131362062 = view;
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
    OperatorsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvOperator = null;
    target.rootLayout = null;

    view2131362062.setOnClickListener(null);
    view2131362062 = null;
  }
}
