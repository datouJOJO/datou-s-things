package com.example.sa_is_very_hard.ui.pie;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.sa_is_very_hard.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class PieFragment extends Fragment {

    private PieViewModel pieViewModel;
    private PieChart pc;
    private Switch showDataSwitch;//展示数据的开关
    private static JSONObject peJson;  //市盈率数据

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pieViewModel =
                ViewModelProviders.of(this).get(PieViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pie, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        pc = root.findViewById(R.id.pc);
        pieViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //获取后端数据
        setData("http://hahaistou.xicp.io/pe");
        while (peJson == null){//等待子线程返回结果
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        showDataSwitch = root.findViewById(R.id.show_pe);
        showData();
        JSONArray arr = (JSONArray) peJson.get("RESULT");
        JSONObject jo = (JSONObject) arr.get(0);
        float total = jo.getFloat("0-10")+jo.getFloat("10-20")
                +jo.getFloat("20-30")+jo.getFloat("30-40")
                +jo.getFloat("40-50")+jo.getFloat("50-60")
                +jo.getFloat("60-70")+jo.getFloat("70-80");

        //数据
        List yVals = new ArrayList<>();
        yVals.add(new PieEntry(jo.getFloat("0-10")/total,"0%-10%"));
        yVals.add(new PieEntry(jo.getFloat("10-20")/total, "10-20%"));
        yVals.add(new PieEntry(jo.getFloat("20-30")/total, "20-30%"));
        yVals.add(new PieEntry(jo.getFloat("30-40")/total, "30-40%"));
        yVals.add(new PieEntry(jo.getFloat("40-50")/total, "40-50%"));
        yVals.add(new PieEntry(jo.getFloat("50-60")/total, "50-60%"));
        yVals.add(new PieEntry(jo.getFloat("60-70")/total, "60-70%"));
        yVals.add(new PieEntry(jo.getFloat("70-80")/total, "70-80%"));
        //颜色
        List colors = new ArrayList<>();
        colors.add(Color.parseColor("#4A92FC"));
        colors.add(Color.parseColor("#ee6e55"));
        colors.add(Color.parseColor("#FFFFEB3B"));
        colors.add(Color.parseColor("#FFE91E63"));
        colors.add(Color.parseColor("#FF673AB7"));
        colors.add(Color.parseColor("#FF8BC34A"));
        colors.add(Color.parseColor("#FFB97F28"));
        colors.add(Color.parseColor("#FFF5080F"));
        //设置所用数据集
        PieDataSet pieDataSet = new PieDataSet(yVals,"各分段市盈率");
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        pc.setData(pieData);

        return root;
    }

    private void showData() {//添加展示数据开关按钮的监听
        showDataSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //显示数据弹窗
                    AlertDialog alertDialog1 = new AlertDialog.Builder(getContext())
                            .setTitle("市盈率占比数据")//标题
                            .setMessage(peJson.toJSONString())//内容
                            .setIcon(R.mipmap.ic_launcher)//图标
                            .create();
                    alertDialog1.show();
                }else{
                    //////
                }
            }
        });
    }

    private void setData(final String url){//获取均价数据
        final StringBuilder json = new StringBuilder();
        new Thread(new Runnable() {//开启线程
            @Override
            public void run() {
                try {
                    URL urlObject = new URL(url);
                    URLConnection uc = urlObject.openConnection();
                    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"UTF-8"));
                    String inputLine = null;
                    while ( (inputLine = in.readLine()) != null) {
                        json.append(inputLine);
                    }
                    in.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                peJson = JSONObject.parseObject(json.toString());
            }
        }).start();
    }
}