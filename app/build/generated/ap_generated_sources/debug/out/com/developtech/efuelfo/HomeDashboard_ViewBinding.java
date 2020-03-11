// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeDashboard_ViewBinding implements Unbinder {
  private HomeDashboard target;

  private View view2131362131;

  private View view2131362128;

  private View view2131362135;

  private View view2131362151;

  @UiThread
  public HomeDashboard_ViewBinding(HomeDashboard target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeDashboard_ViewBinding(final HomeDashboard target, View source) {
    this.target = target;

    View view;
    target.drawer_layout = Utils.findRequiredViewAsType(source, R.id.drawer_layout, "field 'drawer_layout'", DrawerLayout.class);
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootlayout, "field 'rootLayout'", CoordinatorLayout.class);
    target.tvCount = Utils.findRequiredViewAsType(source, R.id.tvCount, "field 'tvCount'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ivDrawer, "method 'onClick'");
    view2131362131 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ivBack, "method 'onClick'");
    view2131362128 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ivFilter, "method 'onClick'");
    view2131362135 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ivNotification, "method 'onClick'");
    view2131362151 = view;
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
    HomeDashboard target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.drawer_layout = null;
    target.rootLayout = null;
    target.tvCount = null;

    view2131362131.setOnClickListener(null);
    view2131362131 = null;
    view2131362128.setOnClickListener(null);
    view2131362128 = null;
    view2131362135.setOnClickListener(null);
    view2131362135 = null;
    view2131362151.setOnClickListener(null);
    view2131362151 = null;
  }
}
