package com.example.texttospeech

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
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
            if (status === TextToSpeech.SUCCESS) {
                val result: Int = t1.setLanguage(Locale.GERMAN)
                if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED
                ) {
                    Log.e("TTS", "Language not supported")
                } else {
                    binding.buttonSpeak.setEnabled(true)
                }
            } else {
                Log.e("TTS", "Initialization failed")
            }
        }
        binding.buttonSpeak.setOnClickListener {
            edttext = binding.editText.text.toString()
            var pitch = binding.seekBarPitch.getProgress() as Float / 50
            if (pitch < 0.1) pitch = 0.1f
            var speed = binding.seekBarSpeed.getProgress() as Float / 50
            if (speed < 0.1) speed = 0.1f

            t1.setPitch(pitch)
            t1.setSpeechRate(speed)

            t1.speak(edttext, TextToSpeech.QUEUE_FLUSH, null)
            Toast.makeText(this, edttext, Toast.LENGTH_LONG).show()

        }


    }


    override fun onPause() {
        t1.stop()
        t1.shutdown()
        super.onPause()
    }


}