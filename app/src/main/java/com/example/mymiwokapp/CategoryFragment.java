package com.example.mymiwokapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
/*
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    private int fragType;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CategoryFragment(int type) {
        fragType = type;
        // Required empty public constructor
    }
/*
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    /*public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView rv = view.findViewById(R.id.rec_view);

        switch(fragType){
            case MainActivity.NUM_FRAG:
                rv.setAdapter(new MyRecyclerAdapter(
                        MainActivity.numbersList,
                        R.color.category_numbers,
                        true));
                break;

            case MainActivity.FAMILY_FRAG:
                rv.setAdapter(new MyRecyclerAdapter(
                        MainActivity.familyList,
                        R.color.category_family,
                        true
                ));
                break;

            case MainActivity.COLOR_FRAG:
                rv.setAdapter(new MyRecyclerAdapter(
                        MainActivity.colorList,
                        R.color.category_colors,
                        true
                ));
                break;

            case MainActivity.PHRASES_FRAG:
                rv.setAdapter(new MyRecyclerAdapter(
                        MainActivity.phrasesList,
                        R.color.category_phrases,
                        false
                ));
                break;
        }
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));



        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onStart() {
        Log.d("FRAGMENT "+fragType, "STARTED");
        super.onStart();
    }
    @Override
    public void onResume() {
        Log.d("FRAGMENT "+fragType, "RESUMED");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("FRAGMENT "+fragType, "PAUSED");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("FRAGMENT "+fragType, "STOPPED");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d("FRAGMENT "+fragType, "DESTROYED");
        super.onDestroy();
    }
}