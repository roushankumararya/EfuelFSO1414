// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.common;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangePasswordActivity_ViewBinding implements Unbinder {
  private ChangePasswordActivity target;

  private View view2131361880;

  @UiThread
  public ChangePasswordActivity_ViewBinding(ChangePasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangePasswordActivity_ViewBinding(final ChangePasswordActivity target, View source) {
    this.target = target;

    View view;
    target.etConfirmPassocode = Utils.findRequiredViewAsType(source, R.id.etConfirmPassocode, "field 'etConfirmPassocode'", EditText.class);
    target.etNewPasscode = Utils.findRequiredViewAsType(source, R.id.etNewPasscode, "field 'etNewPasscode'", EditText.class);
    target.etOldPasscode = Utils.findRequiredViewAsType(source, R.id.etOldPasscode, "field 'etOldPasscode'", EditText.class);
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btnContinue, "method 'onClick'");
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
    ChangePasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etConfirmPassocode = null;
    target.etNewPasscode = null;
    target.etOldPasscode = null;
    target.rootLayout = null;

    view2131361880.setOnClickListener(null);
    view2131361880 = null;
  }
}
