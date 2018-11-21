package org.jqt3of5.android.dicebag.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import org.jqt3of5.android.dicebag.repository.DiceRoll;

import java.util.List;

/**
 * Created by Brittany on 4/28/2018.
 */
@Dao
public interface DiceRollsDao
{
    @Query("SELECT * FROM dicebag")
    LiveData<List<DiceBagEntity>> getDiceBags();

    @Transaction
    @Query("SELECT * FROM diceroll  WHERE bagid = :bagId")
    LiveData<List<DiceRoll>> getRollsForBag(Long bagId);

    @Transaction
    @Query("SELECT * from diceroll  WHERE diceroll.id = :rollId")
    LiveData<DiceRoll> getRollForId(Long rollId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(DiceEntity dice);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(DiceRollEntity roll);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(DiceBagEntity bag);

    @Update
    void update(DiceEntity dice);
    @Update
    void update(DiceRollEntity roll);
    @Update
    void update(DiceBagEntity bag);

    @Delete
    void delete(DiceEntity dice);
    @Delete
    void delete(DiceRollEntity roll);
    @Delete
    void delete(DiceBagEntity bag);

}