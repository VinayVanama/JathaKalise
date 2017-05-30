package com.swathisai.jathakalise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ImageView i1, i2, i3, i4, i5, i6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i1 = (ImageView) findViewById(R.id.invitation);
        i2 = (ImageView) findViewById(R.id.venue);
        i3 = (ImageView) findViewById(R.id.album);
        i4 = (ImageView) findViewById(R.id.videoView);
        i5 = (ImageView) findViewById(R.id.livecoverage);
        i6 = (ImageView) findViewById(R.id.blessing);

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent invite = new Intent(getApplicationContext(),Invitation.class);
                startActivity(invite);
            }
        });


        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent venue = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(venue);
            }
        });

        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent album = new Intent(getApplicationContext(), AlbumView.class);
                startActivity(album);

            }
        });

        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent video = new Intent(getApplicationContext(), RecyclerActivity.class);
                startActivity(video);
            }
        });

        i6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent bless = new Intent(getApplicationContext(), Blessing.class);
                startActivity(bless);

            }
        });

    }


}
