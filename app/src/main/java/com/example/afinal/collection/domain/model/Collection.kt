package com.example.afinal.collection.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.afinal.ui.theme.*

@Entity
data class Collection(
    @PrimaryKey val id: Int? = null,
    val unlockArray: Array<Boolean>,
    val doneTaskCount: Int,
    val shareFlag: Boolean,
    val instructionFlag: Boolean,
) {
    /*
    companion object{
        val taskColors = listOf(RedTag, YellowTag, GreenTag, BlueTag, PurpleTag)
    }
     */
    // how to create one?
}
/*
is that needed?
class InvalidCollectionException(message: String): Exception(message)
 */
