// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.dialogFragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomTextView;
import com.developtech.efuelfo.customs.RoundedImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddVehicleDialog_ViewBinding implements Unbinder {
  private AddVehicleDialog target;

  private View view2131362664;

  private View view2131361875;

  private View view2131362162;

  private View view2131361899;

  private View view2131361879;

  private View view2131361883;

  @UiThread
  public AddVehicleDialog_ViewBinding(final AddVehicleDialog target, View source) {
    this.target = target;

    View view;
    target.spinnerVehicleMake = Utils.findRequiredViewAsType(source, R.id.spinnerVehicleMake, "field 'spinnerVehicleMake'", Spinner.class);
    view = Utils.findRequiredView(source, R.id.tvVehicleModel, "field 'tvVehicleModel' and method 'onClick'");
    target.tvVehicleModel = Utils.castView(view, R.id.tvVehicleModel, "field 'tvVehicleModel'", TextView.class);
    view2131362664 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.etVehicleColor = Utils.findRequiredViewAsType(source, R.id.etVehicleColor, "field 'etVehicleColor'", EditText.class);
    target.etAliasName = Utils.findRequiredViewAsType(source, R.id.etAliasName, "field 'etAliasName'", EditText.class);
    target.etVehicleNumber = Utils.findRequiredViewAsType(source, R.id.etVehicleNumber, "field 'etVehicleNumber'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btnAddVehicle, "field 'btnAddVehicle' and method 'onClick'");
    target.btnAddVehicle = Utils.castView(view, R.id.btnAddVehicle, "field 'btnAddVehicle'", Button.class);
    view2131361875 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.etFuelCapacity = Utils.findRequiredViewAsType(source, R.id.etFuelCapacity, "field 'etFuelCapacity'", EditText.class);
    target.lytSaveChanges = Utils.findRequiredViewAsType(source, R.id.lytSaveChanges, "field 'lytSaveChanges'", LinearLayout.class);
    target.lytDeleteBuy = Utils.findRequiredViewAsType(source, R.id.lytDeleteBuy, "field 'lytDeleteBuy'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.ivVehicle, "field 'ivVehicle' and method 'onClick'");
    target.ivVehicle = Utils.castView(view, R.id.ivVehicle, "field 'ivVehicle'", RoundedImageView.class);
    view2131362162 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.spinnerDriver = Utils.findRequiredViewAsType(source, R.id.spinnerDriver, "field 'spinnerDriver'", Spinner.class);
    target.spinnerVehicleCategory = Utils.findRequiredViewAsType(source, R.id.spinnerVehicleCategory, "field 'spinnerVehicleCategory'", Spinner.class);
    target.radioGroup = Utils.findRequiredViewAsType(source, R.id.rg, "field 'radioGroup'", RadioGroup.class);
    target.rbPrivate = Utils.findRequiredViewAsType(source, R.id.rbPrivate, "field 'rbPrivate'", RadioButton.class);
    target.rbCommercial = Utils.findRequiredViewAsType(source, R.id.rbCommercial, "field 'rbCommercial'", RadioButton.class);
    target.switchSelfDriven = Utils.findRequiredViewAsType(source, R.id.switchSelfDriven, "field 'switchSelfDriven'", Switch.class);
    target.lytDriverName = Utils.findRequiredViewAsType(source, R.id.lytDriverName, "field 'lytDriverName'", LinearLayout.class);
    target.tvFuelType = Utils.findRequiredViewAsType(source, R.id.tvFuelType, "field 'tvFuelType'", CustomTextView.class);
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
    view = Utils.findRequiredView(source, R.id.btnDelete, "method 'onClick'");
    view2131361883 = view;
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
    AddVehicleDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spinnerVehicleMake = null;
    target.tvVehicleModel = null;
    target.etVehicleColor = null;
    target.etAliasName = null;
    target.etVehicleNumber = null;
    target.btnAddVehicle = null;
    target.etFuelCapacity = null;
    target.lytSaveChanges = null;
    target.lytDeleteBuy = null;
    target.ivVehicle = null;
    target.spinnerDriver = null;
    target.spinnerVehicleCategory = null;
    target.radioGroup = null;
    target.rbPrivate = null;
    target.rbCommercial = null;
    target.switchSelfDriven = null;
    target.lytDriverName = null;
    target.tvFuelType = null;

    view2131362664.setOnClickListener(null);
    view2131362664 = null;
    view2131361875.setOnClickListener(null);
    view2131361875 = null;
    view2131362162.setOnClickListener(null);
    view2131362162 = null;
    view2131361899.setOnClickListener(null);
    view2131361899 = null;
    view2131361879.setOnClickListener(null);
    view2131361879 = null;
    view2131361883.setOnClickListener(null);
    view2131361883 = null;
  }
}
