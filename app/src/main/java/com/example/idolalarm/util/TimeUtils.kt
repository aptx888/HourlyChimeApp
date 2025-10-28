package com.example.idolalarm.util

object TimeUtils {
    fun getGreeting(hour: Int): String {
        return when (hour) {
            in 5..11 -> "早上好，现在是${hour}点整，开始新的一天吧！"
            in 12..13 -> "中午好，现在是${hour}点，该吃午饭啦！"
            in 14..17 -> "下午好，现在是${hour}点，继续努力吧！"
            in 18..21 -> "晚上好，现在是${hour}点，该放松一下啦！"
            else -> "夜深了，现在是${hour}点，早点休息吧！"
        }
    }
}
