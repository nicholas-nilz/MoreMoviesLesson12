package sg.edu.rp.c346.id22014726.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DIsplayMovie1 extends AppCompatActivity {

    ArrayList<String> movies;
    ListView lv;
    Button show;
    Button back;
    ArrayList<Movie> al;
    CustomAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();

    al = new ArrayList<Movie>();
    adapter = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(adapter);
    Intent i = getIntent();
    DBHelper db = new DBHelper(DIsplayMovie1.this);
        al.clear();
        al.addAll(db.getMovies());
        adapter.notifyDataSetChanged();
}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie1);

        lv = findViewById(R.id.lvShow);
        show = findViewById(R.id.btnShow);
        back = findViewById(R.id.btnBack);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movie data = al.get(position);
                Intent i = new Intent(DIsplayMovie1.this,
                        UpdateMovie1.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            DBHelper dbh = new DBHelper(DIsplayMovie1.this);
                al.clear();
                String filterText = "PG13";
                al.addAll(dbh.getAllMovies(filterText));
                adapter.notifyDataSetChanged();

            }

        });
    }

}

