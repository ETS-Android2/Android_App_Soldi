package com.project.soldiapp.utils;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.project.soldiapp.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.Utils;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

public class ChartAuxiliar {

    public static final int[] TYPE_COLORS = {
            rgb("#71a5c6"),rgb("#d8b75d"),rgb("#bed8dd"),rgb("#82bbf6"),rgb("#bbf2b8"),rgb("#dfe0de")};

    public static final int[] PAYMENT_COLORS = { rgb("#82bbf6"),rgb("#bbf2b8")};
    public static PieChart chartPersonalization(PieChart p) {
        p.setUsePercentValues(true);
        p.getDescription().setEnabled(false);
        p.setExtraOffsets(5, 10, 5, 5);


        p.setDragDecelerationFrictionCoef(0.95f);
        p.setRotationEnabled(false);

        p.setDrawHoleEnabled(true);
        p.setHoleColor(Color.WHITE);
        p.setHoleRadius(37);
        p.setTransparentCircleRadius(41f);
        p.setEntryLabelTextSize(18);
        p.setDrawEntryLabels(false);

        p.setNoDataText("Add some expenses to see the analysis");
        p.setNoDataTextColor(Color.BLACK);

        return p;
    }

    public static PieChart modifyLegend(PieChart chart){
        Legend legend = chart.getLegend();
        legend.setTextSize(20);
        legend.setFormSize(15);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setXEntrySpace(20);
        legend.setWordWrapEnabled(true);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        return chart;
    }

    public static PieDataSet setPieDataSetConfType(PieDataSet dataSet){
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(TYPE_COLORS);
        return  dataSet;
    }

    public static PieDataSet setPieDataSetConfPaym(PieDataSet dataSet){
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(PAYMENT_COLORS);
        return  dataSet;
    }

    public static PieData setDataConf(PieData data,PieChart chart) {
        data.setValueTextSize(20f);
        data.setValueTextColor(Color.WHITE);
        data.setValueFormatter(new PercentFormatter(chart));
        return data;
    }

    public static LineChart setLineChartConf(LineChart chart){
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getXAxis().setDrawGridLines(false);

        chart.setNoDataText("Add some expenses to see the analysis");
        chart.setNoDataTextColor(Color.BLACK);

        chart.getDescription().setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(16);
        xAxis.setDrawLabels(true);

        chart.setExtraOffsets(10, 10, 10, 10);

        chart.getAxisRight().setEnabled(false);
        YAxis yAxis = chart.getAxisLeft();
        yAxis.setTextSize(16);
        yAxis.setAxisMinimum(0);
        yAxis.setValueFormatter(new BadgeFormatter());

        chart.getLegend().setEnabled(false);
        return chart;
    }

    public static LineDataSet setLineChartDataset(LineDataSet dataSet, Activity activity) {
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setFillAlpha(110);

        if (Utils.getSDKInt() >= 18) {
            // fill drawable only supported on api level 18 and above
            Drawable drawable = ContextCompat.getDrawable(activity, R.drawable.fade_line_chart);
            dataSet.setFillDrawable(drawable);
        }
        else {
            dataSet.setFillColor(Color.BLACK);
        }

        dataSet.setDrawFilled(true);
        dataSet.setColor(rgb("#5eaeae"));
        dataSet.setLineWidth(4);
        dataSet.setValueTextSize(16);
        dataSet.setDrawValues(false);
        dataSet.setCircleRadius(6);
        dataSet.setCircleColor(rgb("#d8b75d"));

        if(dataSet.getValues().size()<8)
            dataSet.setDrawValues(true);


        dataSet.setValueFormatter(new BadgeFormatter());

        return dataSet;
    }
}
