// Generated code from Butter Knife. Do not modify!
package com.developtech.efuelfo.ui.viewHolder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomEditText;
import com.developtech.efuelfo.customs.CustomTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExpenseTypeVH_ViewBinding implements Unbinder {
  private ExpenseTypeVH target;

  @UiThread
  public ExpenseTypeVH_ViewBinding(ExpenseTypeVH target, View source) {
    this.target = target;

    target.cbExpenseType = Utils.findRequiredViewAsType(source, R.id.cbExpenseType, "field 'cbExpenseType'", CheckBox.class);
    target.tvExpenseType = Utils.findRequiredViewAsType(source, R.id.tvExpenseType, "field 'tvExpenseType'", CustomTextView.class);
    target.etAddPrice = Utils.findRequiredViewAsType(source, R.id.etAddPrice, "field 'etAddPrice'", CustomEditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ExpenseTypeVH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.cbExpenseType = null;
    target.tvExpenseType = null;
    target.etAddPrice = null;
  }
}
