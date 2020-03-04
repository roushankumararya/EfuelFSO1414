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

public class NotificationVH_ViewBinding implements Unbinder {
  private NotificationVH target;

  @UiThread
  public NotificationVH_ViewBinding(NotificationVH target, View source) {
    this.target = target;

    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.tvMsg = Utils.findRequiredViewAsType(source, R.id.tvMsg, "field 'tvMsg'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tvTime, "field 'tvTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NotificationVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.tvMsg = null;
    target.tvTime = null;
  }
}
