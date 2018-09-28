package org.jqt3of5.android.dicebag.DiceRolls.edit;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import org.jqt3of5.android.dicebag.R;

public class DicePropertiesActivity extends AppCompatActivity {

    public static String ROLL_ID_KEY = "RollIdKey";

    long _rollId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_properties);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra(ROLL_ID_KEY))
        {
            _rollId = getIntent().getLongExtra(ROLL_ID_KEY, 0);
        }

        LinearLayout diceList = findViewById(R.id.dice_list);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_dice_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
