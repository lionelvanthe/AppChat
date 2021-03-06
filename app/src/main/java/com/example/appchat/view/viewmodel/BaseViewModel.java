package com.example.appchat.view.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BaseViewModel extends ViewModel {

    protected final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    protected  DatabaseReference realtimeDatabase(String path){
        return FirebaseDatabase.getInstance().getReference(path);
    }

}
