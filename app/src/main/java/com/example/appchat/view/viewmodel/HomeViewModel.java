package com.example.appchat.view.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.example.appchat.model.Conversion;
import com.example.appchat.model.Message;
import com.example.appchat.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends BaseViewModel{

    public List<User> listUserOnline = new ArrayList<>();
    private List<User> listUser = new ArrayList<>();
    public String url;
    public MutableLiveData<List<User>> mutableLiveDataUser = new MutableLiveData<>(listUser);

    public List<Conversion> listConversion = new ArrayList<>();
    public MutableLiveData<List<Conversion>> mutableLiveDataConversion = new MutableLiveData<>(listConversion);

    public void setListUser(){
        realtimeDatabase("Profiles").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listUser.clear();
                listUserOnline.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    if(firebaseAuth.getUid() != null){
                        if(!firebaseAuth.getUid().equals(dataSnapshot.getValue(User.class).getId())){
                            if(dataSnapshot.getValue(User.class).isOnline()){
                                listUserOnline.add(dataSnapshot.getValue(User.class));
                            }
                            listUser.add(dataSnapshot.getValue(User.class));
                        }
                        else if(firebaseAuth.getUid().equals(dataSnapshot.getValue(User.class).getId())){

                            url = dataSnapshot.getValue(User.class).getUrl();
                        }
                    }

                }
                mutableLiveDataUser.postValue(listUserOnline);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setListConversion(){
        if(firebaseAuth.getUid() != null){
            realtimeDatabase("Conversion").child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    listConversion.clear();
                    for (DataSnapshot ds: snapshot.getChildren()) {
                        String lastMessage = ds.getValue(Message.class).getMessage();
                        String timestamp = ds.getValue(Message.class).getTimestamp();
                        boolean isSeen = ds.getValue(Message.class).isSeen();
                        for(User user : listUser){
                            if(ds.getKey().equals(user.getId())){
                                listConversion.add(new Conversion(ds.getKey(), user.getName(), lastMessage, timestamp, user.getUrl(), user.isOnline(), isSeen));
                            }
                        }
                    }
                    mutableLiveDataConversion.postValue(listConversion);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}
