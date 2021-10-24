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
import kotlinx.android.synthetic.main.scouting_activity.*

class PostSubmitActivity : Activity(){
    lateinit var match: Match
    private fun retrieveMatchData() {
        match = Gson().fromJson(intent.extras!!.get(match_tag).toString(), Match::class.java)
        Log.e("match", match.timeline?.timeline.toString())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_submit_activity)
        retrieveMatchData()

    }
}