// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.vehicleOwner;

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

public class ExpenseTypeActivity_ViewBinding implements Unbinder {
  private ExpenseTypeActivity target;

  private View view2131362059;

  private View view2131361898;

  @UiThread
  public ExpenseTypeActivity_ViewBinding(ExpenseTypeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ExpenseTypeActivity_ViewBinding(final ExpenseTypeActivity target, View source) {
    this.target = target;

    View view;
    target.rvExpenseType = Utils.findRequiredViewAsType(source, R.id.rvExpenseType, "field 'rvExpenseType'", RecyclerView.class);
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.fabAddExpenseType, "method 'onClick'");
    view2131362059 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnSave, "method 'onClick'");
    view2131361898 = view;
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
    ExpenseTypeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvExpenseType = null;
    target.rootLayout = null;

    view2131362059.setOnClickListener(null);
    view2131362059 = null;
    view2131361898.setOnClickListener(null);
    view2131361898 = null;
  }
}
