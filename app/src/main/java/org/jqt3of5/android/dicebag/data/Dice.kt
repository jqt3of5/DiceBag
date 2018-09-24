package org.jqt3of5.android.dicebag.data

import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import org.jqt3of5.android.dicebag.room.DiceBagEntity
import org.jqt3of5.android.dicebag.room.DiceEntity
import org.jqt3of5.android.dicebag.room.DiceRollEntity
import org.jqt3of5.android.dicebag.room.DiceRollsDao

class DiceRoll(val roll : DiceRollEntity, val dice : List<DiceEntity>, val subrolls : List<DiceRoll>)
{
    companion object {
        fun Create(roll : DiceRollEntity, dao : DiceRollsDao) : DiceRoll
        {
            val dice = dao.getDiceForRoll(roll.id)
            val subrolls = dao.getSubRollForRoll(roll.id).map {
                DiceRoll.Create(it, dao)
            }
            return DiceRoll(roll, dice, subrolls)
        }
    }
}



