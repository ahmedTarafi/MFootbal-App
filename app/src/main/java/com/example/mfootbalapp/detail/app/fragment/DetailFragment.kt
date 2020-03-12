package com.example.mfootbalapp.detail.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mfootbalapp.R
import com.example.mfootbalapp.dashboard.domain.bean.TEAM_OBJECT
import com.example.mfootbalapp.dashboard.domain.bean.Team
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater?.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val team = arguments?.get(TEAM_OBJECT) as Team

        activity?.applicationContext?.let {
            Glide.with(activity?.applicationContext!!).load(team.strTeamBadge)
                .into(teamImg)
        }
        countryText.text = team.strCountry
        leagueNameText.text = team.strLeague
        descriptionText.text = team.strDescriptionEN
    }
}