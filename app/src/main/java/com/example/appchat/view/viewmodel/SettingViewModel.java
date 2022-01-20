package com.example.appchat.view.viewmodel;

import java.util.HashMap;

public class SettingViewModel extends BaseViewModel{

    public void logOut(){

        HashMap<String, Object> hashMapUser = new HashMap<>();
        hashMapUser.put("online", false);
        if(firebaseAuth.getUid() != null){
            realtimeDatabase("Profiles").child(firebaseAuth.getUid()).updateChildren(hashMapUser);
        }
        firebaseAuth.signOut();
    }
}
