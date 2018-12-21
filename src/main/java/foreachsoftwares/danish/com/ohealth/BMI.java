package foreachsoftwares.danish.com.ohealth;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BMI extends Fragment implements AdapterView.OnItemSelectedListener {

    Button b1;
    EditText weight, height;
    TextView result;
    Spinner dim;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bmi, container, false);
        weight =  view.findViewById(R.id.editText1);
        height = view.findViewById(R.id.editText2);
        result =  view.findViewById(R.id.textView);

        b1 =  view.findViewById(R.id.button);
        Spinner spinner = (Spinner)view.findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.dim_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

dim= view.findViewById(R.id.spinner);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = dim.getSelectedItem().toString().trim();
             try{
                if(weight.getText().toString().trim().equals(null)||height.getText().toString().trim().equals(null))
                {
                    Toast.makeText(getActivity(),"Please enter height and weight  ",Toast.LENGTH_LONG).show();
                }
                else{
                    if(text.equals("metres"))
                    {
                    float weight1 = Float.valueOf(weight.getText().toString().trim());
                    float height1 = Float.valueOf(height.getText().toString());
                    float bmi = weight1 / (height1 * height1);

                    if (bmi < 18.5) {
                        result.setText("The Bmi is: " + bmi + " - UNDER WEIGHT!!!");
                    } else if (bmi > 25 && bmi < 30) {
                        result.setText("The Bmi is: " + bmi + " - OVER WEIGHT!!!");
                    } else if (bmi >=18.5 && bmi <= 25) {
                        result.setText("The Bmi is: " + bmi + " - NORMAL WEIGHT");
                    } else if (bmi > 30) {
                        result.setText("The Bmi is: " + bmi + " - OBESE");
                    }
                    }
                    else if(text.equals("inches"))
                        {
                            float weight2 = Float.valueOf(weight.getText().toString().trim());
                            float height2 = Float.valueOf(height.getText().toString());
                            float height3= (float)(height2*0.0254);
                            float bmi1 = weight2 / (height3 * height3);

                            if (bmi1 < 18.5) {
                                result.setText("The Bmi is: " + bmi1 + " - UNDER WEIGHT!!!");
                            } else if (bmi1 > 25 && bmi1 < 30) {
                                result.setText("The Bmi is: " + bmi1 + " - OVER WEIGHT!!!");
                            } else if (bmi1 >=18.5 && bmi1 <= 25) {
                                result.setText("The Bmi is: " + bmi1 + " - NORMAL WEIGHT");
                            } else if (bmi1 > 30) {
                                result.setText("The Bmi is: " + bmi1 + " - OBESE");
                        }
                    }

                }

             }
             catch (Exception e)
             {
                 result.setText(e.toString());
             }
            }
        });


        return view;

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
