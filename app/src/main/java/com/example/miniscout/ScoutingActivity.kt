package com.example.miniscout

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.miniscout.bestpackage.MatchTimer

import com.google.gson.Gson


import kotlinx.android.synthetic.main.scouting_activity.*


class ScoutingActivity : Activity() {
    private lateinit var match: Match
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scouting_activity)
        retrieveMatchData()
        initScoutingSetup()
        MatchTimer(this, 30000,tv_timer_display).start()
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

    public fun placementOneOnClick(view: View) {
        match.elementOneCount++
        updateButtonLabel(btn_placement_one, R.string.btn_placement_one, match.elementOneCount)
    }

    public fun placementTwoOnClick(view: View) {
        match.elementTwoCount++
        updateButtonLabel(btn_placement_two, R.string.btn_placement_two, match.elementTwoCount)
    }

    public fun incapOnClick(view: View) {
        when (match.isIncap) {
            true -> match.isIncap = false
            false -> match.isIncap = true

        }
        updateButtonBackground(btn_incap, match.isIncap)

    }

    public fun submitOnClick(view: View) {


    }

    public fun updateButtonLabel(btn: Button, resource: Int, value: Int) {
        btn.text = getString(resource, value)

    }

    fun updateButtonBackground(btn: Button, boolean: Boolean) {
        when (boolean) {
            true -> btn.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
            false -> btn.setBackgroundColor(ContextCompat.getColor(this, R.color.design_default_color_background))

        }

    }

}








