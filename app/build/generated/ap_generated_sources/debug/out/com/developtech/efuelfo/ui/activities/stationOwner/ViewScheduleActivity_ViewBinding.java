// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.stationOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ViewScheduleActivity_ViewBinding implements Unbinder {
  private ViewScheduleActivity target;

  private View view2131361881;

  @UiThread
  public ViewScheduleActivity_ViewBinding(ViewScheduleActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ViewScheduleActivity_ViewBinding(final ViewScheduleActivity target, View source) {
    this.target = target;

    View view;
    target.rvSchedules = Utils.findRequiredViewAsType(source, R.id.rvSchedules, "field 'rvSchedules'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.btnCreateSchdule, "field 'btnCreateSchdule' and method 'onCLick'");
    target.btnCreateSchdule = Utils.castView(view, R.id.btnCreateSchdule, "field 'btnCreateSchdule'", Button.class);
    view2131361881 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCLick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ViewScheduleActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvSchedules = null;
    target.btnCreateSchdule = null;

    view2131361881.setOnClickListener(null);
    view2131361881 = null;
  }
}
