// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.viewHolder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OperatorVH_ViewBinding implements Unbinder {
  private OperatorVH target;

  @UiThread
  public OperatorVH_ViewBinding(OperatorVH target, View source) {
    this.target = target;

    target.ivPic = Utils.findRequiredViewAsType(source, R.id.ivPic, "field 'ivPic'", ImageView.class);
    target.tvPhoneNumber = Utils.findRequiredViewAsType(source, R.id.tvPhoneNumber, "field 'tvPhoneNumber'", CustomTextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tvName, "field 'tvName'", CustomTextView.class);
    target.lytManager = Utils.findRequiredViewAsType(source, R.id.lytManager, "field 'lytManager'", LinearLayout.class);
    target.viewIsBlocked = Utils.findRequiredView(source, R.id.viewIsBlocked, "field 'viewIsBlocked'");
  }

  @Override
  @CallSuper
  public void unbind() {
    OperatorVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivPic = null;
    target.tvPhoneNumber = null;
    target.tvName = null;
    target.lytManager = null;
    target.viewIsBlocked = null;
  }
}
