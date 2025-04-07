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
import com.example.lop2.adapters.RecyclerAdapterTheLoai;
import com.example.lop2.models.TheLoai;
import com.example.lop2.services.ApiService;
import com.example.lop2.services.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TheLoaiFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private RecyclerAdapterTheLoai recyclerAdapterTheLoai;
    private ArrayList<TheLoai> theLoaiArrayList;
    public TheLoaiFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_the_loai, container, false);
        recyclerView = view.findViewById(R.id.fmTheLoai_recyclerView);
        getData();
        return view;
    }

    private void getData(){
        DataService dataService = ApiService.getService();
        Call<List<TheLoai>> callback = dataService.getTheLoai();
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                theLoaiArrayList = (ArrayList<TheLoai>) response.body();
                recyclerAdapterTheLoai = new RecyclerAdapterTheLoai(getActivity(), theLoaiArrayList);
                LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
                linearLayout.setOrientation(RecyclerView.HORIZONTAL);
                recyclerView.setAdapter(recyclerAdapterTheLoai);
                recyclerView.setLayoutManager(linearLayout);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {
                Log.d("tag", "Load data theloai fail");

            }
        });
    }
}