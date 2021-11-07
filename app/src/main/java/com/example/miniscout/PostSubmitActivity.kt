package com.example.miniscout
import android.app.Activity
import com.google.gson.Gson
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.match_input_activity.*
import android.widget.Toast
import android.content.Intent
import android.util.Log
import android.widget.EditText

import android.os.Bundle
import com.example.miniscout.bestpackage.MatchTimer
import kotlinx.android.synthetic.main.post_submit_activity.*
import kotlinx.android.synthetic.main.scouting_activity.*

class PostSubmitActivity : Activity() {
    lateinit var match: Match
    private fun retrieveMatchData() {
        match = Gson().fromJson(intent.extras!!.get(match_tag).toString(), Match::class.java)
        Log.e("match", match.timeline?.timeline.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_submit_activity)
        retrieveMatchData()
        initScoutingSetup()
    }

    private fun initScoutingSetup() {
        tv_sumOfPlacemenOne.text = getString(R.string.total_placement_one, match.elementOneCount.toString())
        tv_sumOfPlacemenTwo.text = getString(R.string.total_placement_two, match.elementTwoCount.toString())
        if (match.isIncap) tv_finalIncap.text = getString(R.string.final_incap_status, "yes") else tv_finalIncap.text = getString(R.string.final_incap_status, "no")
        et_final_match_number.setText(match.matchNumber)
        et_final_team_number.setText(match.teamNumber)

    }

    fun getTeamNumber(): String {
        return et_final_team_number.text.toString()
    }

    fun getMatchNumber(): String {
        return et_final_match_number.text.toString()
    }

    private fun isEditTextNotEmpty(edittext: List<EditText>): Boolean {
        for (item in edittext) {
            if (item.text.toString() == "") {
                return false
            }

        }
        return true
    }

    fun startNextActivity(view: View) {
        intent = Intent(this, MatchInputActivity::class.java)
        if (isEditTextNotEmpty(listOf(et_final_match_number, et_final_team_number))) {
            intent.putExtra(match_tag, Gson().toJson(match))


            Log.e(
                "match_data",
                "${Gson().fromJson(intent.extras!!.get(match_tag).toString(), Match::class.java)}"
            )
            startActivity(intent)
        }

    }
}