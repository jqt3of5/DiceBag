package org.jqt3of5.android.dicebag

import android.arch.lifecycle.LiveData
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
    @Query("SELECT * FROM DiceInPlay NATURAL JOIN DiceTemplates")
    fun getAll() : LiveData<List<Dice>>

    @Query("SELECT * FROM DiceInPlay NATURAL JOIN DiceTemplates WHERE diceId = :diceId")
    fun getById(diceId : Int) : LiveData<Dice>

    @Insert
    fun insertAll(dice : Array<DiceInPlayEntity>)

    @Delete
    fun delete(dice : DiceInPlayEntity)
}