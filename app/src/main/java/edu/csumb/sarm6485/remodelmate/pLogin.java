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

import com.parse.ui.ParseLoginBuilder;


public class pLogin extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //ParseLoginBuilder builder = new ParseLoginBuilder(MyActivity.this);
        //startActivityForResult(builder.build(), 0);



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


        if(v.getId() == R.id.login_button)
        {


            Intent i = new Intent(this, HomeOwner.class);
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

