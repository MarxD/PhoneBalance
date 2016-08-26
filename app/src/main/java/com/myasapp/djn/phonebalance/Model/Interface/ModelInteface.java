package com.myasapp.djn.phonebalance.Model.Interface;

import com.myasapp.djn.phonebalance.Presenter.Interface.onLoadInformationListener;

/**
 * Created by Administrator on 2016/8/5.
 */
public interface ModelInteface {
    public void sendRequest(String phoneNumber,onLoadInformationListener listener);
}
