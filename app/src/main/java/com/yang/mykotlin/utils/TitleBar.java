package com.yang.mykotlin.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yang.mykotlin.R;

public class TitleBar extends RelativeLayout implements View.OnClickListener {
    public TitleBar(Context context) {
        this(context,null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //找到布局
        View view = inflate(context, R.layout.title_toolbar, null);
        ImageView title_image = view.findViewById(R.id.title_image);
        TextView title_name = view.findViewById(R.id.title_name);
        TextView title_login = view.findViewById(R.id.title_login);

        //使用context获取 加载属性的方法 styleable
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        CharSequence text = array.getText(R.styleable.TitleBar_titleText);
        CharSequence loginText = array.getText(R.styleable.TitleBar_title_loginText);
        int back_color = array.getColor(R.styleable.TitleBar_title_background,0);
        float title_size = array.getDimension(R.styleable.TitleBar_titleSize,10);
        int title_color = array.getColor(R.styleable.TitleBar_titleColor,0);


        title_name.setText(text);
        title_name.setTextColor(title_color);
        title_name.setTextSize(title_size);
        title_name.setBackgroundColor(back_color);

        if(loginText.equals("注册")){
            title_login.setVisibility(VISIBLE);
        }else{
            title_login.setVisibility(GONE);
        }
        title_login.setTextColor(title_color);
        title_login.setTextSize(title_size);
        title_login.setBackgroundColor(back_color);



        array.recycle();
        //添加view设置和xml布局绑定
        addView(view);

        title_name.setOnClickListener(this);
        title_login.setOnClickListener(this);
        title_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.title_image){
            if(onClickTitleImage!=null){
                onClickTitleImage.onClick(view);
            }
        }
        if(view.getId()==R.id.title_login){
            if(onClickTitleLogin!=null){
                onClickTitleLogin.onClick(view);
            }
        }
        if(view.getId()==R.id.title_name){
            if(onClickTitleName!=null){
                onClickTitleName.onClick(view);
            }
        }
    }


    public interface OnClickTitleName{
        void onClick(View v);
    }
    OnClickTitleName onClickTitleName;

    public void setOnClickTitleName(OnClickTitleName onClick) {
        this.onClickTitleName = onClick;
    }



    public interface OnClickTitleImage{
        void onClick(View v);
    }
    OnClickTitleImage onClickTitleImage;

    public void setOnClickTitleImage(OnClickTitleImage onClickTitleImage) {
        this.onClickTitleImage = onClickTitleImage;
    }


    public interface OnClickTitleLogin{
        void onClick(View v);
    }
    OnClickTitleLogin onClickTitleLogin;

    public void setOnClickTitleLogin(OnClickTitleLogin onClickTitleLogin) {
        this.onClickTitleLogin = onClickTitleLogin;
    }
}
