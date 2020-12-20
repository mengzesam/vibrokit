package com.mzs.vibrokit.model;

public class Vector {
    private static final double epsilon=1e-7;
    private double ampitude;
    private double angle;

    public Vector add(Vector rhs){
        Vector ans=new Vector();
        double arc1=angle*Math.PI/180.0;
        double arc2=rhs.angle*Math.PI/180.0;
        double real=ampitude*Math.cos(arc1)+rhs.ampitude*Math.cos(arc2);
        double image=ampitude*Math.sin(arc1)+rhs.ampitude*Math.sin(arc2);
        if(iszero(real) && iszero(image)){
            ans.ampitude=0.0;
            ans.angle=0.0;
            return ans;
        }
        if(iszero(real)){
            ans.ampitude=Math.abs(image);
            if(image<0){
                ans.angle=270.0;
            }else{
                ans.angle=90.0;
            }
            return ans;
        }
        if(iszero(image)){
            ans.ampitude=Math.abs(real);
            if(real<0){
                ans.angle=180.0;
            }else{
                ans.angle=0.0;
            }
            return ans;
        }
        ans.ampitude=Math.sqrt(real*real+image*image);
        ans.angle=Math.atan2(image,real)*180/Math.PI;
        return ans;
    }

    public Vector substitute(Vector rhs) {
        Vector ans = new Vector();
        double arc1=angle*Math.PI/180.0;
        double arc2=rhs.angle*Math.PI/180.0;
        double real=ampitude*Math.cos(arc1)-rhs.ampitude*Math.cos(arc2);
        double image=ampitude*Math.sin(arc1)-rhs.ampitude*Math.sin(arc2);
        if(iszero(real) && iszero(image)){
            ans.ampitude=0.0;
            ans.angle=0.0;
            return ans;
        }
        if(iszero(real)){
            ans.ampitude=Math.abs(image);
            if(image<0){
                ans.angle=270.0;
            }else{
                ans.angle=90.0;
            }
            return ans;
        }
        if(iszero(image)){
            ans.ampitude=Math.abs(real);
            if(real<0){
                ans.angle=180.0;
            }else{
                ans.angle=0.0;
            }
            return ans;
        }
        ans.ampitude=Math.sqrt(real*real+image*image);
        ans.angle=Math.atan2(image,real)*180/Math.PI;
        return ans;
    }

    public Vector multiple(Vector rhs){
        Vector ans=new Vector();
        ans.ampitude=ampitude*rhs.ampitude;
        ans.angle=angle+rhs.angle;
        if(iszero(ans.ampitude)){
            ans.ampitude = 0.0;
            ans.angle = 0.0;
            return ans;
        }
        if(ans.ampitude<0){
            ans.ampitude=-ans.ampitude;
            ans.angle+=180;
        }
        ans.ampitude=(ans.ampitude+1080)%360;
        return ans;
    }

    public Vector divide(Vector rhs) {
        Vector ans = new Vector();
        if(iszero(rhs.ampitude)){
            ans.ampitude=Double.MAX_VALUE;
            ans.angle=0.0;
            return ans;
        }
        ans.ampitude = ampitude / rhs.ampitude;
        ans.angle = angle - rhs.angle;
        if(iszero(ans.ampitude)){
            ans.ampitude = 0.0;
            ans.angle = 0.0;
            return ans;
        }
        if(ans.ampitude<0){
            ans.ampitude=-ans.ampitude;
            ans.angle+=180;
        }
        ans.ampitude=(ans.ampitude+1080)%360;
        return ans;
    }


    public double getAmpitude(){
        return ampitude;
    }

    public double getAngle(){
        return angle;
    }

    public void setAmpitude(double ampitude){
        this.ampitude=Math.abs(ampitude);
        if(iszero(ampitude)){
            this.angle=0.0;
        }else if(ampitude<0){
            this.angle=this.angle+180.0;
        }
    }

    public void setAngle(double angle){
        this.angle=(angle+1080)%360;
    }

    private boolean iszero(double val){
        if(-epsilon<val && val<epsilon){
            return true;
        }else{
            return false;
        }
    }
}
