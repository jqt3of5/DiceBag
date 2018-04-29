package org.jqt3of5.android.dicebag

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Brittany on 4/28/2018.
 */
@Entity(tableName = "DiceInPlay")
class DiceInPlayEntity
{
    @PrimaryKey
    public var diceId : Int = 0

    @ForeignKey(entity= DiceTemplateEntity::class,
            parentColumns = arrayOf("diceTemplateId"),
            childColumns = arrayOf("diceTemplateId")
            /*, onDelete = ForeignKey.CASCADE*/)
    public var diceTemplateId : Int = 0

    @ColumnInfo(name = "description")
    public var description : String = ""

    @ColumnInfo(name = "value")
    public var value : String = ""

    @ColumnInfo(name = "mode")
    public var mode : Int = 0


}