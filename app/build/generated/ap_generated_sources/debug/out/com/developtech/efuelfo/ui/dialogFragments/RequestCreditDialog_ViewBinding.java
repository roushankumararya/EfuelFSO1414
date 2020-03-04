// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.dialogFragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RequestCreditDialog_ViewBinding implements Unbinder {
  private RequestCreditDialog target;

  private View view2131361902;

  @UiThread
  public RequestCreditDialog_ViewBinding(final RequestCreditDialog target, View source) {
    this.target = target;

    View view;
    target.etCreditLimit = Utils.findRequiredViewAsType(source, R.id.etCreditLimit, "field 'etCreditLimit'", EditText.class);
    target.etDuration = Utils.findRequiredViewAsType(source, R.id.etDuration, "field 'etDuration'", EditText.class);
    target.cbTerms = Utils.findRequiredViewAsType(source, R.id.cbTerms, "field 'cbTerms'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.btnSendRequest, "field 'btnSendRequest' and method 'onClick'");
    target.btnSendRequest = Utils.castView(view, R.id.btnSendRequest, "field 'btnSendRequest'", Button.class);
    view2131361902 = view;
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
    RequestCreditDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etCreditLimit = null;
    target.etDuration = null;
    target.cbTerms = null;
    target.btnSendRequest = null;

    view2131361902.setOnClickListener(null);
    view2131361902 = null;
  }
}
