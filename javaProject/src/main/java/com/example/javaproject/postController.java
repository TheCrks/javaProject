package com.example.javaproject;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class postController {
    @FXML
    protected Label warn;
    @FXML
    protected ListView list;
    @FXML
    protected ChoiceBox attributeChoice;
    @FXML
    protected ChoiceBox categoryChoice;
    @FXML
    protected ChoiceBox popChoice;

    @FXML
    protected void back(){
        Public.publicStage.setTitle("Ana Menü");
        Public.publicStage.setScene(Public.menuScene);
        Public.publicStage.show();
    }
    @FXML
    protected void uygula(){
       String att =(String) attributeChoice.getValue();
       String cat = (String) categoryChoice.getValue();
       String pop = (String) popChoice.getValue();
       String[] pops = pop.split("-");
       int min = Integer.parseInt(pops[0]);
       int max = Integer.parseInt(pops[1]);
       ArrayList<Media> shownPost = new ArrayList<>();
       ArrayList<Media> shownPost2 = new ArrayList<>();
       ArrayList<Media> shownPostFinal = new ArrayList<>();

        for (int i=0;i<Library.postLib.size();i++){
           if (Generator.indexOfString(Library.postLib.get(i).getAttributes(),att)){
               System.out.println("att");
               shownPost.add(Library.postLib.get(i));
           }
       }
       for (int i=0;i<shownPost.size();i++){
           if (Objects.equals(shownPost.get(i).getCatagory(), cat)){
               System.out.println("cat");
               shownPost2.add(shownPost.get(i));
           }
       }
       for (int i=0;i<shownPost2.size();i++){
           if (min <=shownPost2.get(i).getPopularity() && shownPost2.get(i).getPopularity() <= max){
               System.out.println("pop");
               shownPostFinal.add(shownPost2.get(i));
           }
       }
       list.getItems().clear();
       for (int i =0;i<shownPostFinal.size();i++){
           list.getItems().add(shownPostFinal.get(i));
       }
    }
    @FXML
    protected void fillList(){
        String[] pops = {"0-1000","1000-5000","5000-10000"};
        Generator.setRandomPosts();
        for (int i=0;i<Library.postLib.size();i++) {
            list.getItems().add(Library.postLib.get(i).toString());
        }
        if (attributeChoice.getItems().size() == 0) {
            for (int i = 0; i < Library.attributes.size(); i++)
                attributeChoice.getItems().add(Library.attributes.get(i));
            for (int i = 0; i < Library.categories.size(); i++)
                categoryChoice.getItems().add(Library.categories.get(i));
            for (int i = 0; i < pops.length; i++)
                popChoice.getItems().add(pops[i]);
        }
    }
    @FXML
    protected void setWarn(){
        int i = list.getSelectionModel().getSelectedIndex();
        warn.setText(list.getItems().get(i).toString());
        warn.setStyle("-fx-text-fill: blue");
        warn.setVisible(true);
    }
}
