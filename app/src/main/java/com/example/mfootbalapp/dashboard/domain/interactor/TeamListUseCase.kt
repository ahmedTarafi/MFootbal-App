package com.example.mfootbalapp.dashboard.domain.interactor

import android.content.Context
import com.example.mfootbalapp.dashboard.domain.bean.Team

interface TeamListUseCase{
    interface Callback {
        fun onTeamListSuccess(listCard:ArrayList<Team>)
        fun onTeamListFailure()
    }

    fun execute(leagueName: String, callback: Callback)
}