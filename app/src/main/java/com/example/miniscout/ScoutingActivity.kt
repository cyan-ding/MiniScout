package com.example.miniscout

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import com.example.miniscout.bestpackage.MatchTimer
import com.google.gson.Gson
import kotlinx.android.synthetic.main.match_input_activity.*


import kotlinx.android.synthetic.main.scouting_activity.*
import kotlinx.android.synthetic.main.scouting_activity.tv_match_number
import kotlinx.android.synthetic.main.scouting_activity.tv_team_number
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
            Timeline.TimelineEntry(
                Timeline.ActionType.Placement1,
                timer.getTimeMS(),
                match.elementOneCount)
            updateButtonLabel(btn_placement_one, R.string.btn_placement_one, match.elementOneCount)
            Log.e("RemovePlacement1", match.elementOneCount.toString())
            return@setOnLongClickListener true


        }
        btn_placement_two.setOnLongClickListener {
            match.elementTwoCount--
            Timeline.TimelineEntry(
                Timeline.ActionType.Placement2,
                timer.getTimeMS(),
                match.elementTwoCount)
            updateButtonLabel(btn_placement_two, R.string.btn_placement_two, match.elementTwoCount)
            Log.e("RemovePlacement2", match.elementTwoCount.toString())
            return@setOnLongClickListener true


        }


    }

     fun placementOneOnClick(view: View) {
        match.elementOneCount++
        updateButtonLabel(btn_placement_one, R.string.btn_placement_one, match.elementOneCount)
        timeline.timeline.add(
            Timeline.TimelineEntry(
                Timeline.ActionType.Placement1,
                timer.getTimeMS(),
                match.elementOneCount
            )
        )
         Log.e("AddPlacement1", match.elementOneCount.toString())
    }

    fun placementTwoOnClick(view: View) {
        match.elementTwoCount++
        updateButtonLabel(btn_placement_two, R.string.btn_placement_two, match.elementTwoCount)
        timeline.timeline.add(
            Timeline.TimelineEntry(
                Timeline.ActionType.Placement2,
                timer.getTimeMS(),
                match.elementTwoCount
            )
        )
        Log.e("AddPlacement2", match.elementTwoCount.toString())
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
    fun getTeamNumber(): String {
        return et_team_number.text.toString()
    }

    fun getMatchNumber(): String {
        return et_match_number.text.toString()
    }

    fun submitOnClick(view: View) {
        Log.e("teamnumber", match.teamNumber)
        Log.e("timeline", timeline.timeline.toString() )
        if (timer.isFinish) {
             intent = Intent(this,PostSubmitActivity::class.java)
            intent.putExtra(match_tag, Gson().toJson(match))

            startActivity(intent)
        }


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








