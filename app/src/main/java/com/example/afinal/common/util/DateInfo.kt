package com.example.afinal.common.util

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.TextStyle
import java.util.*

// If u wanna test any function separately, kotlin playground (a web) is helpful.

// Get current date in specific String pattern. Used in main page, accumulation page.
fun getCurDate(): String {
    return LocalDate.now((ZoneOffset.ofHours(8))).toString()
    //  = "year-month-day" , which is the form we store in database, and transfer between function.
    // * Note: 2022/6/9 is stored as 2022-06-09, so that the sort won't go wrong when sorted.
    // ex. 2022-6-9 > 2022-6-10, since 9>1. But 2022-06-09 < 2022-06-10, which is correct.
}

// Get month-year (ex. June 2022). Used in mainpage.
fun getMY(dateStr: String): String {
    val dateLd: LocalDate = LocalDate.parse(dateStr)
    val month: String = dateLd.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    val year: String = dateLd.getYear().toString()
    return month + " " + year
}

// Transform dateStr into List<Int>
fun getDateInt(dateStr: String): List<Int> {
    return  dateStr.split("-").toList().map { it.toInt() }
}

// Get corresponding Sunday date for current date. Used in accumulation page.
fun toSunday(dateStr:String): String{
    val dateLd : LocalDate = LocalDate.parse(dateStr)
    val dayNameInt : Int = dateLd.getDayOfWeek().getValue()
    // DayOfWeek = one of {"Monday", "TuesDay",..., "Sunday"}
    val sunLd: LocalDate = dateLd.plusDays((7-dayNameInt).toLong())
    return sunLd.toString() // = 1 for Monday, 7 for Sunday, etc.
}

// Get Mon, Tue, ..., Sun from dateStr. Used in mainpage.
fun getDateName(dateStr:String): String {
    val dow: DayOfWeek = LocalDate.parse(dateStr).dayOfWeek
    return dow.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
    // = one of {"Mon", "Tue", ..., "Sun"}
}

fun getDay(dateStr:String): String {
    val spiltStr = dateStr.split("-").toList()
    return spiltStr[2]
}

// 2022-6-9 -> 2022-06-09 (otherwise some library function might failed)
fun fillZero(dateStr:String):String{
    val dateInt : List<Int> = dateStr.split("-").toList().map { it.toInt() }
    return LocalDate.of(dateInt[0], dateInt[1], dateInt[2]).toString()
}

// Get shifted day (input + offset, offset can be +, -, or 0). Used in accumulation page
fun shiftDate(dateStr:String, offset:Long): String{
    val dateLd : LocalDate = LocalDate.parse(dateStr)
    val shiftLd: LocalDate = dateLd.plusDays(offset)
    return shiftLd.toString() // output Date = input Date + offset
}