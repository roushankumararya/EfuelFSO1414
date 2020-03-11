// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.stationOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FuelPriceActivity_ViewBinding implements Unbinder {
  private FuelPriceActivity target;

  private View view2131361893;

  private View view2131362203;

  private View view2131362225;

  private View view2131361905;

  private View view2131361879;

  private View view2131362062;

  @UiThread
  public FuelPriceActivity_ViewBinding(FuelPriceActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FuelPriceActivity_ViewBinding(final FuelPriceActivity target, View source) {
    this.target = target;

    View view;
    target.tvDate = Utils.findRequiredViewAsType(source, R.id.tvDate, "field 'tvDate'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tvTime, "field 'tvTime'", TextView.class);
    target.frameRefresh = Utils.findRequiredViewAsType(source, R.id.frameRefresh, "field 'frameRefresh'", FrameLayout.class);
    target.tvFuelType = Utils.findRequiredViewAsType(source, R.id.tvFuelType, "field 'tvFuelType'", TextView.class);
    target.tvFuelPrice = Utils.findRequiredViewAsType(source, R.id.tvFuelPrice, "field 'tvFuelPrice'", TextView.class);
    target.rvFuelPrices = Utils.findRequiredViewAsType(source, R.id.rvFuelPrices, "field 'rvFuelPrices'", RecyclerView.class);
    target.layButton = Utils.findRequiredViewAsType(source, R.id.layButton, "field 'layButton'", LinearLayout.class);
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", FrameLayout.class);
    target.ivArrowDate = Utils.findRequiredViewAsType(source, R.id.ivArrowDate, "field 'ivArrowDate'", ImageView.class);
    target.ivArrowTime = Utils.findRequiredViewAsType(source, R.id.ivArrowTime, "field 'ivArrowTime'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.btnOk, "field 'btnOk' and method 'onClick'");
    target.btnOk = Utils.castView(view, R.id.btnOk, "field 'btnOk'", Button.class);
    view2131361893 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.lytDate, "field 'lytDate' and method 'onClick'");
    target.lytDate = Utils.castView(view, R.id.lytDate, "field 'lytDate'", LinearLayout.class);
    view2131362203 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.lytTime, "field 'lytTime' and method 'onClick'");
    target.lytTime = Utils.castView(view, R.id.lytTime, "field 'lytTime'", LinearLayout.class);
    view2131362225 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.btnCreateSchedule = Utils.findRequiredViewAsType(source, R.id.btnCreateSchdule, "field 'btnCreateSchedule'", Button.class);
    view = Utils.findRequiredView(source, R.id.btnUpdate, "field 'btnUpdate' and method 'onClick'");
    target.btnUpdate = Utils.castView(view, R.id.btnUpdate, "field 'btnUpdate'", Button.class);
    view2131361905 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnCancel, "field 'btnCancel' and method 'onClick'");
    target.btnCancel = Utils.castView(view, R.id.btnCancel, "field 'btnCancel'", Button.class);
    view2131361879 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.fabAddSchdule, "field 'fabAddSchedule' and method 'onClick'");
    target.fabAddSchedule = Utils.castView(view, R.id.fabAddSchdule, "field 'fabAddSchedule'", FloatingActionButton.class);
    view2131362062 = view;
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
    FuelPriceActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvDate = null;
    target.tvTime = null;
    target.frameRefresh = null;
    target.tvFuelType = null;
    target.tvFuelPrice = null;
    target.rvFuelPrices = null;
    target.layButton = null;
    target.rootLayout = null;
    target.ivArrowDate = null;
    target.ivArrowTime = null;
    target.btnOk = null;
    target.lytDate = null;
    target.lytTime = null;
    target.btnCreateSchedule = null;
    target.btnUpdate = null;
    target.btnCancel = null;
    target.fabAddSchedule = null;

    view2131361893.setOnClickListener(null);
    view2131361893 = null;
    view2131362203.setOnClickListener(null);
    view2131362203 = null;
    view2131362225.setOnClickListener(null);
    view2131362225 = null;
    view2131361905.setOnClickListener(null);
    view2131361905 = null;
    view2131361879.setOnClickListener(null);
    view2131361879 = null;
    view2131362062.setOnClickListener(null);
    view2131362062 = null;
  }
}
