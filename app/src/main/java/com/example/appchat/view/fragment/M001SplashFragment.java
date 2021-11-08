package com.example.appchat.view.fragment;

import android.os.Handler;
import android.view.View;

import com.example.appchat.Constants;
import com.example.appchat.R;
import com.example.appchat.databinding.M001SplashFragmentBinding;
import com.example.appchat.view.viewmodel.SplashViewModel;

public class M001SplashFragment extends BaseFragment<M001SplashFragmentBinding, SplashViewModel> {

    @Override
    protected M001SplashFragmentBinding initBinding(View mRootView) {
        return null;
    }

    @Override
    protected Class getViewModelClass() {
        return SplashViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m001_splash_fragment;
    }

    @Override
    protected void initViews() {
        if(this.mViewModel.getFirebaseAuth().getCurrentUser() != null){
            new Handler().postDelayed(this::gotoHome, 2000);
        }
        else{
            new Handler().postDelayed(this::gotoLogin, 2000);
        }

    }
    private void gotoLogin(){
        callBack.callBack(Constants.KEY_SHOW_M002_LOGIN, null);
    }
    private void gotoHome(){
        callBack.callBack(Constants.KEY_SHOW_M004_HOME, null);
    }
}
