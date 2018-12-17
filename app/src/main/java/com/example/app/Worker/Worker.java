package com.example.app.Worker;

import android.graphics.Bitmap;

/**
 * Created by yickson on 2018/5/27.
 */

public class Worker {

    //private  int workerId;

    private  int workerImage;

    private  String workerName;

    //private  String workerType;

   // private  String workerPhoneNumber;

    public  Worker(String workerName, int workerImage){
        this.workerName = workerName;
         this.workerImage= workerImage;
    }
/*
    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }
*/
    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }
/*
    public String getWorkerType() {
        return workerType;
    }

    public void setWorkerType(String workerType) {
        this.workerType = workerType;
    }

    public String getWorkerPhoneNumber() {
        return workerPhoneNumber;
    }

    public void setWorkerPhoneNumber(String workerPhoneNumber) {
        this.workerPhoneNumber = workerPhoneNumber;
    }
*/
    public int getWorkerImage() {
        return workerImage;
    }

    public void setWorkerImage(int workerImage) {
        this.workerImage = workerImage;
    }
}
