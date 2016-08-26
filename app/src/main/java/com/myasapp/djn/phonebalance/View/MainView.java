package com.myasapp.djn.phonebalance.View;

import com.myasapp.djn.phonebalance.Model.Entity.Result;

/**
 * Created by Administrator on 2016/8/5.
 */
public interface MainView {

    public void Loading();
    public void setProgress(Integer progress);
    public void setData(Result result);
    public void showError(String errorMsg);

}
