package com.yang.mykotlin.fragment

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Paint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.zxing.integration.android.IntentIntegrator
import com.yang.mykotlin.R
import com.yang.mykotlin.activity.ScanActivity
import com.yang.mykotlin.activity.SearchActivity
import com.yang.mykotlin.adapter.Ke_Marq_Adapter
import com.yang.mykotlin.adapter.TopicAdapter
import com.yang.mykotlin.base.BaseFragment
import com.yang.mykotlin.constants.*
import com.yang.mykotlin.databinding.FragmentHomeBinding
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.loader.ImageLoader
import me.crosswall.lib.coverflow.CoverFlow
import java.util.*


class HomeFragment : BaseFragment<FragmentHomeBinding>(), OnBannerListener {
    override fun initData() {
            dataBingViewFragment.titleScan.setOnClickListener {
                Toast.makeText(activity, "这是fragment", Toast.LENGTH_SHORT).show()
            }
            dataBingViewFragment.edtToolSearch.setOnClickListener {
                startActivity(Intent(activity,SearchActivity()::class.java))
            }
            //监听二维码
            onClick()
            initBanner()
            //设置跑马灯
            initMarq()

            //设置RecyclerView横向条目
            recycler()

//  设置文本中心横线      dataBingViewFragment.gg.paint.flags = Paint. STRIKE_THRU_TEXT_FLAG
            //类似画廊效果
            initViewPager()
    }

    private fun onClick() {
        dataBingViewFragment.titleScan.setOnClickListener {
            /*以下是启动我们自定义的扫描活动*/
            /*以下是启动我们自定义的扫描活动*/
            val intentIntegrator = IntentIntegrator(activity)
            intentIntegrator.setBeepEnabled(true)
            /*设置启动我们自定义的扫描活动，若不设置，将启动默认活动*/
            /*设置启动我们自定义的扫描活动，若不设置，将启动默认活动*/intentIntegrator.captureActivity = ScanActivity::class.java
            intentIntegrator.initiateScan()
        }
    }
    //请求权限
    private fun requsetPermission() {
        if (Build.VERSION.SDK_INT > 22) {
            if (ContextCompat.checkSelfPermission(
                    context!!,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                //先判断有没有权限 ，没有就在这里进行权限的申请
                ActivityCompat.requestPermissions(
                    activity!!,
                    arrayOf(Manifest.permission.CAMERA),
                    1
                )
            } else {
            }
        } else {
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //这里已经获取到了摄像头的权限，想干嘛干嘛了可以
            } else {
                //这里是拒绝给APP摄像头权限，给个提示什么的说明一下都可以。
                Toast.makeText(context, "请手动打开相机权限", Toast.LENGTH_SHORT).show()
            }
            else -> {
            }
        }
    }

    //获取扫描回来的结果

