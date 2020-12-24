package com.yang.mykotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yang.mykotlin.R
import com.yang.mykotlin.base.BaseActivity
import com.yang.mykotlin.databinding.ActivityForGetPassBinding

class ForGetPassActivity : BaseActivity<ActivityForGetPassBinding>() {


    override fun initData() {
        dataBingView.titleBar.setOnClickTitleImage {
            finish()
        }
    }

    override fun createLayoutId(): Int {
        return R.layout.activity_for_get_pass
    }
}