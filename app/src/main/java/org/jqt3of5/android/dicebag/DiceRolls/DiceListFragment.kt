package org.jqt3of5.android.dicebag.DiceRolls

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import org.jqt3of5.android.dicebag.DiceRolls.edit.DicePropertiesFragment
import org.jqt3of5.android.dicebag.R
import org.jqt3of5.android.dicebag.repository.DiceRoll

class DiceListFragment : android.support.v4.app.Fragment() {

    lateinit var _diceGrid : GridView
    lateinit var _viewModel : DiceBagViewModel
    lateinit var _diceAdapter : DiceAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater?.inflate(R.layout.dice_list_fragment, container, false)!!

        _diceGrid = view.findViewById(R.id.main_dice_grid)
        _diceGrid.setAdapter(_diceAdapter)
        _diceGrid.setLongClickable(true)
        _diceGrid.onItemClickListener = AdapterView.OnItemClickListener() {parent, view, position, id ->
            _viewModel.roll(position)
        }

        _diceGrid.onItemLongClickListener = AdapterView.OnItemLongClickListener() {parent, view, position, id ->
            activity?.let{
                val manager = it.supportFragmentManager
                val dialog = DicePropertiesFragment()
                val bundle = Bundle()
                bundle.putLong(DicePropertiesFragment.ROLL_ID_KEY, id)
                dialog.arguments = bundle
                dialog.show(manager, "sdf")
            }
             true
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.let {

            _viewModel = ViewModelProviders.of(it).get(DiceBagViewModel::class.java)

            _diceAdapter = DiceAdapter(it)

            _viewModel.getDiceRolls().observe(it, object : Observer<List<DiceRoll>> {
                override fun onChanged(diceRolls: List<DiceRoll>?) {
                    _diceAdapter.setDiceRolls(diceRolls)
                }
            })

            _viewModel.getDiceRollValues().observe(it,  object : Observer<Int> {
                override fun onChanged(integer: Int?) {
                    _diceAdapter.notifyDataSetChanged()
                }
            })
        }
    }
}