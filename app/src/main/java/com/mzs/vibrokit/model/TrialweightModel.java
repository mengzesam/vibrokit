package com.mzs.vibrokit.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
    }
}
