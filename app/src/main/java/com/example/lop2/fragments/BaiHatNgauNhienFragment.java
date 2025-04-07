package com.example.lop2.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lop2.R;
import com.example.lop2.adapters.RecyclerAdapterBaiHatNgauNhien;
import com.example.lop2.models.BaiHat;
import com.example.lop2.services.ApiService;
import com.example.lop2.services.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiHatNgauNhienFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private RecyclerAdapterBaiHatNgauNhien recyclerAdapterBaiHatNgauNhien;
    private ArrayList<BaiHat> baiHatArrayList;

    public BaiHatNgauNhienFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bai_hat_ngau_nhien, container, false);
        recyclerView = view.findViewById(R.id.fmBaiHatNgauNhien_recyclerView);
        getData();
        return view;
    }

    private void getData(){
        DataService dataService = ApiService.getService();
        Call<List<BaiHat>> callback = dataService.getBaiHatNgauNhien();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList = (ArrayList<BaiHat>) response.body();
                recyclerAdapterBaiHatNgauNhien = new RecyclerAdapterBaiHatNgauNhien(getActivity(), baiHatArrayList);
                LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
                recyclerView.setAdapter(recyclerAdapterBaiHatNgauNhien);
                recyclerView.setLayoutManager(linearLayout);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {
                Log.d("tag", "Load data BaiHat Ngau Nhien fail");

            }
        });
    }
}