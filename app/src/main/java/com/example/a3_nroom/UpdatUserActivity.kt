package com.example.a3_nroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.a3_nroom.databinding.ActivityUpdatUserBinding
import com.example.a3_nroom.datbase.User
import com.example.a3_nroom.datbase.UserDatabase
import com.google.android.material.snackbar.Snackbar

class UpdatUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdatUserBinding

    //database
    private val database: UserDatabase by lazy {
        Room.databaseBuilder(this, UserDatabase::class.java,"my_database")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }


    //other
    private lateinit var user: User
    private var userId = 0
    private var defaultName = ""
    private var defaultAge = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            userId = it.getInt("userid")
        }

        binding.apply {
            defaultName = database.getContentDao().getUser(userId).name
            defaultAge = database.getContentDao().getUser(userId).age

            edtUpdateName.setText(defaultName)
            edtUpdateAge.setText(defaultAge.toString())


            //delete
            fabDelete.setOnClickListener {
                user = User(userId,defaultName,defaultAge)
                database.getContentDao().deleteUser(user)
                finish()
            }


            //update
            fabSaveUpdate.setOnClickListener {
                var name = edtUpdateName.text.toString()
                var age = edtUpdateAge.text.toString()

                if(name.isNotEmpty() && age.isNotEmpty()){
                    user = User(userId,name,age.toInt())
                    database.getContentDao().updateUser(user)
                    Snackbar.make(it,"Saved", Snackbar.LENGTH_LONG).show()
                    finish()
                }else{
                    Snackbar.make(it,"Name and Age Can Not be empty", Snackbar.LENGTH_LONG).show()
                }
            }

        }
    }
}