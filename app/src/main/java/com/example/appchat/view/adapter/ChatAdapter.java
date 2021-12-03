package com.example.appchat.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.appchat.R;
import com.example.appchat.databinding.ItemContainerReceivedMessageBinding;
import com.example.appchat.databinding.ItemContainerSentMessageBinding;
import com.example.appchat.model.Message;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Message> list;
    private final Context context;
    private String receivedId;
    private String url;
    private Message clickedMess;

    private static final int VIEW_TYPE_SENT = 0;
    private static final int VIEW_TYPE_RECEIVED = 1;

    public ChatAdapter(List<Message> list, Context context, String receivedId ,String url) {
        this.list = list;
        this.context = context;
        this.receivedId = receivedId;
        this.url = url;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_SENT){
            return new SentMessageViewHolder(
                    ItemContainerSentMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent, false
                    )
            );
        }
        else{
            return new ReceivedMessageViewHolder(
                    ItemContainerReceivedMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent, false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == VIEW_TYPE_SENT){
            Message mess = list.get(position);
            ((SentMessageViewHolder) holder).setData(mess);
            ((SentMessageViewHolder) holder).data = mess;
            if(mess.equals(clickedMess)){
                ((SentMessageViewHolder) holder).binding.tvDateTime.setVisibility(View.VISIBLE);
                ((SentMessageViewHolder) holder).binding.tvSeen.setVisibility(View.VISIBLE);
            }
            else{
                ((SentMessageViewHolder) holder).binding.tvDateTime.setVisibility(View.GONE);
                ((SentMessageViewHolder) holder).binding.tvSeen.setVisibility(View.GONE);
            }
        }
        else{
            Message mess = list.get(position);
            ((ReceivedMessageViewHolder) holder).SetData(mess, url);
            ((ReceivedMessageViewHolder) holder).data = mess;
            if(mess.equals(clickedMess)){
                ((ReceivedMessageViewHolder) holder).binding.tvDateTime.setVisibility(View.VISIBLE);
                ((ReceivedMessageViewHolder) holder).binding.tvSeen.setVisibility(View.VISIBLE);
            }
            else{
                ((ReceivedMessageViewHolder) holder).binding.tvDateTime.setVisibility(View.GONE);
                ((ReceivedMessageViewHolder) holder).binding.tvSeen.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position).getReceivedId().equals(receivedId)){
            return VIEW_TYPE_SENT;
        }
        else{
            return VIEW_TYPE_RECEIVED;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SentMessageViewHolder extends RecyclerView.ViewHolder{

        private final ItemContainerSentMessageBinding binding;
        public Message data;

        public SentMessageViewHolder(ItemContainerSentMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void setData(Message message){
            binding.tvMessage.setText(message.getMessage());
            binding.tvDateTime.setText(message.getTimestamp());
            if(message.isSeen()){
                binding.tvSeen.setText(R.string.seen);
            }
            else{
                binding.tvSeen.setText(R.string.send);
            }
            binding.tvMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickedMess = data;

                    if(binding.tvDateTime.getVisibility() == View.GONE){
                        notifyDataSetChanged();
                        binding.tvSeen.setVisibility(View.VISIBLE);
                    }
                    else{
                        binding.tvDateTime.setVisibility(View.GONE);
                        binding.tvSeen.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    public class ReceivedMessageViewHolder extends RecyclerView.ViewHolder{

        private final ItemContainerReceivedMessageBinding binding;
        public Message data;

        public ReceivedMessageViewHolder(ItemContainerReceivedMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void SetData(Message message, String url){
            binding.tvMessage.setText(message.getMessage());
            binding.tvDateTime.setText(message.getTimestamp());
            if(url != null){
                Glide.with(context).load(url).into(binding.imgProfileOnline);
            }
            binding.tvMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    clickedMess = data;

                    if(binding.tvDateTime.getVisibility() == View.GONE){
                        notifyDataSetChanged();
                    }
                    if(binding.tvDateTime.getVisibility() == View.VISIBLE){
                        binding.tvDateTime.setVisibility(View.GONE);
                        binding.tvSeen.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

}
