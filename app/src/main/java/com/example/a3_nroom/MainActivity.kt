package com.example.a3_nroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.a3_nroom.databinding.ActivityMainBinding
import com.example.a3_nroom.datbase.User
import com.example.a3_nroom.datbase.UserDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val userAdapter by lazy { UsersAdapter() }
    private val db: UserDatabase by lazy {
        Room.databaseBuilder(this, UserDatabase::class.java,"my_database")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            floatingActionButton.setOnClickListener {
                startActivity(Intent(this@MainActivity,AddUserActivity::class.java))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkItems()
    }

    private fun checkItems(){
        binding.apply {
            if (db.getContentDao().getAllUser().isNotEmpty()){
                textView.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

                userAdapter.differ.submitList(db.getContentDao().getAllUser())
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter = userAdapter
            } else {
                recyclerView.visibility = View.GONE
                textView.visibility = View.VISIBLE
            }
        }
    }


}