package com.example.a3_nroom.datbase

import androidx.room.Database
import androidx.room.RoomDatabase


//دیتابیس برای تشکیل شدن به دوچیز نیازمند است یکی جدول (Entity) و یکی توابعی که برای کار بر روی جدول نیاز است(Dao) که هردو را به ان میدهیم
//دقت کنید اگر کوچکترین تغییری پس از خروجی گرفتن در (Entity) دادیم باید برای خروجی بعدی حتما یک عدد به ورژن دیتابیس اضافه کنیم
//مقادیر درون یک کلاس (abstract) هم باید (abstract) باشند.دیتابیس (Room) باید (abstract) باشد


@Database(entities = [User::class] , version = 1)
abstract class UserDatabase():RoomDatabase() {
    abstract fun getContentDao():UserDao
}