package com.example.miniscout

data class Match (
   val teamNumber: String,
   val matchNumber: String,
   var elementOneCount: Int=0,
   var elementTwoCount: Int=0,
   var isIncap: Boolean=false
)
