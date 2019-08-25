package org.jqt3of5.android.dicebag.DiceRolls.edit

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.widget.EditText
import android.widget.LinearLayout
import org.jqt3of5.android.dicebag.room.DiceEntity

class DiceEditRow: LinearLayout {

    constructor(context: Context) : super (context)
    {
        minText = EditText(context)
        maxText = EditText(context)
        modifierText = EditText(context)
        modifierText?.setTextColor(Color.rgb(255, 0, 0))

        addView(minText)
        addView(maxText)
        addView(modifierText)
    }

    private var minText : EditText? = null
    private var maxText : EditText? = null
    private var modifierText : EditText? = null

    var dice : DiceEntity? = null
        set (value) {
            field = value
            value?.let {
                minText?.setText(Integer.toString(it.min))
                maxText?.setText(Integer.toString(it.max))

                modifierText?.setText("+" + Integer.toString(it.modifier))
            }
        }

    val min : Int
        get() = minText?.text.toString().toInt() ?: 0

    val max : Int
        get() = maxText?.text.toString().toInt() ?: 0

    val modifier : Int
        get() = modifierText?.text.toString().toInt() ?: 0
}