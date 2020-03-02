package com.lparolari.covidtester;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.lparolari.covidtester.ui.tester.TesterFormState;
import com.lparolari.covidtester.ui.tester.TesterViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;

import android.preference.PreferenceManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        checkAppOpenNumber();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            startActivity(new Intent(this, InfoActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkAppOpenNumber() {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int n = sharedPreferences.getInt("appOpenNumber", 0);


        if (n == -1) return;

        if (n <= 3) {
            sharedPreferences.edit().putInt("appOpenNumber", n + 1).apply();
        }

        if (n > 3) {
            Snackbar.make(findViewById(R.id.container), R.string.read_informations_q, Snackbar.LENGTH_LONG)
                    .setAction(R.string.read, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sharedPreferences.edit().putInt("appOpenNumber", -1).apply();
                            startActivity(new Intent(getApplicationContext(), InfoActivity.class));
                        }
                    }).show();
        }
    }
}
