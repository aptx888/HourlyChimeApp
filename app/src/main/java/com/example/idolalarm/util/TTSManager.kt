package com.example.idolalarm.util

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import java.util.*

class TTSManager(
    context: Context,
    private val onReady: (() -> Unit)? = null
) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech = TextToSpeech(context, this)

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.CHINA
            onReady?.invoke()   // 初始化完成后回调
        }
    }

    fun speak(text: String, onDone: (() -> Unit)? = null) {
        val params = Bundle()
        val utteranceId = "tts_${System.currentTimeMillis()}"

        tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {}
            override fun onDone(utteranceId: String?) { onDone?.invoke() }
            override fun onError(utteranceId: String?) {}
        })

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, params, utteranceId)
        } else {
            @Suppress("DEPRECATION")
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    fun shutdown() {
        tts.stop()
        tts.shutdown()
    }
}
