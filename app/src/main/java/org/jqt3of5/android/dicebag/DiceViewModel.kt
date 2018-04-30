package org.jqt3of5.android.dicebag

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by Brittany on 4/29/2018.
 */

class DiceViewModel : ViewModel
{
    lateinit private var mDiceDao : DiceInPlayDao
    lateinit private var diceData : LiveData<List<Dice>>

    constructor(diceDao: DiceInPlayDao) : super()
    {
        mDiceDao = diceDao
    }

    public fun getDice() : LiveData<List<Dice>>
    {
        if (diceData == null)
        {
            diceData = mDiceDao.getAll()
        }

        return diceData
    }

    public fun getDice(id : Int) : Dice?
    {
        return mDiceDao.getById(id).value
    }

}