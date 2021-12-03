package com.example.appchat.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.appchat.databinding.ItemContainerUserOnlineBinding;
import com.example.appchat.listener.UserOnlineListener;
import com.example.appchat.model.User;
import java.util.List;

public class UserOnlineAdapter extends RecyclerView.Adapter<UserOnlineAdapter.UserOnlineViewHolder> {

    private final List<User> list;
    private UserOnlineListener userOnlineListener;
    private Context context;

    public UserOnlineAdapter(Context context, List<User> list, UserOnlineListener userOnlineListener) {
        this.list = list;
        this.userOnlineListener = userOnlineListener;
        this.context = context;
    }

    @NonNull
    @Override
    public UserOnlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemContainerUserOnlineBinding binding = ItemContainerUserOnlineBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false
        );

        return new UserOnlineViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull UserOnlineViewHolder holder, int position) {
        holder.setUserData(list.get(position));
    }
    @Override
    public int getItemCount() {
        if(list ==null){
            return 0;
        }
        else{
            return this.list.size();
        }

    }

    public class UserOnlineViewHolder extends RecyclerView.ViewHolder {

        ItemContainerUserOnlineBinding binding;

        public UserOnlineViewHolder(ItemContainerUserOnlineBinding userOnlineBinding) {
            super(userOnlineBinding.getRoot());
            this.binding = userOnlineBinding;
        }

        public void setUserData(User user){
            binding.userNameOnline.setText(user.getName());
            binding.imgProfileOnline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    userOnlineListener.userOnlineClick(user);
                }
            });
            if(user.getUrl() != null){
                Glide.with(context).load(user.getUrl()).into(binding.imgProfileOnline);
            }
        }
    }

}



