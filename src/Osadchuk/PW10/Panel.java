package PW10;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.input.*;

public class Panel extends Application {
	
	private static SearchDictionary sd;
	
	public static void main(String[] args) {
		sd = new SearchDictionary();
		
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		
		// text
		Text text = new Text("Vocabulary");
		text.setScaleX(2);
		text.setScaleY(2);
		text.setLayoutY(20);
		text.setLayoutX(210);
		
		final ListView<String> list  = new ListView<String>();
		list.setLayoutX(100);
		list.setScaleX(1.5);
		list.setLayoutY(150);
		
		final TextField tf = new TextField();
		tf.setPrefColumnCount(25);
		tf.setLayoutX(100);
		tf.setLayoutY(60);
		
		Button bAdd = new Button("Add");
		bAdd.setLayoutX(40);
		bAdd.setLayoutY(100);
		bAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent event) {
				sd.addWord(tf.getText());
				list.getItems().clear();
				list.getItems().addAll(sd.query(""));
			}
			
		});
		
		Button bRem = new Button("Remove");
		bRem.setLayoutX(80);
		bRem.setLayoutY(100);
		bRem.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent event) {
				sd.delWord(tf.getText());
				list.getItems().clear();
				list.getItems().addAll(sd.query(""));
			}
			
		});
		
		Button bFnd = new Button("Find");
		bFnd.setLayoutX(141);
		bFnd.setLayoutY(100);
		bFnd.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent event) {
				list.getItems().clear();
				list.getItems().addAll(sd.query(tf.getText()));
			}
			
		});
		
		Button bShow = new Button("Show all");
		bShow.setLayoutX(350);
		bShow.setLayoutY(100);
		bShow.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent event) {
				list.getItems().clear();
				list.getItems().addAll(sd.query(""));
			}
			
		});
		
		final Label con = new Label();
		con.setLayoutX(310);
		con.setLayoutY(574);
		
		Button bCon = new Button("count");
		bCon.setLayoutX(250);
		bCon.setLayoutY(570);
		bCon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent event) {
				con.setText(String.valueOf(sd.countWords()));
			}
			
		});
		
		
		Group group = new Group(text, tf, bAdd, bRem, bFnd, bShow, bCon, con, list);
		
		Scene scene = new Scene(group);
		stage.setScene(scene);
		stage.setTitle("My Dictionary");
		stage.setWidth(500);
		stage.setHeight(650);
		stage.show();
		
	}
	
}