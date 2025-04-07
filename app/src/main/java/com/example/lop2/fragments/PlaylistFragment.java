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
import com.example.lop2.adapters.RecyclerAdapterPlaylist;
import com.example.lop2.models.Playlist;
import com.example.lop2.services.ApiService;
import com.example.lop2.services.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlaylistFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private RecyclerAdapterPlaylist playlistRecyclerAdapter;
    private ArrayList<Playlist> playlists;

    public PlaylistFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);
        mapping();
        getData();
        return view;
    }

    private void mapping() {
        recyclerView = view.findViewById(R.id.fmPlaylist_recyclerView);
    }

    private void getData(){
        DataService dataService = ApiService.getService();
        Call<List<Playlist>> callback = dataService.getPlaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                 playlists = (ArrayList<Playlist>) response.body();
                 playlistRecyclerAdapter = new RecyclerAdapterPlaylist(getActivity(), playlists, recyclerView);
                 recyclerView.setAdapter(playlistRecyclerAdapter);
                 recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                Log.d("tag", "Load data playlist fail");
            }
        });
    }

}