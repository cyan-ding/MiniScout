package com.example.miniscout

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import com.example.miniscout.bestpackage.MatchTimer

import com.google.gson.Gson


import kotlinx.android.synthetic.main.scouting_activity.*
import java.util.*


class ScoutingActivity : Activity() {
    lateinit var match: Match
    lateinit var timer: MatchTimer
    lateinit var timeline: Timeline

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scouting_activity)
        retrieveMatchData()
        initScoutingSetup()
        timer = MatchTimer(this, 30000,tv_timer_display)
        timeline = Timeline(match.teamNumber)
        timer.start()

    }

    private fun retrieveMatchData() {
        match = Gson().fromJson(intent.extras!!.get(match_tag).toString(), Match::class.java)
    }

    private fun initScoutingSetup() {
        tv_team_number.text = (match.teamNumber)
        tv_match_number.text = (match.matchNumber)
        btn_placement_one.text =
            getString(R.string.btn_placement_one, match.elementOneCount.toString())
        btn_placement_two.text =
            getString(R.string.btn_placement_two, match.elementTwoCount.toString())
        btn_placement_one.setOnLongClickListener {
            match.elementOneCount--

            updateButtonLabel(btn_placement_one, R.string.btn_placement_one, match.elementOneCount)
            return@setOnLongClickListener true

        }
        btn_placement_two.setOnLongClickListener {
            match.elementTwoCount--
            updateButtonLabel(btn_placement_two, R.string.btn_placement_two, match.elementTwoCount)
            return@setOnLongClickListener true
        }


    }

     fun placementOneOnClick(view: View) {
        match.elementOneCount++
        updateButtonLabel(btn_placement_one, R.string.btn_placement_one, match.elementOneCount)
        timeline.timeline.add(
            Timeline.TimelineEntry(
                Timeline.ActionType.Placement,
                timer.getTimeMS(),
                match.elementOneCount
            )
        )
    }

    fun placementTwoOnClick(view: View) {
        match.elementTwoCount++
        updateButtonLabel(btn_placement_two, R.string.btn_placement_two, match.elementTwoCount)
        timeline.timeline.add(
            Timeline.TimelineEntry(
                Timeline.ActionType.Placement,
                timer.getTimeMS(),
                match.elementTwoCount
            )
        )
    }

     fun incapOnClick(view: View) {
        when (match.isIncap) {
            true -> match.isIncap = false
            false -> match.isIncap = true


        }
        updateButtonBackground(btn_incap, match.isIncap)

         timeline.timeline.add(
             Timeline.TimelineEntry(
                 Timeline.ActionType.Incap,
                 timer.getTimeMS(),
                 (if(match.isIncap)1 else 0)
             )
         )



    }

    fun submitOnClick(view: View) {
        Log.e("teamnumber", match.teamNumber)
        Log.e("timeline", timeline.timeline.toString() )


    }

    private fun updateButtonLabel(btn: Button, resource: Int, value: Int) {
        btn.text = getString(resource, value)

    }

    private fun updateButtonBackground(btn: Button, boolean: Boolean) {
        when (boolean) {
            true -> btn.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
            false -> btn.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

        }

    }

}








