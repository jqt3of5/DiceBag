package org.jqt3of5.android.dicebag

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Created by Brittany on 4/28/2018.
 */
@Dao
interface DiceInPlayDao
{
    @Query("SELECT * FROM DiceInPlay")
    fun getAll() : List<DiceTemplateEntity>

    @Query("SELECT * FROM DiceInPlay WHERE diceId IN (:diceIds)")
    fun loadAllByIds(diceIds : Array<Int>) : List<DiceTemplateEntity>

    @Insert
    fun insertAll(dice : Array<DiceInPlayEntity>)

    @Delete
    fun delete(dice : DiceInPlayEntity)
}