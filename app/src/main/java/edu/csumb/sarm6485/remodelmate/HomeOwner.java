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
import android.widget.EditText;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;


public class HomeOwner extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeowner);


        View SearchButton = findViewById(R.id.search_button);
        SearchButton.setOnClickListener(this);

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "0aPN93EAFKwJGX9uAeQRD4edwfuA4ml7LIhgLFuV", "yn2tKwCYp1nitqnqndWz3J8tafANcf9C0DAYUTZp");
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

        if(v.getId() == R.id.search_button)
        {

            cinput1 = (EditText)findViewById(R.id.what_field);
            input = cinput1.getText().toString();

            cinput2 = (EditText)findViewById(R.id.where_field);
            input2 = cinput2.getText().toString();
            //result = (TextView)findViewById(R.id.result_textview);
            //result.setText(Double.toString(fout));

            Intent i = new Intent(this, SearchResultsBroad.class);
            Bundle extraInfo = new Bundle();
            extraInfo.putString("result", input);
            i.putExtras(extraInfo);


            startActivity(i);


        }

        /*else if(v.getId() == R.id.done_button)
        {
            Intent i = new Intent(this, CtoFActivity.class);
            startActivity(i);
            //startActivity(new Intent(this, About.class));
        }*/
    }
}

