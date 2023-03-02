package com.example.m6_hw3

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.m6_hw3.databinding.ItemHashtagBinding

class Adapter(
    private val list: ArrayList<String>,
    private val clickListener: (String) -> Unit
) : RecyclerView.Adapter<Adapter.MessageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            ItemHashtagBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size
    inner class MessageViewHolder(private val binding: ItemHashtagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("Set")
        fun onBind(text: String) {
            binding.tvHint.text = "#$text"
            itemView.setOnClickListener {
                clickListener(text)
            }
        }
    }
}