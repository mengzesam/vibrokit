package com.mzs.vibrokit.model;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OnediskViewModel extends ViewModel {
    public MutableLiveData<String>  mVibration0_a=new MutableLiveData("0.0");
    public MutableLiveData<String>  mVibration0_p=new MutableLiveData("0.0");
    public MutableLiveData<String>  mVibration1_a=new MutableLiveData("0.0");
    public MutableLiveData<String>  mVibration1_p=new MutableLiveData("0.0");
    public MutableLiveData<String>  mTrialweight_a=new MutableLiveData("0.0");
    public MutableLiveData<String>  mTrialweight_p=new MutableLiveData("0.0");

    private Vector mVib0=new Vector();
    private Vector mVib1=new Vector();
    private Vector mTrialWeight=new Vector();
    private Vector mInfluentCoefficent=new Vector();
    private Vector mWeightingA=new Vector();
    private Vector mWeightingB=new Vector();

    public void onCalOnediskClick(){
        getInputs();
        mInfluentCoefficent=(mVib1.substitute(mVib0)).divide(mTrialWeight);
        mWeightingA=mVib0.divide(mInfluentCoefficent);
        mWeightingA.setAngle(mWeightingA.getAngle()+180.0);
        mWeightingB=mVib1.divide(mInfluentCoefficent);
        mWeightingB.setAngle(mWeightingB.getAngle()+180.0);
    }

    public void onResetOnediskClick(){
        mVibration0_a.setValue("");
        mVibration0_p.setValue("");
        mTrialweight_a.setValue("");
        mTrialweight_p.setValue("");
        mVibration1_a.setValue("");
        mVibration1_p.setValue("");
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
}

