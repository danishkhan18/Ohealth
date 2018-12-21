package foreachsoftwares.danish.com.ohealth;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Trends extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.Pedometer:
                    Intent i3 = new Intent(Trends.this, MainActivity.class);
                    startActivity(i3);
                    return true;
                case R.id.BMI:
                    Intent i1=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i1);

                    return true;

                case R.id.Trends:
                    Intent i2 = new Intent(Trends.this, Trends.class);
                    startActivity(i2);
                    return true;

                case R.id.Nearby:
                    Intent mapintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.co.uk/maps?q=Hospitals&hl=en"));
                    startActivity(mapintent);
                    return true;
            }
            return false;
        }

    };


    EditText editText;
    Button b1,b2,b3;
    DBController controler =new DBController(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends);
        editText=(EditText)findViewById(R.id.editText4);
        b1= (Button) findViewById(R.id.button2);
        b2= (Button) findViewById(R.id.button3);
        b3= (Button) findViewById(R.id.button4);
        final java.util.Calendar calendar = java.util.Calendar.getInstance();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    if(editText.getText().toString().trim().equals(null))
                    {
                        Toast.makeText(getApplicationContext(),"Please Insert The Values ",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        String cal="calories";
                        controler =new DBController(getApplicationContext());
                        SQLiteDatabase db=controler.getWritableDatabase();
                        ContentValues cv =new ContentValues();
                        cv.put("date",calendar.get(java.util.Calendar.DATE)+"/"+(1+calendar.get(java.util.Calendar.MONTH))+"/"+calendar.get(java.util.Calendar.YEAR));
                        cv.put("calories","      "+editText.getText().toString().trim()+" "+cal);
                        db.insert("user",null,cv);
                        db.close();
                        Toast.makeText(getApplicationContext(),"Inserted!!",Toast.LENGTH_LONG).show();

                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Trends.this,Adapter.class);
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controler =new DBController(getApplicationContext());
                SQLiteDatabase db=controler.getWritableDatabase();
                db.delete("user", null, null);
                db.close();
                Toast.makeText(getApplicationContext(),"All records deleted!!",Toast.LENGTH_LONG).show();
            }
        });
    }


}