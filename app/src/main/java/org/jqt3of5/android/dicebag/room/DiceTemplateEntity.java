package org.jqt3of5.android.dicebag.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import org.jqt3of5.android.dicebag.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brittany on 7/16/2017.
 */

@Entity(tableName = "DiceTemplates")
public class DiceTemplateEntity
{
    public enum StandardDiceEnum{ D4, D6, D8, D10, D100, D12, D20 }

    @PrimaryKey(autoGenerate = true)
    public int templateId;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public String sideLabelString;

    @ColumnInfo
    public int type_index;

    @Ignore
    public List<String> getSideLabels()
    {
        return new ArrayList<String>();
    }

    @Ignore
    public StandardDiceEnum getType()
    {
        return StandardDiceEnum.values()[type_index];
    }

    @Ignore
    public int getIconResource()
    {

        switch (StandardDiceEnum.values()[type_index])
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
}

