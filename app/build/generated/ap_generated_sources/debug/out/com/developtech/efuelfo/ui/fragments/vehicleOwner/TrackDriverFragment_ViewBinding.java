// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.vehicleOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TrackDriverFragment_ViewBinding implements Unbinder {
  private TrackDriverFragment target;

  private View view2131362149;

  @UiThread
  public TrackDriverFragment_ViewBinding(final TrackDriverFragment target, View source) {
    this.target = target;

    View view;
    target.recycleTrackDriver = Utils.findRequiredViewAsType(source, R.id.recycleTrackDriver, "field 'recycleTrackDriver'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.ivMapList, "field 'ivMapList' and method 'onClick'");
    target.ivMapList = Utils.castView(view, R.id.ivMapList, "field 'ivMapList'", ImageView.class);
    view2131362149 = view;
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
    TrackDriverFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recycleTrackDriver = null;
    target.ivMapList = null;

    view2131362149.setOnClickListener(null);
    view2131362149 = null;
  }
}
