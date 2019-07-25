package com.example.pakingapp4.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe {
    private int id;
    private String name;
    @SerializedName("ingredients")
    @Expose
    private List<Ingredients> ingredients = null;
    @SerializedName("steps")
    @Expose
    private List<Steps> steps = null;
    private int servings;
    private String  image;


    public  Recipe(int id,String name,int servings){
        this.id=id;
        this.name=name;
        this.servings=servings;
    }

    //setter
    public void setId(int id){
        this.id=id;

    }
    public void setName(String name){
        this.name=name;
    }

    public void setIngredients(List<Ingredients>ingredients){
        this.ingredients=ingredients;
    }
    public void setSteps(List<Steps>steps){
        this.steps=steps;
    }
    public void setServings(){

    }
    public void setImage(){

    }
// getter


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public  List <Steps> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
}

