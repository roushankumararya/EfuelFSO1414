// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.vehicleOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NearByFuelStationFragment_ViewBinding implements Unbinder {
  private NearByFuelStationFragment target;

  private View view2131362082;

  private View view2131362087;

  private View view2131362149;

  @UiThread
  public NearByFuelStationFragment_ViewBinding(final NearByFuelStationFragment target,
      View source) {
    this.target = target;

    View view;
    target.recycleStationList = Utils.findRequiredViewAsType(source, R.id.recycleStationList, "field 'recycleStationList'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.frameCancel, "field 'frameCancel' and method 'onCLick'");
    target.frameCancel = Utils.castView(view, R.id.frameCancel, "field 'frameCancel'", FrameLayout.class);
    view2131362082 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCLick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.frameSearch, "field 'frameSearch' and method 'onCLick'");
    target.frameSearch = Utils.castView(view, R.id.frameSearch, "field 'frameSearch'", FrameLayout.class);
    view2131362087 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCLick(p0);
      }
    });
    target.etSearch = Utils.findRequiredViewAsType(source, R.id.etSearch, "field 'etSearch'", EditText.class);
    view = Utils.findRequiredView(source, R.id.ivMap, "field 'ivMap' and method 'onCLick'");
    target.ivMap = Utils.castView(view, R.id.ivMap, "field 'ivMap'", ImageView.class);
    view2131362149 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCLick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    NearByFuelStationFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recycleStationList = null;
    target.frameCancel = null;
    target.frameSearch = null;
    target.etSearch = null;
    target.ivMap = null;

    view2131362082.setOnClickListener(null);
    view2131362082 = null;
    view2131362087.setOnClickListener(null);
    view2131362087 = null;
    view2131362149.setOnClickListener(null);
    view2131362149 = null;
  }
}
