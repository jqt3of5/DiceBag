package org.jqt3of5.android.dicebag.DiceRolls.edit;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jqt3of5.android.dicebag.DiceRolls.DiceBagViewModel;
import org.jqt3of5.android.dicebag.R;
import org.jqt3of5.android.dicebag.repository.DiceRoll;

public class DicePropertiesFragment extends Fragment {

    public static String ROLL_ID_KEY = "RollIdKey";

    long _rollId = 0;
    DiceRoll _roll;
    DiceBagViewModel _viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dice_properties_fragment, container, false);
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

    void updateView(DiceRoll roll)
    {

    }

}
