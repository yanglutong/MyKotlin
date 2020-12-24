package com.kotlin.order.ui.adapter

import OrderFragment
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


/*
    订单Tab对应ViewPager
 */
class OrderVpAdapter(fm: FragmentManager, context: Context)
    : FragmentPagerAdapter(fm) {
    //订单状态
    private val KEY_ORDER_STATUS = "order_status"

    private val titles = arrayOf("全部","待付款","待收货","已完成","已取消")

    override fun getItem(position: Int): Fragment {
        val fragment = OrderFragment()
        val bundle = Bundle()
        //订单状态 order_status
        bundle.putInt(KEY_ORDER_STATUS,position)
        fragment.arguments = bundle
        return fragment

    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}
