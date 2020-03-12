package com.example.mfootbalapp.dashboard.domain.interactor

import android.content.Context
import com.example.mfootbalapp.dashboard.data.repository.LeagueRepositoryImpl
import com.example.mfootbalapp.dashboard.domain.bean.League
import com.example.mfootbalapp.dashboard.domain.repository.LeagueRepository

class LeagueListUseCaseImpl : LeagueListUseCase {
    private var leagueRepository: LeagueRepositoryImpl

    init {
        leagueRepository = LeagueRepositoryImpl()
    }

    override fun execute(callback: LeagueListUseCase.Callback) {
        leagueRepository.getLeaguesList(object : LeagueRepository.LeaguesListCallback {
            override fun onLeaguesListSuccess(listLeagues: ArrayList<League>) {
                callback.onLeagueListSuccess(listLeagues)
            }

            override fun onLeagueListFailure() {
                callback.onLeagueListFailure()
            }
        })
    }

}