// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.dialogFragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FilterVehicleDialogFragment_ViewBinding implements Unbinder {
  private FilterVehicleDialogFragment target;

  private View view2131362633;

  private View view2131362632;

  private View view2131362211;

  private View view2131362229;

  private View view2131362129;

  private View view2131362529;

  private View view2131362532;

  @UiThread
  public FilterVehicleDialogFragment_ViewBinding(final FilterVehicleDialogFragment target,
      View source) {
    this.target = target;

    View view;
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", CustomTextView.class);
    view = Utils.findRequiredView(source, R.id.tvSelectName, "field 'tvSelectName' and method 'onClick'");
    target.tvSelectName = Utils.castView(view, R.id.tvSelectName, "field 'tvSelectName'", CustomTextView.class);
    view2131362633 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvSelectDate, "field 'tvSelectDate' and method 'onClick'");
    target.tvSelectDate = Utils.castView(view, R.id.tvSelectDate, "field 'tvSelectDate'", CustomTextView.class);
    view2131362632 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.rvDialog = Utils.findRequiredViewAsType(source, R.id.rvDialog, "field 'rvDialog'", RecyclerView.class);
    target.lytDate = Utils.findRequiredViewAsType(source, R.id.lytDate, "field 'lytDate'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.lytFrom, "field 'lytForm' and method 'onClick'");
    target.lytForm = Utils.castView(view, R.id.lytFrom, "field 'lytForm'", LinearLayout.class);
    view2131362211 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.lytTo, "field 'lytTo' and method 'onClick'");
    target.lytTo = Utils.castView(view, R.id.lytTo, "field 'lytTo'", LinearLayout.class);
    view2131362229 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvFromDate = Utils.findRequiredViewAsType(source, R.id.tvFromDate, "field 'tvFromDate'", CustomTextView.class);
    target.tvToDate = Utils.findRequiredViewAsType(source, R.id.tvToDate, "field 'tvToDate'", CustomTextView.class);
    target.rootLayout = Utils.findRequiredViewAsType(source, R.id.rootLayout, "field 'rootLayout'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.ivBack, "method 'onClick'");
    view2131362129 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvApplyFilter, "method 'onClick'");
    view2131362529 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvClearFilter, "method 'onClick'");
    view2131362532 = view;
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
    FilterVehicleDialogFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.tvSelectName = null;
    target.tvSelectDate = null;
    target.rvDialog = null;
    target.lytDate = null;
    target.lytForm = null;
    target.lytTo = null;
    target.tvFromDate = null;
    target.tvToDate = null;
    target.rootLayout = null;

    view2131362633.setOnClickListener(null);
    view2131362633 = null;
    view2131362632.setOnClickListener(null);
    view2131362632 = null;
    view2131362211.setOnClickListener(null);
    view2131362211 = null;
    view2131362229.setOnClickListener(null);
    view2131362229 = null;
    view2131362129.setOnClickListener(null);
    view2131362129 = null;
    view2131362529.setOnClickListener(null);
    view2131362529 = null;
    view2131362532.setOnClickListener(null);
    view2131362532 = null;
  }
}
