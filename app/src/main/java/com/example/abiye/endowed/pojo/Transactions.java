package com.example.abiye.endowed.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abiye on 11/25/2018.
 */

public class Transactions {
    @SerializedName("id")
    public Integer id;
    @SerializedName("contract_id")
    public Integer contract_id;
    @SerializedName("customer_id")
    public Integer customer_id;
    @SerializedName("transaction_amount")
    public String transaction_amount;

}
