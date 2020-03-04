// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.dialogFragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddTankDialog_ViewBinding implements Unbinder {
  private AddTankDialog target;

  private View view2131361874;

  private View view2131361885;

  private View view2131361899;

  private View view2131361879;

  @UiThread
  public AddTankDialog_ViewBinding(final AddTankDialog target, View source) {
    this.target = target;

    View view;
    target.spinnerFuelType = Utils.findRequiredViewAsType(source, R.id.spinnerFuelType, "field 'spinnerFuelType'", Spinner.class);
    target.etTankName = Utils.findRequiredViewAsType(source, R.id.etTankName, "field 'etTankName'", EditText.class);
    target.etFuelCapacity = Utils.findRequiredViewAsType(source, R.id.etFuelCapacity, "field 'etFuelCapacity'", EditText.class);
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", LinearLayout.class);
    target.etDensity = Utils.findRequiredViewAsType(source, R.id.etDensity, "field 'etDensity'", EditText.class);
    target.etTemperature = Utils.findRequiredViewAsType(source, R.id.etTemperature, "field 'etTemperature'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btnAddTank, "field 'btnAddTank' and method 'onClick'");
    target.btnAddTank = Utils.castView(view, R.id.btnAddTank, "field 'btnAddTank'", Button.class);
    view2131361874 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnDeleteTank, "field 'btnDeleteTank' and method 'onClick'");
    target.btnDeleteTank = Utils.castView(view, R.id.btnDeleteTank, "field 'btnDeleteTank'", Button.class);
    view2131361885 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.lytSaveChanges = Utils.findRequiredViewAsType(source, R.id.lytSaveChanges, "field 'lytSaveChanges'", LinearLayout.class);
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
    AddTankDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spinnerFuelType = null;
    target.etTankName = null;
    target.etFuelCapacity = null;
    target.rootLayout = null;
    target.etDensity = null;
    target.etTemperature = null;
    target.btnAddTank = null;
    target.btnDeleteTank = null;
    target.lytSaveChanges = null;

    view2131361874.setOnClickListener(null);
    view2131361874 = null;
    view2131361885.setOnClickListener(null);
    view2131361885 = null;
    view2131361899.setOnClickListener(null);
    view2131361899 = null;
    view2131361879.setOnClickListener(null);
    view2131361879 = null;
  }
}
