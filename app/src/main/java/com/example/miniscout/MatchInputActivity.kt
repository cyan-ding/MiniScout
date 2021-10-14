package com.example.miniscout
import com.google.gson.Gson
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.match_input_activity.*
import android.widget.Toast
import android.content.Intent
import android.util.Log
import android.widget.EditText

lateinit var intent:Intent
public var match_tag = "Match"

class MatchInputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.match_input_activity)
    }

    fun getTeamNumber(): String {
        return et_team_number.text.toString()
    }

    fun getMatchNumber(): String {
        return et_match_number.text.toString()
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
        intent = Intent(this,ScoutingActivity::class.java)
        if (isEditTextNotEmpty(listOf(et_match_number,et_team_number))) {
        intent.putExtra(match_tag, Gson().toJson(Match(getTeamNumber(), getMatchNumber())))
            Log.e("match_data","${Gson().fromJson(intent.extras!!.get(match_tag).toString(), Match::class.java)}")
        startActivity(intent)

        }
    }

}
