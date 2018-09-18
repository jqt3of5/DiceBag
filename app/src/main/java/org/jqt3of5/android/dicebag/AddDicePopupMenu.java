package org.jqt3of5.android.dicebag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import org.jqt3of5.android.dicebag.room.*;
import java.util.List;

/**
 * Created by Brittany on 7/21/2017.
 */

public class AddDicePopupMenu extends PopupMenuWithIcons {

    public AddDicePopupMenu(@NonNull Context context, @NonNull View anchor, List<DiceTemplateEntity> diceTemplates) {
        super(context, anchor);
        Menu menu = getMenu();
        for (DiceTemplateEntity template :diceTemplates) {

            int i = diceTemplates.indexOf(template);
            menu.add(1, i, i, template.name).setIcon(template.getIconResource());
        }
    }
}
