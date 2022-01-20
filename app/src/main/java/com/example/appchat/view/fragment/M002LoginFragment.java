package com.example.appchat.view.fragment;

import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.appchat.Constants;
import com.example.appchat.R;
import com.example.appchat.databinding.M002LoginFragmentBinding;
import com.example.appchat.view.viewmodel.LoginViewModel;

public class M002LoginFragment extends BaseFragment<M002LoginFragmentBinding, LoginViewModel>{

    @Override
    protected M002LoginFragmentBinding initBinding(View mRootView) {
        return M002LoginFragmentBinding.bind(mRootView);
    }

    @Override
    protected Class<LoginViewModel> getViewModelClass() {
        return LoginViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m002_login_fragment;
    }

    @Override
    protected void initViews() {
        binding.tvCreateNewAccount.setOnClickListener(v -> gotoRegister());

        binding.btnSignIn.setOnClickListener(view -> {
            if(isValidSignInDetail()){
                loading(true);
                String email = binding.inputEmail.getText().toString().trim();
                String password = binding.inputPassword.getText().toString().trim();
                mViewModel.doLogin(email, password).observe(getViewLifecycleOwner(), aBoolean -> {
                    if(aBoolean ){
                        mViewModel.setUserOnline();
                        binding.wrongEmailOrPassword.setVisibility(View.GONE);
                        gotoHome();
                    }
                    else{
                        binding.wrongEmailOrPassword.setVisibility(View.VISIBLE);
                        loading(false);
                    }
                });
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private Boolean isValidSignInDetail(){

        if(binding.inputEmail.getText().toString().trim().isEmpty()){
            showToast("Enter email");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.getText().toString()).matches()){
            showToast("Enter valid email");
            return false;
        }
        else if(binding.inputPassword.getText().toString().trim().isEmpty()){
            showToast("Enter password");
            return false;
        }

        else {
            return true;
        }
    }

    private void loading(Boolean isLoading){
        if(isLoading){
            binding.btnSignIn.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }
        else{
            binding.btnSignIn.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }


    private void gotoRegister(){
        callBack.callBack(Constants.KEY_SHOW_M003_REGISTER, null);
    }
    private void gotoHome(){
        callBack.callBack(Constants.KEY_SHOW_M004_HOME, null);
    }
}
