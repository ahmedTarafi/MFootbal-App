package com.example.mfootbalapp.dashboard.data.retrofitapi

import com.example.mfootbalapp.dashboard.data.entities.LeagueListJson
import com.example.mfootbalapp.dashboard.data.entities.TeamsListJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


const val LEAGUE_LIST_URL = "all_leagues.php"
const val TEAM_LIST_URL ="search_all_teams.php"
const val TEAM_DETAIL_URL = ""

interface LeagueService {
    @GET(LEAGUE_LIST_URL)
    fun getListLeagues(): Call<LeagueListJson>

    @GET(TEAM_LIST_URL)
    fun getTeamsForLeague(@Query("l") leagueName:String):Call<TeamsListJson>
}