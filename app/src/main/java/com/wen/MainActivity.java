package com.wen;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int secret = new Random().nextInt(10)+1;
    int counter;
    private TextView number;
    private TextView edcounter;
    private ImageView result;
    String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d(TAG,"secret:" + secret);
        number = findViewById(R.id.ed_number);
        edcounter = findViewById(R.id.view_counter);
        result = findViewById(R.id.result);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }

    public void reset() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void button(View view) {
        int n = Integer.parseInt(number.getText().toString());
        counter++;
        edcounter.setText(counter + "");
        result.setVisibility(View.VISIBLE);
        result.setAlpha(1.0f);
        if(n == secret) {
            result.setImageResource(R.drawable.right);
            Toast.makeText(MainActivity.this,"Bingo",Toast.LENGTH_LONG)
                    .show();
        } else if (n > secret) {
            result.setImageResource(R.drawable.error);
            result.animate().alpha(0.0f).setDuration(1200);
            Toast.makeText(MainActivity.this,"Lower",Toast.LENGTH_LONG)
                    .show();
        } else if (n < secret) {
            result.setImageResource(R.drawable.error);
            result.animate().alpha(0.0f).setDuration(1200);
            Toast.makeText(MainActivity.this,"Higher",Toast.LENGTH_LONG)
                    .show();
        }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
