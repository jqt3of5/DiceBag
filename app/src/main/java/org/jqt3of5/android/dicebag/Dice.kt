package org.jqt3of5.android.dicebag

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity

/**
 * Created by Brittany on 4/29/2018.
 */
@Entity
class Dice
{
    @Embedded
    public var dice : DiceInPlayEntity? = null

    @Embedded
    public var template : DiceTemplateEntity? = null
}