package com.example.texttospeech;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity{
    TextToSpeech textToSpeech;
    Button speakButton;
    EditText editText;
    EditText etText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        textToSpeech = new TextToSpeech(this, status -> {
           if (status == TextToSpeech.SUCCESS){
               textToSpeech.setLanguage(Locale.UK);
           }
        });

        speakButton.setOnClickListener(v -> {
            String text = editText.getText().toString();

            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        });


    }
}