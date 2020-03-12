package com.example.mfootbalapp.dashboard.domain.interactor

import android.content.Context
import com.example.mfootbalapp.dashboard.domain.bean.League

interface LeagueListUseCase {
    interface Callback {
        fun onLeagueListSuccess(listLeagues: ArrayList<League>)
        fun onLeagueListFailure()
    }

    fun execute(callback: Callback)
}