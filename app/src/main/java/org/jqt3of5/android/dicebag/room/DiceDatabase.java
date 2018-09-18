package org.jqt3of5.android.dicebag.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Brittany on 4/29/2018.
 */
@Database(entities = {DiceInPlayEntity.class, DiceTemplateEntity.class},version = 1)
public abstract class DiceDatabase extends RoomDatabase
{
    public abstract DiceInPlayDao getDiceInPlay();
    public abstract DiceTemplateDao getDiceTemplates();
}