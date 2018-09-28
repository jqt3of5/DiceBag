package org.jqt3of5.android.dicebag.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Brittany on 4/29/2018.
 */
@Database(entities = {DiceRollEntity.class, DiceBagEntity.class, DiceEntity.class},version = 1)
public abstract class DiceDatabase extends RoomDatabase
{
    private static DiceDatabase database;
    public static DiceDatabase getInstance(Context context)
    {
        if (database == null)
        {
            database = Room.databaseBuilder(context, DiceDatabase.class, "db.sqlite").build();
        }

        return database;
    }

    public abstract DiceRollsDao diceRolls();
}

