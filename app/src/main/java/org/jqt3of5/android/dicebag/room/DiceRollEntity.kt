package org.jqt3of5.android.dicebag.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "DiceRoll")
class DiceRollEntity (var name :String,
                      var modifier : Int,
                      @ForeignKey(entity = DiceBagEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("bagid"))
                      val bagid : Long)
{
    @PrimaryKey (autoGenerate = true)
    val id : Long = 0
}