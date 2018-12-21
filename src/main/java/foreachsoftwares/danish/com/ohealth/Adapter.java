package foreachsoftwares.danish.com.ohealth;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by danishkhan on 26/08/17.
 */

public class Adapter extends AppCompatActivity {
    DBController dbControler=new DBController(this);
    ListView lv;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        lv=(ListView)findViewById(R.id.detail);
        t=(TextView)findViewById(R.id.textView);
        try {
            ArrayList<HashMap<String, String>> data = dbControler.getlist();
            if (data.isEmpty()) {
                t.setText("no data in database");
            } else {
                SimpleAdapter adapter = new SimpleAdapter(Adapter.this, data, R.layout.raw, new String[]{"date", "calories"}, new int[]{R.id.textView1, R.id.textView2});
                lv.setAdapter(adapter);
                int a = data.size();
                t.setText(String.valueOf(a));
            }
        }
        catch (Exception e)
        {
            t.setText(e.getMessage());
        }
    }
}
