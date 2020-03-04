// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.dialogFragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddLocationDialog_ViewBinding implements Unbinder {
  private AddLocationDialog target;

  private View view2131361872;

  private View view2131362130;

  @UiThread
  public AddLocationDialog_ViewBinding(final AddLocationDialog target, View source) {
    this.target = target;

    View view;
    target.actvSearch = Utils.findRequiredViewAsType(source, R.id.actvSearch, "field 'actvSearch'", AutoCompleteTextView.class);
    target.tvSearch = Utils.findRequiredViewAsType(source, R.id.tvSearch, "field 'tvSearch'", TextView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    target.ivPin = Utils.findRequiredViewAsType(source, R.id.ivPin, "field 'ivPin'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.btnAddLocation, "method 'onClick'");
    view2131361872 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ivCancel, "method 'onClick'");
    view2131362130 = view;
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
    AddLocationDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.actvSearch = null;
    target.tvSearch = null;
    target.progressBar = null;
    target.ivPin = null;

    view2131361872.setOnClickListener(null);
    view2131361872 = null;
    view2131362130.setOnClickListener(null);
    view2131362130 = null;
  }
}
