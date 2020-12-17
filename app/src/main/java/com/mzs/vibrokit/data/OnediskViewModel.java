package com.mzs.vibrokit.data;


import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.xml.sax.Parser;

public class OnediskViewModel extends ViewModel {
    public MutableLiveData<String>  mVibration0_a=new MutableLiveData("0.0");
    public MutableLiveData<String>  mVibration0_p=new MutableLiveData("0.0");
/*    private double mVibration0_p;
    private double mVibration1_a;
    private double mVibration1_p;
    private double mTrialweight_a;
    private double mTrialweight_p;
    private double mAlpha_a;
    private double mAlpha_p;
    private double mWeight1_a;
    private double mWeight1_p;
    private double mWeight2_a;
    private double mWeight2_p;*/

   /* public MutableLiveData<String> getmVibration0_a() {
        return mVibration0_a;
    }

    public MutableLiveData<String> getmVibration0_p() {
        return mVibration0_p;
    }
*/
    public void onClick(){
        double pi=3.1415926;
        String sa=mVibration0_a.getValue();
        if(sa==null|sa.equals("")){
            mVibration0_a.setValue("0.0");
            mVibration0_p.setValue("0.0");
        }else{
            double a=Double.parseDouble(sa);
            double p=a*180.0/pi;
            mVibration0_p.setValue(String.format("%s",p));
        }
    }
}

