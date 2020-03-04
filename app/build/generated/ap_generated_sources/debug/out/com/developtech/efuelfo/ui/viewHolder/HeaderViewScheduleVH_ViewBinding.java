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

public class HeaderViewScheduleVH_ViewBinding implements Unbinder {
  private HeaderViewScheduleVH target;

  @UiThread
  public HeaderViewScheduleVH_ViewBinding(HeaderViewScheduleVH target, View source) {
    this.target = target;

    target.tvHeaderSchedules = Utils.findRequiredViewAsType(source, R.id.tvHeaderSchedules, "field 'tvHeaderSchedules'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HeaderViewScheduleVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvHeaderSchedules = null;
  }
}
