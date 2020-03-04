// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.fuelStation;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StationRegistrationActivity_ViewBinding implements Unbinder {
  private StationRegistrationActivity target;

  private View view2131361880;

  @UiThread
  public StationRegistrationActivity_ViewBinding(StationRegistrationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public StationRegistrationActivity_ViewBinding(final StationRegistrationActivity target,
      View source) {
    this.target = target;

    View view;
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tabLayout, "field 'tabLayout'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", ViewPager.class);
    view = Utils.findRequiredView(source, R.id.btnContinue, "field 'btnContinue' and method 'onClick'");
    target.btnContinue = Utils.castView(view, R.id.btnContinue, "field 'btnContinue'", Button.class);
    view2131361880 = view;
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
    StationRegistrationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tabLayout = null;
    target.viewPager = null;
    target.btnContinue = null;

    view2131361880.setOnClickListener(null);
    view2131361880 = null;
  }
}
