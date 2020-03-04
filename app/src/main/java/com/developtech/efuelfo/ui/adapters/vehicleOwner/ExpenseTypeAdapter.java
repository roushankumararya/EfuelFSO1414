package com.developtech.efuelfo.ui.adapters.vehicleOwner;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.responseModel.ExpenseTypeModel;
import com.developtech.efuelfo.ui.viewHolder.ExpenseTypeVH;

import java.util.List;

/**
 * Created by developtech on 2/2/18.
 */

public class ExpenseTypeAdapter extends RecyclerView.Adapter<ExpenseTypeVH> {

    List<ExpenseTypeModel> expenseTypeList;

    public ExpenseTypeAdapter(List<ExpenseTypeModel> expenseTypeList) {
        this.expenseTypeList = expenseTypeList;
    }

    @Override
    public ExpenseTypeVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expense_type, parent, false);
        return new ExpenseTypeVH(view, new CustomEditTextListener());
    }

    @Override
    public void onBindViewHolder(final ExpenseTypeVH holder, final int position) {
        holder.tvExpenseType.setText(expenseTypeList.get(position).getName());

        holder.cbExpenseType.setOnCheckedChangeListener(null);

        holder.cbExpenseType.setChecked(expenseTypeList.get(position).isAdded());

        holder.cbExpenseType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                expenseTypeList.get(position).setAdded(b);
            }
        });

        holder.myCustomTextWatcher.updatePosition(holder.getAdapterPosition());

        if(expenseTypeList.get(holder.getAdapterPosition()).getPrice()!=null && !expenseTypeList.get(holder.getAdapterPosition()).getPrice().isEmpty())
        {
            holder.etAddPrice.setText(expenseTypeList.get(holder.getAdapterPosition()).getPrice());
        }
        else
        {
            holder.etAddPrice.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return expenseTypeList.size();
    }

    public void updateList(List<ExpenseTypeModel> expenseTypeList)
    {
        this.expenseTypeList = expenseTypeList;
        notifyDataSetChanged();
    }

    public List<ExpenseTypeModel> getList()
    {
        return this.expenseTypeList;
    }


    public class CustomEditTextListener implements TextWatcher{
        private int position;

        public void updatePosition(int position)
        {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            expenseTypeList.get(position).setPrice(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
