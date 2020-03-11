// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.common;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding implements Unbinder {
  private SettingActivity target;

  private View view2131362617;

  private View view2131362638;

  private View view2131362611;

  private View view2131362583;

  private View view2131362544;

  private View view2131362559;

  private View view2131362658;

  private View view2131362523;

  private View view2131362639;

  private View view2131362526;

  private View view2131362581;

  private View view2131362172;

  private View view2131362180;

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingActivity_ViewBinding(final SettingActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tvPrivacyPolicy, "field 'tvPrivacyPolicy' and method 'onClick'");
    target.tvPrivacyPolicy = Utils.castView(view, R.id.tvPrivacyPolicy, "field 'tvPrivacyPolicy'", TextView.class);
    view2131362617 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvTermsCond, "field 'tvTermsCond' and method 'onClick'");
    target.tvTermsCond = Utils.castView(view, R.id.tvTermsCond, "field 'tvTermsCond'", TextView.class);
    view2131362638 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvPaymentAgreement, "field 'tvPaymentAgreement' and method 'onClick'");
    target.tvPaymentAgreement = Utils.castView(view, R.id.tvPaymentAgreement, "field 'tvPaymentAgreement'", TextView.class);
    view2131362611 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvLicenceAgreement, "field 'tvLicenceAgreement' and method 'onClick'");
    target.tvLicenceAgreement = Utils.castView(view, R.id.tvLicenceAgreement, "field 'tvLicenceAgreement'", TextView.class);
    view2131362583 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvDisclaimer, "field 'tvDisclaimer' and method 'onClick'");
    target.tvDisclaimer = Utils.castView(view, R.id.tvDisclaimer, "field 'tvDisclaimer'", TextView.class);
    view2131362544 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvFaq, "field 'tvFaq' and method 'onClick'");
    target.tvFaq = Utils.castView(view, R.id.tvFaq, "field 'tvFaq'", TextView.class);
    view2131362559 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvUserGuide, "field 'tvUserGuide' and method 'onClick'");
    target.tvUserGuide = Utils.castView(view, R.id.tvUserGuide, "field 'tvUserGuide'", TextView.class);
    view2131362658 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvAppWalkThrough, "field 'tvAppWalkThrough' and method 'onClick'");
    target.tvAppWalkThrough = Utils.castView(view, R.id.tvAppWalkThrough, "field 'tvAppWalkThrough'", TextView.class);
    view2131362523 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvThemeSub, "field 'tvThemeSub' and method 'onClick'");
    target.tvThemeSub = Utils.castView(view, R.id.tvThemeSub, "field 'tvThemeSub'", TextView.class);
    view2131362639 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvChangePass, "field 'tvChangePass' and method 'onClick'");
    target.tvChangePass = Utils.castView(view, R.id.tvChangePass, "field 'tvChangePass'", TextView.class);
    view2131362526 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvLanguageSub, "field 'tvLanguageSub' and method 'onClick'");
    target.tvLanguageSub = Utils.castView(view, R.id.tvLanguageSub, "field 'tvLanguageSub'", TextView.class);
    view2131362581 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layLang, "field 'layLang' and method 'onClick'");
    target.layLang = Utils.castView(view, R.id.layLang, "field 'layLang'", LinearLayout.class);
    view2131362172 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layTheme, "field 'layTheme' and method 'onClick'");
    target.layTheme = Utils.castView(view, R.id.layTheme, "field 'layTheme'", LinearLayout.class);
    view2131362180 = view;
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
    SettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvPrivacyPolicy = null;
    target.tvTermsCond = null;
    target.tvPaymentAgreement = null;
    target.tvLicenceAgreement = null;
    target.tvDisclaimer = null;
    target.tvFaq = null;
    target.tvUserGuide = null;
    target.tvAppWalkThrough = null;
    target.tvThemeSub = null;
    target.tvChangePass = null;
    target.tvLanguageSub = null;
    target.layLang = null;
    target.layTheme = null;

    view2131362617.setOnClickListener(null);
    view2131362617 = null;
    view2131362638.setOnClickListener(null);
    view2131362638 = null;
    view2131362611.setOnClickListener(null);
    view2131362611 = null;
    view2131362583.setOnClickListener(null);
    view2131362583 = null;
    view2131362544.setOnClickListener(null);
    view2131362544 = null;
    view2131362559.setOnClickListener(null);
    view2131362559 = null;
    view2131362658.setOnClickListener(null);
    view2131362658 = null;
    view2131362523.setOnClickListener(null);
    view2131362523 = null;
    view2131362639.setOnClickListener(null);
    view2131362639 = null;
    view2131362526.setOnClickListener(null);
    view2131362526 = null;
    view2131362581.setOnClickListener(null);
    view2131362581 = null;
    view2131362172.setOnClickListener(null);
    view2131362172 = null;
    view2131362180.setOnClickListener(null);
    view2131362180 = null;
  }
}
