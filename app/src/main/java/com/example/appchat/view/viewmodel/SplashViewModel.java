package com.example.appchat.view.viewmodel;

import com.google.firebase.auth.FirebaseAuth;

public class SplashViewModel extends BaseViewModel{
    public FirebaseAuth getFirebaseAuth(){
        return firebaseAuth;
    }
}
