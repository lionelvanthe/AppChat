package com.example.appchat.view.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.appchat.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends BaseViewModel{

    public List<User> listUser = new ArrayList<>();
    public String url;
    public MutableLiveData<List<User>> mutableLiveData = new MutableLiveData<>(listUser);

    public void setListUser(){
        realtimeDatabase("Profiles").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listUser.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    if(!firebaseAuth.getUid().equals(dataSnapshot.getValue(User.class).getId())){
                        listUser.add(dataSnapshot.getValue(User.class));
                    }
                    else{
                        url = dataSnapshot.getValue(User.class).getUrl();
                    }
                }
                mutableLiveData.postValue(listUser);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
