// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.dialogFragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddDriverDialog_ViewBinding implements Unbinder {
  private AddDriverDialog target;

  private View view2131362157;

  private View view2131361883;

  private View view2131361870;

  private View view2131361899;

  private View view2131361879;

  @UiThread
  public AddDriverDialog_ViewBinding(final AddDriverDialog target, View source) {
    this.target = target;

    View view;
    target.tvEdit = Utils.findRequiredViewAsType(source, R.id.tvEdit, "field 'tvEdit'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ivProfilePic, "field 'ivProfilePic' and method 'onClick'");
    target.ivProfilePic = Utils.castView(view, R.id.ivProfilePic, "field 'ivProfilePic'", ImageView.class);
    view2131362157 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.etAlternateMobile = Utils.findRequiredViewAsType(source, R.id.etAlternateMobile, "field 'etAlternateMobile'", EditText.class);
    target.etEmail = Utils.findRequiredViewAsType(source, R.id.etEmail, "field 'etEmail'", EditText.class);
    target.etDriverMobile = Utils.findRequiredViewAsType(source, R.id.etDriverMobile, "field 'etDriverMobile'", EditText.class);
    target.spinnerCode = Utils.findRequiredViewAsType(source, R.id.spinnerCode, "field 'spinnerCode'", Spinner.class);
    target.etFirstName = Utils.findRequiredViewAsType(source, R.id.etFirstName, "field 'etFirstName'", EditText.class);
    target.etLastName = Utils.findRequiredViewAsType(source, R.id.etLastName, "field 'etLastName'", EditText.class);
    target.lytSaveChanges = Utils.findRequiredViewAsType(source, R.id.lytSaveChanges, "field 'lytSaveChanges'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btnDelete, "field 'btnDelete' and method 'onClick'");
    target.btnDelete = Utils.castView(view, R.id.btnDelete, "field 'btnDelete'", CustomButton.class);
    view2131361883 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnAddDriver, "field 'btnAddDriver' and method 'onClick'");
    target.btnAddDriver = Utils.castView(view, R.id.btnAddDriver, "field 'btnAddDriver'", CustomButton.class);
    view2131361870 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.spinnerGender = Utils.findRequiredViewAsType(source, R.id.spinnerGender, "field 'spinnerGender'", Spinner.class);
    target.spinnerVehicleNumber = Utils.findRequiredViewAsType(source, R.id.spinnerVehicleNo, "field 'spinnerVehicleNumber'", Spinner.class);
    view = Utils.findRequiredView(source, R.id.btnSaveChanges, "method 'onClick'");
    view2131361899 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnCancel, "method 'onClick'");
    view2131361879 = view;
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
    AddDriverDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvEdit = null;
    target.ivProfilePic = null;
    target.etAlternateMobile = null;
    target.etEmail = null;
    target.etDriverMobile = null;
    target.spinnerCode = null;
    target.etFirstName = null;
    target.etLastName = null;
    target.lytSaveChanges = null;
    target.btnDelete = null;
    target.btnAddDriver = null;
    target.spinnerGender = null;
    target.spinnerVehicleNumber = null;

    view2131362157.setOnClickListener(null);
    view2131362157 = null;
    view2131361883.setOnClickListener(null);
    view2131361883 = null;
    view2131361870.setOnClickListener(null);
    view2131361870 = null;
    view2131361899.setOnClickListener(null);
    view2131361899 = null;
    view2131361879.setOnClickListener(null);
    view2131361879 = null;
  }
}
