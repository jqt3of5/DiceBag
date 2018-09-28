package org.jqt3of5.android.dicebag.repository

import android.support.v7.util.DiffUtil
import android.support.v7.util.ListUpdateCallback
import org.jqt3of5.android.dicebag.room.DiceEntity
import org.jqt3of5.android.dicebag.room.DiceRollEntity

class DiceRoll(val rollEntity : DiceRollEntity, val diceEntities : List<DiceEntity>, val subrolls : List<DiceRoll>)
{
    val id : Long
        get() = rollEntity.id

    val parentId : Long?
        get() = rollEntity.parentId

    val name : String
        get() = rollEntity.name

    val modifier : Int
        get () = rollEntity.modifier

    var rollValue : Int? = null

    fun roll()
    {
        rollValue = 0
        for (dice in diceEntities)
        {
            val span = dice.max - dice.min + 1
            dice.rollValue = Math.floor(Math.random() * span + dice.min).toInt()
            rollValue = rollValue?.plus(dice.rollValue!! + dice.modifier)
        }

        for(r in subrolls)
        {
            r.roll()
        }
    }

    class DiceRollDiff(val oldList: List<DiceRoll>, val newList: List<DiceRoll>) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            var same: Boolean = areItemsTheSame(oldItemPosition, newItemPosition)
            val old = oldList[oldItemPosition]
            val new = newList[newItemPosition]

            same = same && old.modifier == new.modifier
            same = same && old.name == new.name

            val diff = DiffUtil.calculateDiff(DiceEntityDiff(old.diceEntities, new.diceEntities))
            diff.dispatchUpdatesTo(object: ListUpdateCallback {
                override fun onChanged(position: Int, count: Int, payload: Any?) {
                    same = false
                }

                override fun onMoved(fromPosition: Int, toPosition: Int) { }

                override fun onInserted(position: Int, count: Int) {
                    same = false
                }

                override fun onRemoved(position: Int, count: Int) {
                    same = false
                }
            })

            return same
        }
    }

    class DiceEntityDiff(val oldList: List<DiceEntity>, val newList: List<DiceEntity>) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            var same: Boolean = areItemsTheSame(oldItemPosition, newItemPosition)
            val old = oldList[oldItemPosition]
            val new = newList[newItemPosition]

            same = same && old.modifier == new.modifier
            same = same && old.name == new.name
            same = same && old.max == new.max
            same = same && old.min == new.min

            return same
        }
    }

}



