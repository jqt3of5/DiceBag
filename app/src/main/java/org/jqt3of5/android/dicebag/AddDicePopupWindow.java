package org.jqt3of5.android.dicebag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * Created by Brittany on 7/19/2017.
 */

public class AddDicePopupWindow extends PopupWindow {

    View.OnClickListener mClickListener;
    public void setOnItemClickListener(View.OnClickListener listener)
    {
        mClickListener = listener;
        ImageView iv_d4 = (ImageView)this.getContentView().findViewById(R.id.iv_popup_d4);
        iv_d4.setOnClickListener(mClickListener);
        ImageView iv_d6 = (ImageView)this.getContentView().findViewById(R.id.iv_popup_d6);
        iv_d6.setOnClickListener(mClickListener);
        ImageView iv_d8 = (ImageView)this.getContentView().findViewById(R.id.iv_popup_d8);
        iv_d8.setOnClickListener(mClickListener);
        ImageView iv_d10 = (ImageView)this.getContentView().findViewById(R.id.iv_popup_d10);
        iv_d10.setOnClickListener(mClickListener);
        ImageView iv_d12 = (ImageView)this.getContentView().findViewById(R.id.iv_popup_d12);
        iv_d12.setOnClickListener(mClickListener);
        ImageView iv_d20 = (ImageView)this.getContentView().findViewById(R.id.iv_popup_d20);
        iv_d20.setOnClickListener(mClickListener);
    }

    public AddDicePopupWindow(Context context)
    {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.available_dice_layout, null);

        setContentView(view);
        setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
    }
}
