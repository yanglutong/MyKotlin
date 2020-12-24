package com.yang.mykotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.yang.mykotlin.R
import com.yang.mykotlin.base.BaseActivity
import com.yang.mykotlin.databinding.ActivitySettingBinding
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity :BaseActivity<ActivitySettingBinding>(), View.OnClickListener {


    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    override fun initData() {
        val mUserProtocolTv = findViewById<TextView>(R.id.mUserProtocolTv)
        val mFeedBackTv = findViewById<TextView>(R.id.mFeedBackTv)
        val mAboutTv = findViewById<TextView>(R.id.mAboutTv)
        mUserProtocolTv.setOnClickListener {
            Toast.makeText(this, "用户协议", Toast.LENGTH_SHORT).show()
        }
        mFeedBackTv.setOnClickListener {
            Toast.makeText(this, "反馈意见", Toast.LENGTH_SHORT).show()
        }
        mAboutTv.setOnClickListener {
            Toast.makeText(this, "关于", Toast.LENGTH_SHORT).show()
        }

        dataBingView.titleBar.setOnClickTitleImage{
            finish()
        }
    }

    override fun createLayoutId(): Int {
       return R.layout.activity_setting
    }
}