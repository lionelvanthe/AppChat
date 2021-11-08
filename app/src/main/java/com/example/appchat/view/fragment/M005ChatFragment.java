package com.example.appchat.view.fragment;

import android.view.View;

import com.example.appchat.R;
import com.example.appchat.databinding.M005ChatFragmentBinding;
import com.example.appchat.model.User;
import com.example.appchat.view.viewmodel.ChatViewModel;

public class M005ChatFragment extends BaseFragment<M005ChatFragmentBinding, ChatViewModel>{

    private User user;

    @Override
    protected M005ChatFragmentBinding initBinding(View mRootView) {
        return M005ChatFragmentBinding.bind(mRootView);
    }

    @Override
    protected Class<ChatViewModel> getViewModelClass() {
        return ChatViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m005_chat_fragment;
    }

    @Override
    protected void initViews() {
        binding.userNameChat.setText(user.getName());
    }

    public void getUser(User user){
        this.user = user;
    }
}
