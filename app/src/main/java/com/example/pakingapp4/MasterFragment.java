package com.example.pakingapp4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pakingapp4.API.RecipesAPI;
import com.example.pakingapp4.API.Service;
import com.example.pakingapp4.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MasterFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecipeAdapter mAdapter;
    private boolean mTwoPane;
    //private List<Recipe> recipes = new ArrayList<>();
    public MasterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mTwoPane = getResources().getBoolean(R.bool.TwoPane);

        final View rootView = inflater.inflate(R.layout.master_fragment, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recipe);
        if(mTwoPane){
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        }

        else{
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));}
        mRecyclerView.setHasFixedSize(true);
        Service api = RecipesAPI.getInstance().create(Service.class);
        Call<List<Recipe>> call=api.getRecipes();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe>Allrecipes=response.body();
                mAdapter=new RecipeAdapter(getContext(), Allrecipes);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.d("MainActivity", "onFailure: there is error");

            }
        });

        return rootView;
    }
}


