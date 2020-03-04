package com.developtech.efuelfo.model.requestModel;

/**
 * Created by dt on 3/21/18.
 */

public class PaginationModel {
    int limit,page;

    public PaginationModel(){}

    public PaginationModel(int limit, int page) {
        this.limit = limit;
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
