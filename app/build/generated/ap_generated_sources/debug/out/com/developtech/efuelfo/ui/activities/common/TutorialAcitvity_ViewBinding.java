// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.activities.common;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TutorialAcitvity_ViewBinding implements Unbinder {
  private TutorialAcitvity target;

  private View view2131361903;

  @UiThread
  public TutorialAcitvity_ViewBinding(TutorialAcitvity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TutorialAcitvity_ViewBinding(final TutorialAcitvity target, View source) {
    this.target = target;

    View view;
    target.tvHeading = Utils.findRequiredViewAsType(source, R.id.tvHeading, "field 'tvHeading'", CustomTextView.class);
    target.tvDesc = Utils.findRequiredViewAsType(source, R.id.tvDesc, "field 'tvDesc'", CustomTextView.class);
    target.view_pager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'view_pager'", ViewPager.class);
    view = Utils.findRequiredView(source, R.id.btnStart, "field 'btnStart' and method 'onClick'");
    target.btnStart = Utils.castView(view, R.id.btnStart, "field 'btnStart'", Button.class);
    view2131361903 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.ivHint1 = Utils.findRequiredViewAsType(source, R.id.ivHint1, "field 'ivHint1'", ImageView.class);
    target.ivHint2 = Utils.findRequiredViewAsType(source, R.id.ivHint2, "field 'ivHint2'", ImageView.class);
    target.ivHint3 = Utils.findRequiredViewAsType(source, R.id.ivHint3, "field 'ivHint3'", ImageView.class);
    target.ivHint4 = Utils.findRequiredViewAsType(source, R.id.ivHint4, "field 'ivHint4'", ImageView.class);
    target.ivHint5 = Utils.findRequiredViewAsType(source, R.id.ivHint5, "field 'ivHint5'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TutorialAcitvity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvHeading = null;
    target.tvDesc = null;
    target.view_pager = null;
    target.btnStart = null;
    target.ivHint1 = null;
    target.ivHint2 = null;
    target.ivHint3 = null;
    target.ivHint4 = null;
    target.ivHint5 = null;

    view2131361903.setOnClickListener(null);
    view2131361903 = null;
  }
}
