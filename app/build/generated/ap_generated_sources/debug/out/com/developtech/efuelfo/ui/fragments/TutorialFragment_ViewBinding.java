// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TutorialFragment_ViewBinding implements Unbinder {
  private TutorialFragment target;

  @UiThread
  public TutorialFragment_ViewBinding(TutorialFragment target, View source) {
    this.target = target;

    target.ivImg = Utils.findRequiredViewAsType(source, R.id.ivImg, "field 'ivImg'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TutorialFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivImg = null;
  }
}
