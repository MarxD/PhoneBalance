package com.myasapp.djn.phonebalance.Model;

import android.os.AsyncTask;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.myasapp.djn.phonebalance.Model.Entity.Result;
import com.myasapp.djn.phonebalance.Model.Interface.ModelInteface;
import com.myasapp.djn.phonebalance.Presenter.Interface.onLoadInformationListener;
import com.myasapp.djn.phonebalance.R;


import org.jsoup.Jsoup;
import java.net.SocketTimeoutException;

import static com.myasapp.djn.phonebalance.Model.Utils.RoundUpUtils.getDecimals;

/**
 * Created by Administrator on 2016/8/5.
 */
public class UpdateModel implements ModelInteface {
    public boolean isSending = false;
    onLoadInformationListener listener;

    @Override
    public void sendRequest(final String phoneNumber, final onLoadInformationListener listener) {
        isSending = true;
        this.listener = listener;
        new sender().execute(phoneNumber);
    }

    private class sender extends AsyncTask<String, Void, Boolean> {
        Result myResult;
        String errorMsg;

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                String Jsonresult = Jsoup.connect("http://123.57.18.0:8080/api/wechat/simcards/" + params[0]).userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").ignoreContentType(true).timeout(10000).get().body().text();
                myResult = JSON.parseObject(Jsonresult,Result.class);
                double used = myResult.getIotinfo().getResult().get(0).getTotal_gprs();
                double Surplus = myResult.getTotalGprs()*1024 - used;
                myResult.setSurplus(getDecimals(Surplus/1024,2));//剩余流量
                myResult.getIotinfo().getResult().get(0).setTotal_gprs(getDecimals(used/1024,2));//已用流量
                return true;
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
                errorMsg = "连接超时";
                return false;
            }
            catch (JSONException e)
            {
                errorMsg = "JSON解析错误";
                return false;
            }
            catch (Exception e)
            {
                errorMsg = "错误："+e.toString();
                return false;
            }
            finally {
                isSending = false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                listener.onSuccess(myResult);
            } else {
                listener.onFaild(errorMsg);
            }
        }
    }
}
