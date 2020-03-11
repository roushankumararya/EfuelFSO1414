// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.vehicleOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomEditText;
import com.developtech.efuelfo.customs.CustomTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserProfileActivity_ViewBinding implements Unbinder {
  private UserProfileActivity target;

  private View view2131362156;

  private View view2131362133;

  @UiThread
  public UserProfileActivity_ViewBinding(UserProfileActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UserProfileActivity_ViewBinding(final UserProfileActivity target, View source) {
    this.target = target;

    View view;
    target.tvFirstNameLabel = Utils.findRequiredViewAsType(source, R.id.tvFirstNameLabel, "field 'tvFirstNameLabel'", CustomTextView.class);
    target.tvLastNameLabel = Utils.findRequiredViewAsType(source, R.id.tvLastNameLabel, "field 'tvLastNameLabel'", CustomTextView.class);
    target.tvMobileLabel = Utils.findRequiredViewAsType(source, R.id.tvMobileLabel, "field 'tvMobileLabel'", CustomTextView.class);
    target.tvEmailLabel = Utils.findRequiredViewAsType(source, R.id.tvEmailLabel, "field 'tvEmailLabel'", CustomTextView.class);
    target.tvCountryLabel = Utils.findRequiredViewAsType(source, R.id.tvCountryLabel, "field 'tvCountryLabel'", CustomTextView.class);
    target.tvLanguageLabel = Utils.findRequiredViewAsType(source, R.id.tvLanguageLabel, "field 'tvLanguageLabel'", CustomTextView.class);
    target.etFirstName = Utils.findRequiredViewAsType(source, R.id.etFirstName, "field 'etFirstName'", CustomEditText.class);
    target.etLastName = Utils.findRequiredViewAsType(source, R.id.etLastName, "field 'etLastName'", CustomEditText.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.etPhone, "field 'etPhone'", CustomEditText.class);
    target.etEmail = Utils.findRequiredViewAsType(source, R.id.etEmail, "field 'etEmail'", CustomEditText.class);
    target.spCountryCode = Utils.findRequiredViewAsType(source, R.id.spCountryCode, "field 'spCountryCode'", Spinner.class);
    target.spinnerCountry = Utils.findRequiredViewAsType(source, R.id.spCountry, "field 'spinnerCountry'", Spinner.class);
    target.spinnerLanguage = Utils.findRequiredViewAsType(source, R.id.spLanguage, "field 'spinnerLanguage'", Spinner.class);
    target.cbAdditionalInfo = Utils.findRequiredViewAsType(source, R.id.cbAdditionalInfo, "field 'cbAdditionalInfo'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.ivProfilePic, "field 'ivProfilePic' and method 'onClick'");
    target.ivProfilePic = Utils.castView(view, R.id.ivProfilePic, "field 'ivProfilePic'", ImageView.class);
    view2131362156 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.nestedScrollView = Utils.findRequiredViewAsType(source, R.id.nestedScrollView, "field 'nestedScrollView'", NestedScrollView.class);
    target.tvAlternateMobile = Utils.findRequiredViewAsType(source, R.id.tvAlternateMobile, "field 'tvAlternateMobile'", CustomTextView.class);
    target.etAlternateMobile = Utils.findRequiredViewAsType(source, R.id.etAlternateMobile, "field 'etAlternateMobile'", CustomEditText.class);
    target.viewToDiable = Utils.findRequiredView(source, R.id.viewToDiable, "field 'viewToDiable'");
    view = Utils.findRequiredView(source, R.id.ivEdit, "field 'ivEdit' and method 'onClick'");
    target.ivEdit = Utils.castView(view, R.id.ivEdit, "field 'ivEdit'", ImageView.class);
    view2131362133 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvLabel = Utils.findRequiredViewAsType(source, R.id.tvLabel, "field 'tvLabel'", TextView.class);
    target.tvValue = Utils.findRequiredViewAsType(source, R.id.tvValue, "field 'tvValue'", TextView.class);
    target.tvFuelStationIdLabel = Utils.findRequiredViewAsType(source, R.id.tvFuelStationIdLabel, "field 'tvFuelStationIdLabel'", TextView.class);
    target.etFuelStationId = Utils.findRequiredViewAsType(source, R.id.etFuelStationId, "field 'etFuelStationId'", EditText.class);
    target.tvAddressLabel = Utils.findRequiredViewAsType(source, R.id.tvAddressLabel, "field 'tvAddressLabel'", TextView.class);
    target.etAddress = Utils.findRequiredViewAsType(source, R.id.etAddress, "field 'etAddress'", EditText.class);
    target.lytPinCode = Utils.findRequiredViewAsType(source, R.id.lytPinCode, "field 'lytPinCode'", LinearLayout.class);
    target.tvPinCodeLabel = Utils.findRequiredViewAsType(source, R.id.tvPinCodeLabel, "field 'tvPinCodeLabel'", TextView.class);
    target.etPinCode = Utils.findRequiredViewAsType(source, R.id.etPinCode, "field 'etPinCode'", EditText.class);
    target.tvLoginId = Utils.findRequiredViewAsType(source, R.id.tvLoginId, "field 'tvLoginId'", TextView.class);
    target.etLoginId = Utils.findRequiredViewAsType(source, R.id.etLoginId, "field 'etLoginId'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UserProfileActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvFirstNameLabel = null;
    target.tvLastNameLabel = null;
    target.tvMobileLabel = null;
    target.tvEmailLabel = null;
    target.tvCountryLabel = null;
    target.tvLanguageLabel = null;
    target.etFirstName = null;
    target.etLastName = null;
    target.etPhone = null;
    target.etEmail = null;
    target.spCountryCode = null;
    target.spinnerCountry = null;
    target.spinnerLanguage = null;
    target.cbAdditionalInfo = null;
    target.ivProfilePic = null;
    target.nestedScrollView = null;
    target.tvAlternateMobile = null;
    target.etAlternateMobile = null;
    target.viewToDiable = null;
    target.ivEdit = null;
    target.tvLabel = null;
    target.tvValue = null;
    target.tvFuelStationIdLabel = null;
    target.etFuelStationId = null;
    target.tvAddressLabel = null;
    target.etAddress = null;
    target.lytPinCode = null;
    target.tvPinCodeLabel = null;
    target.etPinCode = null;
    target.tvLoginId = null;
    target.etLoginId = null;

    view2131362156.setOnClickListener(null);
    view2131362156 = null;
    view2131362133.setOnClickListener(null);
    view2131362133 = null;
  }
}
