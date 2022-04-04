package com.example.a3_nroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a3_nroom.databinding.ActivityAddUserBinding
import com.example.a3_nroom.datbase.User
import com.example.a3_nroom.datbase.UserDatabase
import com.google.android.material.snackbar.Snackbar

class AddUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddUserBinding
    private lateinit var user: User
    private val db:UserDatabase by lazy {
         Room.databaseBuilder(this,UserDatabase::class.java,"my_database")
             .allowMainThreadQueries() //این خط برای مدیریت ترد است.اجباری نیست ولی بنویسید
             .fallbackToDestructiveMigration() // این خط برای جلوگیری از کانفلیگ نسخه های مختلف دیتابیس روی یک دستگاه است.اجباری نیست ولی بنویسید
             .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            fabSave.setOnClickListener {
                var name = editName.text.toString()
                var age = editAge.text.toString()

                if(name.isNotEmpty() && age.isNotEmpty()){
                    user = User(0,name,age.toInt())
                    db.getContentDao().insertUser(user)
                    Snackbar.make(it,"Saved",Snackbar.LENGTH_LONG).show()
                    //finish()
                }else{
                    Snackbar.make(it,"Name and Age Can Not be empty",Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}