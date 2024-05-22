package com.example.railhelper.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.railhelper.AddPostActivity;
import com.example.railhelper.R;
import com.example.railhelper.Zone;
import com.example.railhelper.activityFive;
import com.example.railhelper.activityFour;
import com.example.railhelper.activityOne;
import com.example.railhelper.activitySix;
import com.example.railhelper.activityThree;
import com.example.railhelper.activityTwo;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    Button b1, b2, b3, b4, b5, b6;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        b1 = root.findViewById(R.id.button1);
        b2 = root.findViewById(R.id.button2);
        b3 = root.findViewById(R.id.button3);
        b4 = root.findViewById(R.id.button4);
        b5 = root.findViewById(R.id.button5);
        b6 = root.findViewById(R.id.button6);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);




        return root;
    }



    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.button1)
        {
            Intent intent = new Intent(getActivity(), activityOne.class);
            startActivity(intent);
        }

        else if(v.getId() == R.id.button2)
        {
            Intent intent = new Intent(getActivity(), Zone.class);
            startActivity(intent);
        }

        else if(v.getId() == R.id.button3)
        {
            Intent intent = new Intent(getActivity(), activityThree.class);
            startActivity(intent);
        }

        else if(v.getId() == R.id.button4)
        {
            Intent intent = new Intent(getActivity(), activityFour.class);
            startActivity(intent);
        }

        else if(v.getId() == R.id.button5)
        {
            Intent intent = new Intent(getActivity(), activityFive.class);
            startActivity(intent);
        }

        else if(v.getId() == R.id.button6)
        {
            Intent intent = new Intent(getActivity(), activitySix.class);
            startActivity(intent);
        }

    }
}