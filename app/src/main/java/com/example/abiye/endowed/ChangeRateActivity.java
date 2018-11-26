package com.example.abiye.endowed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abiye.endowed.api.ApiInterfaces;
import com.example.abiye.endowed.api.RetrofitClient;
import com.example.abiye.endowed.pojo.CreateRate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeRateActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnChangeRate;
    EditText getRate;
    ApiInterfaces apiInterface;
    TextView getfromRate;
    ImageButton btngetRate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_rate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnChangeRate = (Button) findViewById(R.id.changeRateBtn);
        btnChangeRate.setOnClickListener(this);
        //EditText TextView take and pass value Rate
        getRate = (EditText)findViewById(R.id.getValueRate);
        getfromRate = (TextView)findViewById(R.id.getRate);
        btngetRate = (ImageButton)findViewById(R.id.btnGetRate);
        btngetRate.setOnClickListener(this);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.changeRateBtn){
            CreateRate passRate = new CreateRate(
                    getRate.getText().toString()
            );
            sendNetworkRequest(passRate);
        }
        if(view.getId()==R.id.btnGetRate){
            //calculate the input rate
            BasicMethodsAmount updateRate = new BasicMethodsAmount();
            getfromRate.setText(String.valueOf((int)updateRate.updatedRateCalculator(Integer.parseInt(getRate.getText().toString()))));
        }
    }

    private void sendNetworkRequest(CreateRate passRate) {
        //api passing
        apiInterface = RetrofitClient.getClient().create(ApiInterfaces.class);
        Call<CreateRate> call = apiInterface.createUser(passRate);
        call.enqueue(new Callback<CreateRate>() {
            @Override
            public void onResponse(Call<CreateRate> call, Response<CreateRate> response) {
                Toast.makeText(ChangeRateActivity.this, "Succes!" , Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<CreateRate> call, Throwable t) {
                Toast.makeText(ChangeRateActivity.this, "Error:(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
