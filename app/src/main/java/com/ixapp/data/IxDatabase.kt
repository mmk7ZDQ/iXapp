package com.ixapp.data

import androidx.room.*
import java.util.*

@Entity
data class Message(
    @PrimaryKey val id: String = java.util.UUID.randomUUID().toString(),
    val type: String,
    val content: String,
    val timestamp: Long,
    val fromMe: Boolean
)

@Entity
data class Contact(
    @PrimaryKey val id: String,
    val name: String,
    val avatarPath: String?
)

@Dao
interface MessageDao {
    @Query("SELECT * FROM Message ORDER BY timestamp ASC")
    fun getAll(): List<Message>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(msg: Message)

    @Delete
    fun delete(msg: Message)
}

@Database(entities = [Message::class, Contact::class], version = 1)
abstract class IxDatabase: RoomDatabase() {
    abstract fun messageDao(): MessageDao
}
