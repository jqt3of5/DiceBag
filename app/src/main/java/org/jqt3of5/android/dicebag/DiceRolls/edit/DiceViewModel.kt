package org.jqt3of5.android.dicebag.DiceRolls.edit

import android.app.Application
import android.arch.lifecycle.*
import org.jqt3of5.android.dicebag.repository.DiceRoll
import org.jqt3of5.android.dicebag.repository.DiceRollRepository

/**
 * Created by Brittany on 4/29/2018.
 */

class DiceViewModel : AndroidViewModel {
    var mRollId: Long = 0
    var diceRollRepository: DiceRollRepository
    var diceRoll: LiveData<DiceRoll>? = null

    constructor(application: Application) : super(application) {
        diceRollRepository = DiceRollRepository(application.applicationContext)
    }

    fun getDiceRoll(rollId : Long): LiveData<DiceRoll> {

        if (diceRoll == null)
        {
            diceRoll = diceRollRepository.getDiceRoll(rollId)
        }

        return diceRoll!!
    }
}