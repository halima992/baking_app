package com.example.pakingapp4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pakingapp4.model.Steps;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class detilesAdapter extends RecyclerView.Adapter<detilesAdapter.ViewHolder>  {
     private List<Steps> stepsContent = new ArrayList<>();
     Context context;
    public detilesAdapter(){
    }
    public detilesAdapter(Context context,List <Steps>Steps){
        stepsContent.addAll(Steps);
        this.context=context;
        Log.d("stepsAdapter","construct : ");

    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        Log.d("onBindViewHolderHH","the response good is : ");
        final Context context = viewGroup.getContext();
        int layoutId = R.layout.steps_items;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        Log.d("onBindViewHolder","the response good is : ");
        View view = inflater.inflate(layoutId, viewGroup,shouldAttachToParentImmediately);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( detilesAdapter.ViewHolder viewHolder, int position) {
        String steps=(stepsContent.get(position).getId()+" "+stepsContent.get(position).getShortDescription());
        viewHolder.mSteps.setText(steps);
    }

    @Override
    public int getItemCount() {
        if (null == stepsContent ) return 0;
        Log.d("stepsAdapter","the response is : "+
                stepsContent.size());
        return stepsContent.size();    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_steps)
          TextView mSteps;
        public ViewHolder( View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            // mSteps = (TextView)itemView.findViewById(R.id.tv_steps);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean twoPane=false;
                    Steps clickedStep = stepsContent.get(getAdapterPosition());

                    if(isTwoPane(twoPane)){
                        String video;
                        String image;
                        String description;

                        video=clickedStep.getVideoURL();
                        image=clickedStep.getThumbnailURL();
                        description=clickedStep.getDescription();
                        FragmentMedia fragmentMedia=new FragmentMedia();
                        fragmentMedia.setDescription(description);
                        fragmentMedia.setImage(image);
                        fragmentMedia.setVideo(video);
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        // FragmentManager fragmentManager=
                        Toast.makeText(context, ("the two pane " + isTwoPane(twoPane) + " is clicked."),
                                Toast.LENGTH_SHORT).show();
                        activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.media_container,fragmentMedia).commit();
                        Toast.makeText(context, ("the two pane " + isTwoPane(twoPane) + " is clicked."),
                                Toast.LENGTH_SHORT).show();

                    }
                    else {
                        ArrayList<Steps>steps=new ArrayList<>();
                        Intent intent = new Intent(context, MediaActivity.class);
                        Bundle bundle = new Bundle();
                        steps.addAll(stepsContent);
                        bundle.putParcelable("step", clickedStep);
                        bundle.putInt("position",getAdapterPosition());
                        bundle.putParcelableArrayList("lists",steps);
                        intent.putExtras(bundle);
                        context.startActivity(intent);


                    }

                }
            });

        }
    }
    public boolean isTwoPane(boolean twoPane){
        twoPane=context.getResources().getBoolean(R.bool.TwoPane);
        return twoPane;
    }
}
