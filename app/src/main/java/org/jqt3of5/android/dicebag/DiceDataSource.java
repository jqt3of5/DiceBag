/*package org.jqt3of5.android.dicebag;

import java.util.ArrayList;


 * Created by Brittany on 7/21/2017.


public class DiceDataSource extends ArrayList<DiceItem> {

    public DiceDataSource(ArrayList<DiceItem> dice)
    {
        addAll(dice);
    }
    public int getCountForDice(DiceItem.StandardDiceEnum diceType)
    {
        int count = 0;
        for (DiceItem item :
                this) {
            if (item.mType == diceType)
            {
                count += 1;
            }
        }
        return count;
    }

    public void rollAllDice()
    {
        for (DiceItem die :
                this) {
            die.rollDice();
        }
    }

    public void rollDiceAtIndex(int index)
    {
        get(index).rollDice();
    }
}
*/