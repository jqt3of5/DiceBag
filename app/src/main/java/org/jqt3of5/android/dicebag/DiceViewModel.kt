package org.jqt3of5.android.dicebag

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.persistence.room.Room
import org.jqt3of5.android.dicebag.data.DiceRoll
import org.jqt3of5.android.dicebag.room.*

/**
 * Created by Brittany on 4/29/2018.
 */

class DiceBagViewModel : AndroidViewModel
{
    private var database : DiceDatabase
    private lateinit var diceData : LiveData<List<DiceRollEntity>>

    constructor(application : Application) : super(application)
    {
        database = Room.databaseBuilder<DiceDatabase>(application.applicationContext, DiceDatabase::class.java, "db.sqlite").build()
    }

    fun getDiceRolls(bagId : Long) : LiveData<List<DiceRollEntity>>
    {
        if (diceData == null)
        {
            diceData = database.diceRolls().getRollsForBag(bagId)
        }

        return diceData
    }
}