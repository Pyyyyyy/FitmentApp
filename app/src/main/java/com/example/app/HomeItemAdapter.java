package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app.FitmentMaterial.MaterialActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class HomeItemAdapter extends BaseAdapter {

    private Context context;
    private List<CategoryBean.DataBean.DataListBean> foodDatas;

    public HomeItemAdapter(Context context, List<CategoryBean.DataBean.DataListBean> foodDatas) {
        this.context = context;
        this.foodDatas = foodDatas;
    }


    @Override
    public int getCount() {
        if (foodDatas != null) {
            return foodDatas.size();
        } else {
            return 10;
        }
    }

    @Override
    public Object getItem(int position) {
        return foodDatas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (context == null) {
            context = parent.getContext();
        }
        final CategoryBean.DataBean.DataListBean subcategory = foodDatas.get(position);
        ViewHold viewHold = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_home_category, null);
            viewHold = new ViewHold();
            viewHold.tv_name = (TextView) convertView.findViewById(R.id.item_home_name);
            viewHold.iv_icon = (SimpleDraweeView) convertView.findViewById(R.id.item_album);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.tv_name.setText(subcategory.getTitle());
        //Uri uri = Uri.parse(subcategory.getImgURL());
        Uri uri = Uri.parse("res://com.example.app/" + this.context.getResources().getIdentifier(subcategory.getImgURL(), "drawable", "com.example.app"));
        viewHold.iv_icon.setImageURI(uri);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MaterialActivity.class);
                intent.putExtra(MaterialActivity.FRUIT_NAME, subcategory.getTitle());
                intent.putExtra(MaterialActivity.FRUIT_IMAGE_ID, context.getResources().getIdentifier(subcategory.getImgURL(), "drawable", "com.example.app"));
                context.startActivity(intent);
            }
        });

        return convertView;


    }

    private static class ViewHold {
        private TextView tv_name;
        private SimpleDraweeView iv_icon;
    }

}
