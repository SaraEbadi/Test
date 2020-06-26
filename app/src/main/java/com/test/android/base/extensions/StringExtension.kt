package com.test.android.base.extensions

private const val SECOND_MILLIS = 1000
private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
private const val DAY_MILLIS = 24 * HOUR_MILLIS

fun String.timeAgo(): String? {
    return getTimeAgo(this.toLong())
}

private fun getTimeAgo(time: Long): String? {
    var myTime = time
    if (myTime < 1000000000000L) {
        myTime *= 1000
    }

    val now = System.currentTimeMillis()
    if (time > now || myTime <= 0) {
        return null
    }

    val diff = now - myTime
    return when {
        diff < MINUTE_MILLIS -> {
            "هم اکنون"
        }
        diff < 2 * MINUTE_MILLIS -> {
            "یک دقیقه پیش"
        }
        diff < 50 * MINUTE_MILLIS -> {
            (diff / MINUTE_MILLIS).toString() + " دقیقه پیش"
        }
        diff < 90 * MINUTE_MILLIS -> {
            "یک ساعت پیش"
        }
        diff < 24 * HOUR_MILLIS -> {
            (diff / HOUR_MILLIS).toString() + " ساعت پیش"
        }
        diff < 48 * HOUR_MILLIS -> {
            "دیروز"
        }
        else -> {
            (diff / DAY_MILLIS).toString() + " روز پیش"
        }
    }
}

fun String.toPersianNumber(): String {
    var newsString = replace("1", "۱")
    newsString = newsString.replace("2", "۲")
    newsString = newsString.replace("3", "۳")
    newsString = newsString.replace("4", "۴")
    newsString = newsString.replace("5", "۵")
    newsString = newsString.replace("6", "۶")
    newsString = newsString.replace("7", "۷")
    newsString = newsString.replace("8", "۸")
    newsString = newsString.replace("9", "۹")
    newsString = newsString.replace("0", "۰")
    return newsString
}