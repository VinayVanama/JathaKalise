package com.swathisai.jathakalise;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CYKUL04 on 18-05-2017.
 */

public class ImageListAdapter extends BaseAdapter {
    Context context;
//    ArrayList<ImageListData> imageListDataArray;
    String image[];

    public ImageListAdapter(Context context, String image[]) {
        this.context = context;
        this.image = image;
    }


    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public Object getItem(int position) {
        return image[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

     View view=   LayoutInflater.from(context).inflate(R.layout.image_list_item,null);
      ImageView iv = (ImageView) view.findViewById(R.id.imageViewItem);

//       ImageListData imageListData= imageListDataArray.get(position);
        Picasso.with(context).load(image[position]).into(iv);

        return view;
    }
}
