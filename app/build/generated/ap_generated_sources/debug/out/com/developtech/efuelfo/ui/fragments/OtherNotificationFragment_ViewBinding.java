// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OtherNotificationFragment_ViewBinding implements Unbinder {
  private OtherNotificationFragment target;

  @UiThread
  public OtherNotificationFragment_ViewBinding(OtherNotificationFragment target, View source) {
    this.target = target;

    target.rvOthers = Utils.findRequiredViewAsType(source, R.id.rvOthers, "field 'rvOthers'", RecyclerView.class);
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", FrameLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OtherNotificationFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvOthers = null;
    target.rootLayout = null;
  }
}
