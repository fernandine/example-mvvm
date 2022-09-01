package com.mvvm.examplemvvm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.examplemvvm.R
import com.mvvm.examplemvvm.models.Blog
import com.mvvm.examplemvvm.viewmodels.MainViewModel

class RecyclerAdapter(val viewModel: MainViewModel, val arrayList: ArrayList<Blog>, val context: Context): RecyclerView.Adapter {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewtype: Int
    ): RecyclerAdapter.NotesViewHolder {
        var root = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return NotesViewHolder(root)
    }
    override fun onBindViewHolder(holder: RecyclerAdapter.NotesViewHolder, position: Int) {
        holder.bind(arrayList.get(position))
    }

    override fun getItemCount(): Int {
        if(arrayList.size ==0) {
            Toast.makeText(context, "Lista vazia", Toast.LENGTH_LONG).show()
        }
        return arrayList.size
    }
    inner class NotesViewHolder(private val binding : View) : RecyclerView.ViewHolder(binding) {
        fun bind(blog: Blog) {
            binding.findViewById<TextView>(R.id.title).text = blog.title
            binding.findViewById<ImageButton>(R.id.delete).setOnClickListener{
                viewModel.remove(blog)
                notifyItemRemoved(arrayList.indexOf(blog))
            }
        }
    }
}