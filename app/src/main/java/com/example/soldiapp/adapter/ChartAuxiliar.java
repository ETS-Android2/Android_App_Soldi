package com.example.soldiapp.adapter;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

public class ChartAuxiliar {

    public static PieChart chartPersonalization(PieChart p) {
        p.setUsePercentValues(true);
        p.getDescription().setEnabled(false);
        p.setExtraOffsets(5, 10, 5, 5);

        p.setDragDecelerationFrictionCoef(0.95f);

        p.setDrawHoleEnabled(true);
        p.setHoleColor(Color.WHITE);
        p.setHoleRadius(37);
        p.setTransparentCircleRadius(41f);
        p.setEntryLabelTextSize(18);
        p.setDrawEntryLabels(false);

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

    public static PieDataSet setPieDataSetConf(PieDataSet dataSet){
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        int[] colors = new int[6];
        for(int i = 0; i< ColorTemplate.JOYFUL_COLORS.length; i++){
            colors[i] = ColorTemplate.JOYFUL_COLORS[i];
        }
        colors[5]= rgb("#ff5148");
        dataSet.setColors(colors);
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

    public static LineDataSet setLineChartDataset(LineDataSet dataSet) {
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setFillAlpha(110);
        dataSet.setDrawFilled(true);
        dataSet.setColor(rgb("#2697f4"));
        dataSet.setLineWidth(4);
        dataSet.setValueTextSize(16);
        dataSet.setDrawValues(false);
        dataSet.setCircleRadius(6);


        dataSet.setValueFormatter(new BadgeFormatter());

        return dataSet;
    }
}
