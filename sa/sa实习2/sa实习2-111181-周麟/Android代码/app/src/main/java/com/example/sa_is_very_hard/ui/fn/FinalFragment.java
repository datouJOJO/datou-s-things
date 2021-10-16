package com.example.sa_is_very_hard.ui.fn;

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

import com.alibaba.fastjson.JSONObject;
import com.example.sa_is_very_hard.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class FinalFragment extends Fragment {

    private FinalViewModel notificationsViewModel;
    private ScatterChart pc;
    private Switch showDataSwitch;//展示数据的开关
    private static JSONObject peJson;  //收盘价数据

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(FinalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_final, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        pc = root.findViewById(R.id.final_Point);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //获取后端数据
        setData("http://hahaistou.xicp.io/final");
        while (peJson == null){//等待子线程返回结果
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        showDataSwitch = root.findViewById(R.id.show_final);
        showData();

        pc.setDrawGridBackground(false);
        pc.setTouchEnabled(true);
        pc.setMaxHighlightDistance(10f);

        // 支持缩放和拖动
        pc.setDragEnabled(true);
        pc.setScaleEnabled(true);

        pc.setMaxVisibleValueCount(10);
        pc.setPinchZoom(true);

        Legend l = pc.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXOffset(5f);

        YAxis yl = pc.getAxisLeft();
        yl.setAxisMinimum(16f);

        pc.getAxisRight().setEnabled(false);

        XAxis xl = pc.getXAxis();
        xl.setDrawGridLines(false);
        List<Double> result = (List<Double>) peJson.get("RESULT");

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        ArrayList<Entry> yVals3 = new ArrayList<Entry>();
        ArrayList<Entry> yVals4 = new ArrayList<Entry>();
        ArrayList<Entry> yVals5 = new ArrayList<Entry>();


        for(int i =0;i<result.size();i++){
            Float f = Float.parseFloat(String.valueOf(result.get(i)));
            if(16.0<=f && f<=16.5){
                yVals1.add(new Entry(i,f));
            }
        }
        for(int i =0;i<result.size();i++){
            Float f = Float.parseFloat(String.valueOf(result.get(i)));
            if(16.5<f && f<=17.0){
                yVals2.add(new Entry(i,f));
            }
        }
        for(int i =0;i<result.size();i++){
            Float f = Float.parseFloat(String.valueOf(result.get(i)));
            if(17.0<f && f<=17.5){
                yVals3.add(new Entry(i,f));
            }
        }
        for(int i =0;i<result.size();i++){
            Float f = Float.parseFloat(String.valueOf(result.get(i)));
            if(17.5<f && f<=18.0){
                yVals4.add(new Entry(i,f));
            }
        }
        for(int i =0;i<result.size();i++){
            Float f = Float.parseFloat(String.valueOf(result.get(i)));
            if(18.0<f && f<=18.5){
                yVals5.add(new Entry(i,f));
            }
        }

        ScatterDataSet set1 = new ScatterDataSet(yVals1, "16.0-16.5");
        set1.setScatterShape(ScatterChart.ScatterShape.SQUARE);
        //设置颜色
        set1.setColor(ColorTemplate.COLORFUL_COLORS[0]);


        ScatterDataSet set2 = new ScatterDataSet(yVals2, "16.5-17.0");
        set2.setScatterShape(ScatterChart.ScatterShape.SQUARE);
        set2.setColor(ColorTemplate.LIBERTY_COLORS[1]);

        ScatterDataSet set3 = new ScatterDataSet(yVals3, "17.0-17.5");
        set3.setScatterShape(ScatterChart.ScatterShape.SQUARE);
        set3.setColor(ColorTemplate.JOYFUL_COLORS [2]);

        ScatterDataSet set4 = new ScatterDataSet(yVals4, "17.5-18.0");
        set4.setScatterShape(ScatterChart.ScatterShape.SQUARE);
        set4.setColor(ColorTemplate.PASTEL_COLORS [3]);

        ScatterDataSet set5 = new ScatterDataSet(yVals5, "18.0-18.5");
        set5.setScatterShape(ScatterChart.ScatterShape.SQUARE);
        set5.setColor(ColorTemplate.VORDIPLOM_COLORS [4]);


        set1.setScatterShapeSize(8f);
        set2.setScatterShapeSize(8f);
        set3.setScatterShapeSize(8f);
        set4.setScatterShapeSize(8f);
        set5.setScatterShapeSize(8f);

        ArrayList<IScatterDataSet> dataSets = new ArrayList<IScatterDataSet>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);
        dataSets.add(set4);
        dataSets.add(set5);

        ScatterData data = new ScatterData(dataSets);
        pc.setData(data);
        pc.invalidate();
        return root;
    }

    private void showData() {//添加展示数据开关按钮的监听
        showDataSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //显示数据弹窗
                    AlertDialog alertDialog1 = new AlertDialog.Builder(getContext())
                            .setTitle("2016年收盘价走势")//标题
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