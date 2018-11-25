package com.example.admin.mymidipro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codemobiles.util.CMXmlJsonConvertor;
import com.codemobiles.util.CircleTransform;

import java.util.ArrayList;

public class LisrViewAdapter extends BaseAdapter {

    public  Context mContext;
    public LayoutInflater mInflater;
    public  ArrayList<Object> mArraylist;

    public LisrViewAdapter(Context context, ArrayList<Object> arrayList){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mArraylist = arrayList;

    }

    @Override
    public int getCount() {
        return mArraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

       if (convertView == null){
           convertView = mInflater.inflate(R.layout.item_listview_org,null);
           holder = new ViewHolder();
           holder.title = convertView.findViewById(R.id.item_listview_title);
           holder.description = convertView.findViewById(R.id.item_listview_desc);
           holder.youtubeThumbnail = (ImageView) convertView.findViewById(R.id.item_listview_youtube_image);
           holder.authorImage = (ImageView) convertView.findViewById(R.id.item_listview_authorIcon);

            convertView.setTag(holder);



       }else {
           holder = (ViewHolder) convertView.getTag();
       }
        Object item = mArraylist.get(position);

        holder.title.setText(CMXmlJsonConvertor.getValue(item,"title"));
        holder.description.setText(CMXmlJsonConvertor.getValue(item,"description"));

        String avatarImageURL = (CMXmlJsonConvertor.getValue(item,"image_link"));
        String youtubeImageURL = (CMXmlJsonConvertor.getValue(item,"youtube_image"));
        String url = "http://stockx.imgix.net/Jordan-1-Retro-Strap-Grand-Purple-Varsity-Maize.png";
        Glide.with(mContext).load(avatarImageURL).placeholder(R.drawable.xlogo).transform(new CircleTransform(mContext)).into(holder.authorImage);
        Glide.with(mContext).load(youtubeImageURL).placeholder(R.drawable.shoepic2).into(holder.youtubeThumbnail);

        return convertView;
    }
    public class ViewHolder {
        TextView title;
        ImageView authorImage;
        ImageView youtubeThumbnail;
        TextView description;
    }
}
