package com.example.appchat.view.viewmodel;

import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appchat.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegisterViewModel extends BaseViewModel{

    private final StorageReference storage = FirebaseStorage.getInstance().getReference();
    private MutableLiveData<Boolean> registerSuccessful = new MutableLiveData<>();

    public LiveData<Boolean> doRegister(String name, String email, String password, Uri uri){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( task -> {
                    if (task.isSuccessful()) {
                        registerSuccessful.postValue(true);
                        upLoadImage(firebaseAuth.getUid(), name, email, password, uri);
                    } else {
                        registerSuccessful.postValue(false);
                    }
                });
        return registerSuccessful;
    }

    private void upInfoToProfile(User user){
        if(firebaseAuth.getUid() != null){
            realtimeDatabase("Profiles").child(firebaseAuth.getUid()).setValue(user);
        }
    }

    private void upLoadImage(String id, String name, String email, String password, Uri uri){

        if(uri == null){
            upInfoToProfile(new User(id, name, email, password, null));
        }
        else{
            StorageReference riversRef = storage.child(System.currentTimeMillis() + uri.getLastPathSegment());
            riversRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            upInfoToProfile(new User(id, name, email, password, uri.toString()));
                        }
                    });
                }
            });
        }
    }
}
