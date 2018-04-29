package org.jqt3of5.android.dicebag;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brittany on 7/16/2017.
 */

@Entity(tableName = "DiceTemplates")
public class DiceTemplateEntity {

    @PrimaryKey
    private int diceTemplateId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name="side_labels")
    private String sideLabelString;

    @ColumnInfo(name="type")
    private StandardDiceEnum type;

    public int getDiceTemplateId()
    {
        return diceTemplateId;
    }
    public void setDiceTemplateId(int id)
    {
        diceTemplateId = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String n)
    {
        name = n;
    }
    public String getSideLabelString()
    {
        return sideLabelString;
    }
    public void setSideLabelString(String labels)
    {
        sideLabelString = labels;
    }
    public StandardDiceEnum getType()
    {
        return type;
    }
    public void setType(StandardDiceEnum t)
    {
        type = t;
    }

    public List<String> getSideLabels()
    {
        return new ArrayList<String>();
    }

    public int getIconResource()
    {
        switch (type)
        {
            case D4:
                return R.drawable.d4;
            case D6:
                return R.drawable.d6;
            case D8:
                return R.drawable.d8;
            case D10:
                return R.drawable.d10;
            case D12:
                return R.drawable.d12;
            case D20:
                return R.drawable.d20;
            case D100:
                return R.drawable.d10;
        }

        return 0;
    }

    public enum StandardDiceEnum
    {
        D4, D6, D8, D10, D100, D12, D20
    }
}