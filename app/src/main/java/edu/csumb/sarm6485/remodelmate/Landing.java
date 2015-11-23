package edu.csumb.sarm6485.remodelmate;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.VideoView;

import com.parse.Parse;
import com.parse.ParseObject;

public class Landing extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        // Set up a click listener for the Calculate button.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "0aPN93EAFKwJGX9uAeQRD4edwfuA4ml7LIhgLFuV", "yn2tKwCYp1nitqnqndWz3J8tafANcf9C0DAYUTZp");

        View LoginButton = findViewById(R.id.login_button);
        LoginButton.setOnClickListener(this);

        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video);
        videoView.start();

        /*ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "barf");
        testObject.saveInBackground();*/
        videoView.setOnPreparedListener (new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        View SignUpButton = findViewById(R.id.signup_button);
        SignUpButton.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_landing, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video);
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

    }
    @Override
    protected void onPause() {
        super.onPause();
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video);
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
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

            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.signup_button)
        {

            Intent i = new Intent(this, TypeOfAccount.class);
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
