package org.jqt3of5.android.dicebag.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Brittany on 4/28/2018.
 */
@Dao
public interface DiceRollsDao
{
    @Query("SELECT * FROM dicebag")
    LiveData<List<DiceBagEntity>> getDiceBags();

    @Query("SELECT * FROM diceroll WHERE bagid = :bagId AND parentId = null")
    LiveData<List<DiceRollEntity>> getRollsForBag(Long bagId);

    @Query("SELECT * FROM dice WHERE rollId = :rollId")
    List<DiceEntity> getDiceForRoll(Long rollId);

    @Query("SELECT * from diceroll WHERE parentId = :rollId")
    List<DiceRollEntity> getSubRollForRoll(Long rollId);

    @Insert
    void insert(DiceEntity dice);
    @Insert
    void insert(DiceRollEntity roll);
    @Insert
    void insert(DiceBagEntity bag);

    @Delete
    void delete(DiceEntity dice);
    @Delete
    void delete(DiceRollEntity roll);
    @Delete
    void delete(DiceBagEntity bag);

}