package com.swathisai.jathakalise;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.swathisai.jathakalise.Utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AlbumView extends AppCompatActivity {
    String IMAGE_URL = "http://54.186.67.253/cykul/getImages.php";
    String images[];
    ListView listImages;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_view);
        listImages = (ListView) findViewById(R.id.listImage);
//        RecyclerView.LayoutManager layout = new LinearLayoutManager(ImageListActivity.this, LinearLayoutManager.VERTICAL, true);
//        recycler.setLayoutManager(layout);
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        pd = new ProgressDialog(this);
        pd.setMessage("Loading");
        pd.show();

        if(Utils.isNetConnected(this))
        {
            fetchImages();
        }
        else
        {
            Utils.showDialog(this);
        }



    }

    public void fetchImages() {

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, IMAGE_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    pd.dismiss();
                    try {
                        JSONObject json = new JSONObject(response);

                        int length = json.length();
                        images = new String[length];

                        //Toast.makeText(AlbumView.this, "length" + length, Toast.LENGTH_SHORT).show();
                        for (int i = 0; i <length;)
                        {
                            images[i] = json.getString(String.valueOf(++i));
                            //    imageListData.setImage(images[i]);


                        }

                        ImageListAdapter imageListAdpter = new ImageListAdapter(AlbumView.this, images);
                        listImages.setAdapter(imageListAdpter);
//                        inflation1(images);


                    } catch (JSONException e) {
                        Toast.makeText(AlbumView.this, "Exception", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AlbumView.this, "Something went Wrong.Please Tray Again..", Toast.LENGTH_SHORT).show();

                }
            });
            requestQueue.add(stringRequest);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    private void inflation1(String[] image)
//    {
//        for(int i=0;i<image.length;i++)
//        {
//            View view= LayoutInflater.from(this).inflate(R.layout.image_list_item,null);
//            ImageView iv= (ImageView)  view.findViewById(R.id.imageViewItem);
//            Picasso.with(this).load(image[i]).into(iv);
//
//
//        }
//
//
//    }
}
