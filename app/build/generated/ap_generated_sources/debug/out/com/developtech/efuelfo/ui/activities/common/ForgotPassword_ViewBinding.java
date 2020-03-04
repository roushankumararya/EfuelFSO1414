// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.common;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ForgotPassword_ViewBinding implements Unbinder {
  private ForgotPassword target;

  private View view2131361900;

  @UiThread
  public ForgotPassword_ViewBinding(ForgotPassword target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ForgotPassword_ViewBinding(final ForgotPassword target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btnSend, "field 'btnSend' and method 'onClick'");
    target.btnSend = Utils.castView(view, R.id.btnSend, "field 'btnSend'", Button.class);
    view2131361900 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.etLoginId = Utils.findRequiredViewAsType(source, R.id.etLoginId, "field 'etLoginId'", EditText.class);
    target.spinnerId = Utils.findRequiredViewAsType(source, R.id.spinnerId, "field 'spinnerId'", Spinner.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ForgotPassword target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnSend = null;
    target.etLoginId = null;
    target.spinnerId = null;

    view2131361900.setOnClickListener(null);
    view2131361900 = null;
  }
}
