package com.alpdurmaz.presentation.web.model;

import java.util.List;

public class ReturnMovieRequest {
    private List<Integer> rentalIdList;

    public List<Integer> getRentalIdList() {
        return rentalIdList;
    }

    public void setRentalIdList(List<Integer> rentalIdList) {
        this.rentalIdList = rentalIdList;
    }
}
