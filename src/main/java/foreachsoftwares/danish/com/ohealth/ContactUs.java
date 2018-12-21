package foreachsoftwares.danish.com.ohealth;



import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactUs extends AppCompatActivity {
    EditText name,feedback;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        name=(EditText)findViewById(R.id.editText1);
        feedback= (EditText)findViewById(R.id.editText2);
        submit= (Button)findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                        Uri.parse("mailto:" + Uri.encode("foreachsoftwares@gmail.com")));

                emailIntent.putExtra(Intent.EXTRA_SUBJECT,name.getText().toString());
                emailIntent.putExtra(Intent.EXTRA_TEXT, feedback.getText().toString());
                startActivity(Intent.createChooser(emailIntent, "Send email via..."));
            }
        });
    }

}
