package com.example.app.Worker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.app.FitmentMaterial.Material;
import com.example.app.FitmentMaterial.MaterialActivity;
import com.example.app.FitmentMaterial.MaterialAdapter;
import com.example.app.R;

import java.util.List;

/**
 * Created by yickson on 2018/5/27.
 */

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.ViewHolder> {

    private Context mContext;

    private List<Worker> mWorkerList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView WorkerImage;
        TextView WorkerName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            WorkerImage = (ImageView) view.findViewById(R.id.image);
            WorkerName = (TextView) view.findViewById(R.id.name);
        }
    }

    public WorkerAdapter(List<Worker> workerList) {
        mWorkerList = workerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.material_item, parent, false);
        final WorkerAdapter.ViewHolder holder = new WorkerAdapter.ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Worker worker = mWorkerList.get(position);
                Intent intent = new Intent(mContext, GongRenActivity.class);
                intent.putExtra(GongRenActivity.FRUIT_NAME, worker.getWorkerName());
                intent.putExtra(GongRenActivity.FRUIT_IMAGE_ID, worker.getWorkerImage());
                mContext.startActivity(intent);
            }
        });
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Worker worker = mWorkerList.get(position);
        holder.WorkerName.setText(worker.getWorkerName());
        //holder.WorkerImage.setImageBitmap(worker.getWorkerImage());
        Glide.with(mContext).load(worker.getWorkerImage()).into(holder.WorkerImage);
    }

    @Override
    public int getItemCount() {
        return mWorkerList.size();
    }
}
