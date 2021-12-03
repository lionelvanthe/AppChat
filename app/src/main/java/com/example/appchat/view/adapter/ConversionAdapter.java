package com.example.appchat.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appchat.databinding.ItemContainerConversionBinding;
import com.example.appchat.listener.ConversionListener;
import com.example.appchat.model.Conversion;

import java.util.List;

public class ConversionAdapter extends RecyclerView.Adapter<ConversionAdapter.ConversionViewHolder> {

    private Context context;
    private ConversionListener conversionListener;
    private List<Conversion> list;

    @NonNull
    @Override
    public ConversionAdapter.ConversionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConversionViewHolder(ItemContainerConversionBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ConversionAdapter.ConversionViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    public ConversionAdapter(Context context, ConversionListener conversionListener, List<Conversion> list) {
        this.context = context;
        this.conversionListener = conversionListener;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ConversionViewHolder extends RecyclerView.ViewHolder {

        private ItemContainerConversionBinding binding;

        public ConversionViewHolder(ItemContainerConversionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void setData(Conversion conversion){
            binding.userNameConversion.setText(conversion.getName());
            binding.timeConversion.setText(conversion.getTimestamp());
            binding.lastMessage.setText(conversion.getLastMessage());
            if(conversion.getUrlAvatar() != null){
                Glide.with(context).load(conversion.getUrlAvatar()).into(binding.imgProfileConversion);
            }
            if(!conversion.isOnline()){
                binding.viewOnlineConversion.setVisibility(View.GONE);
            }
            if(!conversion.isSeen()){
                binding.userNameConversion.setTypeface(binding.userNameConversion.getTypeface(), Typeface.BOLD);
                binding.timeConversion.setTypeface(binding.timeConversion.getTypeface(), Typeface.BOLD);
                binding.lastMessage.setTypeface(binding.lastMessage.getTypeface(), Typeface.BOLD);
            }
            binding.layoutConversion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    conversionListener.conversionListener(conversion);
                }
            });
        }
    }
}
