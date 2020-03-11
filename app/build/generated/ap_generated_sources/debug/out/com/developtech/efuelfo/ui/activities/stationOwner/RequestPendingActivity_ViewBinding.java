// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.stationOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RequestPendingActivity_ViewBinding implements Unbinder {
  private RequestPendingActivity target;

  private View view2131361904;

  private View view2131362618;

  private View view2131361868;

  private View view2131361882;

  @UiThread
  public RequestPendingActivity_ViewBinding(RequestPendingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RequestPendingActivity_ViewBinding(final RequestPendingActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btnTerminate, "field 'btnTerminate' and method 'onClick'");
    target.btnTerminate = Utils.castView(view, R.id.btnTerminate, "field 'btnTerminate'", CustomButton.class);
    view2131361904 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.cbTerms = Utils.findRequiredViewAsType(source, R.id.cbTerms, "field 'cbTerms'", CheckBox.class);
    target.lytSaveChanges = Utils.findRequiredViewAsType(source, R.id.lytSaveChanges, "field 'lytSaveChanges'", LinearLayout.class);
    target.layAmount = Utils.findRequiredViewAsType(source, R.id.layAmount, "field 'layAmount'", LinearLayout.class);
    target.tvFuelStationName = Utils.findRequiredViewAsType(source, R.id.tvFuelStationName, "field 'tvFuelStationName'", TextView.class);
    target.tvCountry = Utils.findRequiredViewAsType(source, R.id.tvCountry, "field 'tvCountry'", TextView.class);
    target.tvPaymentStatus = Utils.findRequiredViewAsType(source, R.id.tvPaymentStatus, "field 'tvPaymentStatus'", TextView.class);
    target.tvDays = Utils.findRequiredViewAsType(source, R.id.tvDays, "field 'tvDays'", TextView.class);
    target.tvAmount = Utils.findRequiredViewAsType(source, R.id.tvAmount, "field 'tvAmount'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tvProfileInfo, "field 'tvProfileInfo' and method 'onClick'");
    target.tvProfileInfo = Utils.castView(view, R.id.tvProfileInfo, "field 'tvProfileInfo'", TextView.class);
    view2131362618 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", FrameLayout.class);
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
    RequestPendingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnTerminate = null;
    target.cbTerms = null;
    target.lytSaveChanges = null;
    target.layAmount = null;
    target.tvFuelStationName = null;
    target.tvCountry = null;
    target.tvPaymentStatus = null;
    target.tvDays = null;
    target.tvAmount = null;
    target.tvProfileInfo = null;
    target.rootLayout = null;

    view2131361904.setOnClickListener(null);
    view2131361904 = null;
    view2131362618.setOnClickListener(null);
    view2131362618 = null;
    view2131361868.setOnClickListener(null);
    view2131361868 = null;
    view2131361882.setOnClickListener(null);
    view2131361882 = null;
  }
}
