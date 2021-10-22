package com.example.miniscout.bestpackage

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import com.example.miniscout.R


class MatchTimer(
    private var context: Context,
    millisInFuture: Long,
    private val tv_timer_display: TextView
) : CountDownTimer(millisInFuture, 1000) {
    var timeMS: Long? = null
    fun getTimeMS(): Long {
        timeMS?.let {
            return it
        }
        return 0
    }

    private fun convMillis(millisUntilFinished: Long): String {
        return (millisUntilFinished / 1000).toString()
    }

    override fun onTick(millisUntilFinished: Long) {
        tv_timer_display.text = convMillis(millisUntilFinished).toString()
        timeMS = millisUntilFinished
    }

    override fun onFinish() {
        tv_timer_display.setTextColor(ContextCompat.getColor(context, R.color.red))
        tv_timer_display.text = "Done"
    }
}