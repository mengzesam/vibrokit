package com.mzs.vibrokit.model;


import android.annotation.SuppressLint;
import android.widget.Adapter;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mzs.vibrokit.R;
import com.mzs.vibrokit.databinding.OnediskItemBinding;
import com.mzs.vibrokit.recyclerview.DataBoundAdapter;
import com.mzs.vibrokit.recyclerview.DataBoundViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OnediskViewModel extends ViewModel {
    public MutableLiveData<String>  mVibration0_a;
    public MutableLiveData<String>  mVibration0_p;
    public MutableLiveData<String>  mVibration1_a;
    public MutableLiveData<String>  mVibration1_p;
    public MutableLiveData<String>  mTrialweight_a;
    public MutableLiveData<String>  mTrialweight_p;

    private OnediskAdapter mAdapter;
    private Vector mVib0;
    private Vector mVib1;
    private Vector mTrialWeight;
    private Vector mInfluentCoefficent;
    private Vector mWeightingA;
    private Vector mWeightingB;
    private static int pos=0;

    public OnediskViewModel() {
        mVibration0_a = new MutableLiveData<String>("80.0");
        mVibration0_p = new MutableLiveData<String>("30.0");
        mVibration1_a = new MutableLiveData<String>("30.0");
        mVibration1_p = new MutableLiveData<String>("90.0");
        mTrialweight_a = new MutableLiveData<String>("200.0");
        mTrialweight_p = new MutableLiveData<String>("150.0");
        mVib0 = new Vector();
        mVib1 = new Vector();
        mTrialWeight = new Vector();
        mInfluentCoefficent = new Vector();
        mWeightingA = new Vector();
        mWeightingB = new Vector();
        OutItem[] items=new OutItem[1];
        items[0]=new OutItem();
        mAdapter=new OnediskAdapter(items);
    }

    public void onCalOnediskClick(){
        getInputs();
        mInfluentCoefficent=(mVib1.substitute(mVib0)).divide(mTrialWeight);
        mWeightingA=mVib0.divide(mInfluentCoefficent);
        mWeightingA.setAngle(mWeightingA.getAngle()+180.0);
        mWeightingB=mVib1.divide(mInfluentCoefficent);
        mWeightingB.setAngle(mWeightingB.getAngle()+180.0);
        int size=mAdapter.getItemCount();
        if(pos<size){
            ((OutItem) mAdapter.getItem(0)).setItem(pos+1, mVib0, mVib1, mTrialWeight,
                    mInfluentCoefficent, mWeightingA, mWeightingB);
        }else{
            mAdapter.addItem(new OutItem(size + 1, mVib0, mVib1, mTrialWeight,
                    mInfluentCoefficent, mWeightingA, mWeightingB));
        }
        pos++;
    }

    public void onResetOnediskClick(){
        mVibration0_a.setValue("");
        mVibration0_p.setValue("");
        mTrialweight_a.setValue("");
        mTrialweight_p.setValue("");
        mVibration1_a.setValue("");
        mVibration1_p.setValue("");
        mAdapter.removeItems();
        if(mAdapter.getItemCount()>0){
            ((OutItem)mAdapter.getItem(0)).clear();
        }
        pos=0;
    }

    private void getInputs(){
        String textVal=mVibration0_a.getValue();
        if(textVal==null|textVal.equals("")){
            mVib0.setAmpitude(0.0);
        }else{
            mVib0.setAmpitude(Double.parseDouble(textVal));
        }
        textVal=mVibration0_p.getValue();
        if(textVal==null|textVal.equals("")){
            mVib0.setAngle(0.0);
        }else{
            mVib0.setAngle(Double.parseDouble(textVal));
        }
        textVal=mVibration1_a.getValue();
        if(textVal==null|textVal.equals("")){
            mVib1.setAmpitude(0.0);
        }else{
            mVib1.setAmpitude(Double.parseDouble(textVal));
        }
        textVal=mVibration1_p.getValue();
        if(textVal==null|textVal.equals("")){
            mVib1.setAngle(0.0);
        }else{
            mVib1.setAngle(Double.parseDouble(textVal));
        }
        textVal=mTrialweight_a.getValue();
        if(textVal==null|textVal.equals("")){
            mTrialWeight.setAmpitude(0.0);
        }else{
            mTrialWeight.setAmpitude(Double.parseDouble(textVal));
        }
        textVal=mTrialweight_p.getValue();
        if(textVal==null|textVal.equals("")){
            mTrialWeight.setAngle(0.0);
        }else{
            mTrialWeight.setAngle(Double.parseDouble(textVal));
        }
    }

    public OnediskAdapter getAdapter() {
        return mAdapter;
    }

    public static class OutItem extends BaseObservable {
        @Bindable
        private String mLine1,mLine2,mLine3;
        private int mItemPos;

        public OutItem(){
            mItemPos=1;
            mLine1=new String("");
            mLine2=new String("");
            mLine3=new String("");
        }

        public OutItem(int itempos,Vector vib0,Vector vib1,Vector tiralweight,Vector influentCoefficent,
                       Vector weightingA, Vector weightingB){
            this.mItemPos=itempos;
            @SuppressLint("DefaultLocale")
            String line1=String.format("(%02d)\t原始振动:%3.0fum∠%.0f°\t\t试加重后振动:%3.0fum∠%.0f°",
                    mItemPos,
                    vib0.getAmpitude(),vib0.getAngle(),
                    vib1.getAmpitude(),vib1.getAngle());
            @SuppressLint("DefaultLocale")
            String line2=String.format("\t\t\t试加重量:%4.0fg∠%.0f°\t\t\t影响系数:%.4f∠%.1f°",
                    tiralweight.getAmpitude(),tiralweight.getAngle(),
                    influentCoefficent.getAmpitude(),influentCoefficent.getAngle());
            @SuppressLint("DefaultLocale")
            String line3=String.format("\t\t\t需平衡量(去试重):%3.0fg∠%.0f°\t(保留试重):%3.0fg∠%.0f°",
                    weightingA.getAmpitude(),weightingA.getAngle(),
                    weightingB.getAmpitude(),weightingB.getAngle());
            mLine1= line1;
            mLine2= line2;
            mLine3= line3;
        }

        public void setItem(int itempos,Vector vib0,Vector vib1,Vector tiralweight,Vector influentCoefficent,
                            Vector weightingA, Vector weightingB){
            this.mItemPos=itempos;
            @SuppressLint("DefaultLocale")
            String line1=String.format("(%02d)\t原始振动:%3.0fum∠%.0f°\t\t试加重后振动:%3.0fum∠%.0f°",
                    mItemPos,
                    vib0.getAmpitude(),vib0.getAngle(),
                    vib1.getAmpitude(),vib1.getAngle());
            @SuppressLint("DefaultLocale")
            String line2=String.format("\t\t\t试加重量:%4.0fg∠%.0f°\t\t\t影响系数:%.4f∠%.1f°",
                    tiralweight.getAmpitude(),tiralweight.getAngle(),
                    influentCoefficent.getAmpitude(),influentCoefficent.getAngle());
            @SuppressLint("DefaultLocale")
            String line3=String.format("\t\t\t需平衡量(去试重):%3.0fg∠%.0f°\t(保留试重):%3.0fg∠%.0f°",
                    weightingA.getAmpitude(),weightingA.getAngle(),
                    weightingB.getAmpitude(),weightingB.getAngle());
            setLine1(line1);
            setLine2(line2);
            setLine3(line3);
        }
        public void clear(){
            mLine1="";
            mLine2="";
            mLine3="";
            notifyPropertyChanged(BR.line1);
            notifyPropertyChanged(BR.line2);
            notifyPropertyChanged(BR.line3);
        }

        public void setLine1(String line1) {
            this.mLine1 = line1;
            notifyPropertyChanged(BR.line1);
        }

        public void setLine2(String line2) {
            this.mLine2 = line2;
            notifyPropertyChanged(BR.line2);
        }

        public void setLine3(String line3) {
            this.mLine3 = line3;
            notifyPropertyChanged(BR.line3);
        }

        public String getLine1() {
            return mLine1;
        }

        public String getLine2() {
            return mLine2;
        }

        public String getLine3() {
            return mLine3;
        }
    }

    public static class OnediskAdapter extends DataBoundAdapter<OnediskItemBinding> {

        public OnediskAdapter(Object... outitems){
            super(R.layout.onedisk_item,outitems);
        }

/*        @Override
        protected void bindItem(DataBoundViewHolder<OnediskItemBinding> holder, int position, List<Object> payloads) {
            //TODO
        }*/
    }
}

