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
import com.example.lop2.adapters.RecyclerAdapterAlbum;
import com.example.lop2.models.Album;
import com.example.lop2.services.ApiService;
import com.example.lop2.services.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AlbumFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerAdapterAlbum playlistRecyclerAlbum;
    private ArrayList<Album> albums;

    public AlbumFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album, container, false);
        mapping();
        getData();
        return view;
    }

    private void mapping() {
        recyclerView = view.findViewById(R.id.fmAlbum_recyclerView);
    }

    private void getData(){
        DataService dataService = ApiService.getService();
        Call<List<Album>> callback = dataService.getAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                albums = (ArrayList<Album>) response.body();
                playlistRecyclerAlbum = new RecyclerAdapterAlbum(getActivity(), albums);
                recyclerView.setAdapter(playlistRecyclerAlbum);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Log.d("tag", "Load data Album fail");
            }
        });
    }


}