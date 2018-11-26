package com.example.abiye.endowed;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abiye.endowed.api.ApiInterfaces;
import com.example.abiye.endowed.api.RetrofitClient;
import com.example.abiye.endowed.pojo.ContractData;
import com.example.abiye.endowed.pojo.CreateRate;
import com.example.abiye.endowed.pojo.Transactions;
import com.example.abiye.endowed.pojo.UserList;
import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static java.lang.Math.round;

public class EndowedMainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnChange;
    ProgressBar progressBarCircle;
    TextView monthLeftCount;
    TextView amountOf;
    int countOfDays;
    int percentOfDays;
    //Given Static Value becouse of not ready DB
    int totalRate = 1020;
    //
    ApiInterfaces apiInterface;
    TextView nameUser;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endowed_main);

        //button change
        btnChange= (Button)findViewById(R.id.button);
        btnChange.setOnClickListener(this);
        //progress bar
        progressBarCircle = (ProgressBar)findViewById(R.id.progressBar);
        final progressAnimation pbAnimation = new progressAnimation();
        //total amount
        amountOf = (TextView)findViewById(R.id.amountOf);
        // left of month
        monthLeftCount = (TextView)findViewById(R.id.monthLeft);
        // name of user
        nameUser =(TextView)findViewById(R.id.welcomeName);
        //API connection
        apiInterface = RetrofitClient.getClient().create(ApiInterfaces.class);

        //get defined user
        Call<UserList> call2 = apiInterface.doGetUserList("2");//userId will be used when register page will be create
        call2.enqueue(new Callback<UserList>()

        {
            @Override
            public void onResponse (Call < UserList > call, Response< UserList > response){
                UserList userList = response.body();
                nameUser.setText(userList.name);
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });
        //Show Left days
        Call<ContractData> callLeftD = apiInterface.getLeftDays("2"); //CustomerId will be used when register page will be create
        callLeftD.enqueue(new Callback<ContractData>() {
            @TargetApi(Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<ContractData> call, Response<ContractData> response) {
                ContractData contractD = response.body();
                Date dateCurrent = new Date();
                int startDays = (int) (dateCurrent.getTime() / (1000*60*60*24));
                String dateEndStr = String.valueOf(contractD.endDate);
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date endDate;
                try {
                    endDate = format.parse(dateEndStr);
                    int endDays = (int) (endDate.getTime() / (1000*60*60*24));
                    setbetweenDays(endDays , startDays);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                // set values after response to progress bar and show total left days
                monthLeftCount.setText(String.valueOf(getCountOfDays()));
            }
            @Override
            public void onFailure(Call<ContractData> call, Throwable t) {
                call.cancel();
            }
        });
        //Show Total Amount
        final Call<Transactions> callTrans = apiInterface.getTransactions("1");
        callTrans.enqueue(new Callback<Transactions>() {
            @Override
            public void onResponse(Call<Transactions> call, Response<Transactions> response) {
                Transactions transactions = response.body();
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(transactions.transaction_amount);
                BasicMethodsAmount totalAmount = new BasicMethodsAmount();
                pbAnimation.getProgressNumb(daystoPercent(getCountOfDays()), (int) totalAmount.savedAmount(arrayList),progressBarCircle,amountOf);

            }


            @Override
            public void onFailure(Call<Transactions> call, Throwable t) {
                Toast.makeText(EndowedMainActivity.this, "Error:(", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //makes day to porcent for progress bar
    public int daystoPercent(int days){
        percentOfDays = round((days * 100)/1080);
        return percentOfDays;
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button ){
            Intent intent = new Intent(this, ChangeRateActivity.class);
            startActivity(intent);
        }
    }
    public void setbetweenDays(int endDays,int startDays) {
        countOfDays = endDays  - startDays;
    }
    public int getCountOfDays() {
        return countOfDays;
    }


  }

