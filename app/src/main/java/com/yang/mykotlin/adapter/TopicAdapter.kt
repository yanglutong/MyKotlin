package com.yang.mykotlin.adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide

import com.yang.mykotlin.R
import kotlinx.android.synthetic.main.layout_topic_item.view.*

/*
    话题数据
 */
class TopicAdapter(private val context: Context, private val list: List<String>) : PagerAdapter() {

    override fun destroyItem(parent: ViewGroup, paramInt: Int, paramObject: Any) {
        parent.removeView(paramObject as View)
    }

    override fun getCount(): Int {
        return this.list.size
    }

    override fun instantiateItem(parent: ViewGroup, position: Int): Any {
        val rooView = LayoutInflater.from(this.context).inflate(R.layout.layout_topic_item, null)
        Glide.with(context).load(list[position]).placeholder(R.drawable.icon_back).error(R.drawable.icon_back).centerCrop().into(
            rooView.mTopicIv
        )
        parent.addView(rooView)
        return rooView
    }

    override fun isViewFromObject(paramView: View, paramObject: Any): Boolean {
        return paramView === paramObject
    }
}
