package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomEditText;
import com.developtech.efuelfo.customs.CustomTextView;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.ExpenseTypeAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 2/14/18.
 */

public class ExpenseTypeVH extends RecyclerView.ViewHolder {

    @BindView(R.id.cbExpenseType)
    public CheckBox cbExpenseType;

    @BindView(R.id.tvExpenseType)
    public CustomTextView tvExpenseType;

    @BindView(R.id.etAddPrice)
    public CustomEditText etAddPrice;

    public ExpenseTypeAdapter.CustomEditTextListener myCustomTextWatcher;

    public ExpenseTypeVH(View itemView, ExpenseTypeAdapter.CustomEditTextListener myCustomTextWatcher) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.myCustomTextWatcher = myCustomTextWatcher;
        this.etAddPrice.addTextChangedListener(myCustomTextWatcher);
    }

}
