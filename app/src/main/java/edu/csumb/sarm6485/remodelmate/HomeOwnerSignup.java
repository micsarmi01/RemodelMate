package edu.csumb.sarm6485.remodelmate;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;
import android.widget.VideoView;

import com.parse.Parse;
import com.parse.ParseObject;

public class HomeOwnerSignup extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeowner_signup);
        // Set up a click listener for the Calculate button.

        View submitButton = findViewById(R.id.customer_submit);
        submitButton.setOnClickListener(this);


        /*ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "barf");
        testObject.saveInBackground();*/

        View signupLayout = findViewById(R.id.homeOwner_signup_view);
        signupLayout.setOnClickListener(this);

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


        if(v.getId() == R.id.customer_submit)
        {


            EditText cinput1;
            EditText cinput2;

            String input;
            String input2;

            cinput1 = (EditText)findViewById(R.id.customer_password);
            input = cinput1.getText().toString();

            cinput2 = (EditText)findViewById(R.id.customer_email);
            input2 = cinput2.getText().toString();

            ParseObject gameScore = new ParseObject("Users");
        gameScore.put("username", input2);
        gameScore.put("password", input );
        gameScore.put("account", 1);
        gameScore.saveInBackground();
            Toast toast = Toast.makeText(getApplicationContext(), "Congrats! Welcome to our small community!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP | Gravity.LEFT, 150, 50);
            toast.show();

            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }
       else if(v.getId() == R.id.homeOwner_signup_view)
        {

            View thisView = findViewById(R.id.homeOwner_signup_view);

            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(thisView.getWindowToken(), 0);
        }
        /*else if(v.getId() == R.id.done_button)
        {
            Intent i = new Intent(this, CtoFActivity.class);
            startActivity(i);
            //startActivity(new Intent(this, About.class));
        }*/
    }
}
