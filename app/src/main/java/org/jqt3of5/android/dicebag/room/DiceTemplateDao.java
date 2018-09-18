package org.jqt3of5.android.dicebag.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Brittany on 4/28/2018.
 */
@Dao
public interface DiceTemplateDao
{
    @Query("SELECT * FROM diceTemplates")
    List<DiceTemplateEntity> getAll();

    @Query("SELECT * FROM diceTemplates WHERE templateId = :diceId")
    List<DiceTemplateEntity> loadAllByIds(int diceId);

    @Insert
    void insertAll(List<DiceTemplateEntity> diceTemplates);

    @Delete
    void delete(DiceTemplateEntity dice);
}