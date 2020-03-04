// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.common;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectAccountActivity_ViewBinding implements Unbinder {
  private SelectAccountActivity target;

  private View view2131361880;

  private View view2131362362;

  private View view2131362361;

  @UiThread
  public SelectAccountActivity_ViewBinding(SelectAccountActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectAccountActivity_ViewBinding(final SelectAccountActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btnContinue, "field 'btnContinue' and method 'onClick'");
    target.btnContinue = Utils.castView(view, R.id.btnContinue, "field 'btnContinue'", Button.class);
    view2131361880 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.relFuelStaff, "field 'relFuelStaff' and method 'onClick'");
    target.relFuelStaff = Utils.castView(view, R.id.relFuelStaff, "field 'relFuelStaff'", RelativeLayout.class);
    view2131362362 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.relFuelOwner, "field 'relFuelOwner' and method 'onClick'");
    target.relFuelOwner = Utils.castView(view, R.id.relFuelOwner, "field 'relFuelOwner'", RelativeLayout.class);
    view2131362361 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.ivFuelOwner = Utils.findRequiredViewAsType(source, R.id.ivFuelOwner, "field 'ivFuelOwner'", ImageView.class);
    target.ivFuelStaff = Utils.findRequiredViewAsType(source, R.id.ivFuelStaff, "field 'ivFuelStaff'", ImageView.class);
    target.tvWarning = Utils.findRequiredViewAsType(source, R.id.tvWarning, "field 'tvWarning'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectAccountActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnContinue = null;
    target.relFuelStaff = null;
    target.relFuelOwner = null;
    target.ivFuelOwner = null;
    target.ivFuelStaff = null;
    target.tvWarning = null;

    view2131361880.setOnClickListener(null);
    view2131361880 = null;
    view2131362362.setOnClickListener(null);
    view2131362362 = null;
    view2131362361.setOnClickListener(null);
    view2131362361 = null;
  }
}
