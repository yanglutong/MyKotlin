package com.yang.mykotlin.activity



import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator
import com.yang.mykotlin.R
import com.yang.mykotlin.base.BaseActivity
import com.yang.mykotlin.databinding.ActivityMainBinding
import com.yang.mykotlin.fragment.*
import kotlinx.coroutines.newFixedThreadPoolContext




class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var homeFragment:HomeFragment
    private lateinit var classifyFragment: ClassifyFragment

    private lateinit var msgFragment: MsgFragment
    private lateinit var myFragment: MyFragment
    private lateinit var shoppingCartFragment: ShoppingCartFragment
    private val mutableList = mutableListOf<Fragment>()
    override fun createLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        //初始化资源
        homeFragment= HomeFragment()

        classifyFragment= ClassifyFragment()

        msgFragment= MsgFragment()

        shoppingCartFragment=ShoppingCartFragment()

        myFragment= MyFragment()
        //添加fragment的界面数据源
        initAdd()
        //设置加载Fragment联动
        initLoad()
    }

    private fun initLoad() {
        dataBingView.radioHome.isChecked=true
        //开启一个事物
        val beginTransaction = supportFragmentManager.beginTransaction()

        beginTransaction.add(R.id.rl,mutableList[0]).show(mutableList[0])
            .add(R.id.rl,mutableList[1]).hide(mutableList[1])
            .add(R.id.rl,mutableList[2]).hide(mutableList[2])
            .add(R.id.rl,mutableList[3]).hide(mutableList[3])
            .add(R.id.rl,mutableList[4]).hide(mutableList[4])
            .commit()

        dataBingView.radioHome.setOnClickListener {
            val beginTransaction1 = supportFragmentManager.beginTransaction()

            for(index in 0 until mutableList.size){
                if(index==0){
                    beginTransaction1.show(mutableList[index])
                }else{
                    beginTransaction1.hide(mutableList[index])
                }
            }
            beginTransaction1.commit()

        }
        dataBingView.radioClassify.setOnClickListener {
            val beginTransaction2 = supportFragmentManager.beginTransaction()

            for(index in 0 until mutableList.size){
                if(index==1){
                    beginTransaction2.show(mutableList[index])
                }else{
                    beginTransaction2.hide(mutableList[index])
                }
            }
            beginTransaction2.commit()
        }
        dataBingView.radioShoppingCart.setOnClickListener {
            val beginTransaction3 = supportFragmentManager.beginTransaction()

            for(index in 0 until mutableList.size){
                if(index==2){
                    beginTransaction3.show(mutableList[index])
                }else{
                    beginTransaction3.hide(mutableList[index])
                }
            }
            beginTransaction3.commit()
        }
        dataBingView.radioMessage.setOnClickListener {
            val beginTransaction4 = supportFragmentManager.beginTransaction()

            for(index in 0 until mutableList.size){
                if(index==3){
                    beginTransaction4.show(mutableList[index])
                }else{
                    beginTransaction4.hide(mutableList[index])
                }
            }
            beginTransaction4.commit()
        }
        dataBingView.radioMy.setOnClickListener {
            val beginTransaction5 = supportFragmentManager.beginTransaction()

            for(index in 0 until mutableList.size){
                if(index==4){
                    beginTransaction5.show(mutableList[index])
                }else{
                    beginTransaction5.hide(mutableList[index])
                }
            }
            beginTransaction5.commit()
        }
    }

    //添加数据源
    private fun initAdd() {
        mutableList.add(homeFragment)
        mutableList.add(classifyFragment)
        mutableList.add(shoppingCartFragment)
        mutableList.add(msgFragment)
        mutableList.add(myFragment)
    }

    //获取扫描结果
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Unit {
        val result =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            Log.i("TAG", "onActivityResult: " + "进来了")
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                homeFragment.setResult(result.contents)
                //                text_result.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}