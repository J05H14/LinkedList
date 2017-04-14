package application;
	
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import linkedList.LinkedList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {
	LinkedList <String, String, String> masterList = new LinkedList<String, String, String>();
	int groupingCategory = 1;
	
	GridPane gp = new GridPane();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			root.getStyleClass().add("grid");
			Scene scene = new Scene(root, 500, 500);
			scene.getStylesheets().add("application/application.css");
			Group buttons = new Group();
			
			
			Button importList = new Button("Import List");
			importList.setMinWidth(75);
			
			importList.addEventHandler(MouseEvent.MOUSE_CLICKED,
					new EventHandler<Event>(){

						@Override
						public void handle(Event event) {
							importList();
						}
				
			});
			
			Button addToList = new Button("Add");
			addToList.setMinWidth(75);
			
			addToList.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){

				@Override
				public void handle(Event event) {
					addToList();
				}
				
			});
			
			Button deleteFromList = new Button("Delete");
			deleteFromList.setMinWidth(75);
			
			deleteFromList.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){

				@Override
				public void handle(Event event) {
					deleteFromList();
				}
				
			});
			
			Button regroupList = new Button("Regroup");
			regroupList.setMinWidth(75);
			
			regroupList.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){

				@Override
				public void handle(Event event) {
					regroupList();
					
				}
				
			});
			
			buttons.getChildren().add(importList);	
			addToList.setTranslateX(75);
			buttons.getChildren().add(addToList);
			deleteFromList.setTranslateX(150);
			buttons.getChildren().add(deleteFromList);
			regroupList.setTranslateX(225);
			buttons.getChildren().add(regroupList);
			
			Label mainListLabel = new Label("Main List");
			VBox main = new VBox();
			mainListLabel.getStyleClass().add("labels");
			main.getChildren().add(mainListLabel);
			main.setAlignment(Pos.CENTER);
			
			Label subListLabel = new Label("Sub List");
			HBox sub = new HBox();
			subListLabel.getStyleClass().add("labels");
			sub.getChildren().add(subListLabel);
			sub.setAlignment(Pos.CENTER);
			
			root.getStyleClass().add("background");
			
			root.setCenter(gp);
			root.setBottom(buttons);
			root.setLeft(main);
			root.setTop(sub);
			
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Linked List");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void importList(){
		File importFile = null;
		JFileChooser fc = new JFileChooser();
		int retVal = fc.showOpenDialog(null);
		String[] categoryOptions = {"Category 1", "Category2", "Category3"};
		
		if(retVal == JFileChooser.APPROVE_OPTION){
			importFile = fc.getSelectedFile();
		}
		this.groupingCategory = JOptionPane.showOptionDialog(null, "Which Grouping Category?", "Category Picker", 0, JOptionPane.QUESTION_MESSAGE, null, categoryOptions, "null") + 1;
		LinkedList<String, String, String>importedList = new LinkedList<String, String, String>(importFile, groupingCategory);
		
		this.masterList = importedList;
		showTable(this.masterList, this.groupingCategory);
	}
	
	public void addToList(){
		String v1 = JOptionPane.showInputDialog("Category 1: ");
		String v2 = JOptionPane.showInputDialog("Category 2: ");
		String v3 = JOptionPane.showInputDialog("Category 3: ");
		
		this.masterList.add(v1, v2, v3);
		showTable(this.masterList, this.groupingCategory);
	}
	
	public void deleteFromList(){
		String mainList = "\n";
		String subList = "\n";
		Integer mainDelIndex;
		Integer subDelIndex;
		
		for(int i = 0; i < this.masterList.size(); i++){
			mainList += "Main Index " + i + ": " + this.masterList.get(i, 1) + "\n" + this.masterList.get(i, 2) + "\n" + this.masterList.get(i, 3) + "\n\n";
		}
		
		mainDelIndex = Integer.parseInt(JOptionPane.showInputDialog("Enter the Main Index You Want to Delete From: " + mainList));

		for(int i = 0; i < this.masterList.size(mainDelIndex); i++){
			subList += "Sub Index " + i + ": " + this.masterList.get(mainDelIndex, i, 1) + "\n" + this.masterList.get(mainDelIndex, i, 2) + "\n" + this.masterList.get(mainDelIndex, i, 1) + "\n\n";
		}
		
		subDelIndex = Integer.parseInt(JOptionPane.showInputDialog("Enter the Sub Index of the Node You Want to Delete From: " + subList));
	
		this.masterList.delete(mainDelIndex, subDelIndex);
		
		showTable(this.masterList, this.groupingCategory);
	}
	
	public void regroupList(){
		String[] categoryOptions = {"Category 1", "Category2", "Category3"};
		this.groupingCategory = JOptionPane.showOptionDialog(null, "Which Grouping Category?", "Category Picker", 0, JOptionPane.QUESTION_MESSAGE, null, categoryOptions, "null") + 1;
		this.masterList.regroup(this.groupingCategory);
		showTable(this.masterList, this.groupingCategory);
	}
	
	public void showTable(LinkedList<String, String, String> list, int groupingCategory){
		gp.getChildren().clear();
		
		for(int sub = 0; sub < list.size(); sub++){
			for(int main = 0; main < list.size(sub); main++){
				Label node;
				if(list.get(sub, main, groupingCategory) != null){
					node = new Label(list.get(sub, main, 1) + "\n" + list.get(sub, main, 2) + "\n" + list.get(sub, main, 3));
					node.getStyleClass().add("node");
					System.out.println("added");
				}
				else{
					node = new Label();
					node.getStyleClass().add("emptyNode");
				}
//				node = new Label("I WORK");
//				System.out.println("test");
//				node.getStyleClass().add("node");
				
				node.setAlignment(Pos.CENTER_LEFT);
				node.setPrefHeight(100);
				node.setPrefWidth(150);
				
//				if(sub != 0 && main != 0){
//					Image sideArrow = new Image(getClass().getResourceAsStream("application/sidearrow.jpeg"));
//					Label sideArrowL = new Label();
//					sideArrowL.setGraphic(new ImageView(sideArrow));
//					gp.add(sideArrowL, main - 1, sub);
//				}
				gp.add(node, main, sub);
			}
		}
	}
}
