// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.fragments.vehicleOwner;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FavouritesFragment_ViewBinding implements Unbinder {
  private FavouritesFragment target;

  @UiThread
  public FavouritesFragment_ViewBinding(FavouritesFragment target, View source) {
    this.target = target;

    target.recycleFavourite = Utils.findRequiredViewAsType(source, R.id.recycleFavourite, "field 'recycleFavourite'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FavouritesFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recycleFavourite = null;
  }
}
