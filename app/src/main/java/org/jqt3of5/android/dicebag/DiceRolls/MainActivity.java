package org.jqt3of5.android.dicebag.DiceRolls;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import org.jqt3of5.android.dicebag.DiceRolls.edit.DicePropertiesFragment;
import org.jqt3of5.android.dicebag.R;
import org.jqt3of5.android.dicebag.repository.DiceRoll;
import org.jqt3of5.android.dicebag.room.DiceEntity;
import org.jqt3of5.android.dicebag.room.DiceRollEntity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String BAG_ID_KEY = "BagIdKey";
    DiceBagViewModel viewModel;

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

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, new DiceListFragment());
        transaction.commit();

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AsyncTask<Void, Void, String>() {

                    @Override
                    protected String doInBackground(Void... voids) {
                        viewModel.getDiceRollRepository().addDiceBag("testbag");

                        //TODO: Temp code to test things
                        DiceRollEntity drEntity = new DiceRollEntity("Test", 2);
                        drEntity.setBagid(viewModel.getMBagId());

                        DiceEntity dice = new DiceEntity("Dice 1", 1, 8, 1);
                        DiceEntity dice1 = new DiceEntity("Dice 2", 1, 6, 1);
                        DiceEntity dice2 = new DiceEntity("Dice 3", 6, 12, 1);
                        final DiceRoll roll = new DiceRoll();
                        roll.rollEntity = drEntity;
                        roll.diceEntities = Arrays.asList(dice, dice1, dice2);
                        viewModel.getDiceRollRepository().addDiceRoll(roll);
                        return null;
                    }
                }.execute();
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
