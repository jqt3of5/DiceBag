package org.jqt3of5.android.dicebag;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.jqt3of5.android.dicebag.room.FullDice;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Brittany on 7/16/2017.
 */

public class DiceAdapter extends BaseAdapter {

    private Context mContext;
    private DiceViewModel mViewModel;

    public DiceAdapter(Context c, DiceViewModel viewModel)
    {
        mContext = c;
        mViewModel = viewModel;
    }
    @Override
    public int getCount() {
        return mViewModel.getDice().getValue().size();
    }

    @Override
    public Object getItem(int i) {
        return mViewModel.getDice().getValue().get(i);
    }

    @Override
    public long getItemId(int i) {
        return mViewModel.getDice().getValue().get(i).dice.diceId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.dice_cell, null);
        }
        TextView textViewTitle = (TextView)view.findViewById(R.id.tv_dice_title);
        TextView textViewValue = (TextView)view.findViewById(R.id.tv_dice_number);

        FullDice dice = mViewModel.getDice().getValue().get(i);

        String description = dice.dice.description;
        String templateName = dice.template.name;

        if (!description.isEmpty() || !templateName.isEmpty())
        {
            textViewTitle.setVisibility(View.VISIBLE);
        }

        String title = "";
        if (!description.isEmpty())
        {
            title = description;
        }

        if (!description.isEmpty() && !templateName.isEmpty())
        {
            title += "-";
        }

        if (!templateName.isEmpty())
        {
            title += templateName;
        }

        textViewValue.setText(title);
        return view;
    }

}
