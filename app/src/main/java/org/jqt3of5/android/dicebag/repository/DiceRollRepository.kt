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

class DiceRollRepository {

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
            dice.bagId = roll.rollEntity.bagid
            dice.rollId = roll.rollEntity.id
            dice.id = database.diceRolls().insert(dice)
        }

        for (subroll in roll.subrolls)
        {
            addDiceRoll(subroll)
        }
    }

    @Transaction
    fun updateDiceRoll(roll: DiceRoll)
    {
        database.diceRolls().insert(roll.rollEntity)

        for (dice in roll.diceEntities)
        {
            database.diceRolls().insert(dice)
        }

        for (subroll in roll.subrolls)
        {
            updateDiceRoll(subroll)
        }
    }

    fun getDiceRoll (rollId : Long) : LiveData<DiceRoll>
    {
        val rollLiveData = database.diceRolls().getRollForId(rollId)
        val liveData = MutableLiveData<DiceRoll>()

        return Transformations.switchMap(rollLiveData) {

            GlobalScope.launch {
                liveData.postValue(createDiceRoll(it))
            }
            liveData
        }
    }

    fun getDiceRolls(bagId : Long) : LiveData<List<DiceRoll>>
    {
        val rollsLiveData = database.diceRolls().getRollsForBag(bagId)
        val liveData = MutableLiveData<List<DiceRoll>>()

        return Transformations.switchMap(rollsLiveData) {

            GlobalScope.launch {
                liveData.postValue(createDiceRollsForEntities(it))
            }
            liveData
        }
    }

    private suspend fun createDiceRollsForEntities(entities : List<DiceRollEntity>) : List<DiceRoll>
    {
        return GlobalScope.async {
            val list = mutableListOf<DiceRoll>()
            for (entity in  entities)
            {
                list.add(createDiceRoll(entity))
            }
            list
        }.await()
    }

    private fun createDiceRoll(entity : DiceRollEntity) : DiceRoll
    {
        val dice = database.diceRolls().getDiceForRoll(entity.id)
        val subrolls = database.diceRolls().getSubRollForRoll(entity.id).map {
            createDiceRoll(it)
        }

        return DiceRoll(entity, dice, subrolls)
    }
}