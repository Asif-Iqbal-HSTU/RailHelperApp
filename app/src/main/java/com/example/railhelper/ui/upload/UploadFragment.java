package com.example.railhelper.ui.upload;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.railhelper.R;
import com.example.railhelper.ui.home.HomeViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UploadFragment extends Fragment {

    private UploadViewModel mViewModel;
    private Object UploadViewModel;

    private FirebaseFirestore mFirestore;

    EditText e1, e2, e3, e4, e5, e6, e7;
    Button b;
    String s1, s2, s3, s4, s5, s6, s7;

    Map<String, String> trainMap = new HashMap<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UploadViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_upload, container, false);

        mFirestore = FirebaseFirestore.getInstance();

        b = root.findViewById(R.id.button);

        e1 = root.findViewById(R.id.editText1);
        e2 = root.findViewById(R.id.editText2);
        e3 = root.findViewById(R.id.editText3);
        e4 = root.findViewById(R.id.editText4);
        e5 = root.findViewById(R.id.editText5);
        e6 = root.findViewById(R.id.editText6);
        e7 = root.findViewById(R.id.editText7);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1 = e1.getText().toString();
                trainMap.put("Train No", s1);

                s2 = e2.getText().toString();
                trainMap.put("Name", s2);

                s3 = e3.getText().toString();
                trainMap.put("Off Day", s3);

                s4 = e4.getText().toString();
                trainMap.put("From", s4);

                s5 = e5.getText().toString();
                trainMap.put("Depatrure", s5);

                s6 = e6.getText().toString();
                trainMap.put("To", s6);

                s7 = e7.getText().toString();
                trainMap.put("Arrival", s7);

                mFirestore.collection("Trains").add(trainMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getActivity(), "Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String error = e.getMessage();
                        Toast.makeText(getActivity(), "Error " + error, Toast.LENGTH_SHORT).show();
                    }
                });

                //Toast.makeText(getActivity(), "Added Successfully", Toast.LENGTH_SHORT).show();

                        /*.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getActivity(), "Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String error = e.getMessage();
                        Toast.makeText(getActivity(), "Error " + error, Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
        });

    /*
    @Override
    /*public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upload, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UploadViewModel.class);
        // TODO: Use the ViewModel

     */
        return root;
    }
}

