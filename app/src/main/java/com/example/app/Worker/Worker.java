package com.example.app.Worker;

import android.graphics.Bitmap;

/**
 * Created by yickson on 2018/5/27.
 */

public class Worker {

    private  int workerId;

    private  Bitmap workerImage;

    private  String workerName;

    private  String workerType;

    private  String workerDescription;

    public void Worker(String workerName, Bitmap workerImage){
        this.workerName = workerName;
         this.workerImage= workerImage;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerType() {
        return workerType;
    }

    public void setWorkerType(String workerType) {
        this.workerType = workerType;
    }

    public String getWorkerDescription() {
        return workerDescription;
    }

    public void setWorkerDescription(String workerDescription) {
        this.workerDescription = workerDescription;
    }

    public Bitmap getWorkerImage() {
        return workerImage;
    }

    public void setWorkerImage(Bitmap workerImage) {
        this.workerImage = workerImage;
    }
}
