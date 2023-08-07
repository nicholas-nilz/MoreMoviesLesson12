package sg.edu.rp.c346.id22014726.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateMovie1 extends AppCompatActivity {

    TextView id;
    Button cancel;
    Button update;
    Button delete;
    EditText title;
    EditText genre;
    EditText year;
    Spinner ratings;
    Movie data;
    String stRatings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_movie1);

        id = findViewById(R.id.id);
        title = findViewById(R.id.etTitle);
        genre = findViewById(R.id.etGenre);
        year = findViewById(R.id.etYear);
        cancel = findViewById(R.id.btnCancel);
        ratings = findViewById(R.id.ratings);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);

        Intent i = getIntent();
        data = (Movie) i.getSerializableExtra("data");

        id.setText(String.valueOf(data.getId()));
        title.setText(String.valueOf(data.getTitle()));
        genre.setText(String.valueOf(data.getGenre()));
        year.setText(String.valueOf(data.getYear()));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(UpdateMovie1.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes made.");

                myBuilder.setNegativeButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        onBackPressed();
                    }
                });
                myBuilder.setPositiveButton("DO NOT DISCARD", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

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
                Toast.makeText(UpdateMovie1.this, "Selected: " + stRatings, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbh = new DBHelper(UpdateMovie1.this);
                data.setTitle(title.getText().toString());
                data.setGenre(genre.getText().toString());
                data.setYear(Integer.valueOf(year.getText().toString()));
                data.setRating(stRatings);
                dbh.updateMovie(data);
                dbh.close();
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(UpdateMovie1.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the movie\n" + String.valueOf(data.getTitle()) + ".");

                myBuilder.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper db = new DBHelper(UpdateMovie1.this);
                        db.deleteMovie(data.getId());
                        finish();
                    }
                });
                myBuilder.setPositiveButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }
}
