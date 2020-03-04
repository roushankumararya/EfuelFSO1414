// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.viewHolder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ViewSchedulesVH_ViewBinding implements Unbinder {
  private ViewSchedulesVH target;

  @UiThread
  public ViewSchedulesVH_ViewBinding(ViewSchedulesVH target, View source) {
    this.target = target;

    target.tvScheduleDateTime = Utils.findRequiredViewAsType(source, R.id.tvScheduleDateTime, "field 'tvScheduleDateTime'", CustomTextView.class);
    target.tvCreatedOn = Utils.findRequiredViewAsType(source, R.id.tvCreatedOn, "field 'tvCreatedOn'", CustomTextView.class);
    target.tvModified = Utils.findRequiredViewAsType(source, R.id.tvModified, "field 'tvModified'", CustomTextView.class);
    target.tvCreatedBy = Utils.findRequiredViewAsType(source, R.id.tvCreatedBy, "field 'tvCreatedBy'", CustomTextView.class);
    target.tvModifiedBy = Utils.findRequiredViewAsType(source, R.id.tvModifiedBy, "field 'tvModifiedBy'", CustomTextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ViewSchedulesVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvScheduleDateTime = null;
    target.tvCreatedOn = null;
    target.tvModified = null;
    target.tvCreatedBy = null;
    target.tvModifiedBy = null;
  }
}
