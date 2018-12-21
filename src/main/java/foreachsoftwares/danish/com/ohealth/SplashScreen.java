package foreachsoftwares.danish.com.ohealth;


        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.MotionEvent;

public class SplashScreen extends AppCompatActivity {
    private Thread splashthread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashthread=new Thread() {
            @Override
            public void run()

            {
                try
                {
                    synchronized (this)
                    {
                        wait(2000);
                    }
                }
                catch(InterruptedException ex){}


                finish();
                Intent i1= new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i1);
            }
        };
        splashthread.start();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction()==MotionEvent.ACTION_DOWN);
        {
            synchronized (splashthread)
            {
                splashthread.notifyAll();
            }
        }
        return true;
    }
}
