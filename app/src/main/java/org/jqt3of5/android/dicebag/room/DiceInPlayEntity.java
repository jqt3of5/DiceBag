package org.jqt3of5.android.dicebag.room;

import android.arch.persistence.room.*;

/**
 * Created by Brittany on 4/28/2018.
 */
@Entity(tableName = "DiceInPlay")
public class DiceInPlayEntity
{
    @PrimaryKey(autoGenerate = true)
    public int diceId;

    @ForeignKey(entity= DiceTemplateEntity.class,
            parentColumns = {"templateId"},
            childColumns = {"diceTemplateId"}
            /*, onDelete = ForeignKey.CASCADE*/)
    public int diceTemplateId;

    @ColumnInfo
    public String description;

    @ColumnInfo
    public String value;

    @ColumnInfo
    public int mode;
}