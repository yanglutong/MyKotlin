package com.yang.mykotlin.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<V : ViewDataBinding>: Fragment() {
    lateinit var dataBingViewFragment:V
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        dataBingViewFragment=DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        initData()
        return dataBingViewFragment.root
    }

    abstract fun initData()
    abstract fun getLayoutId(): Int
}