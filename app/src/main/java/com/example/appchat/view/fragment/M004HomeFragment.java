package com.example.appchat.view.fragment;

import android.view.View;

import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.example.appchat.Constants;
import com.example.appchat.R;
import com.example.appchat.databinding.M004HomeFragmentBinding;
import com.example.appchat.listener.ConversionListener;
import com.example.appchat.listener.UserOnlineListener;
import com.example.appchat.model.Conversion;
import com.example.appchat.model.User;
import com.example.appchat.view.adapter.ConversionAdapter;
import com.example.appchat.view.adapter.UserOnlineAdapter;
import com.example.appchat.view.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class M004HomeFragment extends BaseFragment<M004HomeFragmentBinding, HomeViewModel> implements UserOnlineListener, ConversionListener {

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
        initRecyclerView();
        mViewModel.setListUser();
        setUpView();
        binding.imgProfileHome.setOnClickListener(view -> gotoSetting());
    }

    @Override
    public void userOnlineClick(User user) {
        gotoChat(user);
    }

    @Override
    public void conversionListener(Conversion conversion) {
        gotoChat(conversion);
    }

    private void setUpView(){

        binding.recyclerViewOnlineUser.setHasFixedSize(true);
        mViewModel.mutableLiveDataUser.observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                mViewModel.setListConversion();
                if(mViewModel.url != null){
                    Glide.with(mContext).load(mViewModel.url).into(binding.imgProfileHome);
                }
                binding.recyclerViewOnlineUser.setAdapter(
                        new UserOnlineAdapter(getActivity(), mViewModel.listUserOnline, M004HomeFragment.this));
            }
        });
        binding.recyclerViewConversion.setHasFixedSize(true);
        mViewModel.mutableLiveDataConversion.observe(getViewLifecycleOwner(), new Observer<List<Conversion>>() {
            @Override
            public void onChanged(List<Conversion> conversions) {
                if(conversions.size() > 0){
                    binding.recyclerViewConversion.setAdapter(
                            new ConversionAdapter(getActivity(), M004HomeFragment.this, mViewModel.listConversion));
                }

            }
        });
    }
    private void initRecyclerView(){
        binding.recyclerViewOnlineUser.setAdapter(
                new UserOnlineAdapter(getActivity(), new ArrayList<>(), M004HomeFragment.this));
        binding.recyclerViewConversion.setAdapter(
                new ConversionAdapter(getActivity(), M004HomeFragment.this, new ArrayList<>()));
    }


    private void gotoSetting(){
        callBack.callBack(Constants.KEY_SHOW_M006_SETTING, null);
    }

    private void gotoChat(Conversion conversion){
        callBack.callBack(Constants.KEY_SHOW_M005_CHAT, conversion);
    }

    private void gotoChat(User user){
        callBack.callBack(Constants.KEY_SHOW_M005_CHAT, user);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

    }
}