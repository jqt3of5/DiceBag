package org.jqt3of5.android.dicebag.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.persistence.room.Transaction
import android.content.Context
import kotlinx.coroutines.experimental.*
import org.jqt3of5.android.dicebag.room.DiceBagEntity
import org.jqt3of5.android.dicebag.room.DiceDatabase
import org.jqt3of5.android.dicebag.room.DiceRollEntity

class DiceRollRepository  {

    private val database : DiceDatabase

    constructor(context : Context)
    {
        database = DiceDatabase.getInstance(context)
    }

    @Transaction
    fun addDiceBag(bagname : String)
    {
        val bag = DiceBagEntity(bagname)
        val id = database.diceRolls().insert(bag)

    }

    @Transaction
    fun addDiceRoll(roll: DiceRoll) {

        roll.rollEntity.id = database.diceRolls().insert(roll.rollEntity)
        for (dice in roll.diceEntities) {
            dice.rollId = roll.rollEntity.id
            dice.id = database.diceRolls().insert(dice)
        }

       /* for (subroll in roll.subrolls)
        {
            addDiceRoll(subroll)
        }*/
    }

    @Transaction
    fun updateDiceRoll(roll: DiceRoll)
    {
        database.diceRolls().insert(roll.rollEntity)

        for (dice in roll.diceEntities)
        {
            database.diceRolls().insert(dice)
        }

        /*for (subroll in roll.subrolls)
        {
            updateDiceRoll(subroll)
        }*/
    }


    @Transaction
    fun deleteDiceRoll(roll: DiceRoll)
    {
        for (dice in roll.diceEntities)
        {
            database.diceRolls().delete(dice)
        }

        database.diceRolls().delete(roll.rollEntity)
        /*for (subroll in roll.subrolls)
        {
            updateDiceRoll(subroll)
        }*/
    }



    fun getDiceRolls(bagId : Long) : LiveData<List<DiceRoll>>
    {
        return database.diceRolls().getRollsForBag(bagId)
    }

    fun getDiceBags() : LiveData<List<DiceBagEntity>>
    {
        return database.diceRolls().diceBags
    }
}