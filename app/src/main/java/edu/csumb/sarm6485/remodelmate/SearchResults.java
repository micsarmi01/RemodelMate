package edu.csumb.sarm6485.remodelmate;

/**
 * Created by michaelsarmiento on 11/20/15.
 */

import android.util.Log;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.graphics.Color;

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
import java.text.NumberFormat;

public class SearchResults extends Activity implements OnClickListener {
    static ArrayList<ParseObject> objects = new  ArrayList<ParseObject>();
    ParseObject sourceObject;
    int warrantyType = 2;
    double sqFeetInput = 20;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresults);
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "0aPN93EAFKwJGX9uAeQRD4edwfuA4ml7LIhgLFuV", "yn2tKwCYp1nitqnqndWz3J8tafANcf9C0DAYUTZp");

        View LoginButton = findViewById(R.id.pricing);
        LoginButton.setOnClickListener(this);



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
        query2.whereEqualTo("productTags", "roofing");
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> object, ParseException e){
                TextView result;
                LinearLayout mainLinear;
                System.out.println("You are inside 1");
                if (e == null) {
                    /*result = (TextView)findViewById(R.id.result1_textview);

                    int name = object.size();
                    result.setText(Integer.toString(name));*/

                    //ExternalOnClickListener listnerExternal = new ExternalOnClickListener();



                    for(int i = 0; i < object.size() ; i++){
                        sourceObject = object.get(i);
                        //Testing
                        String name2 = object.get(i).getString("name");
                        String city = object.get(i).getString("city");
                        Double warranty1 = object.get(i).getDouble("warranty1");
                        System.out.println("Warranty1: "+ warranty1);
                        Double warranty2 = object.get(i).getDouble("warranty1");
                        Double warranty3 = object.get(i).getDouble("warranty1");

                        Double laborPer1 = object.get(i).getDouble("laborPer1");
                        System.out.println("Labor1: " + laborPer1);
                        Double laborPer2 = object.get(i).getDouble("laborPer1");
                        Double laborPer3 = object.get(i).getDouble("laborPer1");

                        Double roofingMaterialsPer = object.get(i).getDouble("roofingMaterialsPer");
                        Double warrantyCost = 0.0;

                        if(warrantyType==0){
                            warrantyCost = 0.0;
                        }
                        else if(warrantyType==1){
                            warrantyCost += warranty1;
                        }
                        else if(warrantyType==2){
                            warrantyCost += warranty2;
                        }

                        else if(warrantyType==3){
                            warrantyCost += warranty3;
                        }


                        Double laborCost = 0.0;
                        if(sqFeetInput<=20){
                            laborCost = laborPer1;
                        }
                        else if(sqFeetInput<50 && sqFeetInput>20){
                            laborCost = laborPer2;
                        }

                        else if(sqFeetInput>=50){
                            laborCost = laborPer3;
                        }

                        /*String location2 = object.get(i).getString("Location");
                        String contactName2  = object.get(i).getString("contactName");
                        result.append("\n | " + name2 + " | " + location2 + " | " + contactName2 + " | ");*/

                        //objects.add(sourceObject);
                        mainLinear = (LinearLayout)findViewById(R.id.searchResultsLayout);

                        Double totalCostFinal = simpleAlgo(sqFeetInput, warrantyCost, roofingMaterialsPer) + laborCost;

                        System.out.println("Inside the first loop totalCost: " + totalCostFinal + "For person " + (i+1));

                        LinearLayout newLinear = new LinearLayout(getApplicationContext());

                        LinearLayout newLinear1 = new LinearLayout(getApplicationContext());

                        TextView textViewNewB = new TextView(getApplicationContext());
                        TextView textViewNewCity = new TextView(getApplicationContext());
                        TextView Price = new TextView(getApplicationContext());

                        NumberFormat formatter = NumberFormat.getCurrencyInstance();


                        //textViewNewB.setOnClickListener(new ExternalOnClickListener());



                        textViewNewB.setText(name2);
                        textViewNewCity.setText("City: " + city + " \nPrice: " + formatter.format(totalCostFinal));
                        Price.setText("\n Claim Voucher");
                        textViewNewB.setTextColor(Color.parseColor("#233151"));
                        textViewNewB.setTextSize(20);
                        textViewNewCity.setTextColor(Color.parseColor("#F7A656"));
                        Price.setTextColor(Color.parseColor("#1CC38E"));


                        Price.setTextSize(16);
                        textViewNewCity.setTextSize(18);
                        textViewNewCity.setGravity(Gravity.RIGHT);

                        newLinear1.addView(Price);
                        Price.setPadding(0, 0, 0, 0);
                        textViewNewCity.setPadding(40, 5, 5, 5);


                        //View thisView = textViewNewB.getRootView();
                        //thisView.setBackgroundColor(Color.WHITE);
                        newLinear.addView(textViewNewB);
                        newLinear1.addView(textViewNewCity);
                        //newLinear.addView(textVeiwNewPrice);
                        newLinear.setBackgroundColor(Color.WHITE);
                        newLinear1.setBackgroundColor(Color.WHITE);

                        newLinear.setClickable(true);
                        newLinear.setId(R.id.type);

                        newLinear1.setGravity(Gravity.RIGHT);

                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


                        layoutParams.setMargins(0, 0, 0, 0);
                        layoutParams1.setMargins(0, 0, 0, 30);
                        mainLinear.addView(newLinear, layoutParams);
                        mainLinear.addView(newLinear1, layoutParams1);

                        View LoginButton = findViewById(R.id.type);
                        LoginButton.setOnClickListener(new ExternalOnClickListener());
                    }
                }
                else {

                }
            }
        });
        //Create

        //textViewNewA.setText("Hi");

        //A.addView(textViewNewA);

        //llPrincipal.addView(A);

       /* for(int i=0; i<objects.size();i++){
            LinearLayout B = new LinearLayout(this);
            B.setOrientation(LinearLayout.VERTICAL);


            TextView textViewNewB = new TextView(this);
            textViewNewB.setText(objects.get(i).getString("contactName"));
            View thisView = textViewNewB.getRootView();
            thisView.setBackgroundColor(Color.WHITE);
            B.addView(textViewNewB);
            B.setBackgroundColor(Color.WHITE);
            llPrincipal.addView(B);
        }*/



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


       if(v.getId() == R.id.pricing)
        {
            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }

        /*else if(v.getId() == R.id.done_button)
        {
            Intent i = new Intent(this, CtoFActivity.class);
            startActivity(i);
            //startActivity(new Intent(this, About.class));
        }*/
    }

    public double simpleAlgo(double sqFeetPass, double warrantyCostPass, double materialCostPass){

        double totalCost = 0;
        //take square feet and multiply by materialCost
        double totalMatCost = sqFeetPass * materialCostPass;

        //add warranty cost


        //sum
        totalCost = totalMatCost + warrantyCostPass;

        System.out.println("You are inside 2");
        System.out.println(sqFeetPass + " warranty: " + warrantyCostPass  + " materials: " + materialCostPass );
        System.out.println("Total cost = " + totalCost);
        return totalCost;
    }
}

class ExternalOnClickListener implements View.OnClickListener {

    public ExternalOnClickListener() {
        // keep references for your onClick logic
    }

    @Override public void onClick(View v) {
        // TODO: add code here
    }

}