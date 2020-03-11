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

public class FilterDriverDialogFragment_ViewBinding implements Unbinder {
  private FilterDriverDialogFragment target;

  private View view2131362627;

  private View view2131362626;

  private View view2131362209;

  private View view2131362226;

  private View view2131362128;

  private View view2131362524;

  private View view2131362527;

  @UiThread
  public FilterDriverDialogFragment_ViewBinding(final FilterDriverDialogFragment target,
      View source) {
    this.target = target;

    View view;
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", CustomTextView.class);
    view = Utils.findRequiredView(source, R.id.tvSelectName, "field 'tvSelectName' and method 'onClick'");
    target.tvSelectName = Utils.castView(view, R.id.tvSelectName, "field 'tvSelectName'", CustomTextView.class);
    view2131362627 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvSelectDate, "field 'tvSelectDate' and method 'onClick'");
    target.tvSelectDate = Utils.castView(view, R.id.tvSelectDate, "field 'tvSelectDate'", CustomTextView.class);
    view2131362626 = view;
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
    view2131362209 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.lytTo, "field 'lytTo' and method 'onClick'");
    target.lytTo = Utils.castView(view, R.id.lytTo, "field 'lytTo'", LinearLayout.class);
    view2131362226 = view;
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
    view2131362128 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvApplyFilter, "method 'onClick'");
    view2131362524 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvClearFilter, "method 'onClick'");
    view2131362527 = view;
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
    FilterDriverDialogFragment target = this.target;
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

    view2131362627.setOnClickListener(null);
    view2131362627 = null;
    view2131362626.setOnClickListener(null);
    view2131362626 = null;
    view2131362209.setOnClickListener(null);
    view2131362209 = null;
    view2131362226.setOnClickListener(null);
    view2131362226 = null;
    view2131362128.setOnClickListener(null);
    view2131362128 = null;
    view2131362524.setOnClickListener(null);
    view2131362524 = null;
    view2131362527.setOnClickListener(null);
    view2131362527 = null;
  }
}
