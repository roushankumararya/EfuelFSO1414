// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.github.mikephil.charting.charts.PieChart;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReportFragment_ViewBinding implements Unbinder {
  private ReportFragment target;

  private View view2131362629;

  private View view2131362552;

  @UiThread
  public ReportFragment_ViewBinding(final ReportFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tvStartDate, "field 'tvStartDate' and method 'onClick'");
    target.tvStartDate = Utils.castView(view, R.id.tvStartDate, "field 'tvStartDate'", TextView.class);
    view2131362629 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvEndDate, "field 'tvEndDate' and method 'onClick'");
    target.tvEndDate = Utils.castView(view, R.id.tvEndDate, "field 'tvEndDate'", TextView.class);
    view2131362552 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", FrameLayout.class);
    target.tvTotalCost = Utils.findRequiredViewAsType(source, R.id.tvTotalCost, "field 'tvTotalCost'", TextView.class);
    target.tvTotalVolume = Utils.findRequiredViewAsType(source, R.id.tvTotalVolume, "field 'tvTotalVolume'", TextView.class);
    target.rvRefueling = Utils.findRequiredViewAsType(source, R.id.rvRefueling, "field 'rvRefueling'", RecyclerView.class);
    target.tvDays = Utils.findRequiredViewAsType(source, R.id.tvDays, "field 'tvDays'", TextView.class);
    target.pieChart = Utils.findRequiredViewAsType(source, R.id.pieChart, "field 'pieChart'", PieChart.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ReportFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvStartDate = null;
    target.tvEndDate = null;
    target.rootLayout = null;
    target.tvTotalCost = null;
    target.tvTotalVolume = null;
    target.rvRefueling = null;
    target.tvDays = null;
    target.pieChart = null;

    view2131362629.setOnClickListener(null);
    view2131362629 = null;
    view2131362552.setOnClickListener(null);
    view2131362552 = null;
  }
}
