package livecricket.cricketapplication.com.livecricket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Amey on 11-Dec-17.
 */

public class Cricket_list extends AppCompatActivity {

    public ArrayList<String> t1name;
    public ArrayList<String> Idname;
    public ArrayList<String> t2name;
    public ArrayList<String> teamnames;
    public ArrayList<String> matchnumbers;

    private ListView lv;
    String URL = "https://cricscore-api.appspot.com/csa";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cricket_list_activity);

        Idname = new ArrayList<>();
        t1name = new ArrayList<>();
        matchnumbers = new ArrayList<>();

        t2name = new ArrayList<>();
        teamnames = new ArrayList<>();

        lv = findViewById(R.id.List1);

        loadListData(URL);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //String ClickedValue = lv.getAdapter().getItem(i).toString();
                String ClickedValue = matchnumbers.get(i);
                Intent intent = new Intent(getApplicationContext(), MatchActivity.class);
                intent.putExtra("Match", ClickedValue);
                startActivity(intent);
            }
        });
    }

    private void loadListData(String url) {
        /*
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonArray=new JSONArray(response);

                    // JSONArray jsonArray=jsonObject.getJSONArray("");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String id=jsonObject1.getString("id");
                        Idname.add(id);
                        String t1=jsonObject1.getString("t1");
                        t1name.add(t1);
                        String t2=jsonObject1.getString("t2");
                        t2name.add(t2);
                        // teamnames.add(t1name +t2name);
                        teamnames.add(t1name.get(i)+" vs "+t2name.get(i));

                    }


                    lv.setAdapter(new ArrayAdapter<String>(Cricket_list.this, android.R.layout.simple_list_item_1,  teamnames ));
                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }
    */


        matchnumbers.add("Match 1");
        matchnumbers.add("Match 2");

        lv.setAdapter(new ArrayAdapter<String>(Cricket_list.this, android.R.layout.simple_list_item_1,  matchnumbers ));
    }
}
