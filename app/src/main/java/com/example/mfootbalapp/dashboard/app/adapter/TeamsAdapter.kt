package com.example.mfootbalapp.dashboard.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mfootbalapp.R
import com.example.mfootbalapp.dashboard.domain.bean.Team
import kotlinx.android.synthetic.main.adapter_team_item.view.*

/**
 * Created by TARAFI Ahmed on 12/03/2020.
 */
class TeamsAdapter (val mContext: Context, var teamsList: ArrayList<Team>?) : RecyclerView.Adapter<TeamsAdapter.ItemViewHolder>() {
    private var onItemClickListener: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_team_item, parent, false))
    }

    override fun getItemCount(): Int {
        return teamsList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val team = teamsList?.get(position)
        Glide.with(mContext)
            .load(team?.strTeamBadge)
            .into(holder.teamLogo)

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(position)
        }
    }

    class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val teamLogo = view.imageItem
    }

    fun setOnItemClickListener(onItemClickListener: ((position: Int) -> Unit)?) {
        this.onItemClickListener = onItemClickListener
    }
}