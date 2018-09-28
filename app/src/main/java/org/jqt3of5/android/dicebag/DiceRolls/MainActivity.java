package org.jqt3of5.android.dicebag.DiceRolls;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;

import org.jqt3of5.android.dicebag.DiceRolls.edit.DicePropertiesActivity;
import org.jqt3of5.android.dicebag.R;
import org.jqt3of5.android.dicebag.repository.DiceRoll;
import org.jqt3of5.android.dicebag.room.DiceEntity;
import org.jqt3of5.android.dicebag.room.DiceRollEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String BAG_ID_KEY = "BagIdKey";


    DiceBagViewModel viewModel;
    GridView mMainDiceGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewModel = ViewModelProviders.of(this).get(DiceBagViewModel.class);
        if (savedInstanceState != null)
        {
            viewModel.setMBagId(savedInstanceState.getLong(BAG_ID_KEY));
        }
        else
        {
            viewModel.setMBagId(getIntent().getLongExtra(BAG_ID_KEY, 1));
        }

        final DiceAdapter diceAdapter = new DiceAdapter(this);

        viewModel.getDiceRolls().observe(this, new Observer<List<DiceRoll>>() {
            @Override
            public void onChanged(@Nullable List<DiceRoll> diceRolls) {
                diceAdapter.setDiceRolls(diceRolls);
            }
        });

        mMainDiceGrid = findViewById(R.id.main_dice_grid);
        mMainDiceGrid.setAdapter(diceAdapter);
        mMainDiceGrid.setLongClickable(true);
        mMainDiceGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Roll dice
                ((DiceRoll)diceAdapter.getItem(i)).roll();
                mMainDiceGrid.invalidateViews();
            }
        });
        mMainDiceGrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DicePropertiesActivity.class);
                intent.putExtra(DicePropertiesActivity.ROLL_ID_KEY, ((DiceRoll)diceAdapter.getItem(i)).getId());
                startActivity(intent);
                return false;
            }
        });


        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Temp code to test things
                DiceRollEntity drEntity = new DiceRollEntity("Test", 2);
                drEntity.setBagid(viewModel.getMBagId());

                DiceEntity dice = new DiceEntity("Dice 1", 1, 8, 1);
                DiceEntity dice1 = new DiceEntity("Dice 2", 1, 6, 1);
                DiceEntity dice2 = new DiceEntity("Dice 3", 6, 12, 1);
                final DiceRoll roll = new DiceRoll(drEntity, Arrays.asList(dice, dice1, dice2), new ArrayList<DiceRoll>());

                new AsyncTask<Void, Void, String>() {

                    @Override
                    protected String doInBackground(Void... voids) {
                        viewModel.getDiceRollRepository().addDiceRoll(roll);
                        return null;
                    }
                }.execute();

                mMainDiceGrid.invalidateViews();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(BAG_ID_KEY, viewModel.getMBagId());
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
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
