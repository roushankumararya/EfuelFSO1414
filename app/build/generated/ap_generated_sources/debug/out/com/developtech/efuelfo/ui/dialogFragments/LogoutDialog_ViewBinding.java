// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.dialogFragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LogoutDialog_ViewBinding implements Unbinder {
  private LogoutDialog target;

  private View view2131361907;

  private View view2131361892;

  @UiThread
  public LogoutDialog_ViewBinding(final LogoutDialog target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btnYes, "field 'btnYes' and method 'onClick'");
    target.btnYes = Utils.castView(view, R.id.btnYes, "field 'btnYes'", Button.class);
    view2131361907 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnNo, "field 'btnNo' and method 'onClick'");
    target.btnNo = Utils.castView(view, R.id.btnNo, "field 'btnNo'", Button.class);
    view2131361892 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", CustomTextView.class);
    target.tvDesc = Utils.findRequiredViewAsType(source, R.id.tvDesc, "field 'tvDesc'", CustomTextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LogoutDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnYes = null;
    target.btnNo = null;
    target.tvTitle = null;
    target.tvDesc = null;

    view2131361907.setOnClickListener(null);
    view2131361907 = null;
    view2131361892.setOnClickListener(null);
    view2131361892 = null;
  }
}
