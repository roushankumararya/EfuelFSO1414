// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.stationOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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

public class TransactionDetailsActivity_ViewBinding implements Unbinder {
  private TransactionDetailsActivity target;

  private View view2131362085;

  private View view2131362089;

  private View view2131362088;

  private View view2131361888;

  private View view2131361896;

  private View view2131362083;

  private View view2131362146;

  @UiThread
  public TransactionDetailsActivity_ViewBinding(TransactionDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TransactionDetailsActivity_ViewBinding(final TransactionDetailsActivity target,
      View source) {
    this.target = target;

    View view;
    target.tvTransId = Utils.findRequiredViewAsType(source, R.id.tvTransId, "field 'tvTransId'", TextView.class);
    target.tvTransDate = Utils.findRequiredViewAsType(source, R.id.tvTransDate, "field 'tvTransDate'", TextView.class);
    target.tvOwnerName = Utils.findRequiredViewAsType(source, R.id.tvOwnerName, "field 'tvOwnerName'", TextView.class);
    view = Utils.findRequiredView(source, R.id.frameOwnerInfo, "field 'frameOwnerInfo' and method 'onClick'");
    target.frameOwnerInfo = Utils.castView(view, R.id.frameOwnerInfo, "field 'frameOwnerInfo'", FrameLayout.class);
    view2131362085 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvVehicleNumber = Utils.findRequiredViewAsType(source, R.id.tvVehicleNumber, "field 'tvVehicleNumber'", TextView.class);
    view = Utils.findRequiredView(source, R.id.frameVehicleInfo, "field 'frameVehicleInfo' and method 'onClick'");
    target.frameVehicleInfo = Utils.castView(view, R.id.frameVehicleInfo, "field 'frameVehicleInfo'", FrameLayout.class);
    view2131362089 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvFuelStation = Utils.findRequiredViewAsType(source, R.id.tvFuelStation, "field 'tvFuelStation'", TextView.class);
    view = Utils.findRequiredView(source, R.id.frameStationInfo, "field 'frameStationInfo' and method 'onClick'");
    target.frameStationInfo = Utils.castView(view, R.id.frameStationInfo, "field 'frameStationInfo'", FrameLayout.class);
    view2131362088 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvPaymentType = Utils.findRequiredViewAsType(source, R.id.tvPaymentType, "field 'tvPaymentType'", TextView.class);
    target.tvFuelType = Utils.findRequiredViewAsType(source, R.id.tvFuelType, "field 'tvFuelType'", TextView.class);
    target.ivQrCode = Utils.findRequiredViewAsType(source, R.id.ivQrCode, "field 'ivQrCode'", ImageView.class);
    target.tvQty = Utils.findRequiredViewAsType(source, R.id.tvQty, "field 'tvQty'", TextView.class);
    target.tvTotalAmount = Utils.findRequiredViewAsType(source, R.id.tvTotalAmount, "field 'tvTotalAmount'", TextView.class);
    target.tvPaymentStatus = Utils.findRequiredViewAsType(source, R.id.tvPaymentStatus, "field 'tvPaymentStatus'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnEmailInvoice, "field 'btnEmailInvoice' and method 'onClick'");
    target.btnEmailInvoice = Utils.castView(view, R.id.btnEmailInvoice, "field 'btnEmailInvoice'", Button.class);
    view2131361888 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnReceive, "field 'btnReceive' and method 'onClick'");
    target.btnReceive = Utils.castView(view, R.id.btnReceive, "field 'btnReceive'", Button.class);
    view2131361896 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvDriverName = Utils.findRequiredViewAsType(source, R.id.tvDriverName, "field 'tvDriverName'", TextView.class);
    view = Utils.findRequiredView(source, R.id.frameDriverInfo, "field 'frameDriverInfo' and method 'onClick'");
    target.frameDriverInfo = Utils.castView(view, R.id.frameDriverInfo, "field 'frameDriverInfo'", FrameLayout.class);
    view2131362083 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.lytDriver = Utils.findRequiredViewAsType(source, R.id.lytDriver, "field 'lytDriver'", LinearLayout.class);
    target.lytPaymentType = Utils.findRequiredViewAsType(source, R.id.lytPaymentType, "field 'lytPaymentType'", LinearLayout.class);
    target.lytPaymentStatus = Utils.findRequiredViewAsType(source, R.id.lytPaymentStatus, "field 'lytPaymentStatus'", LinearLayout.class);
    target.tvOwnerNameLabel = Utils.findRequiredViewAsType(source, R.id.tvOwnerNameLabel, "field 'tvOwnerNameLabel'", TextView.class);
    target.lytOwner = Utils.findRequiredViewAsType(source, R.id.lytOwner, "field 'lytOwner'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.ivInfoWindow, "field 'ivInfoWindow' and method 'onClick'");
    target.ivInfoWindow = Utils.castView(view, R.id.ivInfoWindow, "field 'ivInfoWindow'", ImageView.class);
    view2131362146 = view;
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
    TransactionDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTransId = null;
    target.tvTransDate = null;
    target.tvOwnerName = null;
    target.frameOwnerInfo = null;
    target.tvVehicleNumber = null;
    target.frameVehicleInfo = null;
    target.tvFuelStation = null;
    target.frameStationInfo = null;
    target.tvPaymentType = null;
    target.tvFuelType = null;
    target.ivQrCode = null;
    target.tvQty = null;
    target.tvTotalAmount = null;
    target.tvPaymentStatus = null;
    target.btnEmailInvoice = null;
    target.btnReceive = null;
    target.tvDriverName = null;
    target.frameDriverInfo = null;
    target.lytDriver = null;
    target.lytPaymentType = null;
    target.lytPaymentStatus = null;
    target.tvOwnerNameLabel = null;
    target.lytOwner = null;
    target.ivInfoWindow = null;

    view2131362085.setOnClickListener(null);
    view2131362085 = null;
    view2131362089.setOnClickListener(null);
    view2131362089 = null;
    view2131362088.setOnClickListener(null);
    view2131362088 = null;
    view2131361888.setOnClickListener(null);
    view2131361888 = null;
    view2131361896.setOnClickListener(null);
    view2131361896 = null;
    view2131362083.setOnClickListener(null);
    view2131362083 = null;
    view2131362146.setOnClickListener(null);
    view2131362146 = null;
  }
}
