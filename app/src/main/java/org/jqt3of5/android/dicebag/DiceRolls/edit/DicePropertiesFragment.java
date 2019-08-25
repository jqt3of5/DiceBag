package org.jqt3of5.android.dicebag.DiceRolls.edit;


import android.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.jqt3of5.android.dicebag.DiceRolls.DiceBagViewModel;
import org.jqt3of5.android.dicebag.R;
import org.jqt3of5.android.dicebag.repository.DiceRoll;
import org.jqt3of5.android.dicebag.room.DiceEntity;
import android.arch.lifecycle.*;

import java.util.ArrayList;
import java.util.List;


public class DicePropertiesFragment extends DialogFragment {

    public static String ROLL_ID_KEY = "RollIdKey";

    long _rollId = 0;
    DiceRoll _roll;
    DiceBagViewModel _viewModel;
    EditText _rollNameEditText;
    EditText _rollModifierEditText;
    ViewGroup _diceListView;

    ArrayList<DiceEditRow> _diceRows = new ArrayList<DiceEditRow>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.dice_properties_fragment, container, false);
        _rollNameEditText = view.findViewById(R.id.roll_name);
        _rollModifierEditText = view.findViewById(R.id.roll_modifier);
        _diceListView = view.findViewById(R.id.dice_list);

        Button saveButton = view.findViewById(R.id.save_add_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDice();
                dismiss();
            }
        });

        Button cancelButton = view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        Button addButton = view.findViewById(R.id.add_dice_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiceEntity dice = new DiceEntity("", 0, 6,1);
                _roll.diceEntities.add(dice);
                DiceEditRow row = new DiceEditRow(getContext());
                row.setDice(dice);

                _diceRows.add(row);
                _diceListView.addView(row);
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putLong(ROLL_ID_KEY, _rollId);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null)
        {
            _rollId = savedInstanceState.getLong(ROLL_ID_KEY);
        }
        else
        {
            _rollId = getArguments().getLong(ROLL_ID_KEY);
        }

        if (getActivity() != null)
        {
            _viewModel = ViewModelProviders.of(this).get(DiceBagViewModel.class);
            _viewModel.getDiceRollForId(_rollId).observe(this, new Observer<DiceRoll>() {

                @Override
                public void onChanged(@Nullable DiceRoll diceRoll) {
                    _roll = diceRoll;
                    updateView(_roll);
                }
            });
        }

       // _viewModel.updateDiceRoll(_roll);
    }

    void saveDice()
    {
        _roll.rollEntity.setName(_rollNameEditText.getText().toString());
        //This string.replace stuff is bad
        int modifier = Integer.parseInt(_rollModifierEditText.getText().toString().replace("+", ""));
        _roll.rollEntity.setModifier(modifier);

        for (DiceEditRow row :
                _diceRows) {
            row.getDice().setMin(row.getMin());
            row.getDice().setMax(row.getMax());
            row.getDice().setModifier(row.getModifier());
        }

        _viewModel.updateDiceRoll(_roll);
    }
    void updateView(DiceRoll roll)
    {
        _rollNameEditText.setText(roll.rollEntity.getName());
        _rollModifierEditText.setText("+" + Integer.toString(roll.rollEntity.getModifier()));
        _rollModifierEditText.setTextColor(Color.rgb(255,0,0));

        TextView min_title = new TextView(getContext());
        min_title.setText("Min");
        TextView max_title = new TextView(getContext());
        max_title.setText("Max");
        TextView modifier_title = new TextView(getContext());
        modifier_title.setText("Modifier");

        LinearLayout title_layout = new LinearLayout(this.getActivity());
        title_layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        title_layout.addView(min_title);
        title_layout.addView(max_title);
        title_layout.addView(modifier_title);

        _diceListView.addView(title_layout);

        for (DiceEntity dice :
             roll.diceEntities) {

            DiceEditRow row = new DiceEditRow(getContext());
            _diceRows.add(row);
            row.setDice(dice);
            _diceListView.addView(row);
        }
    }
}
