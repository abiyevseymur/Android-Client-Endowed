package com.example.abiye.endowed;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by abiye on 11/24/2018.
 */

public class progressAnimation {
    int counter;
    void getProgressNumb(int ProgressNumb,int totalValue, ProgressBar progressBarCircle, final TextView tv){
        ValueAnimator animator = new ValueAnimator();


        animator.setObjectValues(counter, totalValue);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                tv.setText(String.valueOf(animation.getAnimatedValue()));
            }
        });
        animator.setEvaluator(new TypeEvaluator<Integer>() {
            public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
                return Math.round(startValue + (endValue - startValue) * fraction);
            }
        });
        animator.setDuration(1600);
        animator.start();
        ObjectAnimator anim = ObjectAnimator.ofInt(progressBarCircle, "progress", 0, ProgressNumb);
        anim.setDuration(1600);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
    }

}
