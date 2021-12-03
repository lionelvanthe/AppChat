package com.example.appchat.view.viewmodel;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

public class LoginViewModel extends BaseViewModel{

    public MutableLiveData<Boolean> loginSuccessful = new MutableLiveData<>();

    public void doLogin(String email, String password){

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        loginSuccessful.postValue(true);
                    } else {
                        // If sign in fails, display a message to the user.
                        loginSuccessful.postValue(false);
                    }
                });
    }

    public void setUserOnline(){
        if(firebaseAuth.getUid() != null){
            HashMap<String, Object> hashMapUser = new HashMap<>();
            hashMapUser.put("online", true);
            realtimeDatabase("Profiles").child(firebaseAuth.getUid()).updateChildren(hashMapUser);
        }
    }

}
