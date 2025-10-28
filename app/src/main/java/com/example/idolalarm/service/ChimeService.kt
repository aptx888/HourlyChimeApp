package com.example.idolalarm.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import com.example.idolalarm.R
import com.example.idolalarm.util.TTSManager
import com.example.idolalarm.util.TimeUtils
import java.util.*

class ChimeService : Service() {

    private lateinit var ttsManager: TTSManager
    private val handler = Handler(Looper.getMainLooper())

    private val runnable = object : Runnable {
        override fun run() {
            val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            val message = TimeUtils.getGreeting(hour)

            ttsManager.speak(message) {
                val mp = MediaPlayer.create(this@ChimeService, R.raw.nighteat)
                mp?.setOnCompletionListener { it.release() }
                mp?.start()
            }

            handler.postDelayed(this, 60 * 60 * 1000) // 每小时播报
        }
    }

    override fun onCreate() {
        super.onCreate()
        ttsManager = TTSManager(this) {
            // 初始化完成后启动定时任务
            handler.post(runnable)
        }
    }

    override fun onDestroy() {
        handler.removeCallbacks(runnable)
        ttsManager.shutdown()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
