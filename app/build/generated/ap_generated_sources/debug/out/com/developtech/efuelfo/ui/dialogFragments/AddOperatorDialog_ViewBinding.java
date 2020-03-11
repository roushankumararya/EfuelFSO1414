// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.dialogFragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomButton;
import com.developtech.efuelfo.customs.CustomEditText;
import com.developtech.efuelfo.customs.RoundedImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddOperatorDialog_ViewBinding implements Unbinder {
  private AddOperatorDialog target;

  private View view2131361873;

  private View view2131362153;

  private View view2131362130;

  private View view2131361876;

  private View view2131361899;

  private View view2131361879;

  @UiThread
  public AddOperatorDialog_ViewBinding(final AddOperatorDialog target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btnAddOperator, "field 'btnAddOperator' and method 'onClick'");
    target.btnAddOperator = Utils.castView(view, R.id.btnAddOperator, "field 'btnAddOperator'", CustomButton.class);
    view2131361873 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.btnDeleteOperator = Utils.findRequiredViewAsType(source, R.id.btnDeleteOperator, "field 'btnDeleteOperator'", CustomButton.class);
    target.lytSaveChanges = Utils.findRequiredViewAsType(source, R.id.lytSaveChanges, "field 'lytSaveChanges'", LinearLayout.class);
    target.etFirstName = Utils.findRequiredViewAsType(source, R.id.etFirstName, "field 'etFirstName'", CustomEditText.class);
    target.etLastName = Utils.findRequiredViewAsType(source, R.id.etLastName, "field 'etLastName'", CustomEditText.class);
    target.etMobileNumber = Utils.findRequiredViewAsType(source, R.id.etMobileNumber, "field 'etMobileNumber'", CustomEditText.class);
    target.etEmail = Utils.findRequiredViewAsType(source, R.id.etEmail, "field 'etEmail'", CustomEditText.class);
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.addoperatormenu, "field 'rootLayout'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.ivOperatorProfile, "field 'ivOperatorProfile' and method 'onClick'");
    target.ivOperatorProfile = Utils.castView(view, R.id.ivOperatorProfile, "field 'ivOperatorProfile'", RoundedImageView.class);
    view2131362153 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.spinnerCode = Utils.findRequiredViewAsType(source, R.id.spinnerCode, "field 'spinnerCode'", Spinner.class);
    target.lytOverlayName = Utils.findRequiredView(source, R.id.lytOverlayName, "field 'lytOverlayName'");
    target.lytOverlayMobile = Utils.findRequiredView(source, R.id.lytOverlayMobile, "field 'lytOverlayMobile'");
    target.lytOverlayBottom = Utils.findRequiredView(source, R.id.lytOverlayBottom, "field 'lytOverlayBottom'");
    target.progress = Utils.findRequiredViewAsType(source, R.id.progress_bar, "field 'progress'", FrameLayout.class);
    target.switchManager = Utils.findRequiredViewAsType(source, R.id.switchManager, "field 'switchManager'", Switch.class);
    target.lytManager = Utils.findRequiredViewAsType(source, R.id.lytManager, "field 'lytManager'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.ivDelete, "field 'ivDelete' and method 'onClick'");
    target.ivDelete = Utils.castView(view, R.id.ivDelete, "field 'ivDelete'", ImageView.class);
    view2131362130 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.lnrBlockSection = Utils.findRequiredViewAsType(source, R.id.lnrBlockSection, "field 'lnrBlockSection'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btnBlock, "field 'btnBlock' and method 'onClick'");
    target.btnBlock = Utils.castView(view, R.id.btnBlock, "field 'btnBlock'", Button.class);
    view2131361876 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
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
    AddOperatorDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnAddOperator = null;
    target.btnDeleteOperator = null;
    target.lytSaveChanges = null;
    target.etFirstName = null;
    target.etLastName = null;
    target.etMobileNumber = null;
    target.etEmail = null;
    target.rootLayout = null;
    target.ivOperatorProfile = null;
    target.spinnerCode = null;
    target.lytOverlayName = null;
    target.lytOverlayMobile = null;
    target.lytOverlayBottom = null;
    target.progress = null;
    target.switchManager = null;
    target.lytManager = null;
    target.ivDelete = null;
    target.lnrBlockSection = null;
    target.btnBlock = null;

    view2131361873.setOnClickListener(null);
    view2131361873 = null;
    view2131362153.setOnClickListener(null);
    view2131362153 = null;
    view2131362130.setOnClickListener(null);
    view2131362130 = null;
    view2131361876.setOnClickListener(null);
    view2131361876 = null;
    view2131361899.setOnClickListener(null);
    view2131361899 = null;
    view2131361879.setOnClickListener(null);
    view2131361879 = null;
  }
}
