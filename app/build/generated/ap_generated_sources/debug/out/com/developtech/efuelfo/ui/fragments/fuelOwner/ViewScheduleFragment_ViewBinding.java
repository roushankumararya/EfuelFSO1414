// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.fuelOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ViewScheduleFragment_ViewBinding implements Unbinder {
  private ViewScheduleFragment target;

  private View view2131361881;

  @UiThread
  public ViewScheduleFragment_ViewBinding(final ViewScheduleFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btnCreateSchdule, "method 'onCLick'");
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
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131361881.setOnClickListener(null);
    view2131361881 = null;
  }
}
