package com.example.mvidemo.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvidemo.R
import com.example.mvidemo.data.api.ApiHelperImpl
import com.example.mvidemo.data.api.RetrofitBuilder
import com.example.mvidemo.data.model.User
import com.example.mvidemo.data.repository.MainRepository
import com.example.mvidemo.ui.adapter.MainAdapter
import com.example.mvidemo.ui.intent.DataIntent
import com.example.mvidemo.ui.viewmodel.DataViewModel
import com.example.mvidemo.ui.viewstate.DataState
import kotlinx.android.synthetic.main.activity_main.buttonShowUsers
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_main.recyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var dataViewModel: DataViewModel
    private val adapter = MainAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupClicks()
        setupViewModel()
        observeViewModel()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        recyclerView.adapter = adapter
    }

    private fun setupClicks() {
        buttonShowUsers.setOnClickListener {
            lifecycleScope.launch {
                dataViewModel.dataIntent.send(DataIntent.FetchData)
            }
        }
    }

    private fun setupViewModel() {
        dataViewModel = DataViewModel(MainRepository(ApiHelperImpl(RetrofitBuilder.apiService)))
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(users: List<User>) {
        recyclerView.visibility = View.VISIBLE
        users.let { listOfUsers -> listOfUsers.let { adapter.addData(it) } }
        adapter.notifyDataSetChanged()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            dataViewModel.dataState.collect {
                when (it) {
                    is DataState.Inactive -> {
                        Log.d("Inactive", "Initial state of StateFlow")
                    }

                    is DataState.Loading -> {
                        buttonShowUsers.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }

                    is DataState.Error -> {
                        progressBar.visibility = View.GONE
                        buttonShowUsers.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_LONG).show()
                    }

                    is DataState.ResponseData -> {
                        progressBar.visibility = View.GONE
                        buttonShowUsers.visibility = View.GONE
                        renderList(it.data.data)
                    }
                }
            }
        }
    }
}