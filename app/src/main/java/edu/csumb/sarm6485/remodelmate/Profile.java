package edu.csumb.sarm6485.remodelmate;

/**
 * Created by michaelsarmiento on 11/20/15.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.view.View.OnClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Profile extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        View LoginButton = findViewById(R.id.getvoucher);
        LoginButton.setOnClickListener(this);

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


        if(v.getId() == R.id.getvoucher) {

            Toast toast = Toast.makeText(this, "Successfuly Collected Voucher!\n Contractor will contact you shortly!\"", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP | Gravity.LEFT, 150, 50);
            toast.show();

            Intent i = new Intent(this, HomeOwner.class);
            startActivity(i);

        }
    }
}