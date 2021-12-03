package com.example.appchat.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.appchat.OnActionCallBack;

public abstract class BaseFragment<K extends ViewDataBinding, V extends ViewModel> extends Fragment implements View.OnClickListener {

    protected Context mContext;
    protected View mRootView;
    protected OnActionCallBack callBack;

    public void setCallback(OnActionCallBack callBack){
        this.callBack = callBack;
    }

    protected K binding;
    protected V mViewModel;

    public final <T extends View> T findViewById(int id){
        return findViewById(id);
    }

    public final <T extends View> T finViewById(int id, View.OnClickListener event){
        T v = mRootView.findViewById(id);
        if(v != null && event != null){
            v.setOnClickListener(event);
        }
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(getLayoutId(), container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(getViewModelClass());
        binding = initBinding(mRootView);

        initViews();
        return mRootView;
    }


    protected abstract K initBinding(View mRootView);

    protected abstract Class<V> getViewModelClass();

    protected abstract int getLayoutId();

    protected abstract void initViews();

    @Override
    public void onClick(View view) {
        //do nothing
    }
}
