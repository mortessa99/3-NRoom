package com.example.a3_nroom

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a3_nroom.databinding.ItemUserBinding
import com.example.a3_nroom.datbase.User

class UsersAdapter:RecyclerView.Adapter<UsersAdapter.ViewHolder>(){
    lateinit var binding:ItemUserBinding
    lateinit var context: Context //فقط برای رویداد کلیک تعریف کردیم
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemUserBinding.inflate(inflater,parent,false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: UsersAdapter.ViewHolder, position: Int) {
        holder.myBind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder:RecyclerView.ViewHolder(binding.root){
        fun myBind(item:User){
            binding.apply {
                txtItem.text = "${item.id}-${item.name} ${item.age}"
                root.setOnClickListener {
                    val intent = Intent(context,UpdatUserActivity::class.java)
                    intent.putExtra("userid",item.id)
                    context.startActivity(intent)
                }
            }
        }
    }

    private val diffUtil = object :DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this,diffUtil)

}