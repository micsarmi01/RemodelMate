package edu.csumb.sarm6485.remodelmate;

/**
 * Created by michaelsarmiento on 11/20/15.
 */

import android.view.View.OnClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;


public class Questions extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);


        View SearchButton = findViewById(R.id.submit_search);
        SearchButton.setOnClickListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.project_type);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.project, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Spinner spinner2 = (Spinner) findViewById(R.id.warranty);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.warranty_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);


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
        EditText cinput1;
        EditText cinput2;
        String input;
        String input2;
        TextView result;

        if(v.getId() == R.id.submit_search)
        {


            cinput1 = (EditText)findViewById(R.id.feet);
            input = cinput1.getText().toString();
            double number = Double.parseDouble(input);

            //cinput2 = (EditText)findViewById(R.id.where_field);
            //input2 = cinput2.getText().toString();
            //result = (TextView)findViewById(R.id.result_textview);
            //result.setText(Double.toString(fout));

            Intent i = new Intent(this, SearchResults.class);
            Bundle extraInfo = new Bundle();
            extraInfo.putDouble("feet", number);
            //extraInfo.putString("result2", input2);
            i.putExtras(extraInfo);


            startActivity(i);


        }


        /*else if(v.getId() == R.id.advSearch_button)
        {
            Intent i = new Intent(this, Questions.class);
            startActivity(i);
            //startActivity(new Intent(this, About.class));
        }*/
    }
}

