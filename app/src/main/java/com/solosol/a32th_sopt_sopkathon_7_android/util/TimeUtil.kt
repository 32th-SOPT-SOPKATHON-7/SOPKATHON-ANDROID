package com.solosol.a32th_sopt_sopkathon_7_android.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object TimeUtil {
    fun calculateTimeDifference(dateTimeString: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val dateTime = LocalDateTime.parse(dateTimeString, formatter)


        val now = LocalDateTime.now()
        val diffMinutes = ChronoUnit.MINUTES.between(dateTime, now)

        return if (diffMinutes >= 60) {
            val diffHours = diffMinutes / 60
            "$diffHours 시간 전"
        } else {
            "$diffMinutes 분 전"
        }
    }
}