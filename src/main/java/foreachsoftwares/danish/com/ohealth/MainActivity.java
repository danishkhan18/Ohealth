package foreachsoftwares.danish.com.ohealth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import static foreachsoftwares.danish.com.ohealth.R.id.Home;

public class MainActivity extends AppCompatActivity{


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            =new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.Home:
                    Cards home = new Cards();
                    getSupportFragmentManager().beginTransaction().add(R.id.content,home).commit();
                    return true;

                case R.id.Pedometer:
                    Intent i3= new Intent(MainActivity.this,Pedometer.class);
                    startActivity(i3);
                    return true;
                case R.id.BMI:
                    BMI open= new BMI();
                    getSupportFragmentManager().beginTransaction().add(R.id.content,open).commit();
                    return true;

                case R.id.Trends:
                    Intent i2= new Intent(MainActivity.this,Trends.class);
                    startActivity(i2);
                    return true;

                case R.id.Nearby:
                    Intent mapintent= new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.co.uk/maps?q=Hospitals&hl=en"));
                    startActivity(mapintent);
                    return true;
            }

            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Cards home = new Cards();
        getSupportFragmentManager().beginTransaction().add(R.id.content,home).commit();
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
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);

            return true;
        }
            if (id == R.id.ContactUs) {
                Intent i= new Intent(MainActivity.this,ContactUs.class);
                startActivity(i);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
/*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/