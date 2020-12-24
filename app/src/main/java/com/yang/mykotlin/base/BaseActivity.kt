package com.yang.mykotlin.base

import android.media.DrmInitData
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

 abstract class BaseActivity<T :ViewDataBinding> : AppCompatActivity(){
    lateinit var dataBingView: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         dataBingView= DataBindingUtil.setContentView<T>(this, createLayoutId())
        initData()
    }
    abstract fun initData()
    abstract fun createLayoutId(): Int
}