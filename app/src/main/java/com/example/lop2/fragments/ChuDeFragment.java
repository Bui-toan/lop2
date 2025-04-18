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
import com.example.lop2.adapters.RecyclerAdapterChuDe;
import com.example.lop2.models.ChuDe;
import com.example.lop2.services.ApiService;
import com.example.lop2.services.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChuDeFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerAdapterChuDe recyclerAdapterChuDe;
    private ArrayList<ChuDe> chuDeArrayList;

    public ChuDeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chu_de, container, false);
        recyclerView = view.findViewById(R.id.fmChuDe_recyclerView);
        getData();
        return view;
    }

    private void getData(){
        DataService dataService = ApiService.getService();
        Call<List<ChuDe>> callback = dataService.getChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                chuDeArrayList = (ArrayList<ChuDe>) response.body();
                recyclerAdapterChuDe = new RecyclerAdapterChuDe(getActivity(), chuDeArrayList);
                LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
                linearLayout.setOrientation(RecyclerView.HORIZONTAL);
                recyclerView.setAdapter(recyclerAdapterChuDe);
                recyclerView.setLayoutManager(linearLayout);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {
                Log.d("tag", "Load data chude fail");

            }
        });
    }
}