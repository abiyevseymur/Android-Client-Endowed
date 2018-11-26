package com.example.abiye.endowed.api;

import com.example.abiye.endowed.pojo.ContractData;
import com.example.abiye.endowed.pojo.CreateRate;
import com.example.abiye.endowed.pojo.Transactions;
import com.example.abiye.endowed.pojo.UserList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiInterfaces {

    @GET("/find_all")
    Call<CreateRate> doGetListResources();

    @POST("/createRate")
    Call<CreateRate> createUser(@Body CreateRate createRate);

    @GET("/get/{id}")
    Call<UserList> doGetUserList(@Path("id") String id);

    @GET("/get_transaction/{id}")
    Call<Transactions> getTransactions(@Path("id") String id);

    @GET("/get-contract-by-id/{id}")
    Call<ContractData> getLeftDays(@Path("id") String id);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("id") String id, @Field("rate") String rate);
}


