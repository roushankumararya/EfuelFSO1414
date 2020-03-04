// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.dialogFragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FilterTransDialogFragment_ViewBinding implements Unbinder {
  private FilterTransDialogFragment target;

  private View view2131361868;

  private View view2131361882;

  @UiThread
  public FilterTransDialogFragment_ViewBinding(final FilterTransDialogFragment target,
      View source) {
    this.target = target;

    View view;
    target.spinnerFuelType = Utils.findRequiredViewAsType(source, R.id.spinnerFuelType, "field 'spinnerFuelType'", Spinner.class);
    target.switchDate = Utils.findRequiredViewAsType(source, R.id.switchDate, "field 'switchDate'", Switch.class);
    target.tvStartDate = Utils.findRequiredViewAsType(source, R.id.tvStartDate, "field 'tvStartDate'", TextView.class);
    target.tvEndDate = Utils.findRequiredViewAsType(source, R.id.tvEndDate, "field 'tvEndDate'", TextView.class);
    target.lytDate = Utils.findRequiredViewAsType(source, R.id.lytDate, "field 'lytDate'", LinearLayout.class);
    target.tvStartDateLabel = Utils.findRequiredViewAsType(source, R.id.tvStartDateLabel, "field 'tvStartDateLabel'", TextView.class);
    target.tvEndDateLabel = Utils.findRequiredViewAsType(source, R.id.tvEndDateLabel, "field 'tvEndDateLabel'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnAccept, "method 'onClick'");
    view2131361868 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnDecline, "method 'onClick'");
    view2131361882 = view;
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
    FilterTransDialogFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spinnerFuelType = null;
    target.switchDate = null;
    target.tvStartDate = null;
    target.tvEndDate = null;
    target.lytDate = null;
    target.tvStartDateLabel = null;
    target.tvEndDateLabel = null;

    view2131361868.setOnClickListener(null);
    view2131361868 = null;
    view2131361882.setOnClickListener(null);
    view2131361882 = null;
  }
}
