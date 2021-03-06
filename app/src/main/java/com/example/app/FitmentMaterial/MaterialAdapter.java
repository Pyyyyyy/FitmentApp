package com.example.app.FitmentMaterial;



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
import com.example.app.R;

import java.util.List;


public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.ViewHolder>{


    private Context mContext;

    private List<Material> mMaterialList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView MaterialImage;
        TextView MaterialName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            MaterialImage = (ImageView) view.findViewById(R.id.image);
            MaterialName = (TextView) view.findViewById(R.id.name);
        }
    }

    public MaterialAdapter(List<Material> materialList) {
        mMaterialList = materialList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.material_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Material material = mMaterialList.get(position);
                Intent intent = new Intent(mContext, MaterialActivity.class);
                intent.putExtra(MaterialActivity.FRUIT_NAME, material.getName());
                intent.putExtra(MaterialActivity.FRUIT_IMAGE_ID, material.getPicture());
                mContext.startActivity(intent);
            }
        });
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Material material = mMaterialList.get(position);
        holder.MaterialName.setText(material.getName());
       // holder.MaterialImage.setImageBitmap(material.getPicture());
        Glide.with(mContext).load(material.getPicture()).into(holder.MaterialImage);
    }

    @Override
    public int getItemCount() {
        return mMaterialList.size();
    }

}