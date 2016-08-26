package com.myasapp.djn.phonebalance.Presenter;

import com.myasapp.djn.phonebalance.Model.Entity.Result;
import com.myasapp.djn.phonebalance.Model.UpdateModel;
import com.myasapp.djn.phonebalance.Presenter.Interface.PreserterInterface;
import com.myasapp.djn.phonebalance.Presenter.Interface.onLoadInformationListener;
import com.myasapp.djn.phonebalance.View.MainView;

/**
 * Created by Administrator on 2016/8/5.
 */
public class Presenter implements PreserterInterface,onLoadInformationListener{

    private MainView main;
    private UpdateModel updateModel;
    public Presenter(MainView main)
    {
        this.main = main;
        updateModel = new UpdateModel();
    }

    @Override
    public void getInformation(String PhoneNumber) {
        if(updateModel.isSending)
        {
            return;
        }
        main.Loading();
        updateModel.sendRequest(PhoneNumber,this);
    }

    @Override
    public void onSuccess(Result result) {
        main.setData(result);
    }

    @Override
    public void onFaild(String errorMsg) {
        main.showError(errorMsg);
    }
}
