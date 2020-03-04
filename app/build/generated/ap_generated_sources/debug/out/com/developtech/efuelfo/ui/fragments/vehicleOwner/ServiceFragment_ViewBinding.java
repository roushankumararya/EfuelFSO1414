// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.vehicleOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ServiceFragment_ViewBinding implements Unbinder {
  private ServiceFragment target;

  private View view2131362059;

  @UiThread
  public ServiceFragment_ViewBinding(final ServiceFragment target, View source) {
    this.target = target;

    View view;
    target.rvExpense = Utils.findRequiredViewAsType(source, R.id.rvExpense, "field 'rvExpense'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.fabAddExpense, "method 'onClick'");
    view2131362059 = view;
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
    ServiceFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvExpense = null;

    view2131362059.setOnClickListener(null);
    view2131362059 = null;
  }
}
