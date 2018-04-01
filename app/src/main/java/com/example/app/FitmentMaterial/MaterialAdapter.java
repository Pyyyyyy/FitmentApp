package com.example.app.FitmentMaterial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app.R;

import java.util.List;

/**
 * Created by 1 on 2018/3/25.
 */

public class MaterialAdapter extends ArrayAdapter<Material> {
    private int resourceId;
    public MaterialAdapter(Context context,int textViewResourceId,List<Material> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Material material = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            //viewHolder.materialImage = (ImageView)view.findViewById(R.id.picture);
            viewHolder.materialName = (TextView)view.findViewById(R.id.material_name);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        //viewHolder.materialImage.setImageResource(material.getImageId());
        viewHolder.materialName.setText(material.getName());
        return view;
    }
    class ViewHolder{
        ImageView materialImage;
        TextView materialName;
    }

}
