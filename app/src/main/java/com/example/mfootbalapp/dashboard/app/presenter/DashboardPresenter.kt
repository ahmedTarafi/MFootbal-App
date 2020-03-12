package com.example.mfootbalapp.dashboard.app.presenter

import com.example.mfootbalapp.dashboard.app.iviews.DashboardIView
import com.example.mfootbalapp.dashboard.domain.bean.League
import com.example.mfootbalapp.dashboard.domain.bean.Team
import com.example.mfootbalapp.dashboard.domain.interactor.LeagueListUseCase
import com.example.mfootbalapp.dashboard.domain.interactor.LeagueListUseCaseImpl
import com.example.mfootbalapp.dashboard.domain.interactor.TeamListUseCase
import com.example.mfootbalapp.dashboard.domain.interactor.TeamListUseCaseImpl
import javax.inject.Inject

/**
 * Created by TARAFI Ahmed on 12/03/2020.
 */
class DashboardPresenter @Inject constructor() {
    private var dashboardIView: DashboardIView?
    private val leagueListUseCaseImpl:LeagueListUseCaseImpl
    private val teamListUseCaseImpl: TeamListUseCaseImpl

    init {
        dashboardIView = null
        leagueListUseCaseImpl = LeagueListUseCaseImpl()
        teamListUseCaseImpl = TeamListUseCaseImpl()
    }

    fun fetchTeamsList(leagueName: String) {
        dashboardIView?.showLoading()
        teamListUseCaseImpl.execute(leagueName, object : TeamListUseCase.Callback{
            override fun onTeamListSuccess(listTeams: ArrayList<Team>) {
                dashboardIView?.hideLoading()
                dashboardIView?.showTeamsList(listTeams)
            }

            override fun onTeamListFailure() {

            }

        })
    }

    fun setView(dashboardIView: DashboardIView) {
        this.dashboardIView = dashboardIView
    }

    fun fetchLeaguesList() {
        dashboardIView?.showLoading()
        leagueListUseCaseImpl.execute(object:LeagueListUseCase.Callback{
            override fun onLeagueListSuccess(listLeagues: ArrayList<League>) {
                dashboardIView?.hideLoading()
                dashboardIView?.handleLeagueList(listLeagues)
            }

            override fun onLeagueListFailure() {
                dashboardIView?.hideLoading()
            }

        })
    }
}