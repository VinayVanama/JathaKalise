package com.swathisai.jathakalise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView i1, i2, i3, i4, i5, i6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        i1 = (ImageView) findViewById(R.id.invitation);
        i2 = (ImageView) findViewById(R.id.venue);
        i3 = (ImageView) findViewById(R.id.album);
        i5 = (ImageView) findViewById(R.id.livecoverage);
        i6 = (ImageView) findViewById(R.id.blessing);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.livecoverage:
                Intent myInten = new Intent(view.getContext(), LiveCoverage.class);
                startActivity(myInten);
                return;

            case R.id.blessing:

                Intent track = new Intent(view.getContext(), Blessing.class);
                startActivity(track);
                return;

        }
    }
}
