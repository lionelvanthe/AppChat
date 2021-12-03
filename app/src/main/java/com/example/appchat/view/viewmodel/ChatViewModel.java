package com.example.appchat.view.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.appchat.model.Message;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends BaseViewModel{

    private ValueEventListener seenListenerChat, seenListenerConversion;

    public MutableLiveData<List<Message>> readMessage(String receiverId){
        List<Message> list = new ArrayList<>();
        MutableLiveData<List<Message>> mutableLiveData = new MutableLiveData<>(list);
        if(firebaseAuth.getUid() != null){
            realtimeDatabase("Message").child(firebaseAuth.getUid()).child(receiverId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        list.add(dataSnapshot.getValue(Message.class));
                    }
                    mutableLiveData.postValue(list);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        return mutableLiveData;
    }

    public void seen(String receiverId){

        if(firebaseAuth.getUid() != null){
            seenListenerChat = realtimeDatabase("Message").child(receiverId).child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        dataSnapshot.child("seen").getRef().setValue(true);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

            seenListenerConversion = realtimeDatabase("Conversion").child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        dataSnapshot.child("seen").getRef().setValue(true);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }

    public void removeListener(String receiverId){
        if(firebaseAuth.getUid() != null){
            realtimeDatabase("Message").child(receiverId).child(firebaseAuth.getUid()).removeEventListener(seenListenerChat);
            realtimeDatabase("Conversion").child(firebaseAuth.getUid()).removeEventListener(seenListenerConversion);
        }
    }

    public void sendMessage(Message message, String receiverId){
        if(firebaseAuth.getUid() != null){
            realtimeDatabase("Message").child(firebaseAuth.getUid()).child(receiverId).push().setValue(message);
            realtimeDatabase("Message").child(receiverId).child(firebaseAuth.getUid()).push().setValue(message);
            realtimeDatabase("Conversion").child(receiverId).child(firebaseAuth.getUid()).setValue(message);
            realtimeDatabase("Conversion").child(firebaseAuth.getUid()).child(receiverId).setValue(message);
        }
    }
}
