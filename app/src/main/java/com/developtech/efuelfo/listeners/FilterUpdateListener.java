package com.developtech.efuelfo.listeners;

import com.developtech.efuelfo.model.responseModel.CreditAgreementResponseModel;
import com.developtech.efuelfo.model.responseModel.PaymentHistoryResponseModel;

import java.util.List;

/**
 * Created by dt on 2/13/18.
 */

public interface FilterUpdateListener {
    void updateList(List<PaymentHistoryResponseModel> filteredList);
    void updateData(List<CreditAgreementResponseModel> filterList);
    void clearFilter();
}
