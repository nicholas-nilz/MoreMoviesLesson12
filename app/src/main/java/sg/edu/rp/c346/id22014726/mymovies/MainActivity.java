package sg.edu.rp.c346.id22014726.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText title;
    EditText genre;
    EditText year;
    Button insert;
    Button display;
    Spinner ratings;
    String stRatings;

    ArrayList<Movie> al;
    ArrayAdapter<Movie> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = findViewById(R.id.btnInsert);
        display = findViewById(R.id.btnDisplay);
        title = findViewById(R.id.etTitle);
        genre = findViewById(R.id.etGenre);
        year = findViewById(R.id.etYear);
        ratings = findViewById(R.id.ratings);

        al = new ArrayList<Movie>();
        aa = new ArrayAdapter<Movie>(this,
                android.R.layout.simple_list_item_1, al);

        // Create a string array for the ratings
        String[] ratingsArray = {"G", "PG", "PG13", "NC16", "M18", "R21"};

        // Create an ArrayAdapter for the ratings Spinner
        ArrayAdapter<String> ratingsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, ratingsArray);

        // Set the ArrayAdapter to the ratings Spinner
        ratings.setAdapter(ratingsAdapter);

     ratings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 0:
                    stRatings = "G";
                    break;
                case 1:
                    stRatings = "PG";
                    break;
                case 2:
                    stRatings = "PG13";
                    break;
                case 3:
                    stRatings = "NC16";
                    break;
                case 4:
                    stRatings = "M18";
                    break;
                case 5:
                    stRatings = "R21";
                    break;

            }
            Toast.makeText(MainActivity.this, "Selected: " + stRatings, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    });

        insert.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            DBHelper db = new DBHelper(MainActivity.this);

            String stTitle = String.valueOf(title.getText());
            String stGenre = String.valueOf(genre.getText());
            int iYear = Integer.parseInt(String.valueOf(year.getText()));
            db.insertMovie(stTitle, stGenre, iYear, stRatings);
            Toast.makeText(MainActivity.this, "Movie successfully added", Toast.LENGTH_SHORT).show();
        }

    });


        display.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, DIsplayMovie1.class);
            startActivity(intent);
        }
    });

    }
}