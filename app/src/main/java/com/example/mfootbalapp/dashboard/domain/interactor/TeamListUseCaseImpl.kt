package com.example.mfootbalapp.dashboard.domain.interactor

import android.content.Context
import com.example.mfootbalapp.dashboard.data.repository.LeagueRepositoryImpl
import com.example.mfootbalapp.dashboard.domain.bean.Team
import com.example.mfootbalapp.dashboard.domain.repository.LeagueRepository

class TeamListUseCaseImpl : TeamListUseCase {

    private var leagueRepository: LeagueRepositoryImpl

    init {
        leagueRepository = LeagueRepositoryImpl()
    }

    override fun execute(leagueName: String, callback: TeamListUseCase.Callback) {
        leagueRepository.getTeamsForLigue(leagueName, object : LeagueRepository.TeamsListCallback {
            override fun onTeamsListSuccess(listTeams: ArrayList<Team>) {
                callback.onTeamListSuccess(listTeams)
            }

            override fun onTeamsListFailure() {
                callback.onTeamListFailure()
            }

        })
    }
}