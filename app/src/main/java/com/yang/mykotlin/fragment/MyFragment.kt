package com.yang.mykotlin.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.yang.mykotlin.R
import com.yang.mykotlin.activity.LoginActivity
import com.yang.mykotlin.activity.MainActivity
import com.yang.mykotlin.activity.OrderByActivity2
import com.yang.mykotlin.activity.SettingActivity
import com.yang.mykotlin.base.BaseFragment
import com.yang.mykotlin.databinding.FragmentMyBinding
import kotlinx.android.synthetic.main.fragment_my.*


class MyFragment : BaseFragment<FragmentMyBinding>(), View.OnClickListener {
    private var a=false
    override fun initData() {

        dataBingViewFragment.mUserNameTv.setOnClickListener(this)
        dataBingViewFragment.mSettingTv.setOnClickListener(this)
        dataBingViewFragment.mShareTv.setOnClickListener(this)
        dataBingViewFragment.mAddressTv.setOnClickListener(this)


        //代付款 和待收货 已完成
        dataBingViewFragment.mWaitPayOrderTv.setOnClickListener(this)
        dataBingViewFragment.mWaitConfirmOrderTv.setOnClickListener(this)
        dataBingViewFragment.mCompleteOrderTv.setOnClickListener(this)
        dataBingViewFragment.mAllOrderTv.setOnClickListener(this)


    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_my
    }
    override fun onClick(v: View?) {
        //点击进入我的订单是否为登录状态
        if(v!!.id==R.id.mAllOrderTv){
            if(!a){ //未登录
                startActivity(Intent(context,LoginActivity::class.java))
            }else{

            }
            startActivity(Intent(context,LoginActivity::class.java))
        }
        if(v!!.id==R.id.mCompleteOrderTv||v.id==R.id.mWaitConfirmOrderTv||v.id==R.id.mWaitPayOrderTv){
            startActivity(Intent(context,OrderByActivity2()::class.java))
        }
        //登录注册 登录和未登录
        if(v!!.id==R.id.mUserNameTv){
            //未登录状态
            if(!a){
                startActivity(Intent(context,LoginActivity::class.java))
            }else{

            }
        }

        //收货管理 登录和未登录两种状态
        if(v!!.id==R.id.mAddressTv){
            //未登录状态
            if(!a){
                startActivity(Intent(context,LoginActivity::class.java))
            }else{
                //登录状态 进入我的订单
                startActivity(Intent(context,OrderByActivity2::class.java))
            }
        }


        //分享
        if(v!!.id==R.id.mShareTv){
            Toast.makeText(context, "敬请期待~~~", Toast.LENGTH_SHORT).show()
        }

        //设置
        if(v!!.id==R.id.mSettingTv){
            startActivity(Intent(activity,SettingActivity::class.java))
        }
    }
}