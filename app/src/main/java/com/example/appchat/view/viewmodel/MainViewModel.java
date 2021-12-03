package com.example.appchat.view.viewmodel;

import java.util.HashMap;

public class MainViewModel extends BaseViewModel{

    public void onLine(boolean isOnline){
        if(firebaseAuth.getUid() == null){
            return;
        }
        else{
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("online", isOnline);
            realtimeDatabase("Profiles").child(firebaseAuth.getUid())
                    .updateChildren(hashMap);
        }
    }

}
