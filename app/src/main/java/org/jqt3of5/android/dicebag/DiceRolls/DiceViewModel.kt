package org.jqt3of5.android.dicebag.DiceRolls

import android.app.Application
import android.arch.lifecycle.*
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch
import org.jqt3of5.android.dicebag.repository.DiceRoll
import org.jqt3of5.android.dicebag.repository.DiceRollRepository

/**
 * Created by Brittany on 4/29/2018.
 */

class DiceBagViewModel : AndroidViewModel {

    var mBagId: Long = 0
    var diceRollRepository: DiceRollRepository

    private  var diceRollLiveData : MutableLiveData<List<DiceRoll>>
    private var diceRolls : List<DiceRoll>? = null
    private var diceRollValueLiveData : MutableLiveData<Int>

    constructor(application: Application) : super(application) {
        diceRollRepository = DiceRollRepository(application.applicationContext)
        diceRollLiveData = MutableLiveData()
        diceRollValueLiveData = MutableLiveData()
    }

    fun updateDiceRoll(roll : DiceRoll)
    {
        GlobalScope.launch {
            diceRollRepository.updateDiceRoll(roll)
        }

    }

    fun deleteDiceRoll(roll: DiceRoll)
    {
        diceRollRepository.deleteDiceRoll(roll)
    }

    fun addDiceRoll(roll : DiceRoll)
    {
        diceRollRepository.deleteDiceRoll(roll)
    }

    fun getDiceRolls(): LiveData<List<DiceRoll>> {

        var liveData = diceRollRepository.getDiceRolls(mBagId)
        return Transformations.switchMap(liveData) {
            diceRolls?.let { oldRolls ->

                for(newRoll in it)
                {
                    oldRolls.find { newRoll.rollEntity.id == it.rollEntity.id }?.let {
                        newRoll.rollValue = it.rollValue

                        for (newDice in newRoll.diceEntities)
                        {
                            it.diceEntities.find { newDice.id == it.id }?.let {

                                newDice.rollValue = it.rollValue
                            }
                        }
                    }
                }
            }

            diceRolls = it
            diceRollLiveData.postValue(diceRolls)

            diceRollLiveData
        }
    }

    fun getDiceRollForId(rollId : Long) : LiveData<DiceRoll>
    {
        return diceRollRepository.getDiceRollForId(rollId)
    }

    fun getDiceRollValues() : LiveData<Int>
    {
        return diceRollValueLiveData
    }

    fun roll(i : Int)
    {
        diceRolls?.let {
            it[i].roll()

            diceRollValueLiveData.postValue(i)
        }
    }
}