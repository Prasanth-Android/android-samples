package com.example.navya.sample;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Task2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Task2#newInstance} factory method to
 * create an instance of this fragment.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Task2 extends Fragment {


    Spinner spinner;
    TextView travel_type,travel_distance;
    TableLayout tableLayout;
    HashMap<String, LocationInfo> map = new HashMap<String, LocationInfo>();
    ArrayList<String> arraySpinner = new ArrayList<>();
    double latitude,longitude;
    String title = " ";
    Button navigate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_task2, container, false);
        spinner = (Spinner)rootview.findViewById(R.id.spinner);
        tableLayout = (TableLayout)rootview.findViewById(R.id.tableLayout);

        String serverURL = "http://express-it.optusnet.com.au/sample.json";
        new LongOperation().execute(serverURL);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long id) {
                // TODO Auto-generated method stub

                LocationInfo selectedlocation = map.get(arg0.getItemAtPosition(position));
                latitude = selectedlocation.getLatitude();
                longitude = selectedlocation.getLongitude();
                title = selectedlocation.getName();
                HashMap<String, String> map = new HashMap<String, String>();
                JSONObject jObject =selectedlocation.getFromcentral();
                Iterator<?> keys = jObject.keys();
                int i=0;
                tableLayout.removeAllViews();
                while( keys.hasNext() ){
                    String key = (String)keys.next();
                    String value = null;
                    try {
                        value = jObject.getString(key);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    map.put(key, value);
                    TableRow row= new TableRow(Task2.this.getActivity());
                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    row.setLayoutParams(lp);
                    travel_distance = new TextView(Task2.this.getActivity());

                    travel_distance.setText(value);
                    travel_type = new TextView(Task2.this.getActivity());

                    travel_type.setText(key);

                    row.addView(travel_type);
                    row.addView(travel_distance);

                    tableLayout.addView(row,i);
                    i++;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });

        navigate = (Button)rootview.findViewById(R.id.button);
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(arraySpinner.size() != 0) {
                    Intent intent = new Intent(Task2.this.getActivity(), MapActivity.class);
                    intent.putExtra("lat", latitude + "");
                    intent.putExtra("lon", longitude + "");
                    intent.putExtra("title",title);
                    startActivity(intent);
                }else{
                    Toast.makeText(Task2.this.getActivity(),"No Data is Available",Toast.LENGTH_SHORT).show();
                }
            }
        });



        return rootview;
    }

    private class LongOperation  extends AsyncTask<String, Void, Void> {

        // Required initialization

        private final HttpClient Client = new DefaultHttpClient();
        private String Content;
        private String Error = null;
        private ProgressDialog Dialog = new ProgressDialog(Task2.this.getActivity());
        String data ="";
        int sizeData = 0;



        protected void onPreExecute() {
            // NOTE: You can call UI Element here.

            //Start Progress Dialog (Message)

            Dialog.setMessage("Please wait..");
            Dialog.show();

//            try{
//                // Set Request parameter
//                data +="&" + URLEncoder.encode("data", "UTF-8") + "="+serverText.getText();
//
//            } catch (UnsupportedEncodingException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }

        }

        // Call after onPreExecute method
        protected Void doInBackground(String... urls) {

            /************ Make Post Call To Web Server ***********/
            BufferedReader reader=null;

            // Send data
            try
            {

                // Defined URL  where to send data
                URL url = new URL(urls[0]);

                // Send POST data request

                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write( data );
                wr.flush();

                // Get the server response

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    // Append server response in string
                    sb.append(line + "");
                }

                // Append Server Response To Content String
                Content = sb.toString();
            }
            catch(Exception ex)
            {
                Error = ex.getMessage();
            }
            finally
            {
                try
                {

                    reader.close();
                }

                catch(Exception ex) {}
            }

            /*****************************************************/
            return null;
        }

        protected void onPostExecute(Void unused) {
            // NOTE: You can call UI Element here.

            // Close progress dialog
            Dialog.dismiss();

            if (Error != null) {



            } else {

                // Show Response Json On Screen (activity)


                /****************** Start Parse Response JSON Data *************/

                String OutputData = "";
                JSONArray jsonResponse;

                try {

                    /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/


                    /***** Returns the value mapped by name if it exists and is a JSONArray. ***/
                    /*******  Returns null otherwise.  *******/
                    jsonResponse = new JSONArray(Content);
                    for(int i=0;i<jsonResponse.length();i++){

                        JSONObject e = jsonResponse.getJSONObject(i);
                        LocationInfo info = new LocationInfo();
                        info.setId(e.getInt("id"));
                        info.setName(e.getString("name"));

                        JSONObject fromcentral = e.getJSONObject("fromcentral");
                        info.setFromcentral(fromcentral);
                        JSONObject location = e.getJSONObject("location");
                        info.setLatitude(location.getDouble("latitude"));
                        info.setLongitude(location.getDouble("longitude"));
                        map.put(e.getString("name"),info);
                        arraySpinner.add(e.getString("name"));

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Task2.this.getActivity(),
                            android.R.layout.simple_spinner_item, arraySpinner);
                    spinner.setAdapter(adapter);

                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        }

    }

}