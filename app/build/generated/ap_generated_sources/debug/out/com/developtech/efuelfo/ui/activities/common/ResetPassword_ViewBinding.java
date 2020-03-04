// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.common;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ResetPassword_ViewBinding implements Unbinder {
  private ResetPassword target;

  private View view2131361880;

  @UiThread
  public ResetPassword_ViewBinding(ResetPassword target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ResetPassword_ViewBinding(final ResetPassword target, View source) {
    this.target = target;

    View view;
    target.etNewPass = Utils.findRequiredViewAsType(source, R.id.etNewPass, "field 'etNewPass'", EditText.class);
    target.etConfirmPass = Utils.findRequiredViewAsType(source, R.id.etConfirmPass, "field 'etConfirmPass'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btnContinue, "field 'btnContinue' and method 'OnClick'");
    target.btnContinue = Utils.castView(view, R.id.btnContinue, "field 'btnContinue'", Button.class);
    view2131361880 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ResetPassword target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etNewPass = null;
    target.etConfirmPass = null;
    target.btnContinue = null;

    view2131361880.setOnClickListener(null);
    view2131361880 = null;
  }
}
