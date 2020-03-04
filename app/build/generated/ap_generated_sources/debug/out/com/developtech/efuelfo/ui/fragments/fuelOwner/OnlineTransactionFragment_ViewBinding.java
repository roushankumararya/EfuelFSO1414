// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.fuelOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OnlineTransactionFragment_ViewBinding implements Unbinder {
  private OnlineTransactionFragment target;

  private View view2131361888;

  private View view2131361896;

  @UiThread
  public OnlineTransactionFragment_ViewBinding(final OnlineTransactionFragment target,
      View source) {
    this.target = target;

    View view;
    target.recycleView = Utils.findRequiredViewAsType(source, R.id.recycleView, "field 'recycleView'", RecyclerView.class);
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", FrameLayout.class);
    target.lytInvoiceRecieve = Utils.findRequiredViewAsType(source, R.id.lytInvoiceRecieve, "field 'lytInvoiceRecieve'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btnEmailInvoice, "method 'onClick'");
    view2131361888 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnReceive, "method 'onClick'");
    view2131361896 = view;
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
    OnlineTransactionFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recycleView = null;
    target.rootLayout = null;
    target.lytInvoiceRecieve = null;

    view2131361888.setOnClickListener(null);
    view2131361888 = null;
    view2131361896.setOnClickListener(null);
    view2131361896 = null;
  }
}
