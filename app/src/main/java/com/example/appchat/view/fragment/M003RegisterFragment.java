package com.example.appchat.view.fragment;

import android.Manifest;
import android.net.Uri;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;
import com.example.appchat.Constants;
import com.example.appchat.R;
import com.example.appchat.databinding.M003RegisterFragmentBinding;
import com.example.appchat.view.viewmodel.RegisterViewModel;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;
import java.util.List;
import gun0912.tedbottompicker.TedBottomPicker;

public class M003RegisterFragment extends BaseFragment<M003RegisterFragmentBinding, RegisterViewModel>{

    private Uri userUri = null;

    @Override
    protected M003RegisterFragmentBinding initBinding(View mRootView) {
        return M003RegisterFragmentBinding.bind(mRootView);
    }

    @Override
    protected Class<RegisterViewModel> getViewModelClass() {
        return RegisterViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m003_register_fragment;
    }

    @Override
    protected void initViews() {

        binding.imgProfile.setOnClickListener(v -> requestPermission());
        binding.btnSignUp.setOnClickListener(view -> {
            if(isValidSignUpDetail()){
                String name = binding.inputName.getText().toString().trim();
                String email = binding.signUpEmail.getText().toString().trim();
                String password = binding.signUpPassword.getText().toString().trim();
                mViewModel.doRegister(name, email, password, userUri).observe(getViewLifecycleOwner(), aBoolean -> {
                    if(aBoolean){
                        loading(false);
                        gotoLogin();
                    }
                });
                loading(true);
            }
        });

        binding.tvSignIn.setOnClickListener(view -> gotoLogin());
    }

    private void requestPermission() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openImage();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {

            }
        };

        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    private void openImage() {

        TedBottomPicker.with(getActivity())
                .show(uri -> {
                    if(uri != null){
                        userUri = uri;
                        binding.imgProfile.setImageURI(uri);
                        binding.tvAddImage.setVisibility(View.GONE);
                    }
                });
    }


    private Boolean isValidSignUpDetail(){

        if(binding.inputName.getText().toString().trim().isEmpty()){
            showToast("Enter name");
            return false;
        }
        else if(binding.signUpEmail.getText().toString().trim().isEmpty()){
            showToast("Enter email");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(binding.signUpEmail.getText().toString()).matches()){
            showToast("Enter valid email");
            return false;
        }
        else if(binding.signUpPassword.getText().toString().trim().isEmpty()){
            showToast("Enter password");
            return false;
        }
        else if(binding.confirmPassword.getText().toString().trim().isEmpty()){
            showToast("Confirm your password");
            return false;
        }
        else if(!binding.signUpPassword.getText().toString().equals(binding.confirmPassword.getText().toString().trim())){
            showToast("Password & confirm password must be same");
            return false;
        }
        else {
            return true;
        }
    }

    private void loading(Boolean isLoading){
        if(isLoading){
            binding.btnSignUp.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }
        else{
            binding.btnSignUp.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void showToast(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private void gotoLogin(){
        callBack.callBack(Constants.KEY_SHOW_M002_LOGIN, null);
    }
}
