package com.example.user.project;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.user.project.Adapter.FeedAdapter;
import com.example.user.project.Common.HTTPDataHandler;
import com.example.user.project.Model.RSSObject;
import com.google.gson.Gson;

import java.util.zip.Inflater;

public class homeFragment extends Fragment {

    RecyclerView recyclerView;
    RSSObject rssObject;


    //RSS link
    private final String RSS_LINK="http://rss.nytimes.com/services/xml/rss/nyt/Nutrition.xml";
    private final String RSS_TO_JSON_API = " https://api.rss2json.com/v1/api.json?rss_url=";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.news_feed_fragment,container,false);

        setHasOptionsMenu(true);


        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadRSS();

        return view;

    }

    private void loadRSS() {

        AsyncTask<String,String,String> loadRSSAsync = new AsyncTask<String, String, String>() {

            ProgressDialog progressDialog = new ProgressDialog(getActivity());

            protected void OnPreExecuted(){
                progressDialog.setMessage("Please Wait");
                progressDialog.show();
            }

            protected String doInBackground(String... params) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(params[0]);

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                progressDialog.dismiss();
                rssObject = new Gson().fromJson(s, RSSObject.class);
                FeedAdapter adapter = new FeedAdapter(rssObject,getActivity());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        };

        StringBuilder url_get_data = new StringBuilder(RSS_TO_JSON_API);
        url_get_data.append(RSS_LINK);
        loadRSSAsync.execute(url_get_data.toString());


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.news_feed_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menu_refresh){
            loadRSS();
        }
        return true;

    }
}
