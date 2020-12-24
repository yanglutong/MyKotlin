package com.yang.mykotlin.activity

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import com.yang.mykotlin.R
import com.yang.mykotlin.base.BaseActivity
import com.yang.mykotlin.databinding.ViewToolbarSearchBinding
import com.yang.mykotlin.dialogsearview.SearchAdapter
import com.yang.mykotlin.dialogsearview.SharedPreference
import com.yang.mykotlin.dialogsearview.Utils
import com.yang.mykotlin.fragment.HomeFragment
import java.util.*

class SearchActivity :BaseActivity<ViewToolbarSearchBinding>(){
    private var mCountries: ArrayList<String>? = null
    private var countryStored: ArrayList<String>? = null
    override fun initData() {
        loadToolBarSearch()
    }

    private fun loadToolBarSearch() {
        countryStored = SharedPreference.loadList(this, Utils.PREFS_NAME, Utils.KEY_COUNTRIES)


        val parentToolbarSearch =
            findViewById<View>(R.id.parent_toolbar_search) as LinearLayout
        val imgToolBack =
            findViewById<View>(R.id.img_tool_back) as ImageView
        //点击搜索 刷新搜索历史记录
        //点击搜索 刷新搜索历史记录
        val search_all = findViewById<View>(R.id.search_all) as TextView


        //输入框


        //输入框
        val edtToolSearch =
            findViewById<View>(R.id.edt_tool_search) as EditText
        val listSearch =
            findViewById<View>(R.id.list_search) as ListView
        val txtEmpty = findViewById<View>(R.id.txt_empty) as TextView

        Utils.setListViewHeightBasedOnChildren(listSearch)

        edtToolSearch.hint = "搜索商品/品类"


        countryStored =
            if (countryStored != null && (countryStored as ArrayList<String>).size > 0) countryStored else ArrayList<String>()
        val searchAdapter = SearchAdapter(this, countryStored, false)

        listSearch.visibility = View.VISIBLE
        listSearch.adapter = searchAdapter


        listSearch.onItemClickListener =
            OnItemClickListener { adapterView, view, position, l ->
                val country = adapterView.getItemAtPosition(position).toString()
                SharedPreference.addList(
                    this@SearchActivity,
                    Utils.PREFS_NAME,
                    Utils.KEY_COUNTRIES,
                    country
                )
                edtToolSearch.setText(country)
                listSearch.visibility = View.GONE
            }
        edtToolSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
                val country: Array<String> =
                    this@SearchActivity.resources.getStringArray(R.array.countries_array)
                mCountries =
                    ArrayList(Arrays.asList(*country))
                listSearch.visibility = View.VISIBLE
                searchAdapter.updateList(mCountries, true)
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                val filterList =
                    ArrayList<String>()
                var isNodata = false
                //正在输入内容 s.toString().trim().toLowerCase()将字符串转换成小写字符
                //startsWith 如果字符串以指定的前缀开始，则返回 true；否则返回 false。
                //如果集合里的数据字符有和用户正在输入的字符前缀相同的话就返回true
                if (s.isNotEmpty()) {
//                    遍历一个数组/集合，只取出下标:
                    for ((index,e) in mCountries!!.withIndex()){
                        if (e.toLowerCase()
                                .startsWith(s.toString().trim { it <= ' ' }.toLowerCase())
                        ) {
                            filterList.add(e)
                            listSearch.visibility = View.VISIBLE
                            searchAdapter.updateList(filterList, true)
                            isNodata = true
                        }
                    }
                    if (!isNodata) {
                        listSearch.visibility = View.GONE
                        txtEmpty.visibility = View.VISIBLE
                        txtEmpty.text = "暂无历史记录"
                    }
                    //没有输入内容 让集合和显示的文本都隐藏
                } else {
                    listSearch.visibility = View.GONE
                    txtEmpty.visibility = View.GONE
                }
            }
        })


        imgToolBack.setOnClickListener { finish() }


        search_all.setOnClickListener {
            val strings =
                ArrayList<String>()
            val trim = edtToolSearch.text.toString().trim { it <= ' ' }
            if (!TextUtils.isEmpty(trim)) {
                strings.add(trim)
            }
            val searchAdapter =
                SearchAdapter(this@SearchActivity, strings, false)
            listSearch.visibility = View.VISIBLE
            listSearch.adapter = searchAdapter
        }

    }

    override fun createLayoutId(): Int {
        return R.layout.view_toolbar_search
    }
}