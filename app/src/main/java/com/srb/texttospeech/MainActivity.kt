package com.srb.texttospeech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(),TextToSpeech.OnInitListener {
    private var tts : TextToSpeech?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSpeak.isEnabled=false

        tts = TextToSpeech(this,this)
        btnSpeak.setOnClickListener{
                 textToSpeech()
        }
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){

            val result= tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","The language specified is not supported")
            }else{
                btnSpeak.isEnabled=true
            }
        }
        else{
            Log.e("TTS","Initialization failed")
        }
    }

    private fun textToSpeech(){
        val text = etWrite.text.toString()
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onDestroy(){
        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
         super.onDestroy()

    }

}