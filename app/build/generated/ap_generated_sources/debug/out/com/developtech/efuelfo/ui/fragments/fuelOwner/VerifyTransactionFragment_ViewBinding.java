// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.fuelOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VerifyTransactionFragment_ViewBinding implements Unbinder {
  private VerifyTransactionFragment target;

  private View view2131362158;

  private View view2131362157;

  @UiThread
  public VerifyTransactionFragment_ViewBinding(final VerifyTransactionFragment target,
      View source) {
    this.target = target;

    View view;
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", FrameLayout.class);
    target.etVerifyTransSearch = Utils.findRequiredViewAsType(source, R.id.etVerifyTransSearch, "field 'etVerifyTransSearch'", EditText.class);
    target.rvVerifyTrans = Utils.findRequiredViewAsType(source, R.id.rvVerifyTrans, "field 'rvVerifyTrans'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.ivSearch, "method 'onClick'");
    view2131362158 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ivQrCode, "method 'onClick'");
    view2131362157 = view;
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
    VerifyTransactionFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rootLayout = null;
    target.etVerifyTransSearch = null;
    target.rvVerifyTrans = null;

    view2131362158.setOnClickListener(null);
    view2131362158 = null;
    view2131362157.setOnClickListener(null);
    view2131362157 = null;
  }
}
