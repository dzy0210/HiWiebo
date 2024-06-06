package com.example.weibo_duzhaoyang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.weibo_duzhaoyang.bean.BaseBean;
import com.example.weibo_duzhaoyang.bean.UserInfo;
import com.example.weibo_duzhaoyang.retrofit.RetrofitManager;
import com.example.weibo_duzhaoyang.utils.SharedPreferencesUtil;
import com.google.android.material.appbar.MaterialToolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MineFragment extends Fragment {
    ImageView ivAvtar;
    TextView tvUsername;
    TextView tvFansNum;
    MaterialToolbar toolbar;
    boolean logged;
    SharedPreferencesUtil spu = MyApplication.getSp();
    TextView tvOperate;
    private View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        initView();
        initToolbar();

        return view;
    }
    void initView() {
        ivAvtar = view.findViewById(R.id.iv_avatar);
        tvUsername = view.findViewById(R.id.tv_user_name);
        tvFansNum = view.findViewById(R.id.tv_fans_num);
        ivAvtar.setOnClickListener(this::login);
        tvUsername.setOnClickListener(this::login);
        tvFansNum.setOnClickListener(this::login);
        tvOperate = view.findViewById(R.id.toolbar_operation);
        loadData();
    }

    private void login(View view) {
        startActivity(new Intent(getContext(), LoginActivity.class));
    }

    void initToolbar() {
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("我的");
        if (logged) {
            tvOperate = toolbar.findViewById(R.id.toolbar_operation);
            tvOperate.setText("退出");
            tvOperate.setOnClickListener(v -> {
                spu.putData("logged", false);
                startActivity(new Intent(getContext(), LoginActivity.class));
            });
        } else {
            tvOperate.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initToolbar();
        loadData();

    }
    void loadData() {
        logged = (Boolean) spu.getData("logged", false);
        if (!logged) {
            Glide.with(ivAvtar).load(R.drawable.null_avatar).apply(new RequestOptions().placeholder(R.drawable.null_avatar).circleCrop()).into(ivAvtar);
            tvUsername.setText("请先登录");
            tvFansNum.setText("点击头像去登陆");
            ivAvtar.setEnabled(true);
            tvUsername.setEnabled(true);
            tvFansNum.setEnabled(true);
            tvOperate.setVisibility(View.INVISIBLE);
        } else {
            ivAvtar.setEnabled(false);
            tvUsername.setEnabled(false);
            tvFansNum.setEnabled(false);
            tvUsername.setText((String) spu.getData("username", ""));
            Glide.with(ivAvtar).load((String) spu.getData("avatar", "")).apply(new RequestOptions().placeholder(R.drawable.null_avatar).circleCrop()).into(ivAvtar);
            tvFansNum.setText("粉丝数：9999");
        }
    }
}