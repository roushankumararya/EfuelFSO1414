// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.fuelOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ViewCashTransactionFragment_ViewBinding implements Unbinder {
  private ViewCashTransactionFragment target;

  @UiThread
  public ViewCashTransactionFragment_ViewBinding(ViewCashTransactionFragment target, View source) {
    this.target = target;

    target.rvViewCash = Utils.findRequiredViewAsType(source, R.id.rvViewCashTrans, "field 'rvViewCash'", RecyclerView.class);
    target.lytInvoiceRecieve = Utils.findRequiredViewAsType(source, R.id.lytInvoiceRecieve, "field 'lytInvoiceRecieve'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ViewCashTransactionFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvViewCash = null;
    target.lytInvoiceRecieve = null;
  }
}
