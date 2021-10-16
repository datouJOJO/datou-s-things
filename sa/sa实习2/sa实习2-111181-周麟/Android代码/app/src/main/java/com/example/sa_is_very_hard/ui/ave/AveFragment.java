package com.example.sa_is_very_hard.ui.ave;

import android.app.AlertDialog;
import android.os.Bundle;
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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.w3c.dom.Entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class AveFragment extends Fragment {

    private AveViewModel homeViewModel;
    private LineChart pc;
    private Switch showDataSwitch;//展示数据的开关
    private static JSONObject peJson;  //均价数据

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(AveViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ave, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

//        pc = root.findViewById(R.id.pc);
        pc = root.findViewById(R.id.ave_line);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //获取后端数据
        setData("http://hahaistou.xicp.io/ave");
        while (peJson == null){//等待子线程返回结果
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        showDataSwitch = root.findViewById(R.id.show_ave);
        showData();

        List<Double> result = (List<Double>) peJson.get("RESULT");
        List<Entry> entries = new ArrayList<>();
        for(int i =0;i<result.size();i++){
            entries.add(new Entry(i,Float.parseFloat(String.valueOf(result.get(i)))));
        }
        //显示边界
        pc.setDrawBorders(true);
        LineDataSet lineDataSet = new LineDataSet(entries, "2016年均价走势");
        LineData data = new LineData(lineDataSet);
        pc.setData(data);
        return root;
    }

    private void showData() {//添加展示数据开关按钮的监听
        showDataSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //显示数据弹窗
                    AlertDialog alertDialog1 = new AlertDialog.Builder(getContext())
                            .setTitle("1999-2016年均价占比数据")//标题
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