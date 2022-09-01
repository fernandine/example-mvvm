package com.mvvm.examplemvvm

import android.app.ActivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputBinding
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.examplemvvm.adapters.RecyclerAdapter
import com.mvvm.examplemvvm.models.Blog
import com.mvvm.examplemvvm.viewmodels.MainViewModel
import com.mvvm.examplemvvm.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModel
    private lateinit var mainRecycler: RecyclerView
    private lateinit var but: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainRecycler = findViewById(R.id.recycler)
        val application = requireNotNull(this).application
        val factory = MainViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        but = findViewById(R.id.button)
        but.setOnClickListener {
            addData()
        }
        initialaliseAdapter()
    }

    private fun initialaliseAdapter() {
        mainRecycler.layoutManager = viewManager
        observeData()
    }

    fun observeData() {
        viewModel.lst.observe(this, {
            Log.i(
                "data",
                it.toString())
            mainRecycler.adapter = RecyclerAdapter(viewModel, it, this)

        })
    }

    fun addData() {
        var txtPlce = findViewById<EditText>(R.id.title)
        var title = txtPlce.text.toString()
        if(title.isNullOrBlank()) {
            Toast.makeText(this, "Entre com o valor!", Toast.LENGTH_LONG).show()
        } else {
            var blog = Blog(title)
            viewModel.addData(blog)
            txtPlce.text.clear()
            mainRecycler.adapter?.notifyDataSetChanged()
        }
    }
}