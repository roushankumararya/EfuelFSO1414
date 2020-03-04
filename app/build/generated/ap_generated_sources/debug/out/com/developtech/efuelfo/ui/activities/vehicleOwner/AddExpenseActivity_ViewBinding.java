// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.vehicleOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddExpenseActivity_ViewBinding implements Unbinder {
  private AddExpenseActivity target;

  private View view2131362543;

  private View view2131362646;

  private View view2131362520;

  private View view2131361898;

  @UiThread
  public AddExpenseActivity_ViewBinding(AddExpenseActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddExpenseActivity_ViewBinding(final AddExpenseActivity target, View source) {
    this.target = target;

    View view;
    target.lytExpenseList = Utils.findRequiredViewAsType(source, R.id.lytExpenseList, "field 'lytExpenseList'", LinearLayout.class);
    target.lytExpenseTypes = Utils.findRequiredViewAsType(source, R.id.lytExpenseTypes, "field 'lytExpenseTypes'", LinearLayout.class);
    target.tvExpenseTotal = Utils.findRequiredViewAsType(source, R.id.tvExpenseTotal, "field 'tvExpenseTotal'", TextView.class);
    target.spinnerVehicle = Utils.findRequiredViewAsType(source, R.id.spinnerVehicle, "field 'spinnerVehicle'", Spinner.class);
    view = Utils.findRequiredView(source, R.id.tvDate, "field 'tvDate' and method 'onClick'");
    target.tvDate = Utils.castView(view, R.id.tvDate, "field 'tvDate'", TextView.class);
    view2131362543 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvTime, "field 'tvTime' and method 'onClick'");
    target.tvTime = Utils.castView(view, R.id.tvTime, "field 'tvTime'", TextView.class);
    view2131362646 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.etOdometer = Utils.findRequiredViewAsType(source, R.id.etOdometer, "field 'etOdometer'", EditText.class);
    target.etNotes = Utils.findRequiredViewAsType(source, R.id.etNotes, "field 'etNotes'", EditText.class);
    target.etReason = Utils.findRequiredViewAsType(source, R.id.etReason, "field 'etReason'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tvAddExpenseType, "field 'tvAddExpenseType' and method 'onClick'");
    target.tvAddExpenseType = Utils.castView(view, R.id.tvAddExpenseType, "field 'tvAddExpenseType'", TextView.class);
    view2131362520 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvTypeOfExpenseLabel = Utils.findRequiredViewAsType(source, R.id.tvTypeOfExpenseLabel, "field 'tvTypeOfExpenseLabel'", TextView.class);
    target.tvReasonLabel = Utils.findRequiredViewAsType(source, R.id.tvReasonLabel, "field 'tvReasonLabel'", TextView.class);
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.btnSave, "method 'onClick'");
    view2131361898 = view;
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
    AddExpenseActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.lytExpenseList = null;
    target.lytExpenseTypes = null;
    target.tvExpenseTotal = null;
    target.spinnerVehicle = null;
    target.tvDate = null;
    target.tvTime = null;
    target.etOdometer = null;
    target.etNotes = null;
    target.etReason = null;
    target.tvAddExpenseType = null;
    target.tvTypeOfExpenseLabel = null;
    target.tvReasonLabel = null;
    target.rootLayout = null;

    view2131362543.setOnClickListener(null);
    view2131362543 = null;
    view2131362646.setOnClickListener(null);
    view2131362646 = null;
    view2131362520.setOnClickListener(null);
    view2131362520 = null;
    view2131361898.setOnClickListener(null);
    view2131361898 = null;
  }
}
