package com.example.appchat.view.viewmodel;

public class SettingViewModel extends BaseViewModel{

    public void logOut(){
        firebaseAuth.signOut();
    }
}
