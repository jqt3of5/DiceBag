package org.jqt3of5.android.dicebag.DiceRolls

import android.app.Application
import android.arch.lifecycle.*
import org.jqt3of5.android.dicebag.repository.DiceRoll
import org.jqt3of5.android.dicebag.repository.DiceRollRepository

/**
 * Created by Brittany on 4/29/2018.
 */

class DiceBagViewModel : AndroidViewModel {
    var mBagId: Long = 0
    var diceRollRepository: DiceRollRepository
    private var diceRolls: List<DiceRoll>? = null

    constructor(application: Application) : super(application) {
        diceRollRepository = DiceRollRepository(application.applicationContext)
    }

    fun getDiceRolls(): LiveData<List<DiceRoll>> {
        val liveData = MutableLiveData<List<DiceRoll>>()

        if (diceRolls != null)
        {
            liveData.postValue(diceRolls)
        }

        Transformations.switchMap(diceRollRepository.getDiceRolls(mBagId)) {
            if (diceRolls != null) {
                copyRollValues(diceRolls!!, it)
            }

            diceRolls = it

            liveData.postValue(it)

            liveData
        }

        return liveData
    }

    private fun copyRollValues(oldRolls : List<DiceRoll>, newRolls : List<DiceRoll>)
    {
        //For each diceroll, we want to copy over the old value if it exists in the current viewmodel list
        newRolls.forEach {newRoll ->
            oldRolls?.find {oldRoll ->
                newRoll.id == oldRoll.id
            }?.let {oldRoll ->
                //Copy Value
                newRoll.rollValue = oldRoll.rollValue

                //Copy Dice
                newRoll.diceEntities.forEach { newDice ->
                    oldRoll.diceEntities.find { oldDice ->
                        oldDice.id == newDice.id
                    }?.let { oldDice ->
                        //Copy Dice Value
                        newDice.rollValue = oldDice.rollValue
                    }
                }

                //Copy subrolls
                copyRollValues(oldRoll.subrolls, newRoll.subrolls)
            }
        }
    }


}