package kijja.amityexam.todolist.data.model

import com.google.gson.annotations.SerializedName

data class TodoListEntity(
    @SerializedName("userId")
    val userId: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("completed")
    val status: Boolean? = null,
)
