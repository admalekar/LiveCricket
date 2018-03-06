package livecricket.cricketapplication.com.livecricket;

import android.os.AsyncTask;
import android.os.Debug;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompatSideChannelService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Iterator;

public class MatchActivity extends AppCompatActivity {


    private DatabaseReference root;
    private String user = "Amey";
    private String latest_msg;
    private String latest_msg_name;
    private TextView Text_message;
    private JSONread js;
    private Spinner spinner;
    public ArrayList<String> Idname;
    public ArrayList<String> t1name;
    public ArrayList<String> t2name;
    public ArrayList<String> teamnames;
    public ArrayList<String> matchnumbers;
    public ImageView volumeButton;
    String URL="https://cricscore-api.appspot.com/csa";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Idname = new ArrayList<>();
        t1name = new ArrayList<>();

        matchnumbers = new ArrayList<>();
        t2name = new ArrayList<>();
        teamnames = new ArrayList<>();
        volumeButton = findViewById(R.id.volumeButton);

        spinner = findViewById(R.id.spinner_id);

        loadSpinnerData(URL);





        js = new JSONread();

        Text_message = findViewById(R.id.Text_message);


        Bundle matchList = getIntent().getExtras();
        String MatchPos = matchList.getString("Match");
        System.out.println(MatchPos);
        System.out.println(MatchPos);


        root = FirebaseDatabase.getInstance().getReference().getRoot().child(MatchPos);

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                change_child_convo(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private void change_child_convo(DataSnapshot dataSnapshot) {

        Iterator i = dataSnapshot.getChildren().iterator();
        while (i.hasNext()) {

            latest_msg = (String) ((DataSnapshot) i.next()).getValue();
            latest_msg_name = (String) ((DataSnapshot) i.next()).getValue();
            Text_message.setText(latest_msg);


        }

    }


    private void loadSpinnerData(String url) {
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


                    spinner.setAdapter(new ArrayAdapter<String>(MatchActivity.this, android.R.layout.simple_spinner_dropdown_item,  teamnames ));
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

        */

        matchnumbers.add("Match 1");
        matchnumbers.add("Match 2");

        spinner.setAdapter(new ArrayAdapter<String>(MatchActivity.this, android.R.layout.simple_spinner_dropdown_item,  matchnumbers ));



    }


    public void volumeOnClick(View view) {

        Log.d("in method","0");
        if(!volumeButton.isEnabled()){
            volumeButton.setEnabled(true);

            Log.d("enabled","1");
        }
        else
            volumeButton.setEnabled(false);
        Log.d("disabled","2");

    }
}
class JSONread extends AsyncTask<String, Void, String>{


    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }

    String server_response;

    @Override
    protected String doInBackground(String... strings) {

        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                server_response = readStream(urlConnection.getInputStream());
                Log.v("CatalogClient", server_response);




            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return server_response;
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);




        Log.e("Response", "" + server_response);


    }
    }



