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

  private View view2131362623;

  private View view2131362644;

  private View view2131362617;

  private View view2131362588;

  private View view2131362549;

  private View view2131362564;

  private View view2131362664;

  private View view2131362528;

  private View view2131362645;

  private View view2131362531;

  private View view2131362586;

  private View view2131362173;

  private View view2131362181;

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
    view2131362623 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvTermsCond, "field 'tvTermsCond' and method 'onClick'");
    target.tvTermsCond = Utils.castView(view, R.id.tvTermsCond, "field 'tvTermsCond'", TextView.class);
    view2131362644 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvPaymentAgreement, "field 'tvPaymentAgreement' and method 'onClick'");
    target.tvPaymentAgreement = Utils.castView(view, R.id.tvPaymentAgreement, "field 'tvPaymentAgreement'", TextView.class);
    view2131362617 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvLicenceAgreement, "field 'tvLicenceAgreement' and method 'onClick'");
    target.tvLicenceAgreement = Utils.castView(view, R.id.tvLicenceAgreement, "field 'tvLicenceAgreement'", TextView.class);
    view2131362588 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvDisclaimer, "field 'tvDisclaimer' and method 'onClick'");
    target.tvDisclaimer = Utils.castView(view, R.id.tvDisclaimer, "field 'tvDisclaimer'", TextView.class);
    view2131362549 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvFaq, "field 'tvFaq' and method 'onClick'");
    target.tvFaq = Utils.castView(view, R.id.tvFaq, "field 'tvFaq'", TextView.class);
    view2131362564 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvUserGuide, "field 'tvUserGuide' and method 'onClick'");
    target.tvUserGuide = Utils.castView(view, R.id.tvUserGuide, "field 'tvUserGuide'", TextView.class);
    view2131362664 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvAppWalkThrough, "field 'tvAppWalkThrough' and method 'onClick'");
    target.tvAppWalkThrough = Utils.castView(view, R.id.tvAppWalkThrough, "field 'tvAppWalkThrough'", TextView.class);
    view2131362528 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvThemeSub, "field 'tvThemeSub' and method 'onClick'");
    target.tvThemeSub = Utils.castView(view, R.id.tvThemeSub, "field 'tvThemeSub'", TextView.class);
    view2131362645 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvChangePass, "field 'tvChangePass' and method 'onClick'");
    target.tvChangePass = Utils.castView(view, R.id.tvChangePass, "field 'tvChangePass'", TextView.class);
    view2131362531 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvLanguageSub, "field 'tvLanguageSub' and method 'onClick'");
    target.tvLanguageSub = Utils.castView(view, R.id.tvLanguageSub, "field 'tvLanguageSub'", TextView.class);
    view2131362586 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layLang, "field 'layLang' and method 'onClick'");
    target.layLang = Utils.castView(view, R.id.layLang, "field 'layLang'", LinearLayout.class);
    view2131362173 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layTheme, "field 'layTheme' and method 'onClick'");
    target.layTheme = Utils.castView(view, R.id.layTheme, "field 'layTheme'", LinearLayout.class);
    view2131362181 = view;
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

    view2131362623.setOnClickListener(null);
    view2131362623 = null;
    view2131362644.setOnClickListener(null);
    view2131362644 = null;
    view2131362617.setOnClickListener(null);
    view2131362617 = null;
    view2131362588.setOnClickListener(null);
    view2131362588 = null;
    view2131362549.setOnClickListener(null);
    view2131362549 = null;
    view2131362564.setOnClickListener(null);
    view2131362564 = null;
    view2131362664.setOnClickListener(null);
    view2131362664 = null;
    view2131362528.setOnClickListener(null);
    view2131362528 = null;
    view2131362645.setOnClickListener(null);
    view2131362645 = null;
    view2131362531.setOnClickListener(null);
    view2131362531 = null;
    view2131362586.setOnClickListener(null);
    view2131362586 = null;
    view2131362173.setOnClickListener(null);
    view2131362173 = null;
    view2131362181.setOnClickListener(null);
    view2131362181 = null;
  }
}
