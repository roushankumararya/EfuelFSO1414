// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.vehicleOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
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

public class ExpenseDetailActivity_ViewBinding implements Unbinder {
  private ExpenseDetailActivity target;

  private View view2131362538;

  private View view2131362640;

  private View view2131362515;

  private View view2131361887;

  private View view2131361883;

  @UiThread
  public ExpenseDetailActivity_ViewBinding(ExpenseDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ExpenseDetailActivity_ViewBinding(final ExpenseDetailActivity target, View source) {
    this.target = target;

    View view;
    target.tvVehicleLabel = Utils.findRequiredViewAsType(source, R.id.tvVehicleLabel, "field 'tvVehicleLabel'", TextView.class);
    target.spinnerVehicle = Utils.findRequiredViewAsType(source, R.id.spinnerVehicle, "field 'spinnerVehicle'", Spinner.class);
    target.tvDateLabel = Utils.findRequiredViewAsType(source, R.id.tvDateLabel, "field 'tvDateLabel'", TextView.class);
    target.tvTimeLabel = Utils.findRequiredViewAsType(source, R.id.tvTimeLabel, "field 'tvTimeLabel'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tvDate, "field 'tvDate' and method 'onClick'");
    target.tvDate = Utils.castView(view, R.id.tvDate, "field 'tvDate'", TextView.class);
    view2131362538 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvTime, "field 'tvTime' and method 'onClick'");
    target.tvTime = Utils.castView(view, R.id.tvTime, "field 'tvTime'", TextView.class);
    view2131362640 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvOdometerLabel = Utils.findRequiredViewAsType(source, R.id.tvOdometerLabel, "field 'tvOdometerLabel'", TextView.class);
    target.etOdometer = Utils.findRequiredViewAsType(source, R.id.etOdometer, "field 'etOdometer'", EditText.class);
    target.tvTypeOfExpenseLabel = Utils.findRequiredViewAsType(source, R.id.tvTypeOfExpenseLabel, "field 'tvTypeOfExpenseLabel'", TextView.class);
    target.viewLine1 = Utils.findRequiredView(source, R.id.viewline1, "field 'viewLine1'");
    target.viewLine2 = Utils.findRequiredView(source, R.id.viewLine2, "field 'viewLine2'");
    target.tvTotalLabel = Utils.findRequiredViewAsType(source, R.id.tvTotalLabel, "field 'tvTotalLabel'", TextView.class);
    target.tvExpenseTotal = Utils.findRequiredViewAsType(source, R.id.tvExpenseTotal, "field 'tvExpenseTotal'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tvAddExpenseType, "field 'tvAddExpenseType' and method 'onClick'");
    target.tvAddExpenseType = Utils.castView(view, R.id.tvAddExpenseType, "field 'tvAddExpenseType'", TextView.class);
    view2131362515 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvReasonLabel = Utils.findRequiredViewAsType(source, R.id.tvReasonLabel, "field 'tvReasonLabel'", TextView.class);
    target.etReason = Utils.findRequiredViewAsType(source, R.id.etReason, "field 'etReason'", TextView.class);
    target.tvNotesLabel = Utils.findRequiredViewAsType(source, R.id.tvNotesLabel, "field 'tvNotesLabel'", TextView.class);
    target.etNotes = Utils.findRequiredViewAsType(source, R.id.etNotes, "field 'etNotes'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnEdit, "field 'btnEdit' and method 'onClick'");
    target.btnEdit = Utils.castView(view, R.id.btnEdit, "field 'btnEdit'", Button.class);
    view2131361887 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.lytExpenseLit = Utils.findRequiredViewAsType(source, R.id.lytExpenseList, "field 'lytExpenseLit'", LinearLayout.class);
    target.lytTypeOfExpense = Utils.findRequiredViewAsType(source, R.id.lytTypeOfExpense, "field 'lytTypeOfExpense'", LinearLayout.class);
    target.lytExpenseTypes = Utils.findRequiredViewAsType(source, R.id.lytExpenseTypes, "field 'lytExpenseTypes'", LinearLayout.class);
    target.viewlayer = Utils.findRequiredView(source, R.id.viewlayer, "field 'viewlayer'");
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.btnDelete, "method 'onClick'");
    view2131361883 = view;
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
    ExpenseDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvVehicleLabel = null;
    target.spinnerVehicle = null;
    target.tvDateLabel = null;
    target.tvTimeLabel = null;
    target.tvDate = null;
    target.tvTime = null;
    target.tvOdometerLabel = null;
    target.etOdometer = null;
    target.tvTypeOfExpenseLabel = null;
    target.viewLine1 = null;
    target.viewLine2 = null;
    target.tvTotalLabel = null;
    target.tvExpenseTotal = null;
    target.tvAddExpenseType = null;
    target.tvReasonLabel = null;
    target.etReason = null;
    target.tvNotesLabel = null;
    target.etNotes = null;
    target.btnEdit = null;
    target.lytExpenseLit = null;
    target.lytTypeOfExpense = null;
    target.lytExpenseTypes = null;
    target.viewlayer = null;
    target.rootLayout = null;

    view2131362538.setOnClickListener(null);
    view2131362538 = null;
    view2131362640.setOnClickListener(null);
    view2131362640 = null;
    view2131362515.setOnClickListener(null);
    view2131362515 = null;
    view2131361887.setOnClickListener(null);
    view2131361887 = null;
    view2131361883.setOnClickListener(null);
    view2131361883 = null;
  }
}
