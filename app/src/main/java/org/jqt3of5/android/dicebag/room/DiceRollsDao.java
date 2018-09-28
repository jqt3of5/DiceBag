package org.jqt3of5.android.dicebag.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Brittany on 4/28/2018.
 */
@Dao
public interface DiceRollsDao
{
    @Query("SELECT * FROM dicebag")
    LiveData<List<DiceBagEntity>> getDiceBags();

    @Query("SELECT * FROM dice WHERE rollId = :rollId")
    List<DiceEntity> getDiceForRoll(Long rollId);

    @Query("SELECT * from diceroll WHERE parentId = :rollId")
    List<DiceRollEntity> getSubRollForRoll(Long rollId);

    @Query("SELECT * from diceroll WHERE id = :rollId")
    DiceRollEntity getRollForId(Long rollId);

    @Query("SELECT * FROM diceroll WHERE bagid = :bagId")
    LiveData<List<DiceRollEntity>> getRollsForBag(Long bagId);

    @Query("SELECT * FROM dice WHERE bagId = :bagId")
    LiveData<List<DiceEntity>> getDiceForBag(Long bagId);


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