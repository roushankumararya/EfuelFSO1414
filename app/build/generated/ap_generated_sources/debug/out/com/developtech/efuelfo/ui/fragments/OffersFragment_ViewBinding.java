// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OffersFragment_ViewBinding implements Unbinder {
  private OffersFragment target;

  @UiThread
  public OffersFragment_ViewBinding(OffersFragment target, View source) {
    this.target = target;

    target.rvOffers = Utils.findRequiredViewAsType(source, R.id.rvOffers, "field 'rvOffers'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OffersFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvOffers = null;
  }
}
