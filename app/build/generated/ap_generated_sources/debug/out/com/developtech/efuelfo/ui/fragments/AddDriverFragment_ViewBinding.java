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

public class AddDriverFragment_ViewBinding implements Unbinder {
  private AddDriverFragment target;

  private View view2131362057;

  @UiThread
  public AddDriverFragment_ViewBinding(final AddDriverFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.fabAddDriver, "field 'fabAddDriver' and method 'onClick'");
    target.fabAddDriver = Utils.castView(view, R.id.fabAddDriver, "field 'fabAddDriver'", FloatingActionButton.class);
    view2131362057 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.recyclerDriver = Utils.findRequiredViewAsType(source, R.id.recyclerDriver, "field 'recyclerDriver'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddDriverFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.fabAddDriver = null;
    target.recyclerDriver = null;

    view2131362057.setOnClickListener(null);
    view2131362057 = null;
  }
}
