package com.myasapp.djn.phonebalance.View.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.myasapp.djn.phonebalance.Model.Entity.Result;
import com.myasapp.djn.phonebalance.Model.Services.UpdateWidgetInfoService;
import com.myasapp.djn.phonebalance.Model.Utils.Const;
import com.myasapp.djn.phonebalance.Presenter.Presenter;
import com.myasapp.djn.phonebalance.R;
import com.myasapp.djn.phonebalance.View.MainView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static com.myasapp.djn.phonebalance.Model.Utils.RoundUpUtils.getDecimals;

/**
 * Implementation of App Widget functionality.
 */
public class InfoWidget extends AppWidgetProvider {
    RemoteViews views;
    static Set ids = new HashSet();//储存控件ID数列
    static final Intent updateServiceIntent = new Intent(Const.SERVICE_ACTION_START);
    static final Intent intent_update = new Intent(Const.SERVICE_ACTION_START);

    /**
     * when you add anyone widget
     *
     * @param context
     * @param appWidgetManager
     * @param appWidgetIds
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            ids.add(Integer.valueOf(appWidgetId));
        }
          sendIntent(context,intent_update);
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction()!=null&&intent.getAction().equals(Const.BroadCast_Action_UPDATE)) {
            setData((Result) intent.getExtras().get("result"),context);
        } else if (intent.hasCategory(Intent.CATEGORY_ALTERNATIVE)) {
            intent_update.putExtra("isClick", true);
            sendIntent(context,intent_update);

        }

    }

    private PendingIntent getPendingIntent(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, InfoWidget.class);
        intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        return pendingIntent;
    }

    /**
     * first add
     *
     * @param context
     */
    @Override
    public void onEnabled(Context context) {
        sendIntent(context,updateServiceIntent);
        super.onEnabled(context);
    }

    /**
     * last dleted
     *
     * @param context
     */
    @Override
    public void onDisabled(Context context) {
            context.stopService(updateServiceIntent);
        super.onDisabled(context);
    }

    /**
     * when you deleted anyone widget
     *
     * @param context
     * @param appWidgetIds
     */
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {

        for (int id : appWidgetIds) {
            ids.remove(id);
        }
        super.onDeleted(context, appWidgetIds);
    }

    private void sendIntent(Context context,Intent intent)
    {
        if(TextUtils.isEmpty(intent.getPackage()))
        {
            intent.setPackage(context.getPackageName());
        }
        context.startService(intent);
    }

    public void setData(Result result,Context context) {
        Log.i("myservice", "剩余" + result.getSurplus() + "已用" + result.getIotinfo().getResult().get(0).getTotal_gprs()+"MB");
        Iterator it = ids.iterator();
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        while (it.hasNext()) {
            views = new RemoteViews(context.getPackageName(), R.layout.infowidget);
            views.setOnClickPendingIntent(R.id.iv_refresh, getPendingIntent(context));//设置桌面控件上的点击事件
            views.setTextViewText(R.id.tv_surplus, getDecimals(result.getSurplus(), 2) + "MB");
            views.setTextViewText(R.id.tv_used, getDecimals(result.getIotinfo().getResult().get(0).getTotal_gprs(), 2) + "MB");
            int surplusColor = Color.GREEN;
            if (result.getSurplus() < (result.getTotalGprs() / 3)) {
                surplusColor = Color.YELLOW;
                if (result.getSurplus() <= 0) {
                    surplusColor = Color.RED;
                }
            }
            views.setTextColor(R.id.tv_surplus, surplusColor);
            manager.updateAppWidget((Integer) it.next(), views);
        }

    }

}

