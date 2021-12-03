package com.example.appchat.view.fragment;

import android.view.View;
import androidx.lifecycle.Observer;
import com.bumptech.glide.Glide;
import com.example.appchat.R;
import com.example.appchat.databinding.M005ChatFragmentBinding;
import com.example.appchat.model.Conversion;
import com.example.appchat.model.Message;
import com.example.appchat.model.User;
import com.example.appchat.view.adapter.ChatAdapter;
import com.example.appchat.view.viewmodel.ChatViewModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class M005ChatFragment extends BaseFragment<M005ChatFragmentBinding, ChatViewModel>{

    private User user;
    private ChatAdapter adapter;
    private Conversion conversion;

    @Override
    protected M005ChatFragmentBinding initBinding(View mRootView) {
        return M005ChatFragmentBinding.bind(mRootView);
    }

    @Override
    protected Class<ChatViewModel> getViewModelClass() {
        return ChatViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m005_chat_fragment;
    }

    @Override
    protected void initViews() {

        if(user != null){
            onLineUserOrConversion(user.getUrl(), user.getName(), user.getId());
        }
        else {
            onLineUserOrConversion(conversion.getUrlAvatar(), conversion.getName(), conversion.getConversionId());

        }
    }

    private void onLineUserOrConversion(String url, String name, String id){

        if(url != null){
            Glide.with(mContext).load(url).into(binding.imgProfileOnChat);
        }
        binding.nameOnChat.setText(name);

        binding.imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = binding.inputMessage.getText().toString().trim();
                String timestamp = getReadableDateTime(new Date());
                if(!message.equals("")){
                    mViewModel.sendMessage(new Message(message, timestamp, id), id);
                }
                binding.inputMessage.setText("");
            }
        });
        setUpRecyclerview(id, url);
    }

    private void setUpRecyclerview(String id, String url){

        binding.recyclerViewChat.setHasFixedSize(true);
        mViewModel.readMessage(id).observe(getViewLifecycleOwner(), new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                adapter = new ChatAdapter(messages, getActivity(),id, url);
                if(messages.size() == 0){
                    adapter.notifyDataSetChanged();
                }
                else{
                    adapter.notifyItemRangeInserted(messages.size(), messages.size());
                    binding.recyclerViewChat.smoothScrollToPosition(messages.size() - 1);
                }
                binding.recyclerViewChat.setAdapter(adapter);
            }
        });
    }

    private String getReadableDateTime(Date date){
        return new SimpleDateFormat("MM/dd/yyyy - hh:mm", Locale.getDefault()).format(date);
    }

    public void getUser(User user){
        this.user = user;
    }
    public void getConversion(Conversion conversion){
        this.conversion = conversion;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(user != null){
            mViewModel.seen(user.getId());
        }
        else{
            mViewModel.seen(conversion.getConversionId());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(user != null){
            mViewModel.removeListener(user.getId());
        }
        else{
            mViewModel.removeListener(conversion.getConversionId());
        }
    }
}
