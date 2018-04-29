/*package org.jqt3of5.android.dicebag;


 * Created by Brittany on 7/29/2017.


public class DefaultDiceBuilder
{
    public static DiceItem createStandardDice(DiceItem.StandardDiceEnum diceType)
    {
        switch(diceType)
        {
            case D4:
                return new D4Dice();
            case D6:
                return new D6Dice();
            case D8:
                return new D8Dice();
            case D10:
                return new D10Dice();
            case D100:
                return new D100Dice();
            case D12:
                return new D12Dice();
            case D20:
                return new D20Dice();
        }

        return null;
    }
}
//TODO: These will likely be converted to using JSON
class D4Dice extends DiceItem
{
    public D4Dice() {
        super("D4", StandardDiceEnum.D4, new String[]{"1","2","3","4"});
    }
}
class D6Dice extends DiceItem
{
    public D6Dice() {
        super("D6",StandardDiceEnum.D6, new String[]{"1","2","3","4","5","6"});
    }
}
class D8Dice extends DiceItem
{
    public D8Dice() {
        super("D8", StandardDiceEnum.D8, new String[]{"1","2","3","4","5","6", "7", "8"});
    }
}
class D10Dice extends DiceItem
{
    public D10Dice() {
        super("D10", StandardDiceEnum.D10, new String[]{"0","1","2","3","4","5","6","7","8","9"});
    }
}

class D100Dice extends DiceItem
{
    public D100Dice() {
        super("D100", StandardDiceEnum.D100, new String[]{"00","10","20","30","40","50","60","70","80","90"});
    }
}
class D12Dice extends DiceItem
{
    public D12Dice() {
        super("D12",StandardDiceEnum.D12, new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"});
    }
}
class D20Dice extends DiceItem
{
    public D20Dice() {
        super("D20", StandardDiceEnum.D20, new String[]{
                "1","2","3","4","5","6","7","8","9","10","11",
                "12","13","14","15","16","17","18","19","20"});
    }
}
*/