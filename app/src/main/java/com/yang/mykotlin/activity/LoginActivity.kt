package com.yang.mykotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yang.mykotlin.R
import com.yang.mykotlin.base.BaseActivity
import com.yang.mykotlin.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun initData() {
        //忘记密码
        dataBingView.mForgetPwdTv.setOnClickListener {
            startActivity(Intent(this,ForGetPassActivity::class.java))
        }
        //点击返回图标
        dataBingView.titleBar.setOnClickTitleImage {
            finish()
        }
        //点击注册
        dataBingView.titleBar.setOnClickTitleLogin {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
        //关闭登录注册页面
        dataBingView.titleBar.setOnClickTitleImage {
            finish()
        }
    }

    override fun createLayoutId(): Int {
        return R.layout.activity_login
    }
}