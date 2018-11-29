package org.jqt3of5.android.dicebag.DiceRolls.edit

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.widget.EditText
import android.widget.LinearLayout
import org.jqt3of5.android.dicebag.room.DiceEntity

class DiceEditRow(context : Context) : LinearLayout(context) {

    private var minText : EditText? = null
    private var maxText : EditText? = null
    private var modifierText : EditText? = null

    var dice : DiceEntity? = null

    val min : Int
        get() = minText?.text.toString().toInt() ?: 0

    val max : Int
        get() = minText?.text.toString().toInt() ?: 0

    val modifier : Int
        get() = minText?.text.toString().toInt() ?: 0

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        val min = EditText(context)
        val max = EditText(context)
        val modifier = EditText(context)
        modifier.setTextColor(Color.rgb(255, 0, 0))

        addView(min)
        addView(max)
        addView(modifier)

        dice?.let {
            min.setText(Integer.toString(it.min))
            max.setText(Integer.toString(it.max))

            modifier.setText("+" + Integer.toString(it.modifier))
        }
    }
}