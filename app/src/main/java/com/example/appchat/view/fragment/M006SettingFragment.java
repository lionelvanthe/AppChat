package com.example.appchat.view.fragment;

import android.view.View;

import com.example.appchat.Constants;
import com.example.appchat.R;
import com.example.appchat.databinding.M006SettingFragmentBinding;
import com.example.appchat.view.viewmodel.SettingViewModel;

public class M006SettingFragment extends BaseFragment<M006SettingFragmentBinding, SettingViewModel>{
    @Override
    protected M006SettingFragmentBinding initBinding(View mRootView) {
        return M006SettingFragmentBinding.bind(mRootView);
    }

    @Override
    protected Class<SettingViewModel> getViewModelClass() {
        return SettingViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m006_setting_fragment;
    }

    @Override
    protected void initViews() {
        binding.logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.logOut();
                gotoLogin();
            }
        });
    }
    private void gotoLogin(){
        callBack.callBack(Constants.KEY_SHOW_M002_LOGIN, null);
    }
}
