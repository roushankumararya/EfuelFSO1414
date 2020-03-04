// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.viewHolder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddDriverVH_ViewBinding implements Unbinder {
  private AddDriverVH target;

  @UiThread
  public AddDriverVH_ViewBinding(AddDriverVH target, View source) {
    this.target = target;

    target.tvPhoneNumber = Utils.findRequiredViewAsType(source, R.id.tvPhoneNumber, "field 'tvPhoneNumber'", TextView.class);
    target.tvManager = Utils.findRequiredViewAsType(source, R.id.tvManager, "field 'tvManager'", TextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tvName, "field 'tvName'", TextView.class);
    target.ivPic = Utils.findRequiredViewAsType(source, R.id.ivPic, "field 'ivPic'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddDriverVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvPhoneNumber = null;
    target.tvManager = null;
    target.tvName = null;
    target.ivPic = null;
  }
}
