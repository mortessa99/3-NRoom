package com.example.a3_nroom.datbase

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAllUsers()

    @Query("SELECT * FROM user_table WHERE id Like :userId")
    fun getUser(userId:Int):User

    @Query("SELECT * FROM user_table")
    fun getAllUser():MutableList<User>

    //شیوه دیگر نوشتن getAllUser() و قرار دادن ترتیب روی id
    //    @Query("SELECT * FROM user_table ORDER BY id DESC" ) //پیش فرض ASC است که به ترتیب است.DESC برعکس است
    //    fun getAllUserr():MutableList<User>
}