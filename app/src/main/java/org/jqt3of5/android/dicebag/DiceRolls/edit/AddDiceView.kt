package org.jqt3of5.android.dicebag.DiceRolls.edit

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.*
import org.jqt3of5.android.dicebag.R

class AddDiceView: ConstraintLayout
{
    private val diceTypes = listOf("d4","d6","d8","d10","d12","d20","d100")
    private var nameEditText : EditText
    private var modifierEditText : EditText
    private var typeSpinner : Spinner

    var name : String
        get () = nameEditText.text.toString()
        set(value) {nameEditText.setText(value)}

    var type : String
        get () = diceTypes[typeSpinner.selectedItemPosition]
        set(value) = typeSpinner.setSelection(diceTypes.indexOf(value))

    var modifier : Int
        get () = modifierEditText.text.toString().toInt()
        set(value) = modifierEditText.setText(value.toString())

    var onDelete : (() -> Unit) = {}

    constructor(context : Context, attrs: AttributeSet) : super(context, attrs)
    {
        LayoutInflater.from(context).inflate(R.layout.dice_properties_view, this, true)
        nameEditText = findViewById<EditText>(R.id.dice_name)
        modifierEditText = findViewById<EditText>(R.id.dice_modifier)
        typeSpinner = findViewById<Spinner>(R.id.dice_type_spinner)
    }

    constructor(context : Context) : super(context)
    {
        LayoutInflater.from(context).inflate(R.layout.dice_properties_view, this, true)

        nameEditText = findViewById<EditText>(R.id.dice_name)
        modifierEditText = findViewById<EditText>(R.id.dice_modifier)
        typeSpinner = findViewById<Spinner>(R.id.dice_type_spinner)

        typeSpinner.adapter = ArrayAdapter<String>(context, R.layout.simple_list_item, diceTypes)
        typeSpinner.setSelection(1)

        val image = findViewById<ImageView>(R.id.delete_image)
        image.setOnClickListener {
            onDelete()
        }
    }
}