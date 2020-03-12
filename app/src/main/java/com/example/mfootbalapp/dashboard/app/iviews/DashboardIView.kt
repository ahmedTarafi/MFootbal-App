package com.example.mfootbalapp.dashboard.app.iviews

import com.example.mfootbalapp.dashboard.domain.bean.League
import com.example.mfootbalapp.dashboard.domain.bean.Team
import java.util.ArrayList

/**
 * Created by TARAFI Ahmed on 12/03/2020.
 */
interface DashboardIView {
    fun handleLeagueList(listLeagues: ArrayList<League>)
    fun showLoading()
    fun hideLoading()
    fun showTeamsList(listTeams: ArrayList<Team>)
}