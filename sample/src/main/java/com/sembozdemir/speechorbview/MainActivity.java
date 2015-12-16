package com.sembozdemir.speechorbview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.sembozdemir.speechorbview.library.SpeechOrbView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        * handle SpeechOrbView
        * If you want to toggle animation of SpeechOrbView when clicking, do like that below
        * */
        final SpeechOrbView speechOrbView = (SpeechOrbView) findViewById(R.id.speech_orb_view);
        speechOrbView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!speechOrbView.isPlaying()) {
                    speechOrbView.play();
                } else {
                    speechOrbView.stop();
                }
            }
        });

        /*
        * change repeat mode (default value is true)
        * You can disable or enable repeat mode if you want
        * */
        CheckBox checkboxRepeatMode = (CheckBox) findViewById(R.id.checkbox_repeat_mode);
        checkboxRepeatMode.setChecked(speechOrbView.isRepeatMode());
        checkboxRepeatMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                speechOrbView.setRepeatMode(isChecked);
            }
        });

        /*
        * start/end listener (optional)
        * If you want to handle of start or end animation event
        * */
        speechOrbView.setListener(new SpeechOrbView.Listener() {
            @Override
            public void onStartPlaying() {
                // do something
                Toast.makeText(MainActivity.this, "SpeechOrbView is playing", Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onEndPlaying() {
                // do something
                Toast.makeText(MainActivity.this, "SpeechOrbView has stopped", Toast.LENGTH_LONG)
                        .show();
            }
        });


        /*
        * handle button start
        * If you want to start animation of SpeechOrbView, just call play()
        * */
        findViewById(R.id.button_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speechOrbView.play();
            }
        });

        /*
        * handle button stop
        * If you want to stop animation of SpeechOrbView, just call stop()
        * */
        findViewById(R.id.button_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speechOrbView.stop();
            }
        });

    }
}
