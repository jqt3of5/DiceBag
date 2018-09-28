package org.jqt3of5.android.dicebag.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "diceroll")
class DiceRollEntity (var name :String,
                      var modifier : Int)
{
    @PrimaryKey (autoGenerate = true)
    var id : Long = 0

    @ForeignKey(entity = DiceBagEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("bagid"))
    var bagid : Long = 0

    var parentId : Long? = null
}