package com.walker.finalappdotaheros.ui

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.walker.finalappdotaheros.R
import com.walker.finalappdotaheros.models.Hero
import com.walker.finalappdotaheros.network.BASE_URL
import kotlinx.coroutines.runBlocking


class MyHeroCardRecyclerViewAdapter(
        private var values: List<Hero>,
        private val context: Context,
        private val likeHero: (id: Int) -> Unit,
        private val dislikeHero: (id: Int) -> Unit,
        private val isHeroLiked: (id: Int) -> Boolean
) : RecyclerView.Adapter<MyHeroCardRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_herocard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.atackTypeText.text = item.attack_type
        holder.imageView.setImageURI(Uri.parse(item.img))
        holder.heroNameText.text = item.localized_name

        val isHeroLiked = runBlocking {
            isHeroLiked(item.id)
        }

        if(isHeroLiked) {
            holder.favThisHero.setImageResource(R.drawable.ic_baseline_star_24_liked)
        } else {
            holder.favThisHero.setImageResource(R.drawable.ic_baseline_star_24)
        }

        holder.favThisHero.setOnClickListener {
            val _isHeroLiked = runBlocking {
                isHeroLiked(item.id)
            }

            if(_isHeroLiked){
                dislikeHero(item.id)
                holder.favThisHero.setImageResource(R.drawable.ic_baseline_star_24)
            } else {
                likeHero(item.id)
                holder.favThisHero.setImageResource(R.drawable.ic_baseline_star_24_liked)
            }
        }

        if(holder.primaryAttrImage.drawable == null) {
            when (item.primary_attr) {
                "agi" -> holder.primaryAttrImage.setImageResource(R.drawable.agilidade)
                "int" -> holder.primaryAttrImage.setImageResource(R.drawable.inteligencia)
                "str" -> holder.primaryAttrImage.setImageResource(R.drawable.forca)
            }
        }

        if(holder.imageView.drawable == null) {
            Glide
                .with(context)
                .load(BASE_URL + item.img)
                .centerCrop()
                .placeholder(R.drawable.dota2)
                .error(R.drawable.dota2)
                .into(holder.imageView)
        }

        setRolesChips(item.roles, holder.chipGroup)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val heroNameText: TextView = view.findViewById(R.id.heroNameText)
        val atackTypeText: TextView = view.findViewById(R.id.atackTypeText)
        val primaryAttrImage: ImageView = view.findViewById(R.id.primaryAttrImage)
        val chipGroup: ChipGroup = view.findViewById(R.id.rolesChipGroup)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val favThisHero: ImageButton = view.findViewById(R.id.favThisHero)

        override fun toString(): String {
            return super.toString() + " '" + heroNameText.text + "'"
        }
    }

    private fun setRolesChips(roles: ArrayList<String>, chipGroup: ChipGroup) {
        chipGroup.removeAllViews()
        for (role in roles) {
            val chip = Chip(context).apply {
                text = role
                isClickable = false
                isCheckable = false
                isCloseIconVisible = false
                chipBackgroundColor = ColorStateList.valueOf(
                        ContextCompat.getColor(
                                context,
                                R.color.gray
                        )
                )
                setTextColor(ColorStateList.valueOf(
                        ContextCompat.getColor(
                                context,
                                R.color.light_gray
                        )
                ))
            }
            chipGroup.addView(chip)
        }
    }
}