// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.vehicleOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CreditAgreementFragment_ViewBinding implements Unbinder {
  private CreditAgreementFragment target;

  @UiThread
  public CreditAgreementFragment_ViewBinding(CreditAgreementFragment target, View source) {
    this.target = target;

    target.tab_layout = Utils.findRequiredViewAsType(source, R.id.tab_layout, "field 'tab_layout'", TabLayout.class);
    target.view_pager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'view_pager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CreditAgreementFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tab_layout = null;
    target.view_pager = null;
  }
}
