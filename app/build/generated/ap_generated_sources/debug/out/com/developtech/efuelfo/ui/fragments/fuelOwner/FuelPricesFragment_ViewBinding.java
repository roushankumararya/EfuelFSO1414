// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.fuelOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FuelPricesFragment_ViewBinding implements Unbinder {
  private FuelPricesFragment target;

  private View view2131362538;

  private View view2131362640;

  private View view2131362085;

  private View view2131361893;

  private View view2131362126;

  private View view2131362127;

  private View view2131361881;

  @UiThread
  public FuelPricesFragment_ViewBinding(final FuelPricesFragment target, View source) {
    this.target = target;

    View view;
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
    view = Utils.findRequiredView(source, R.id.frameRefresh, "field 'frameRefresh' and method 'onClick'");
    target.frameRefresh = Utils.castView(view, R.id.frameRefresh, "field 'frameRefresh'", FrameLayout.class);
    view2131362085 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvFuelType = Utils.findRequiredViewAsType(source, R.id.tvFuelType, "field 'tvFuelType'", TextView.class);
    target.tvFuelPrice = Utils.findRequiredViewAsType(source, R.id.tvFuelPrice, "field 'tvFuelPrice'", TextView.class);
    target.fabAddSchdule = Utils.findRequiredViewAsType(source, R.id.fabAddSchdule, "field 'fabAddSchdule'", FloatingActionButton.class);
    target.rvFuelPrices = Utils.findRequiredViewAsType(source, R.id.rvFuelPrices, "field 'rvFuelPrices'", RecyclerView.class);
    target.layButton = Utils.findRequiredViewAsType(source, R.id.layButton, "field 'layButton'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btnOk, "field 'btnViewSchedule' and method 'onClick'");
    target.btnViewSchedule = Utils.castView(view, R.id.btnOk, "field 'btnViewSchedule'", Button.class);
    view2131361893 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.appBarLayout = Utils.findRequiredViewAsType(source, R.id.appBarLayout, "field 'appBarLayout'", AppBarLayout.class);
    view = Utils.findRequiredView(source, R.id.ivArrowDate, "method 'onClick'");
    view2131362126 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ivArrowTime, "method 'onClick'");
    view2131362127 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnCreateSchdule, "method 'onClick'");
    view2131361881 = view;
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
    FuelPricesFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvDate = null;
    target.tvTime = null;
    target.frameRefresh = null;
    target.tvFuelType = null;
    target.tvFuelPrice = null;
    target.fabAddSchdule = null;
    target.rvFuelPrices = null;
    target.layButton = null;
    target.btnViewSchedule = null;
    target.appBarLayout = null;

    view2131362538.setOnClickListener(null);
    view2131362538 = null;
    view2131362640.setOnClickListener(null);
    view2131362640 = null;
    view2131362085.setOnClickListener(null);
    view2131362085 = null;
    view2131361893.setOnClickListener(null);
    view2131361893 = null;
    view2131362126.setOnClickListener(null);
    view2131362126 = null;
    view2131362127.setOnClickListener(null);
    view2131362127 = null;
    view2131361881.setOnClickListener(null);
    view2131361881 = null;
  }
}
