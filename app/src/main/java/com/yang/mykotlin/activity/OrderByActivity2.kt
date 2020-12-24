package com.yang.mykotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.kotlin.order.ui.adapter.OrderVpAdapter
import com.yang.mykotlin.R
import com.yang.mykotlin.base.BaseActivity
import com.yang.mykotlin.databinding.ActivityOrderBy2Binding

class OrderByActivity2 : BaseActivity<ActivityOrderBy2Binding>(){
    //订单状态
    private val KEY_ORDER_STATUS = "order_status"
    override fun initData() {
        dataBingView.toolbar.setOnClickTitleImage{
            finish()
        }

        dataBingView.tab.tabMode = TabLayout.MODE_FIXED
        dataBingView.pager.adapter = OrderVpAdapter(supportFragmentManager,this)
        dataBingView.tab.setupWithViewPager(dataBingView.pager)

        //根据订单状态设置当前页面
        dataBingView.pager.currentItem = intent.getIntExtra(KEY_ORDER_STATUS,OrderStatus.ORDER_ALL)
    }

    /*
    订单状态
 */
    class OrderStatus {
        companion object {
            const val ORDER_ALL = 0//全部
            const val ORDER_WAIT_PAY = 1//待支付
            const val ORDER_WAIT_CONFIRM = 2//待收货
            const val ORDER_COMPLETED = 3//已完成
            const val ORDER_CANCELED = 4//已取消
        }
    }

    override fun createLayoutId(): Int {
       return R.layout.activity_order_by2
    }
}