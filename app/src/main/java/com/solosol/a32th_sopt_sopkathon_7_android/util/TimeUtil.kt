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
        val _diffMinutes = diffMinutes - 539
        return if (_diffMinutes >= 60) {
            val realDiffHours = _diffMinutes / 60
            "$realDiffHours 시간 전"
        } else {
            "$_diffMinutes 분 전"
        }
    }
}