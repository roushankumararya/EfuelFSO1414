// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.vehicleOwner;

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

public class PendingFragment_ViewBinding implements Unbinder {
  private PendingFragment target;

  @UiThread
  public PendingFragment_ViewBinding(PendingFragment target, View source) {
    this.target = target;

    target.recycleView = Utils.findRequiredViewAsType(source, R.id.recycleView, "field 'recycleView'", RecyclerView.class);
    target.lytInvoiceRecieve = Utils.findRequiredViewAsType(source, R.id.lytInvoiceRecieve, "field 'lytInvoiceRecieve'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PendingFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recycleView = null;
    target.lytInvoiceRecieve = null;
  }
}
