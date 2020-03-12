package com.example.mfootbalapp.dashboard.domain.repository

import android.content.Context
import com.example.mfootbalapp.dashboard.domain.bean.League
import com.example.mfootbalapp.dashboard.domain.bean.Team

interface LeagueRepository {
    interface LeaguesListCallback {
        fun onLeaguesListSuccess(listLeagues : ArrayList<League>)
        fun onLeagueListFailure()
    }

    interface TeamsListCallback {
        fun onTeamsListSuccess(listTeams : ArrayList<Team>)
        fun onTeamsListFailure()
    }

    fun getLeaguesList(leaguesListCallback: LeaguesListCallback)
    fun getTeamsForLigue(leagueName: String,teamsListCallback: TeamsListCallback)
}