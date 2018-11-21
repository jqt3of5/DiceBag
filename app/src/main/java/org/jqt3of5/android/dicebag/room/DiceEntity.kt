package org.jqt3of5.android.dicebag.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "dice")
class DiceEntity(var name :String, var modifier : Int,var max : Int,var min : Int) {
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0

    @ForeignKey(entity = DiceRollEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("rollId"))
    var rollId : Long = 0

    @Ignore
    var rollValue : Int? = null

    val numSides : Int
        get () = max - min + 1
}