package org.jqt3of5.android.dicebag.DiceRolls;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jqt3of5.android.dicebag.R;
import org.jqt3of5.android.dicebag.repository.DiceRoll;
import org.jqt3of5.android.dicebag.room.DiceEntity;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.HORIZONTAL;

/**
 * Created by Brittany on 7/16/2017.
 */

public class DiceAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<DiceRoll> mDiceRolls;

    public DiceAdapter(Context c)
    {
        mContext = c;
        mDiceRolls = new ArrayList<DiceRoll>();
    }

    public void setDiceRolls(List<DiceRoll> diceRolls)
    {
        mDiceRolls.clear();
        mDiceRolls.addAll(diceRolls);
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return mDiceRolls.size();
    }

    @Override
    public Object getItem(int i) {
        return mDiceRolls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mDiceRolls.get(i).rollEntity.getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.dice_cell, null);
        }
        TextView rollTitle = view.findViewById(R.id.dice_title);
        TextView rollTotal = view.findViewById(R.id.total_roll);
        TextView rollCalc = view.findViewById(R.id.total_roll_value);
        TextView rollmodifier = view.findViewById(R.id.roll_modifier);

        LinearLayout diceList = view.findViewById(R.id.dice_list);

        DiceRoll diceRoll = mDiceRolls.get(i);

        if (!diceRoll.rollEntity.getName().isEmpty())
        {
            rollTitle.setVisibility(View.VISIBLE);
            rollTitle.setText(diceRoll.rollEntity.getName());
        }

        if (diceRoll.getRollValue() != null)
        {
            int total = diceRoll.getRollValue() + diceRoll.rollEntity.getModifier();
            rollTotal.setText("" + total);
            rollCalc.setText("" + diceRoll.getRollValue());
            rollmodifier.setText(" +" + diceRoll.getRollEntity().getModifier());
        }
        else
        {
            rollTotal.setText("?");
            rollCalc.setText("?");
        }

        if (diceRoll.rollEntity.getModifier() >= 0)
        {
            rollmodifier.setText("+" + diceRoll.rollEntity.getModifier());
        }
        else
        {
            rollmodifier.setText(diceRoll.rollEntity.getModifier());
        }


        diceList.removeAllViews();
        for (DiceEntity dice : diceRoll.getDiceEntities())
        {
            diceList.addView(createDiceView(dice));
        }

        return view;
    }

    private View createDiceView(DiceEntity entity)
    {
        LinearLayout parent = new LinearLayout(mContext);
        parent.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        parent.setLayoutDirection(HORIZONTAL);
        TextView diceTextView = new TextView(mContext);
        TextView modifierTextView = new TextView(mContext);
        modifierTextView.setTextColor(Color.RED);

        parent.addView(diceTextView);
        parent.addView(modifierTextView);

        if (entity.getRollValue() != null)
        {
            diceTextView.setText("d" + entity.getNumSides() + ": " + entity.getRollValue());
        }
        else
        {
            diceTextView.setText("d" + entity.getNumSides() + ": ?");
        }

        if (entity.getModifier() >= 0)
        {
            modifierTextView.setText("+" + entity.getModifier());
        }
        else
        {
            modifierTextView.setText(entity.getModifier());
        }

        return parent;


    }

}
