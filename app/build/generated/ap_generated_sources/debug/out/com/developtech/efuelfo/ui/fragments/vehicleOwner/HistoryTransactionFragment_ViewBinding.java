// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.vehicleOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HistoryTransactionFragment_ViewBinding implements Unbinder {
  private HistoryTransactionFragment target;

  @UiThread
  public HistoryTransactionFragment_ViewBinding(HistoryTransactionFragment target, View source) {
    this.target = target;

    target.recycleView = Utils.findRequiredViewAsType(source, R.id.recycleView, "field 'recycleView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HistoryTransactionFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recycleView = null;
  }
}
