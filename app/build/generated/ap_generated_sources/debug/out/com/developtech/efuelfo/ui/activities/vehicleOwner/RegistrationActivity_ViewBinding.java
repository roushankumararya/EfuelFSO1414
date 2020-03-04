// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.vehicleOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegistrationActivity_ViewBinding implements Unbinder {
  private RegistrationActivity target;

  private View view2131361880;

  @UiThread
  public RegistrationActivity_ViewBinding(RegistrationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegistrationActivity_ViewBinding(final RegistrationActivity target, View source) {
    this.target = target;

    View view;
    target.view_pager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'view_pager'", ViewPager.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tabLayout, "field 'tabLayout'", TabLayout.class);
    view = Utils.findRequiredView(source, R.id.btnContinue, "field 'btnContinue' and method 'onClick'");
    target.btnContinue = Utils.castView(view, R.id.btnContinue, "field 'btnContinue'", Button.class);
    view2131361880 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.ivDrawer = Utils.findRequiredViewAsType(source, R.id.ivDrawer, "field 'ivDrawer'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RegistrationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.view_pager = null;
    target.tabLayout = null;
    target.btnContinue = null;
    target.ivDrawer = null;

    view2131361880.setOnClickListener(null);
    view2131361880 = null;
  }
}
