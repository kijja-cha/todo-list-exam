package kijja.amityexam.todolist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "todos")
data class TodoListEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("userId")
    val userId: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("completed")
    val status: Boolean? = null,
)
