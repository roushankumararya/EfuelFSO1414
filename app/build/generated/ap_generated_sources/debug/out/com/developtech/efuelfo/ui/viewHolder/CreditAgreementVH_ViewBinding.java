// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.viewHolder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CreditAgreementVH_ViewBinding implements Unbinder {
  private CreditAgreementVH target;

  @UiThread
  public CreditAgreementVH_ViewBinding(CreditAgreementVH target, View source) {
    this.target = target;

    target.tvOwnerName = Utils.findRequiredViewAsType(source, R.id.tvOwnerName, "field 'tvOwnerName'", TextView.class);
    target.tvOwnerNumber = Utils.findRequiredViewAsType(source, R.id.tvOwnerNumber, "field 'tvOwnerNumber'", TextView.class);
    target.tvDate = Utils.findRequiredViewAsType(source, R.id.tvDate, "field 'tvDate'", TextView.class);
    target.tvDuration = Utils.findRequiredViewAsType(source, R.id.tvDuration, "field 'tvDuration'", TextView.class);
    target.tvBalance = Utils.findRequiredViewAsType(source, R.id.tvBalance, "field 'tvBalance'", TextView.class);
    target.tvStatus = Utils.findRequiredViewAsType(source, R.id.tvStatus, "field 'tvStatus'", TextView.class);
    target.viewEllipseStatus = Utils.findRequiredView(source, R.id.viewEllipeseStatus, "field 'viewEllipseStatus'");
  }

  @Override
  @CallSuper
  public void unbind() {
    CreditAgreementVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvOwnerName = null;
    target.tvOwnerNumber = null;
    target.tvDate = null;
    target.tvDuration = null;
    target.tvBalance = null;
    target.tvStatus = null;
    target.viewEllipseStatus = null;
  }
}
