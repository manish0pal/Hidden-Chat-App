package in.manish.calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends  RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private Context mContext ;
    private List<Message> messageList ;


    public MessageAdapter(Context mContext, List<Message> messageList) {
        this.mContext = mContext;
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.msg_layout,parent,false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messageList.get(position);
        if(message.getSender().equalsIgnoreCase(ChatActivity.user)){
            //my msg
            holder.other_msg_tv.setVisibility(View.GONE);
            holder.my_msg_tv.setText(message.getMsg());
        }
        else {
            //other msg
            holder.my_msg_tv.setVisibility(View.GONE);
            holder.other_msg_tv.setText(message.getMsg());
        }


    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {

        TextView other_msg_tv,my_msg_tv;



        public MessageViewHolder(View itemView) {
            super(itemView);

            other_msg_tv = itemView.findViewById(R.id.other_msg_tv);
            my_msg_tv = itemView.findViewById(R.id.my_msg_tv);
        }
    }

}
