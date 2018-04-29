package org.jqt3of5.android.dicebag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Brittany on 7/21/2017.
 */

public class AddDicePopupMenu extends PopupMenuWithIcons {

    public AddDicePopupMenu(@NonNull Context context, @NonNull View anchor) {
        super(context, anchor);
        Menu menu = getMenu();
        for (DiceItem.StandardDiceEnum dice : DiceItem.StandardDiceEnum.values()) {
            DiceItem item = DefaultDiceBuilder.createStandardDice(dice);
            int i = dice.ordinal();
            menu.add(1, i, i, item.mName).setIcon(item.getIconResource());
        }
    }
}
