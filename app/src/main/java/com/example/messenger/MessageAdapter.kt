package com.example.messenger

import android.content.Context
import android.icu.util.MeasureUnit.ITEM
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context,val messageList: ArrayList<com.example.messenger.Message>):
    RecyclerView.Adapter<ViewHolder>() {

    val ITEM_RECEIVE = 1
    val ITEM_SENT = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == 1){
            val view : View = LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            return ReceiveViewHolder(view)
        }
        else{
            val view : View = LayoutInflater.from(context).inflate(R.layout.sent,parent,false)
            return SentViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return ITEM_SENT
        }else{
            return ITEM_RECEIVE
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMsg = messageList[position]
//        if(holder.javaClass == SentViewHolder::class.java){
//            val viewHolder = holder as SentViewHolder
//            holder.sentMessage.text = currentMsg.message
//        }else{
//            val viewHolder = holder as ReceiveViewHolder
//            holder.receiveMessage.text = currentMsg.message
//        }
        if(holder is SentViewHolder){
            holder.sentMessage.text = currentMsg.message ?: ""
        }else if(holder is ReceiveViewHolder){
            holder.receiveMessage.text = currentMsg.message ?: ""
        }
    }

    class SentViewHolder(itemView: android.view.View) : ViewHolder(itemView){
        val sentMessage = itemView.findViewById<TextView>(R.id.tvsentmsg)
    }

    class ReceiveViewHolder(itemView: android.view.View) : ViewHolder(itemView){
        val receiveMessage = itemView.findViewById<TextView>(R.id.tvreceivemsg)
    }
}