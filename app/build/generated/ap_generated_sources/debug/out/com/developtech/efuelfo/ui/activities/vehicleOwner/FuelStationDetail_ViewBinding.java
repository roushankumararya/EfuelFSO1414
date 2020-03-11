// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.vehicleOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FuelStationDetail_ViewBinding implements Unbinder {
  private FuelStationDetail target;

  private View view2131362066;

  private View view2131361877;

  private View view2131361897;

  @UiThread
  public FuelStationDetail_ViewBinding(FuelStationDetail target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FuelStationDetail_ViewBinding(final FuelStationDetail target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.favoriteImage, "field 'favoriteImage' and method 'onClick'");
    target.favoriteImage = Utils.castView(view, R.id.favoriteImage, "field 'favoriteImage'", ImageView.class);
    view2131362066 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvStationAddress = Utils.findRequiredViewAsType(source, R.id.tvStationAddress, "field 'tvStationAddress'", TextView.class);
    target.tvPhoneNumber = Utils.findRequiredViewAsType(source, R.id.tvPhoneNumber, "field 'tvPhoneNumber'", TextView.class);
    target.tvStationCode = Utils.findRequiredViewAsType(source, R.id.tvStationCode, "field 'tvStationCode'", TextView.class);
    target.tvDistance = Utils.findRequiredViewAsType(source, R.id.tvDistance, "field 'tvDistance'", TextView.class);
    target.tvStartTime = Utils.findRequiredViewAsType(source, R.id.tvStartTime, "field 'tvStartTime'", TextView.class);
    target.tvEndTime = Utils.findRequiredViewAsType(source, R.id.tvEndTime, "field 'tvEndTime'", TextView.class);
    target.tvFuelPrice = Utils.findRequiredViewAsType(source, R.id.tvFuelPrice, "field 'tvFuelPrice'", TextView.class);
    target.ivQrCode = Utils.findRequiredViewAsType(source, R.id.ivQrCode, "field 'ivQrCode'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.btnBuyNow, "field 'btnBuyNow' and method 'onClick'");
    target.btnBuyNow = Utils.castView(view, R.id.btnBuyNow, "field 'btnBuyNow'", Button.class);
    view2131361877 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnRequestCredit, "field 'btnRequestCredit' and method 'onClick'");
    target.btnRequestCredit = Utils.castView(view, R.id.btnRequestCredit, "field 'btnRequestCredit'", Button.class);
    view2131361897 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tvName, "field 'tvName'", TextView.class);
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", FrameLayout.class);
    target.rvFuelPrices = Utils.findRequiredViewAsType(source, R.id.rvFuelPrices, "field 'rvFuelPrices'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FuelStationDetail target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.favoriteImage = null;
    target.tvStationAddress = null;
    target.tvPhoneNumber = null;
    target.tvStationCode = null;
    target.tvDistance = null;
    target.tvStartTime = null;
    target.tvEndTime = null;
    target.tvFuelPrice = null;
    target.ivQrCode = null;
    target.btnBuyNow = null;
    target.btnRequestCredit = null;
    target.tvName = null;
    target.rootLayout = null;
    target.rvFuelPrices = null;

    view2131362066.setOnClickListener(null);
    view2131362066 = null;
    view2131361877.setOnClickListener(null);
    view2131361877 = null;
    view2131361897.setOnClickListener(null);
    view2131361897 = null;
  }
}
