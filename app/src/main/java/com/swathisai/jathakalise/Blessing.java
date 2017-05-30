package com.swathisai.jathakalise;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.swathisai.jathakalise.Utils.Utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Blessing extends AppCompatActivity {

    EditText name,bless;
    Button submit;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blessing);

        name = (EditText)findViewById(R.id.name);
        bless = (EditText)findViewById(R.id.remarks);
        submit = (Button)findViewById(R.id.submit);

        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Utils.isNetConnected(Blessing.this))
                {
                    if(name.getText().toString().isEmpty())
                    {
                        Toast.makeText(Blessing.this, "Please provide your name", Toast.LENGTH_SHORT).show();
                    }
                    else if(bless.getText().toString().isEmpty())
                    {
                        Toast.makeText(Blessing.this, "Blessings cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        blessings();
                    }
                }
                else
                    Utils.showDialog(Blessing.this);

            }
        });
    }

    private void blessings()
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        String BLESS_URL = getString(R.string.BLESS_URL);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BLESS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            progressDialog.dismiss();
                            JSONObject rootObject = new JSONObject(response);
                            String status = rootObject.getString("result_status");

                            if(status.equals("true"))
                            {
                                startActivity(new Intent(getApplicationContext(),Thankyou.class));
                                finish();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(Blessing.this, "Network Error", Toast.LENGTH_LONG).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(Blessing.this, "Network Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("name",name.getText().toString().trim());
                params.put("blessings",bless.getText().toString().trim());
                return params;
            }
        };

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
}
