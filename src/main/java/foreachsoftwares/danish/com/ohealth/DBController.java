package foreachsoftwares.danish.com.ohealth;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by danishkhan on 26/08/17.
 */

public class DBController extends SQLiteOpenHelper{

    private static final String tablename="user";
    private static final String date="date";
    private static final String calories="calories";
    public DBController(Context context) {
        super(context,"details", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE IF NOT EXISTS "+tablename+"("+date+" text ,"+calories+" text)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query= "DROP TABLE IF EXISTS "+tablename;
        db.execSQL(query);
        onCreate(db);
    }


    public ArrayList<HashMap<String,String>> getlist()
    {  ArrayList<HashMap<String,String>> list=new  ArrayList<HashMap<String,String>>();
        String query="SELECT * FROM "+tablename;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do
            {
                HashMap<String,String>map=new HashMap<String, String>();
                map.put("date",cursor.getString(0));
                map.put("calories",cursor.getString(1));
                list.add(map);
            }
            while (cursor.moveToNext());
        }
        return list;
    }
}