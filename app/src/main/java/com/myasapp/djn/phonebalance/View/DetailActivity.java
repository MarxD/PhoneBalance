package com.myasapp.djn.phonebalance.View;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.filippudak.ProgressPieView.ProgressPieView;
import com.myasapp.djn.phonebalance.Model.Entity.Result;
import com.myasapp.djn.phonebalance.Presenter.Presenter;
import com.myasapp.djn.phonebalance.R;

import static com.myasapp.djn.phonebalance.Model.Utils.RoundUpUtils.getDecimals;

public class DetailActivity extends AppCompatActivity implements MainView {

    TextView phonenumber, userstatu, used, total, balance, surplus_now, cardpackge,error_msg,tv_percent;
    ProgressPieView progressView;
    SwipeRefreshLayout refreshlayout;
    LinearLayout card;
    int statuColor;
    FloatingActionButton fab;
    Presenter getInfoPresenter;
    private boolean isFirstLoad = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        setListener();
        query();
    }

    private void query() {
        getInfoPresenter.getInformation("18493105401");
    }

    private void setListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });
    }

    private void init() {
        progressView = (ProgressPieView) findViewById(R.id.progressView);
        phonenumber = (TextView) findViewById(R.id.number);
        userstatu = (TextView) findViewById(R.id.phone_statu);
        used = (TextView) findViewById(R.id.used_thirty);
        balance = (TextView) findViewById(R.id.balance);
        surplus_now = (TextView) findViewById(R.id.surplus_now);
        total = (TextView) findViewById(R.id.total);
        cardpackge = (TextView) findViewById(R.id.cardpackage);
        error_msg = (TextView) findViewById(R.id.error_msg);
        fab = (FloatingActionButton) findViewById(R.id.refresh_fab);
        refreshlayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        refreshlayout.setEnabled(false);
        refreshlayout.setColorSchemeColors(Color.BLUE,Color.RED,Color.GREEN);
        refreshlayout.setProgressViewOffset(false, 0, 24);
        tv_percent = (TextView) findViewById(R.id.tv_percent);
        card = (LinearLayout) findViewById(R.id.Card);
        getInfoPresenter = new Presenter(this);
    }







    private String changeColor(String codeStr) {

        int code = Integer.valueOf(codeStr);
        String strs[] = this.getResources().getStringArray(R.array.stetus);
        if (code != 0) {
            statuColor = Color.RED;
        }
        if(code==04)
            statuColor = Color.GRAY;
        statuColor = Color.GREEN;
        return strs[code>strs.length?strs.length-1:code];
    }


    @Override
    public void Loading() {
        refreshlayout.setRefreshing(true);
        if(isFirstLoad) {

            card.setVisibility(View.GONE);
        }
        error_msg.setVisibility(View.GONE);
    }

    @Override
    public void setProgress(Integer progress) {

    }

    @Override
    public void setData(Result result) {
        card.setVisibility(View.VISIBLE);
        refreshlayout.setRefreshing(false);
        phonenumber.setText(result.getPhoneno());
        userstatu.setText(changeColor(result.getIotinfo().getStatus()));
        userstatu.setTextColor(statuColor);
        used.setText(result.getIotinfo().getResult().get(0).getTotal_gprs()+"MB");
        balance.setText(result.getBalance().getResult().get(0).getBalance()+getResources().getString(R.string.yuan));
        if(result.getBalance().getResult().get(0).getBalance()<=0)
        {
            balance.setTextColor(Color.RED);
        }
        total.setText(result.getTotalGprs()+"MB");
        surplus_now.setText(result.getSurplus()+"MB");
        surplus_now.setTextColor(Color.GREEN);
        if(result.getSurplus()<(result.getTotalGprs()/2)) {
            surplus_now.setTextColor(ContextCompat.getColor(this,R.color.holo_orange_dark                 ));
            if (result.getSurplus() <= 0) {
                surplus_now.setTextColor(Color.RED);
            }
        }
        cardpackge.setText(result.getCardpackage());
        double percent = getDecimals((result.getSurplus()/result.getTotalGprs())*100,2);
        setProgress(percent<0?0d:percent);
        isFirstLoad = false;
    }

    private void setProgress(double percent)
    {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(600);
    //    progressView.startAnimation(scaleAnimation);
        tv_percent.startAnimation(scaleAnimation);
        progressView.setMax(100);
        progressView.setProgress(100);
        int backColor = ContextCompat.getColor(this,R.color.gray);
        int progressColor ;
        if(percent>50&&percent<100)
        {
            progressColor = ContextCompat.getColor(this,R.color.holo_blue_light);
        }
       else  if(percent<50&&percent>20)
        {
            progressColor = ContextCompat.getColor(this,R.color.holo_orange_dark);
        }
        else
        {
            progressColor = ContextCompat.getColor(this,R.color.holo_red_dark);
        }
        progressView.setProgressColor(progressColor);
        progressView.setBackgroundColor(backColor);
        progressView.setAnimationSpeed(10);
        progressView.setProgress(100);
        progressView.animateProgressFill((int)percent);
        progressView.setText(percent+"%");
        tv_percent.setText(getString(R.string.availableGPRS)+percent+"%");
    }

    @Override
    public void showError(String errorMsg) {
        refreshlayout.setRefreshing(false);
        card.setVisibility(View.GONE);
        error_msg.setVisibility(View.VISIBLE);
        error_msg.setText(errorMsg);
    }
}
