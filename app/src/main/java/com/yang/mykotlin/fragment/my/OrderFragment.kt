

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yang.mykotlin.R


/*
    订单列表Fragment
 */
class OrderFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

   /* private lateinit var mAdapter:OrderAdapter

    *//*
        Dagger注册
     *//*
    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(mActivityComponent).orderModule(OrderModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_order,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    *//*
        初始化视图
     *//*
    private fun initView() {
        mOrderRv.layoutManager = LinearLayoutManager(activity)
        mAdapter = OrderAdapter(activity)
        mOrderRv.adapter = mAdapter

        *//*
            订单对应操作
         *//*
        mAdapter.listener = object :OrderAdapter.OnOptClickListener {
            override fun onOptClick(optType: Int, order: Order) {
                when(optType){
                    OrderConstant.OPT_ORDER_PAY -> {
                        ARouter.getInstance().build(RouterPath.PaySDK.PATH_PAY)
                                .withInt(ProviderConstant.KEY_ORDER_ID,order.id)
                                .withLong(ProviderConstant.KEY_ORDER_PRICE,order.totalPrice)
                                .navigation()
                    }
                    OrderConstant.OPT_ORDER_CONFIRM -> {
                       mPresenter.confirmOrder(order.id)
                    }
                    OrderConstant.OPT_ORDER_CANCEL -> {
                        //mPresenter.cancelOrder(order.id)
                        showCancelDialog(order)
                    }
                }
            }
        }

        *//*
            列表单项点击事件
         *//*
        mAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Order> {
            override fun onItemClick(item: Order, position: Int) {
                startActivity<OrderDetailActivity>(ProviderConstant.KEY_ORDER_ID to item.id)
            }
        })


    }

    *//*
        取消订单对话框
     *//*
    private fun showCancelDialog(order:Order) {
        AlertView("取消订单", "确定取消该订单？", "取消", null, arrayOf("确定"), activity, AlertView.Style.Alert, OnItemClickListener { o, position ->
            if (position == 0){
                mPresenter.cancelOrder(order.id)
            }
        }

        ).show()
    }

    *//*
        加载数据
     *//*
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getOrderList(arguments.getInt(OrderConstant.KEY_ORDER_STATUS,-1))
    }

    *//*
        获取订单列表回调
     *//*
    override fun onGetOrderListResult(result: MutableList<Order>?) {
        if (result != null && result.size > 0){
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        }else{
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }*/

 /*   *//*
        订单确认收货回调
     *//*
    override fun onConfirmOrderResult(result: Boolean) {
        toast("确认收货成功")
        loadData()
    }

    *//*
        取消订单回调
     *//*
    override fun onCancelOrderResult(result: Boolean) {
        toast("取消订单成功")
        loadData()
    }*/
}
