package com.example.appchat.view.fragment;

import android.view.View;
import com.bumptech.glide.Glide;
import com.example.appchat.Constants;
import com.example.appchat.R;
import com.example.appchat.databinding.M004HomeFragmentBinding;
import com.example.appchat.listener.UserOnlineListener;
import com.example.appchat.model.User;
import com.example.appchat.view.adapter.UserOnlineAdapter;
import com.example.appchat.view.viewmodel.HomeViewModel;

public class M004HomeFragment extends BaseFragment<M004HomeFragmentBinding, HomeViewModel> implements UserOnlineListener {

    @Override
    protected M004HomeFragmentBinding initBinding(View mRootView) {
        return M004HomeFragmentBinding.bind(mRootView);
    }

    @Override
    protected Class<HomeViewModel> getViewModelClass() {
        return HomeViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m004_home_fragment;
    }

    @Override
    protected void initViews() {
        setUpView();
        binding.imgProfileHome.setOnClickListener(view -> gotoSetting());
    }

    @Override
    public void userOnlineClick(User user) {
        gotoChat(user);
    }

    private void gotoChat(User user){
        callBack.callBack(Constants.KEY_SHOW_M005_CHAT, user);
    }

    private void setUpView(){
        mViewModel.setListUser();
        binding.recyclerViewOnlineUser.setHasFixedSize(true);
        mViewModel.mutableLiveData.observe(getViewLifecycleOwner(), users -> {
            if(users.size() >0){
                binding.recyclerViewOnlineUser.setVisibility(View.VISIBLE);
                binding.recyclerViewOnlineUser.setAdapter(
                        new UserOnlineAdapter(getActivity(), mViewModel.listUser, M004HomeFragment.this));

            }
            else{
                binding.recyclerViewOnlineUser.setVisibility(View.GONE);
            }
            if(mViewModel.url != null){
                Glide.with(mContext).load(mViewModel.url).into(binding.imgProfileHome);
            }
        });
    }

    private void gotoSetting(){
        callBack.callBack(Constants.KEY_SHOW_M006_SETTING, null);
    }
}