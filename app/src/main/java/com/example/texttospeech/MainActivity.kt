package com.example.texttospeech

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.example.texttospeech.databinding.ActivityMainBinding
import java.util.*


//Text to speechA TextToSpeech instance can only be used to synthesize text once
// it has completed its initialization. Implement the TextToSpeech.OnInitListener
// to be notified of the completion of the initialization.
//When you are done using the TextToSpeech instance,
// call the shutdown() method to release the native resources
// used by the TextToSpeech engine.
// Apps targeting Android 11 that use text-to-speech should declare TextToSpeech.
// Engine.INTENT_ACTION_TTS_SERVICE in the queries elements of their manifest:
//Speech to text means that anything that the user says is converted into text.
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var t1: TextToSpeech
    lateinit var edttext: String
    lateinit var imgtxt: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        t1 = TextToSpeech(applicationContext) { status ->
            if (status != TextToSpeech.ERROR) {
                t1.language = Locale.UK
            }
        }
        binding.btnSpeak.setOnClickListener {
            edttext = binding.edtTxt.text.toString()
            Toast.makeText(this, edttext, Toast.LENGTH_LONG).show()
            t1.speak(edttext, TextToSpeech.QUEUE_FLUSH, null)
        }


    }


    override fun onPause() {
        t1.stop()
        t1.shutdown()
        super.onPause()
    }


}