package com.example.mfootbalapp.dashboard.data.entities

import com.example.mfootbalapp.dashboard.domain.bean.League
import com.example.mfootbalapp.dashboard.domain.bean.Team
import retrofit2.Response

class LeagueMapper {
    fun toLeaguesList(leagueListJson: LeagueListJson): ArrayList<League> {
        val listLeagues = ArrayList<League>()
        if(leagueListJson?.leagues!=null){
            leagueListJson.leagues.mapTo(listLeagues){toLeagueBO(it)}
        }

        return listLeagues
    }

    private fun toLeagueBO(leagueJson: LeagueJson): League {
        return League(idLeague = leagueJson.idLeague,
            strLeague = leagueJson.strLeague?:"",
            strLeagueAlternate = leagueJson.strLeagueAlternate?:"",
            strSport = leagueJson.strSport?:"")
    }

    fun toTeamsList(teamsListJson: TeamsListJson?): ArrayList<Team> {
        val listTeams = ArrayList<Team>()
        if(teamsListJson?.teams != null){
            teamsListJson.teams.mapTo(listTeams){toTeamBO(it)}
        }

        return listTeams
    }

    private fun toTeamBO(teamJson: TeamJson): Team {
        return Team(idTeam = teamJson.idTeam,
            strLeague = teamJson.strLeague,
            strDescriptionEN = teamJson.strDescriptionEN ,
            strTeamBadge = teamJson.strTeamBadge,
            strTeam = teamJson.strTeam,
            strCountry = teamJson.strCountry)
    }

}