package org.jqt3of5.android.dicebag.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "dicebag")
class DiceBagEntity(var name : String) {
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0
}