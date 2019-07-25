package com.example.pakingapp4;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.pakingapp4.model.Ingredients;
import com.example.pakingapp4.model.Steps;

import java.util.ArrayList;

public class DetilesActivity extends AppCompatActivity {

    TextView textView;
    TextView textView2;
    private boolean mTwoPane;
    public static final String SHARED_PREFS = "prefs";
    public static final String KEY_BUTTON_TEXT = "keyButtonText";
    public static final String INGREDIENT ="ingredient";
    StringBuilder sb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detiles);
        Bundle bundle = getIntent().getExtras();
        ArrayList<Ingredients> list = bundle.getParcelableArrayList("objects");
        ArrayList<Steps> steps = bundle.getParcelableArrayList("steps");
        Log.d("steps", "onCreate: "+steps.get(0).getShortDescription());
        sb = new StringBuilder();
        sb.append("Ingerdients"+"\n");
        for(Ingredients s :list){
            sb.append(s.getQuantity()+" "+s.getMeasure()+" of "+s.getIngredient()+"\n");}
        if(savedInstanceState==null){
            fragmentIngredients ingredientsFragment = new fragmentIngredients();
            ingredientsFragment.setmList(list);
            ingredientsFragment.setmSteps(steps);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
               fragmentTransaction.add(R.id.details_container,ingredientsFragment)
                .commit();

    }}


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items,menu);
        return true;}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            finish();
            return true;
        }
        if (id == R.id.widget) {
           SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(INGREDIENT, String.valueOf(sb));
            editor.apply();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

