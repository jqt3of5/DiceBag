package org.jqt3of5.android.dicebag.room;

import android.arch.persistence.room.Embedded;

public class FullDice {
    @Embedded
    public DiceInPlayEntity dice;
    @Embedded
    public DiceTemplateEntity template;
}
