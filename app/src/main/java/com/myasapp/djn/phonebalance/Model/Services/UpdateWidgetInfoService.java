package com.myasapp.djn.phonebalance.Model.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.WindowManager;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.myasapp.djn.phonebalance.Model.Entity.Result;
import com.myasapp.djn.phonebalance.Model.Utils.Const;
import com.myasapp.djn.phonebalance.Presenter.Presenter;
import com.myasapp.djn.phonebalance.View.MainView;

/**
 * Created by Administrator on 2016/8/9.
 */
public class UpdateWidgetInfoService extends Service implements MainView {
    Boolean isLoop = true;
    Thread updateThread;
    Context mContext;
    Presenter presenter;

    @Override
    public void onCreate() {
        super.onCreate();
        presenter = new Presenter(this);
        updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isLoop) {
                        Update();
                        Thread.sleep(1800000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        updateThread.start();
        mContext = getApplicationContext();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private void Update() {
        presenter.getInformation(Const.Phone_Number);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        super.onStartCommand(intent, flags, startId);
        if (intent != null && intent.getBooleanExtra("isClick", false)) {
            Update();
        }
        return START_STICKY;
    }


    @Override
    public void onDestroy() {

        Log.i("myservice", "服务销毁");
        if (updateThread != null) {
            isLoop = false;
            updateThread.interrupt();
        }

        super.onDestroy();
    }

    @Override
    public void Loading() {

    }

    @Override
    public void setProgress(Integer progress) {

    }

    @Override
    public void setData(Result result) {
        Log.i("myservice", "查询完毕");
        Intent intent = new Intent(Const.BroadCast_Action_UPDATE);
        intent.putExtra("result", result);
        mContext.sendBroadcast(intent);
        Toast.makeText(mContext, "查询成功，剩余流量" + result.getSurplus() + "MB", Toast.LENGTH_LONG).show();

    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(mContext, "查询失败,原因" + errorMsg, Toast.LENGTH_SHORT).show();
    }
}
