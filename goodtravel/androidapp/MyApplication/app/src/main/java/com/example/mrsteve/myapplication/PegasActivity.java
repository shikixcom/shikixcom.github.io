package com.example.mrsteve.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

public class PegasActivity extends AppCompatActivity  {

    int a = 1;
    int b = 0;
    int c = 2;
    int rat = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pegas);

        SmileRating smileRating = (SmileRating) findViewById(R.id.smile_rating);
        final TextView rating = (TextView) findViewById(R.id.rating);




        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley) {
                switch (smiley) {
                    case SmileRating.BAD:
                        rat =- a;
                        break;
                    case SmileRating.GOOD:
                        rat =+ a;
                        break;
                    case SmileRating.GREAT:
                        rat =+ c;
                        break;
                    case SmileRating.OKAY:
                        rat =+ b;
                        break;
                    case SmileRating.TERRIBLE:
                        rat =-c;
                        break;
                }

                if (rat == 2) {
                    rating.setText("GREAT");
                }

                if (rat == 1) {
                    rating.setText("GOOD");
                }
                if (rat == 0) {
                    rating.setText("OKAY");
                }
                if (rat == -1) {
                    rating.setText("BAD");
                }
                if (rat == -2) {
                    rating.setText("TERRIBLE");
                }
            }
        });



//        Toolbar toolbarpegas = (Toolbar) findViewById(R.id.toolbarbar);
//        setSupportActionBar(toolbarpegas);
//        if (getSupportActionBar() != null)
//             getSupportActionBar().setTitle("Pegas");


    }

    public void opfacebook (View v) {

        Intent intentfacebook = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pegastouristik"));
        startActivity(intentfacebook);

    }

    public void opyoutube (View v) {

        Intent intentfacebook = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/PEGASRETAIL"));
        startActivity(intentfacebook);

    }

    public void optwitter (View v) {

        Intent intentfacebook = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitter.com/pegastourist"));
        startActivity(intentfacebook);

    }

    public void opbrowser (View v) {

        Intent intentfacebook = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pegast.ru"));
        startActivity(intentfacebook);

    }

}
