package foreachsoftwares.danish.com.ohealth;


        import android.app.Activity;
        import android.content.Intent;
        import android.support.v4.app.Fragment;
        import android.os.Bundle;
        import android.support.v7.widget.CardView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

public class Cards extends Fragment {

    CardView running,diet,yoga,steps;
    public Pedometer p;
    TextView step,kilometres;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cards, container, false);

        running=view.findViewById(R.id.c1);
        diet=view.findViewById(R.id.c2);
        yoga=view.findViewById(R.id.c3);

        running.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2= new Intent(getActivity(),webrunning.class);
                startActivity(i2);
            }
        });

        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3= new Intent(getActivity(),webdiet.class);
                startActivity(i3);
            }
        });


        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4= new Intent(getActivity(),webyoga.class);
                startActivity(i4);
            }
        });
        return view;
    }

}
