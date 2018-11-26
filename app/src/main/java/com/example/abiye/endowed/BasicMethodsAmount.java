package com.example.abiye.endowed;

import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class BasicMethodsAmount {


        public double updatedRateCalculator (int updatedRate){
            //meselen
            double bankPercent= 0.07;
            double pensionFundPercent= 0.22;
            double unemployementTax= 0.005;
            double resultRate= ((1-unemployementTax)+pensionFundPercent+bankPercent)*updatedRate;

            // System.out.println(resultRate);
            return resultRate;
        }

        public double savedAmount(ArrayList<String> transactionsList){
            BasicMethodsAmount main = new BasicMethodsAmount();
            double saved=0;
            for (String e:transactionsList) {
                saved= (saved+main.updatedRateCalculator(Integer.parseInt(e)));
            }
            Log.e(TAG, "savedAmount: " + saved);
            return saved;
        }

      /*  public static void main(String[] args) {
            Main main = new Main();

            double updatedRate= main.updatedRateCalculator(100);
            System.out.println("updated rate= "+updatedRate);
            ArrayList<Integer> transactionsList= new ArrayList<>(3);
            transactionsList.add(100);
            transactionsList.add(100);
            transactionsList.add(100);
            main.savedAmount(transactionsList);
        }*/
}
