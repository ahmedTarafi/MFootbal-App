package com.example.mfootbalapp.dashboard.domain.bean

import java.io.Serializable

const val TEAM_OBJECT = "com.example.mfootbalapp.dashboard.domain.bean.Team"

data class Team (val idTeam:Long,
                 val strTeam:String,
                 val strLeague:String,
                 val strTeamBadge:String,
                 val strDescriptionEN:String,
                 val strCountry:String) :Serializable{
}