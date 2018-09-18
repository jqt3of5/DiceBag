package org.jqt3of5.android.dicebag.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "dice")
class DiceEntity(var name :String, var modifier : Int,var max : Int,var min : Int,
                 @ForeignKey(entity = DiceRollEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("rollId"))
                 val rollId : Long) {
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0
}