// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.common;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OtpActivity_ViewBinding implements Unbinder {
  private OtpActivity target;

  private View view2131361906;

  private View view2131362175;

  private View view2131362623;

  @UiThread
  public OtpActivity_ViewBinding(OtpActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OtpActivity_ViewBinding(final OtpActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btnVerify, "field 'btnVerify' and method 'onClick'");
    target.btnVerify = Utils.castView(view, R.id.btnVerify, "field 'btnVerify'", Button.class);
    view2131361906 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.etOtp = Utils.findRequiredViewAsType(source, R.id.etOtp, "field 'etOtp'", EditText.class);
    target.tv5 = Utils.findRequiredViewAsType(source, R.id.tv5, "field 'tv5'", TextView.class);
    target.tv4 = Utils.findRequiredViewAsType(source, R.id.tv4, "field 'tv4'", TextView.class);
    target.tv3 = Utils.findRequiredViewAsType(source, R.id.tv3, "field 'tv3'", TextView.class);
    target.tv2 = Utils.findRequiredViewAsType(source, R.id.tv2, "field 'tv2'", TextView.class);
    target.tv1 = Utils.findRequiredViewAsType(source, R.id.tv1, "field 'tv1'", TextView.class);
    target.tv = Utils.findRequiredViewAsType(source, R.id.tv, "field 'tv'", TextView.class);
    target.view = Utils.findRequiredView(source, R.id.view, "field 'view'");
    target.view1 = Utils.findRequiredView(source, R.id.view1, "field 'view1'");
    target.view2 = Utils.findRequiredView(source, R.id.view2, "field 'view2'");
    target.view3 = Utils.findRequiredView(source, R.id.view3, "field 'view3'");
    target.view4 = Utils.findRequiredView(source, R.id.view4, "field 'view4'");
    target.view5 = Utils.findRequiredView(source, R.id.view5, "field 'view5'");
    view = Utils.findRequiredView(source, R.id.layOtp, "field 'layOtp' and method 'onClick'");
    target.layOtp = Utils.castView(view, R.id.layOtp, "field 'layOtp'", LinearLayout.class);
    view2131362175 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvMobileNumber = Utils.findRequiredViewAsType(source, R.id.tvMobileNumber, "field 'tvMobileNumber'", CustomTextView.class);
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootlayout, "field 'rootLayout'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.tvResend, "method 'onClick'");
    view2131362623 = view;
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
    OtpActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnVerify = null;
    target.etOtp = null;
    target.tv5 = null;
    target.tv4 = null;
    target.tv3 = null;
    target.tv2 = null;
    target.tv1 = null;
    target.tv = null;
    target.view = null;
    target.view1 = null;
    target.view2 = null;
    target.view3 = null;
    target.view4 = null;
    target.view5 = null;
    target.layOtp = null;
    target.tvMobileNumber = null;
    target.rootLayout = null;

    view2131361906.setOnClickListener(null);
    view2131361906 = null;
    view2131362175.setOnClickListener(null);
    view2131362175 = null;
    view2131362623.setOnClickListener(null);
    view2131362623 = null;
  }
}
