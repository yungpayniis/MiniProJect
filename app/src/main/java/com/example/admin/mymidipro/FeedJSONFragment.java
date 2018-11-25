package com.example.admin.mymidipro;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.codemobiles.util.CMFeedJsonUtil;
import com.codemobiles.util.CMFeedXmlUtil;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedJSONFragment extends Fragment {

    private  ListView listview;
    public FeedJSONFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_feed_xml, container, false);
        listview =(ListView)v.findViewById(R.id.listview);
        feedData();
        return  v;
    }

    private void feedData() {
        new FeedAsynTask().execute("http://testminipro.000webhostapp.com/55/youtubesjson.xml");

    }
    public class FeedAsynTask extends AsyncTask<String, Void, ArrayList<Object>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getActivity(),"Connecting..",Toast.LENGTH_LONG).show();
        }

        @Override
        protected ArrayList<Object> doInBackground(String... params) {

            RequestBody request = new FormEncodingBuilder().add("type","xml").build();
            ArrayList<Object> result = CMFeedXmlUtil.feed(params[0],"youtube_item",request);
            return result;

        }

        @Override
        protected void onPostExecute(ArrayList<Object> s) {
            super.onPostExecute(s);
            if(s != null){

                Toast.makeText(getActivity(),"Shoes:: "+s.size()+"item",Toast.LENGTH_LONG).show();
                listview.setAdapter(new LisrViewAdapter(getActivity(),new ArrayList<Object>(s)));
            }


        }
    }
}

