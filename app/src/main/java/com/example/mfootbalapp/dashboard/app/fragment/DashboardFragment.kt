package com.example.mfootbalapp.dashboard.app.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mfootbalapp.R
import com.example.mfootbalapp.dashboard.app.adapter.TeamsAdapter
import com.example.mfootbalapp.dashboard.app.iviews.DashboardIView
import com.example.mfootbalapp.dashboard.app.presenter.DashboardPresenter
import com.example.mfootbalapp.dashboard.domain.bean.League
import com.example.mfootbalapp.dashboard.domain.bean.TEAM_OBJECT
import com.example.mfootbalapp.dashboard.domain.bean.Team
import com.example.mfootbalapp.detail.app.fragment.DetailFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlin.collections.ArrayList


/**
 * Created by TARAFI Ahmed on 12/03/2020.
 */
class DashboardFragment : Fragment(), DashboardIView {

    lateinit var dashboardPresenter: DashboardPresenter
    lateinit var teamsAdapter: TeamsAdapter
    private var listLeagues: ArrayList<League>? = null
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater?.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboardPresenter = DashboardPresenter()
        dashboardPresenter.setView(this)
        dashboardPresenter.fetchLeaguesList()

        btnCancel.setOnClickListener {
            searchText.text.clear()
        }

        activity?.applicationContext?.let {
            mContext = activity?.applicationContext!!
            teamsAdapter = TeamsAdapter(mContext, null)
        }

        searchText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                dashboardPresenter.fetchTeamsList(editable.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }

    private fun setAutocomplete() {
        if (listLeagues != null) {
            val namesList = arrayOfNulls<String>(listLeagues!!.size)
            for (i in 0 until listLeagues!!.size) {
                namesList[i] = listLeagues!![i].strLeague
            }
            val arrayAdapter = ArrayAdapter(
                mContext,
                android.R.layout.simple_dropdown_item_1line,
                namesList
            )

            searchText.setAdapter(arrayAdapter)
        }
    }

    override fun showTeamsList(listTeams: ArrayList<Team>) {
        teamsAdapter = TeamsAdapter(activity?.applicationContext!!, listTeams)
        list_teams.adapter = teamsAdapter
        val recyclerViewLayoutManager =
            GridLayoutManager(context, 2)
        list_teams.layoutManager = recyclerViewLayoutManager

        teamsAdapter.setOnItemClickListener {
            val args = Bundle()
            val team = teamsAdapter.teamsList!!.get(it)
            args.putSerializable(TEAM_OBJECT, team)
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = DetailFragment()
            fragment.arguments = args
            fragmentTransaction.replace(R.id.frame_container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    override fun handleLeagueList(listLeagues: ArrayList<League>) {
        this.listLeagues = listLeagues
        setAutocomplete()
    }

    override fun showLoading() {
        loadingProgress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingProgress.visibility = View.GONE
    }
}