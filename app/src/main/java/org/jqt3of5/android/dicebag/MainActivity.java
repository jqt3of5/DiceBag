package org.jqt3of5.android.dicebag;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    DiceDataSource mDiceDataSource;
    GridView mMainDiceGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DiceDbHelper dbHelper = new DiceDbHelper(this);
        ArrayList<DiceItem> diceInPlay = dbHelper.getAllDiceInPlay();

        mDiceDataSource = new DiceDataSource(diceInPlay);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDiceDataSource.rollAllDice();
                mMainDiceGrid.invalidateViews();
            }
        });

        mMainDiceGrid = (GridView)findViewById(R.id.main_dice_grid);
        DiceAdapter diceAdapter = new DiceAdapter(this, mDiceDataSource);
        mMainDiceGrid.setAdapter(diceAdapter);
        mMainDiceGrid.setLongClickable(true);
        mMainDiceGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mDiceDataSource.rollDiceAtIndex(i);
                mMainDiceGrid.invalidateViews();
            }
        });
        mMainDiceGrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DicePropertiesActivity.class);
                //intent.putExtra()
                startActivity(intent);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id)
        {
            case R.id.action_settings:
                return true;
            case R.id.add_dice:
                PopupMenu menu = new AddDicePopupMenu(this, findViewById(R.id.add_dice));
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                               @Override
                                               public boolean onMenuItemClick(MenuItem item) {
                                                   int id = item.getItemId();
                                                   DiceItem.StandardDiceEnum dice = DiceItem.StandardDiceEnum.values()[id];
                                                   mDiceDataSource.add(DefaultDiceBuilder.createStandardDice(dice));
                                                   mMainDiceGrid.invalidateViews();
                                                   return true;
                                               }
                                           });
                menu.show();
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
