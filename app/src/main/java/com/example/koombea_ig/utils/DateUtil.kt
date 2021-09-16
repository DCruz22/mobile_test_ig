package com.example.koombea_ig.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtil {

    fun formatDate(date: String): LocalDateTime? {
        val df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZ")
        val dt = LocalDateTime.parse(date, df)
        return dt
    }

    fun String.toOrdinalDate(): String {
        val date = this.split(" ")
        val month = date[1]
        val day = date[2].toInt()
        val ordinal = getDayOfMonthSuffix(day)
        return "$month $day$ordinal"

    }

    fun getDayOfMonthSuffix(day: Int): String {
        if (day in 11..13) {
            return "th";
        }

        return when (day % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
    }
}