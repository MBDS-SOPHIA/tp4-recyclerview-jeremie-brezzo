package com.openclassrooms.magicgithub.ui.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.magicgithub.databinding.ItemListUserBinding
import com.openclassrooms.magicgithub.model.User
import com.openclassrooms.magicgithub.utils.UserDiffCallback
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide

class UserListAdapter(private val callback: Listener) : RecyclerView.Adapter<UserListAdapter.ListUserViewHolder>() {
    private var users: List<User> = ArrayList()

    interface Listener {
        fun onClickDelete(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        val binding = ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListUserViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun updateList(newList: List<User>) {
        val diffResult = DiffUtil.calculateDiff(UserDiffCallback(newList, users))
        users = newList
        diffResult.dispatchUpdatesTo(this)
    }

    class ListUserViewHolder(private val binding: ItemListUserBinding, private val callback: Listener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(user.avatarUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .into(itemListUserAvatar)
                itemListUserUsername.text = user.login
                itemListUserDeleteButton.setOnClickListener { callback.onClickDelete(user) }
            }
        }
    }
}