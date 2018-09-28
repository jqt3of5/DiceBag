package org.jqt3of5.android.dicebag.DiceRolls.edit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.jqt3of5.android.dicebag.PopupMenuWithIcons;
import org.jqt3of5.android.dicebag.room.*;
import java.util.List;

/**
 * Created by Brittany on 7/21/2017.
 */

public class AddRollPopupMenu extends PopupMenuWithIcons {

    public AddRollPopupMenu(@NonNull Context context, @NonNull View anchor) {
        super(context, anchor);
        Menu menu = getMenu();

    }
}
