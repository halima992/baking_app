package com.example.pakingapp4;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pakingapp4.model.Ingredients;
import com.example.pakingapp4.model.Steps;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class fragmentIngredients extends Fragment {
    public static final String LIST_INGREDIENTS = "list_inger";
    public static final String List_STEPS = "list_steps";
    @BindView(R.id.tv_content)
     TextView textView;
    private List<Ingredients> mList;
    private List<Steps>mSteps;
    public fragmentIngredients() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            mList = savedInstanceState.getParcelableArrayList(LIST_INGREDIENTS);
            mSteps=savedInstanceState.getParcelableArrayList(List_STEPS);

        }
        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ButterKnife.bind(this, rootView);

         //textView = (TextView) rootView.findViewById(R.id.tv_content);
        if(mList != null){
        for(Ingredients s :mList){
            String text=(s.getQuantity()+" "+s.getMeasure()+" of "+s.getIngredient()+"\n");
            textView.append(text);

        }}
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_steps);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        if(mSteps!=null){
            detilesAdapter mAdapter=new detilesAdapter(getContext(),mSteps);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }



        return rootView;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(LIST_INGREDIENTS, (ArrayList<? extends Parcelable>) mList);
        outState.putParcelableArrayList(List_STEPS,(ArrayList<? extends Parcelable>) mSteps);
    }
    public void setmList(List<Ingredients> mList) {
        this.mList = mList;
    }
    public void setmSteps(List<Steps> mSteps) {
        this.mSteps = mSteps;
    }

}
