package foreachsoftwares.danish.com.ohealth;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Calendar;

public class Pedometer extends AppCompatActivity implements SensorEventListener {

    private TextView textView;

    public static TextView distance;

    public static int value = 0;

    public static int day;

    public static int currentDay;
    public static int abcd;

    private SensorManager mSensorManager;

    private Sensor mStepCounterSensor;

    public static double kilometres;

    private Sensor mStepDetectorSensor;

    static float[] values;

    public static float previous_value; // The last value before the app was closed
    float newValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);

        textView = (TextView) findViewById(R.id.textView);

        distance=(TextView)findViewById(R.id.textView16);

        mSensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        mStepCounterSensor = mSensorManager
                .getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mStepDetectorSensor = mSensorManager
                .getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Log.e( "onSensorChanged: ", Arrays.toString(values));

        if (day!=currentDay)
        {
            value = (int)(values[0]-previous_value);
        }


    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.Pedometer:
                    Intent i3=new Intent(getApplicationContext(),Pedometer.class);
                    startActivity(i3);
                    return true;
                case R.id.BMI:
                    Intent i1=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i1);
                    return true;
                case R.id.Home:
                    Intent i2=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i2);
                    return true;
                case R.id.Trends:
                    Intent i4=new Intent(getApplicationContext(),Trends.class);
                    startActivity(i4);
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
    public void onSensorChanged(final SensorEvent event) {




        Sensor sensor = event.sensor;
        values = event.values;
        Log.e("onSensorChanged: ",Arrays.toString(values) );

        if (values.length > 0) {
            value=(int)(values[0]);


        }
        Calendar calendar = Calendar.getInstance();
        currentDay = calendar.get(Calendar.DAY_OF_YEAR);

        if (day != currentDay) { // New day
            if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
                newValue = (value - previous_value);
                textView.setText("Steps: " + newValue);
                kilometres = (float) (newValue) / 1312.0;
                distance.setText("Distance =" + kilometres + " km");
            } else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
                // For test only. Only allowed value is 1.0 i.e. for step taken
                textView.setText("Steps: " + newValue);
                kilometres = (float) ((newValue) / 1312.0);
                distance.setText("Distance =" + kilometres + " km");

            }
        } else {
            if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
                textView.setText("Steps: " + value);

                kilometres = (float) (value / 1312.0);
                distance.setText("Distance =" + kilometres + " km");

            } else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
                // For test only. Only allowed value is 1.0 i.e. for step taken

                textView.setText("Steps: " + value);
                kilometres = (float) (value / 1312.0);
                distance.setText("Distance =" + kilometres + " km");


            }
        }
    }




    @Override
    protected void onPause() {
        super.onPause();
        try{
            Calendar calendar = Calendar.getInstance();
            day = calendar.get(Calendar.DAY_OF_YEAR);
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("day",day);
            editor.putInt("value", value); // Writing the last value to shared prefs
            editor.apply();
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onResume() {

        super.onResume();
        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        previous_value = prefs.getInt("value", 0); // Reading the last value
        day=prefs.getInt("day",0);
        mSensorManager.registerListener(this, mStepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mStepDetectorSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this, mStepCounterSensor);
        mSensorManager.unregisterListener(this, mStepDetectorSensor);
    }
}