    private fun initViewPager() {

        //话题
        dataBingViewFragment.mTopicPager.adapter = TopicAdapter(this!!.context!!, listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO, HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE))
        dataBingViewFragment.mTopicPager.currentItem = 1
        dataBingViewFragment.mTopicPager.offscreenPageLimit = 5
        CoverFlow.Builder().with(dataBingViewFragment.mTopicPager).
        scale(0.3f).pagerMargin(-30.0f).
        spaceSize(0.0f).build()
    }

    private fun recycler() {
        val mutableListOf = mutableListOf(
            HOME_DISCOUNT_ONE,
            HOME_DISCOUNT_TWO, HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR,HOME_DISCOUNT_FIVE
        )
        //创建布局管理器
        var layoutManager= LinearLayoutManager(context)
        //设置管理器的滑动方向
        layoutManager.orientation=LinearLayoutManager.HORIZONTAL
        //设置布局管理器
        dataBingViewFragment.HomeRecycler.layoutManager=layoutManager
        //创建 适配器
        var reAdapter=ReAdapter(context,mutableListOf)
        //设置适配器
        dataBingViewFragment.HomeRecycler.adapter=reAdapter
        //刷新数据
        reAdapter.notifyDataSetChanged()
    }

    class ReAdapter(context: Context?,mutableList: MutableList<*>) : RecyclerView.Adapter<ReAdapter.Day>() {
        var context:Context?=null
        var mutableList:MutableList<*>?=null
        init {
            this.context=context
            this.mutableList=mutableList
        }
        class Day(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val item_image: ImageView = itemView.findViewById(R.id.item_image)
            val item_tv: TextView =itemView.findViewById(R.id.item_tv)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Day {
//            TODO("Not yet implemented")
            return Day(LayoutInflater.from(context).inflate(R.layout.home_item,parent,false))
        }

        override fun getItemCount(): Int {
//            TODO("Not yet implemented")
            return mutableList?.size!!
        }

        override fun onBindViewHolder(holder: Day, position: Int) {
//            TODO("Not yet implemented")
            var day=Day(holder.itemView)
            Glide.with(context!!).load(this!!.mutableList!![position]).into(day.item_image)
            day.item_tv.paint.flags = Paint. STRIKE_THRU_TEXT_FLAG
        }
    }

    private fun initMarq() {
        val tvMarqueeView = dataBingViewFragment.tvMarqueeView
        //跑马灯
        var arr=ArrayList<String>()
        arr.add("新用户立减1000元优惠券")
        arr.add("夏日炎炎，第一波福利还有30秒到达战场")
        Log.i("TAG", "initMarq: ${arr[0]}  ${arr[1]}")
        //跑马灯
        val adapter = Ke_Marq_Adapter(activity,arr )
        adapter.setOnItemClickListener { _, _ ->
            if (tvMarqueeView.isStart) {
                tvMarqueeView.stopFilp()
            }
        }
        tvMarqueeView.setAdapter(adapter)
    }

    private fun initBanner() {
        //创建数据源存放banner数据
        val mutableListOf = mutableListOf("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503471047&di=679d7a6c499f59d1b0dcd56b62a9aa6c&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.90sheji.com%2Fdianpu_cover%2F11%2F14%2F64%2F55%2F94ibannercsn_1200.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503471047&di=679d7a6c499f59d1b0dcd56b62a9aa6c&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.90sheji.com%2Fdianpu_cover%2F11%2F14%2F64%2F55%2F94ibannercsn_1200.jpg", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503471047&di=679d7a6c499f59d1b0dcd56b62a9aa6c&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.90sheji.com%2Fdianpu_cover%2F11%2F14%2F64%2F55%2F94ibannercsn_1200.jpg", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503471047&di=679d7a6c499f59d1b0dcd56b62a9aa6c&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.90sheji.com%2Fdianpu_cover%2F11%2F14%2F64%2F55%2F94ibannercsn_1200.jpg", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503471047&di=679d7a6c499f59d1b0dcd56b62a9aa6c&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.90sheji.com%2Fdianpu_cover%2F11%2F14%2F64%2F55%2F94ibannercsn_1200.jpg")
        val banner = dataBingViewFragment.banner
        banner.setImages(mutableListOf)


        //设置内置样式，共有六种可以点入方法内逐一体验使用。

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        //设置图片加载器，图片加载器在下方

        banner.setImageLoader(MyLoader())
        //设置图片网址或地址的集合

        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Accordion)
        //设置轮播图的标题集合
//        banner.setBannerTitles(mutableListOf)
        //设置轮播间隔时间
        banner.setDelayTime(2000)

        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true)

        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.RIGHT) //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
            .setOnBannerListener(this) //必须最后调用的方法，启动轮播图。
            .start()


        //设置跑马灯图片


    }
    //自定义的图片加载器
    class MyLoader : ImageLoader() {
        override fun displayImage(
            context: Context?,
            path: Any?,
            imageView: ImageView?
        ) {

            Glide.with(context!!).load(path as String?).into(imageView!!)

        }
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    //
    override fun OnBannerClick(p0: Int) {

    }


    fun setResult(contents: String?) {
        Toast.makeText(context, "$contents", Toast.LENGTH_SHORT).show()
    }
}