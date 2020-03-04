// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.common;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131362174;

  private View view2131362177;

  private View view2131362568;

  private View view2131361891;

  private View view2131362644;

  private View view2131361890;

  private View view2131361889;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.layLogin, "field 'layLogin' and method 'onClick'");
    target.layLogin = Utils.castView(view, R.id.layLogin, "field 'layLogin'", LinearLayout.class);
    view2131362174 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.laySocialLogin = Utils.findRequiredViewAsType(source, R.id.laySocialLogin, "field 'laySocialLogin'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.layRegister, "field 'layRegister' and method 'onClick'");
    target.layRegister = Utils.castView(view, R.id.layRegister, "field 'layRegister'", LinearLayout.class);
    view2131362177 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.layoutRegister = Utils.findRequiredViewAsType(source, R.id.layoutRegister, "field 'layoutRegister'", LinearLayout.class);
    target.layoutLogin = Utils.findRequiredViewAsType(source, R.id.layoutLogin, "field 'layoutLogin'", LinearLayout.class);
    target.cbLogIn = Utils.findRequiredViewAsType(source, R.id.cbLogIn, "field 'cbLogIn'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.tvForgetPass, "field 'tvForgetPass' and method 'onClick'");
    target.tvForgetPass = Utils.castView(view, R.id.tvForgetPass, "field 'tvForgetPass'", TextView.class);
    view2131362568 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnLogin, "field 'btnLogin' and method 'onClick'");
    target.btnLogin = Utils.castView(view, R.id.btnLogin, "field 'btnLogin'", Button.class);
    view2131361891 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.viewLogin = Utils.findRequiredView(source, R.id.viewLogin, "field 'viewLogin'");
    target.viewRegister = Utils.findRequiredView(source, R.id.viewRegister, "field 'viewRegister'");
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.etPassword, "field 'etPassword'", EditText.class);
    target.etPasswordSignUp = Utils.findRequiredViewAsType(source, R.id.etPasswordSignUp, "field 'etPasswordSignUp'", EditText.class);
    target.etMobileNumber = Utils.findRequiredViewAsType(source, R.id.etMobileNumber, "field 'etMobileNumber'", EditText.class);
    target.spinnerCode = Utils.findRequiredViewAsType(source, R.id.spinnerCode, "field 'spinnerCode'", Spinner.class);
    target.etEmailAddress = Utils.findRequiredViewAsType(source, R.id.etEmailAddress, "field 'etEmailAddress'", EditText.class);
    target.etLastName = Utils.findRequiredViewAsType(source, R.id.etLastName, "field 'etLastName'", EditText.class);
    target.etFirstName = Utils.findRequiredViewAsType(source, R.id.etFirstName, "field 'etFirstName'", EditText.class);
    target.etLoginId = Utils.findRequiredViewAsType(source, R.id.etLoginId, "field 'etLoginId'", EditText.class);
    target.layForget = Utils.findRequiredViewAsType(source, R.id.layForget, "field 'layForget'", LinearLayout.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.tvOr = Utils.findRequiredViewAsType(source, R.id.tvOr, "field 'tvOr'", TextView.class);
    target.tvLogin = Utils.findRequiredViewAsType(source, R.id.tvLogin, "field 'tvLogin'", TextView.class);
    target.spinnerCode1 = Utils.findRequiredViewAsType(source, R.id.spinnerCode1, "field 'spinnerCode1'", Spinner.class);
    view = Utils.findRequiredView(source, R.id.tvTermsCond, "field 'tvTermsCond' and method 'onClick'");
    target.tvTermsCond = Utils.castView(view, R.id.tvTermsCond, "field 'tvTermsCond'", TextView.class);
    view2131362644 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvTitleBottom = Utils.findRequiredViewAsType(source, R.id.tvTitleBottom, "field 'tvTitleBottom'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnGoogle, "method 'onClick'");
    view2131361890 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnFacebook, "method 'onClick'");
    view2131361889 = view;
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
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.layLogin = null;
    target.laySocialLogin = null;
    target.layRegister = null;
    target.layoutRegister = null;
    target.layoutLogin = null;
    target.cbLogIn = null;
    target.tvForgetPass = null;
    target.btnLogin = null;
    target.viewLogin = null;
    target.viewRegister = null;
    target.etPassword = null;
    target.etPasswordSignUp = null;
    target.etMobileNumber = null;
    target.spinnerCode = null;
    target.etEmailAddress = null;
    target.etLastName = null;
    target.etFirstName = null;
    target.etLoginId = null;
    target.layForget = null;
    target.tvTitle = null;
    target.tvOr = null;
    target.tvLogin = null;
    target.spinnerCode1 = null;
    target.tvTermsCond = null;
    target.tvTitleBottom = null;

    view2131362174.setOnClickListener(null);
    view2131362174 = null;
    view2131362177.setOnClickListener(null);
    view2131362177 = null;
    view2131362568.setOnClickListener(null);
    view2131362568 = null;
    view2131361891.setOnClickListener(null);
    view2131361891 = null;
    view2131362644.setOnClickListener(null);
    view2131362644 = null;
    view2131361890.setOnClickListener(null);
    view2131361890 = null;
    view2131361889.setOnClickListener(null);
    view2131361889 = null;
  }
}
