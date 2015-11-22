package edu.csumb.sarm6485.remodelmate;

/**
 * Created by michaelsarmiento on 11/20/15.
 */

import android.util.Log;
import android.view.View.OnClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class SearchResults extends Activity implements OnClickListener {
    static ArrayList<ParseObject> objects = new  ArrayList<ParseObject>();
    ParseObject sourceObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresults);


        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "0aPN93EAFKwJGX9uAeQRD4edwfuA4ml7LIhgLFuV", "yn2tKwCYp1nitqnqndWz3J8tafANcf9C0DAYUTZp");


        //View LoginButton = findViewById(R.id.login_button);
        //LoginButton.setOnClickListener(this);



        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("Contractor");
        query.getInBackground("2ttvvCKy56", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                TextView result;
                if (e == null) {
                    ArrayList<ParseObject> objectTemp = new ArrayList<ParseObject>();
                    result = (TextView)findViewById(R.id.result_textview);

                    String name = object.getString("name");
                    String contactName = object.getString("contactName");


                    objectTemp.add(object);
                    result.setText(name + contactName + Integer.toString(objectTemp.size()+5));

                    } else {
                    // something went wrong
                }
            }
        });*/


        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Contractor");
        query2.whereEqualTo("state", "MD");
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> object, ParseException e){
                TextView result;
                if (e == null) {
                    result = (TextView)findViewById(R.id.result1_textview);

                    int name = object.size();
                    result.setText(Integer.toString(name));


                    for(int i = 0; i < object.size() ; i++){
                        sourceObject = object.get(i);

                        objects.add(sourceObject);

                        String name2 = object.get(i).getString("name");
                        String location2 = object.get(i).getString("Location");
                        String contactName2  = object.get(i).getString("contactName");
                        result.append("\n | " + name2 + " | " + location2 + " | " + contactName2 + " | ");

                    }
                }
                else {

                }
            }
        });
        //Create
        LinearLayout llPrincipal = (LinearLayout)findViewById(R.id.searchResultsLayout);
        TextView textViewNew = new TextView(this);

        LinearLayout A = new LinearLayout(this);
        A.setOrientation(LinearLayout.VERTICAL);


        TextView textViewNewA = new TextView(this);
        textViewNewA.setText("Hi");

        A.addView(textViewNewA);

        llPrincipal.addView(A);

        for(int i=0; i<objects.size();i++){
            LinearLayout B = new LinearLayout(this);
            B.setOrientation(LinearLayout.VERTICAL);


            TextView textViewNewB = new TextView(this);
            textViewNewB.setText(objects.get(i).getString("contactName"));

            B.addView(textViewNewB);

            llPrincipal .addView(B);
        }

    }


    public void addToArrayList(ArrayList<ParseObject> objects){

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_landing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {


       /*if(v.getId() == R.id.login_button)
        {

        }*/

        /*else if(v.getId() == R.id.done_button)
        {
            Intent i = new Intent(this, CtoFActivity.class);
            startActivity(i);
            //startActivity(new Intent(this, About.class));
        }*/
    }
}