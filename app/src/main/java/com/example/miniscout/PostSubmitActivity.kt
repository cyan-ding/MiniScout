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

class PostSubmitActivity : Activity(){
    lateinit var match: Match
    private fun retrieveMatchData() {
        match = Gson().fromJson(intent.extras!!.get("timeline").toString(), Match::class.java)
        Log.e("match", match.timeline?.timeline.toString())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_submit_activity)
        retrieveMatchData()
        var count: Int = 0
        initScoutingSetup()
    }
    private fun initScoutingSetup() {
        tv_sumOfPlacemenOne.text = (match.elementOneCount.toString())
        tv_sumOfPlacemenOne.text = (match.elementOneCount.toString())
    }







}