/*package org.jqt3of5.android.dicebag;

import java.util.ArrayList;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

 * Created by Brittany on 7/30/2017.

public class DiceDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_FILE="dice.db";

    class DiceTable
    {
        public static final String CREATE_TABLE="CREATE TABLE IF NOT EXISTS " + DiceTable.TABLE_NAME
                + " ("+DiceTable.ID_COLUMN+ " INTEGER PRIMARY KEY,"
                + DiceTable.TYPE_COLUMN + " INTEGER," + DiceTable.NAME_COLUMN + " VARCHAR(64),"
                + DiceTable.LABELS_COLUMN + " VARCHAR(64))";

        public static final String DELETE_TABLE="DROP TABLE IF EXISTS " + DiceTable.TABLE_NAME;

        public static final String TABLE_NAME="dice";
        public static final String ID_COLUMN="id";
        public static final String TYPE_COLUMN="type";
        public static final String NAME_COLUMN="name";
        public static final String LABELS_COLUMN="labels";
    }

    class DiceInPlayTable
    {
        public static final String CREATE_TABLE="CREATE TABLE IF NOT EXISTS " + DiceInPlayTable.TABLE_NAME
                + " ("+DiceInPlayTable.ID_COLUMN+ " INTEGER PRIMARY KEY,"
                + DiceInPlayTable.DICE_ID_COLUMN + " INTEGER  FOREIGN KEY REFERENCES " + DiceTable.TABLE_NAME + "(" + DiceTable.ID_COLUMN + "),"
                + DiceInPlayTable.TITLE_COLUMN + " VARCHAR(64))";

        public static final String DELETE_TABLE="DROP TABLE IF EXISTS " + DiceInPlayTable.TABLE_NAME;

        public static final String TABLE_NAME="in_play";

        public static final String ID_COLUMN="id";
        public static final String DICE_ID_COLUMN="dice_id";
        public static final String TITLE_COLUMN="title";
    }


    public DiceDbHelper(Context context)
    {
        super(context, DATABASE_FILE, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(DiceTable.CREATE_TABLE);
        sqLiteDatabase.execSQL(DiceInPlayTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DiceTable.DELETE_TABLE);
        sqLiteDatabase.execSQL(DiceInPlayTable.DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public ArrayList<DiceItem> getAllDiceInPlay()
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("select * from %s JOIN %S WHERE %s = %s",DiceTable.TABLE_NAME, DiceInPlayTable.TABLE_NAME, DiceTable.ID_COLUMN, DiceInPlayTable.DICE_ID_COLUMN);
        Cursor results = db.rawQuery(query, null);

        ArrayList<DiceItem> diceNames = new ArrayList<DiceItem>();
        results.moveToFirst();
        while(!results.isAfterLast())
        {
            String title = results.getString(results.getColumnIndex(DiceInPlayTable.TITLE_COLUMN));
            String name = results.getString(results.getColumnIndex(DiceTable.NAME_COLUMN));
            String type_str = results.getString(results.getColumnIndex(DiceTable.TYPE_COLUMN));
            DiceItem.StandardDiceEnum type = DiceItem.StandardDiceEnum.valueOf(type_str);
            String label_str = results.getString(results.getColumnIndex(DiceTable.LABELS_COLUMN));
            String [] labels = label_str.split(",");

            DiceItem item = new DiceItem(title, name, type, labels);
            diceNames.add(item);
            results.moveToNext();
        }
        return diceNames;
    }
}
*/