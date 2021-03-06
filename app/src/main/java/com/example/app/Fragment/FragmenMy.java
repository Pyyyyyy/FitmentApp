package com.example.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.WoDe.LoginActivity;
import com.example.app.Tools.wallet;
import com.example.app.WoDe.UserInfm;
import com.example.app.message;

import static android.content.Context.MODE_PRIVATE;

public class FragmenMy extends Fragment {

    public static  Boolean user =   false ;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.user_layout, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        TextView btn = (TextView) getActivity().findViewById(R.id.enter);
        TextView btn2 =(TextView) getActivity().findViewById(R.id.wallet);
        TextView btn3 = (TextView) getActivity().findViewById(R.id.message);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!user) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                else if(user){
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), UserInfm.class);
                    startActivity(intent);
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), wallet.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), message.class);
                startActivity(intent);
            }
        });


    }
}
