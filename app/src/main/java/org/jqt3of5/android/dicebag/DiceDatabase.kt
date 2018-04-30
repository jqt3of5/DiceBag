package org.jqt3of5.android.dicebag

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by Brittany on 4/29/2018.
 */
@Database(entities = arrayOf(DiceTemplateEntity::class, DiceInPlayEntity::class),version = 1)
abstract class DiceDatabase : RoomDatabase
{
    constructor() : super()


    public abstract fun getDiceInPlay() : DiceInPlayDao
    public abstract fun getDiceTemplates() : DiceDao
}