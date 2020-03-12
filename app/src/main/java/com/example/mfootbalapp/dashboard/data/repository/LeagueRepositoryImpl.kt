package com.example.mfootbalapp.dashboard.data.repository

import com.example.mfootbalapp.dashboard.data.entities.LeagueListJson
import com.example.mfootbalapp.dashboard.data.entities.LeagueMapper
import com.example.mfootbalapp.dashboard.data.entities.TeamsListJson
import com.example.mfootbalapp.dashboard.data.retrofitapi.LeagueService
import com.example.mfootbalapp.dashboard.domain.repository.LeagueRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class LeagueRepositoryImpl : LeagueRepository {
    private val leagueService: LeagueService
    private val leagueMapper: LeagueMapper

    init {
        leagueMapper = LeagueMapper()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(logging).connectTimeout(30, TimeUnit.SECONDS).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        leagueService = retrofit.create(LeagueService::class.java)
    }


    override fun getLeaguesList(leaguesListCallback: LeagueRepository.LeaguesListCallback) {
        leagueService.getListLeagues().enqueue(object : Callback<LeagueListJson> {
            override fun onFailure(call: Call<LeagueListJson>, t: Throwable) {
                leaguesListCallback.onLeagueListFailure()
            }

            override fun onResponse(
                call: Call<LeagueListJson>,
                response: Response<LeagueListJson>
            ) {
                if (response.isSuccessful && response.body()!=null) {
                    val listLeagues = leagueMapper.toLeaguesList(response.body()!!)
                    leaguesListCallback.onLeaguesListSuccess(listLeagues)
                }else{
                    leaguesListCallback.onLeagueListFailure()
                }
            }

        })
    }

    override fun getTeamsForLigue(
        leagueName: String,
        teamsListCallback: LeagueRepository.TeamsListCallback
    ) {
        leagueService.getTeamsForLeague(leagueName).enqueue(object : Callback<TeamsListJson> {
            override fun onFailure(call: Call<TeamsListJson>, t: Throwable) {
                teamsListCallback.onTeamsListFailure()
            }

            override fun onResponse(call: Call<TeamsListJson>, response: Response<TeamsListJson>) {
                if (response.isSuccessful) {
                    val listTeams = leagueMapper.toTeamsList(response.body())
                    teamsListCallback.onTeamsListSuccess(listTeams)
                }else{
                    teamsListCallback.onTeamsListFailure()
                }
            }

        })
    }
}