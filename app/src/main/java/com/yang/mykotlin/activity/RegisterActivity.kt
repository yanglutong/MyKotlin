package com.yang.mykotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yang.mykotlin.R
import com.yang.mykotlin.base.BaseActivity
import com.yang.mykotlin.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    override fun initData() {
        //点击返回图标
        dataBingView.titleBar.setOnClickTitleImage {
            finish()
        }

    }

    override fun createLayoutId(): Int {
        return R.layout.activity_register
    }
}