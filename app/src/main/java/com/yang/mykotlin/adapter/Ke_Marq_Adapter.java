package com.yang.mykotlin.adapter;

import android.content.Context;
import android.widget.TextView;


import com.xj.marqueeview.base.CommonAdapter;
import com.xj.marqueeview.base.ViewHolder;
import com.yang.mykotlin.R;


import java.util.ArrayList;
import java.util.List;

/**
 * @author gzp
 * @description:
 * @date : 2020/11/27 20:00
 */
public class Ke_Marq_Adapter extends CommonAdapter<String> {


    public Ke_Marq_Adapter(Context context, ArrayList<String> arrayList) {
        super(context, R.layout.ke_mar_item, arrayList);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        TextView mText = viewHolder.getView(R.id.ke_mtext);
        mText.setText(item);
    }
}
