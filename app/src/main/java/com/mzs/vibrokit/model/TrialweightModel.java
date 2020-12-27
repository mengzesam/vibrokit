package com.mzs.vibrokit.model;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.mzs.vibrokit.R;

import java.util.ArrayList;

public class TrialweightModel extends ViewModel {
    public MutableLiveData<String> mTextKeySensor;
    public MutableLiveData<String> mTextXSensor;
    public MutableLiveData<String> mTextXAmpitude;
    public MutableLiveData<String> mTextXAngle;
    public MutableLiveData<String> mTextXLag;
    public MutableLiveData<String> mTextYSensor;
    public MutableLiveData<String> mTextYAmpitude;
    public MutableLiveData<String> mTextYAngle;
    public MutableLiveData<String> mTextYLag;

    private Integer mTurnDirection; //1 :clockwise,-1:counter-clockwise
    private Float mKeySensor;
    private Vector mXVib;
    private Float mXSensor;
    private Float mXLag;
    private Vector mYVib;
    private Float mYSensor;
    private Float mYLag;

    private LineChart mChart;
    private View mChartParent;


    public TrialweightModel(){
        mTextKeySensor=new MutableLiveData<String>("90.0");
        mTextXSensor=new MutableLiveData<String>("45.0");
        mTextXAmpitude=new MutableLiveData<String>("100.0");
        mTextXAngle=new MutableLiveData<String>("0.0");
        mTextXLag=new MutableLiveData<String>("30.0");
        mTextYSensor=new MutableLiveData<String>("135.0");
        mTextYAmpitude=new MutableLiveData<String>("100.0");
        mTextYAngle=new MutableLiveData<String>("0.0");
        mTextYLag=new MutableLiveData<String>("30.0");
        mChart=null;
    }

    public void setChart(LineChart chart,View chartParent){
        mChartParent=chartParent;
        mChart=chart;
    }

    public void initChart(){
        if(mChart==null){
            return;
        }
        {
              // // Chart Style //  // background color
                mChart.setBackgroundColor(Color.WHITE);
                // disable description text
                mChart.getDescription().setEnabled(false);
                // enable touch gestures
                mChart.setTouchEnabled(true);
                mChart.setDrawGridBackground(false);
                // create marker to display box when values are selected
//                MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);

                // Set the marker to the chart
//                mv.setChartView(chart);
//                chart.setMarker(mv);
                // enable scaling and dragging
                mChart.setDragEnabled(true);
                mChart.setScaleEnabled(true);
                // force pinch zoom along both axis
                mChart.setPinchZoom(true);
                XAxis xAxis;
                {   // // X-Axis Style // //
                    xAxis = mChart.getXAxis();
                    // vertical grid lines
                    xAxis.enableGridDashedLine(10f, 10f, 0f);
                }
                YAxis yAxis;
                {   // // Y-Axis Style // //
                    yAxis = mChart.getAxisLeft();

                    // disable dual axis (only use LEFT axis)
                    mChart.getAxisRight().setEnabled(false);
                    // horizontal grid lines
                    yAxis.enableGridDashedLine(10f, 10f, 0f);
                    // axis range
                    yAxis.setAxisMaximum(200f);
                    yAxis.setAxisMinimum(-50f);
                }
                {   // // Create Limit Lines // //
                    LimitLine llXAxis = new LimitLine(9f, "Index 10");
                    llXAxis.setLineWidth(4f);
                    llXAxis.enableDashedLine(10f, 10f, 0f);
                    llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
                    llXAxis.setTextSize(10f);
//                    llXAxis.setTypeface(tfRegular);

                    LimitLine ll1 = new LimitLine(150f, "Upper Limit");
                    ll1.setLineWidth(4f);
                    ll1.enableDashedLine(10f, 10f, 0f);
                    ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
                    ll1.setTextSize(10f);
//                    ll1.setTypeface(tfRegular);

                    LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
                    ll2.setLineWidth(4f);
                    ll2.enableDashedLine(10f, 10f, 0f);
                    ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
                    ll2.setTextSize(10f);
//                    ll2.setTypeface(tfRegular);

                    // draw limit lines behind data instead of on top
                    yAxis.setDrawLimitLinesBehindData(true);
                    xAxis.setDrawLimitLinesBehindData(true);

                    // add limit lines
                    yAxis.addLimitLine(ll1);
                    yAxis.addLimitLine(ll2);
                    //xAxis.addLimitLine(llXAxis);
                }
                setData(45, 180);
                // draw points over time
                mChart.animateX(1500);
                // get the legend (only possible after setting data)
                Legend l = mChart.getLegend();
                // draw legend entries as lines
                l.setForm(Legend.LegendForm.LINE);
                mChart.invalidate();
        }
    }

    private void setData(int count, float range) {
        if(mChart==null){
            return;
        }
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range) - 30;
            values.add(new Entry(i, val, mChartParent.getResources().getDrawable(R.drawable.star)));
        }

        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");
            set1.setDrawIcons(false);
            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f);

            // black lines and points
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            // draw points as solid circles
            set1.setDrawCircleHole(false);
            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return mChart.getAxisLeft().getAxisMinimum();
                }
            });


            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                //Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
                //set1.setFillDrawable(drawable);
                set1.setFillColor(Color.BLACK);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);
            // set data
            mChart.setData(data);
        }
    }
}
