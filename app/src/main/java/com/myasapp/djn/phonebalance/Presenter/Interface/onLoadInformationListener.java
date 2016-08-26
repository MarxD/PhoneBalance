package com.myasapp.djn.phonebalance.Presenter.Interface;

import com.myasapp.djn.phonebalance.Model.Entity.Result;

/**
 * Created by Administrator on 2016/8/5.
 */
public interface onLoadInformationListener {
    public void onSuccess(Result result);
    public void onFaild(String errorMsg);
}
