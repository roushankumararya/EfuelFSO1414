// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProfileFragment_ViewBinding implements Unbinder {
  private ProfileFragment target;

  private View view2131362360;

  @UiThread
  public ProfileFragment_ViewBinding(final ProfileFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.relSelectImage, "field 'relSelectImage' and method 'onClick'");
    target.relSelectImage = Utils.castView(view, R.id.relSelectImage, "field 'relSelectImage'", RelativeLayout.class);
    view2131362360 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.etFirstName = Utils.findRequiredViewAsType(source, R.id.etFirstName, "field 'etFirstName'", EditText.class);
    target.etLastName = Utils.findRequiredViewAsType(source, R.id.etLastName, "field 'etLastName'", EditText.class);
    target.spinnerCode = Utils.findRequiredViewAsType(source, R.id.spinnerCode, "field 'spinnerCode'", Spinner.class);
    target.etMobileNumber = Utils.findRequiredViewAsType(source, R.id.etMobileNumber, "field 'etMobileNumber'", EditText.class);
    target.etEmail = Utils.findRequiredViewAsType(source, R.id.etEmail, "field 'etEmail'", EditText.class);
    target.etLoginId = Utils.findRequiredViewAsType(source, R.id.etLoginId, "field 'etLoginId'", EditText.class);
    target.etAddress = Utils.findRequiredViewAsType(source, R.id.etAddress, "field 'etAddress'", EditText.class);
    target.cbAdditionalInfo = Utils.findRequiredViewAsType(source, R.id.cbAdditionalInfo, "field 'cbAdditionalInfo'", CheckBox.class);
    target.etAlternateMobile = Utils.findRequiredViewAsType(source, R.id.etAlternateMobile, "field 'etAlternateMobile'", EditText.class);
    target.ivProfilePic = Utils.findRequiredViewAsType(source, R.id.ivProfilePic, "field 'ivProfilePic'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.relSelectImage = null;
    target.etFirstName = null;
    target.etLastName = null;
    target.spinnerCode = null;
    target.etMobileNumber = null;
    target.etEmail = null;
    target.etLoginId = null;
    target.etAddress = null;
    target.cbAdditionalInfo = null;
    target.etAlternateMobile = null;
    target.ivProfilePic = null;

    view2131362360.setOnClickListener(null);
    view2131362360 = null;
  }
}
