package com.example.a3_nroom.datbase

import androidx.room.Entity
import androidx.room.PrimaryKey
//استفاده از (ColumnInfo@) اجباری نیست و جدول با همین نامهای (id,name,age) ساخته میشود
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val age:Int
)