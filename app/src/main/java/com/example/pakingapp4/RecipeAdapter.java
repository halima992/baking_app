package com.example.pakingapp4;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pakingapp4.model.Ingredients;
import com.example.pakingapp4.model.Recipe;
import com.example.pakingapp4.model.Steps;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>  {
    private List<Recipe> recipesContent = new ArrayList<>();
    Context context;
    public RecipeAdapter(){
    }
    public RecipeAdapter(Context context,List <Recipe>recipes){
        recipesContent.addAll(recipes);
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutId = R.layout.list_items;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutId, viewGroup,shouldAttachToParentImmediately);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        String recipe=recipesContent.get(position).getName();
        Log.d("recipeAdapter","the response miliion is : ");
        viewHolder.mRecipe.setText(recipe);


    }

    @Override
    public int getItemCount() {
        if (null == recipesContent ) return 0;
        return recipesContent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_cake)
         TextView mRecipe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            // mRecipe = (TextView)itemView.findViewById(R.id.tv_cake);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetilesActivity.class);
                    Bundle bundle = new Bundle();
                    Recipe clickedRecipe = recipesContent.get(getAdapterPosition());
                    ArrayList<Ingredients> objects = new ArrayList<>();
                    ArrayList<Steps>steps=new ArrayList<>();
                    objects.addAll(clickedRecipe.getIngredients());
                    steps.addAll(clickedRecipe.getSteps());
                    bundle.putParcelableArrayList("objects",objects);
                    bundle.putParcelableArrayList("steps",steps);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
