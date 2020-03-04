// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.viewHolder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectNameVH_ViewBinding implements Unbinder {
  private SelectNameVH target;

  @UiThread
  public SelectNameVH_ViewBinding(SelectNameVH target, View source) {
    this.target = target;

    target.rbName = Utils.findRequiredViewAsType(source, R.id.rbName, "field 'rbName'", RadioButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectNameVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rbName = null;
  }
}
