package com.example.abiye.endowed.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abiye on 11/25/2018.
 */

public class ContractData {
    @SerializedName("id")
    public Integer id;
    @SerializedName("customerid")
    public Integer customerId;
    @SerializedName("startdate")
    public String startDate;
    @SerializedName("enddate")
    public String endDate;
    @SerializedName("bankname")
    public String bankName;
    @SerializedName("balance")
    public String balance;
    @SerializedName("currentrate")
    public String currentrate;
}
