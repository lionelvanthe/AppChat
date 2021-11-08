package com.example.appchat.view.act;

import android.view.View;

import com.example.appchat.Constants;
import com.example.appchat.OnActionCallBack;
import com.example.appchat.R;
import com.example.appchat.databinding.ActivityMainBinding;
import com.example.appchat.model.User;
import com.example.appchat.view.fragment.M001SplashFragment;
import com.example.appchat.view.fragment.M002LoginFragment;
import com.example.appchat.view.fragment.M003RegisterFragment;
import com.example.appchat.view.fragment.M004HomeFragment;
import com.example.appchat.view.fragment.M005ChatFragment;
import com.example.appchat.view.fragment.M006SettingFragment;
import com.example.appchat.view.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements OnActionCallBack {

    @Override
    protected Class<MainViewModel> getViewModelClass() {
        return MainViewModel.class;
    }

    @Override
    protected ActivityMainBinding initBinding(View rootView) {
        return ActivityMainBinding.bind(rootView);
    }

    @Override
    protected void initViews() {
        M001SplashFragment splashFragment = new M001SplashFragment();
        splashFragment.setCallback(this);
        showFragment(R.id.container_view, splashFragment, false, 0, 0);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void callBack(String key, Object data) {
        switch (key){
            case Constants.KEY_SHOW_M002_LOGIN:
                M002LoginFragment m002LoginFragment = new M002LoginFragment();
                m002LoginFragment.setCallback(this);
                showFragment(R.id.container_view, m002LoginFragment, true, R.anim.anim_start, R.anim.anim_end);
                break;
            case Constants.KEY_SHOW_M003_REGISTER:
                M003RegisterFragment m003RegisterFragment = new M003RegisterFragment();
                m003RegisterFragment.setCallback(this);
                showFragment(R.id.container_view, m003RegisterFragment, true, R.anim.anim_start, R.anim.anim_end);
                break;
            case Constants.KEY_SHOW_M004_HOME:
                M004HomeFragment m004HomeFragment = new M004HomeFragment();
                m004HomeFragment.setCallback(this);
                showFragment(R.id.container_view, m004HomeFragment, false, R.anim.anim_start, R.anim.anim_end);
                break;
            case Constants.KEY_SHOW_M005_CHAT:
                M005ChatFragment m005ChatFragment = new M005ChatFragment();
                m005ChatFragment.setCallback(this);
                m005ChatFragment.getUser((User) data);
                showFragment(R.id.container_view, m005ChatFragment, true, R.anim.anim_start, R.anim.anim_end);
                break;
            case Constants.KEY_SHOW_M006_SETTING:
                M006SettingFragment m006SettingFragment = new M006SettingFragment();
                m006SettingFragment.setCallback(this);
                showFragment(R.id.container_view, m006SettingFragment, true, R.anim.anim_start, R.anim.anim_end);
                break;


        }
    }
}