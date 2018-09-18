package org.jqt3of5.android.dicebag

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.persistence.room.Room
import org.jqt3of5.android.dicebag.room.*

/**
 * Created by Brittany on 4/29/2018.
 */

class DiceViewModel : AndroidViewModel
{
    private var database : DiceDatabase
    private lateinit var diceData : LiveData<List<FullDice>>

    public constructor(application : Application) : super(application)
    {
        database = Room.databaseBuilder<DiceDatabase>(application.applicationContext, DiceDatabase::class.java, "db.sqlite").build()
    }

    fun getDice() : LiveData<List<FullDice>>
    {
        if (diceData == null)
        {
            diceData = database.diceInPlay.allDiceInPlay
        }

        return diceData
    }

    fun getDice(id : Int) : FullDice
    {
        return database.diceInPlay.getById(id)
    }

}