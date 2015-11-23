package edu.csumb.sarm6485.remodelmate;

/**
 * Created by michaelsarmiento on 11/20/15.
 */

import android.media.MediaPlayer;
import android.util.Log;
import android.view.Gravity;
import android.view.View.OnClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class Login extends Activity implements OnClickListener {

    String input1;
    String input2;
    EditText cinput1;
    EditText cinput2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        View LoginButton = findViewById(R.id.login_button);
        LoginButton.setOnClickListener(this);

        View layoutView = findViewById(R.id.layoutLinearLogin);
        layoutView.setOnClickListener(this);


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



        if (v.getId() == R.id.login_button) {


            cinput1 = (EditText) findViewById(R.id.username_field);
            input1 = cinput1.getText().toString();
            cinput2 = (EditText) findViewById(R.id.password_field);
            input2 = cinput2.getText().toString();

            ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Users");
            query2.whereEqualTo("username", input1);
            query2.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> object, ParseException e) {


                    System.out.println("You are inside 1");
                    if (e == null) {
                        System.out.println("size: " + object.size());
                        if (object.size() == 0) {
                            Intent B = new Intent(getApplicationContext(), errorLogin.class);


                            Toast toast = Toast.makeText(getApplicationContext(), "Please Try Again.\n Your username does not exist.", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP | Gravity.LEFT, 150, 50);
                            toast.show();

                            startActivity(B);
                        }

                        for (int i = 0; i < object.size(); i++) {
                            String compareTo = object.get(i).getString("password");
                            System.out.println(compareTo);
                            int accountType = object.get(i).getInt("account");

                            System.out.println("acc:" + accountType);
                            System.out.println("password: " + compareTo);
                            System.out.println("equals: " + compareTo.equals(input2));

                            if(compareTo.equals(input2)) {
                                if (accountType == 1) {
                                    Intent A = new Intent(getApplicationContext(), HomeOwner.class);
                                    startActivity(A);
                                }
                                else if (accountType == 2) {
                                    Intent C = new Intent(getApplicationContext(), Contractor.class);
                                    startActivity(C);
                                }
                            } else if(!(compareTo.equals(input2))) {


                                Intent B = new Intent(getApplicationContext(), errorLogin.class);


                                Toast toast = Toast.makeText(getApplicationContext(), "Please Try Again.\n Your password did not match", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.TOP | Gravity.LEFT, 150, 50);
                                toast.show();

                                startActivity(B);

                            }

                        }
                        //cinput2 = (EditText)findViewById(R.id.where_field);
                        //input2 = cinput2.getText().toString();
                        //result = (TextView)findViewById(R.id.result_textview);
                        //result.setText(Double.toString(fout));

                        //Intent i = new Intent(this, SearchResults.class);
                        //Bundle extraInfo = new Bundle();
                        //extraInfo.putDouble("feet", number);
                        //extraInfo.putString("result2", input2);
                        //i.putExtras(extraInfo);


                        //startActivity(i);
                    } else {
                        System.out.println(e);
                    }
                }
            });
        }

        else if(v.getId() == R.id.layoutLinearLogin){

            View thisView = findViewById(R.id.layoutLinearLogin);

            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(thisView.getWindowToken(), 0);
        }
    }
}