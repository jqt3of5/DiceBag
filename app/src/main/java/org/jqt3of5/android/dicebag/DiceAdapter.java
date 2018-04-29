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

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Brittany on 7/16/2017.
 */

public class DiceAdapter extends BaseAdapter {

    private Context mContext;
    private DiceDataSource mDataSource;

    public DiceAdapter(Context c, DiceDataSource dataSource)
    {
        mContext = c;
        mDataSource = dataSource;
    }
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
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

        DiceItem die = mDataSource.get(i);

        if (!die.mTitle.isEmpty() || !die.mName.isEmpty())
        {
            textViewTitle.setVisibility(View.VISIBLE);
        }

        if (!die.mTitle.isEmpty() && !die.mName.isEmpty()) {
            textViewTitle.setText(die.mTitle + "-" + die.mName);
        }
        else if (!die.mName.isEmpty())
        {
            textViewTitle.setText(die.mName);
        }
        else if (!die.mTitle.isEmpty())
        {
            textViewTitle.setText(die.mTitle);
        }

        textViewValue.setText(die.mCurrentValue);
        return view;
    }

}
