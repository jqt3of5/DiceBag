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
public interface DiceInPlayDao
{
    @Query("SELECT * FROM DiceInPlay INNER JOIN DiceTemplates ON DiceInPlay.diceTemplateId = DiceTemplates.templateId")
    LiveData<List<FullDice>> getAllDiceInPlay();

    @Query("SELECT * FROM DiceInPlay INNER JOIN DiceTemplates ON DiceInPlay.diceTemplateId = DiceTemplates.templateId WHERE DiceInPlay.diceId = :diceId")
    FullDice getById(int diceId);

    @Insert
    void insertAll(List<DiceInPlayEntity> dice);

    @Delete
    void delete(DiceInPlayEntity dice);
}