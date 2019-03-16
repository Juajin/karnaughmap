package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Main extends Application {
	ListView logicExpression = new ListView();
	FileReader fileReader = null;
	File file;
	BufferedReader br;
	String path = null;
	String line;
	ObservableList<TTNode> list = FXCollections.observableArrayList();
	FileChooser fileChooserTT = new FileChooser();
	FileChooser fileChooserBE = new FileChooser();
	String user[] = { "anil.dursun", "kadir.guvel", "simge.erdogan", "meltem.yildirim", "admin" };
	String pw[] = { "jhebyqw4lya", "", "", "", "admin" };
	String checkUser, checkPw;
	int length;
	int go = 2;
	Stage nwstg = new Stage();
	String simplyyy="";

	@Override
	public void start(Stage loginScreen) {

		// FileChoosers
		fileChooserTT.getExtensionFilters().addAll(new ExtensionFilter("Text File", "*tt"),

				new ExtensionFilter("All Files", "*.*"));
		fileChooserBE.getExtensionFilters().addAll(new ExtensionFilter("Text File", "*be"),

				new ExtensionFilter("All Files", "*.*"));
		// login Screen

		loginScreen.setTitle("Project 2");

		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10, 50, 50, 50));

		// Adding HBox
		HBox hb = new HBox();
		hb.setPadding(new Insets(20, 20, 20, 30));

		// Adding GridPane
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20, 20, 20, 20));
		gridPane.setHgap(5);
		gridPane.setVgap(5);

		// Implementing Nodes for GridPane
		Label lblUserName = new Label("Username");
		final TextField txtUserName = new TextField();
		Label lblPassword = new Label("Password");
		final PasswordField pf = new PasswordField();
		Button btnLogin = new Button("Login");
		final Label lblMessage = new Label();
		Button btnAbout = new Button("About");
		// Adding Nodes to GridPane layout
		gridPane.add(lblUserName, 0, 0);
		gridPane.add(txtUserName, 1, 0);
		gridPane.add(lblPassword, 0, 1);
		gridPane.add(pf, 1, 1);
		gridPane.add(btnLogin, 2, 0);
		gridPane.add(btnAbout, 2, 1);
		gridPane.add(lblMessage, 1, 2);

		// Reflection for gridPane
		Reflection r = new Reflection();
		r.setFraction(0.7f);
		gridPane.setEffect(r);

		// DropShadow effect
		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(5);
		dropShadow.setOffsetY(5);

		// Adding text and DropShadow effect to it
		Text text = new Text("  Project 2");
		text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
		text.setEffect(dropShadow);

		// Adding text to HBox
		hb.getChildren().add(text);

		// Add ID's to Nodes
		bp.setId("bp");
		gridPane.setId("root");
		btnLogin.setId("btnLogin");
		text.setId("text");
		btnAbout.setId("btnLogin");
		// Action for btnLogin
		btnLogin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				checkUser = txtUserName.getText().toString();
				checkPw = pf.getText().toString();
				for (int i = 0; i < user.length; i++) {
					if (i == user.length - 1 && !(checkUser.equals(user[i]) && checkPw.equals(pw[i]))) {
						go = 1;
					}
					if (checkUser.equals(user[i]) && checkPw.equals(pw[i])) {
						lblMessage.setText("Congratulations!");
						lblMessage.setTextFill(Color.GREEN);
						loginScreen.close();

						// main
						BorderPane root = new BorderPane();
						// GridPane
						GridPane gridPane = new GridPane();

						gridPane.setHgap(4);
						gridPane.setVgap(4);
						MenuBar mainMenu = new MenuBar();
						root.setTop(mainMenu);
						root.setLeft(gridPane);
						Stage primaryStage = new Stage();
						Menu fileMenu = new Menu("File");
						MenuItem openTTFile = new MenuItem("Open File(.tt)");
						openTTFile.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								file = fileChooserTT.showOpenDialog(primaryStage);
								path = file.getAbsolutePath().toString();

								try {
									fileReader = new FileReader(path);

								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								br = new BufferedReader(fileReader);
								list.clear();
								try {
									while ((line = br.readLine()) != null) {

										String[] array = new String[2];
										String[] inputs = new String[4];
										array = line.split(";");
										inputs = array[0].split(",");
										length = inputs.length;

										if (inputs.length == 4)
											list.add(new TTNode(inputs[0], inputs[1], inputs[2], inputs[3], array[1]));
										else if (inputs.length == 3)
											list.add(new TTNode(inputs[0], inputs[1], inputs[2], "X", array[1]));
										else if (inputs.length == 2)
											list.add(new TTNode(inputs[0], inputs[1], "X", "X", array[1]));
										else if (inputs.length == 1)
											list.add(new TTNode(inputs[0], "X", "X", "X", array[1]));
									}

								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
						});

						MenuItem openBEFile = new MenuItem("Open File(.be)");
						openBEFile.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								file = fileChooserBE.showOpenDialog(primaryStage);
								path = file.getAbsolutePath().toString();

								try {
									fileReader = new FileReader(path);

								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								br = new BufferedReader(fileReader);
								list.clear();
								try {
									while ((line = br.readLine()) != null) {

										String[] array = new String[2];
										String[] inputs;

										array = line.split("\\=");
										inputs = array[1].split("\\+");
										for (int j = 0; j < inputs.length; j++) {

											String[] temp = new String[4];
											for (int k = 0; k < temp.length; k++) {
												temp[k]="";
											}
											String[] inpt=null;
											inpt = (inputs[j]).split("\\.");
											for (int k = 0; k < inpt.length; k++) {
												if (inpt[k].substring(0, 1).equalsIgnoreCase("A")) {
													temp[0] = inpt[k];
												} else if (inpt[k].substring(0, 1).equalsIgnoreCase("B")) {
													temp[1] = inpt[k];
												} else if (inpt[k].substring(0, 1).equalsIgnoreCase("C")) {
													temp[2] = inpt[k];
												} else if (inpt[k].substring(0, 1).equalsIgnoreCase("D")) {
													temp[3] = inpt[k];
												}
												
													
											}
											String tempp = "";
											if (!temp[0].equals(null) && (temp[0].equalsIgnoreCase("A")
													|| temp[0].equalsIgnoreCase("A'"))) {
												tempp += temp[0] + ".";
											} else {
												tempp += "X.";
											}
											if (!temp[1].equals(null) && (temp[1].equalsIgnoreCase("B")
													|| temp[1].equalsIgnoreCase("B'"))) {
												tempp += temp[1] + ".";
											} else {
												tempp += "X.";
											}
											if (!temp[2].equals(null) && (temp[2].equalsIgnoreCase("C")
													|| temp[2].equalsIgnoreCase("C'"))) {
												tempp += temp[2] + ".";
											} else {
												tempp += "X.";
											}
											if (!temp[3].equals(null) && (temp[3].equalsIgnoreCase("D")
													|| temp[3].equalsIgnoreCase("D'"))) {
												tempp += temp[3] + ".";
											} else {
												tempp += "X.";
											}
											tempp = tempp.substring(0, tempp.length() - 1);
											simplyyy+=tempp+"+";

										}

									}

								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								simplyyy.substring(0, simplyyy.length()-1);

							}
						});
						MenuItem exitApp = new MenuItem("Exit");
						exitApp.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								primaryStage.close();
							}
						});
						fileMenu.getItems().addAll(openTTFile, openBEFile, exitApp);
						Menu edit = new Menu("Edit");
						Menu theme = new Menu("Theme");
						edit.getItems().add(theme);
						MenuItem dark = new MenuItem("Dark");
						MenuItem pink = new MenuItem("Pink");
						MenuItem gray = new MenuItem("Gray");
						theme.getItems().addAll(dark, pink, gray);

						mainMenu.getMenus().addAll(fileMenu, edit);
						// All screen design

						ObservableList<String> options = FXCollections.observableArrayList("Table size = 2",
								"Table size = 3", "Table size = 4", ".tt file");
						final ComboBox ttSize = new ComboBox(options);
						Button openTT = new Button("Open Table");
						openTT.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								if (ttSize.getValue().equals("Table size = 2")) {
									TableView<TTNode> table = new TableView<TTNode>();
									TableColumn<TTNode, String> inputA = new TableColumn("-A-");
									TableColumn<TTNode, String> inputB = new TableColumn("-B-");
									TableColumn<TTNode, String> outputF = new TableColumn("-F-");
									inputA.setCellValueFactory(new PropertyValueFactory<TTNode, String>("inputA"));
									inputB.setCellValueFactory(new PropertyValueFactory<TTNode, String>("inputB"));
									outputF.setCellValueFactory(new PropertyValueFactory<TTNode, String>("outputF"));
									table.getColumns().addAll(inputA, inputB, outputF);
								} else if (ttSize.getValue().equals("Table size = 3")) {

								} else if (ttSize.getValue().equals("Table size = 4")) {

								} else if (ttSize.getValue().equals(".tt file")) {
									TableView<TTNode> table = new TableView<TTNode>();
									TableColumn<TTNode, String> inputA = new TableColumn("-A-");
									TableColumn<TTNode, String> inputB = new TableColumn("-B-");
									TableColumn<TTNode, String> inputC = new TableColumn("-C-");
									TableColumn<TTNode, String> inputD = new TableColumn("-D-");
									TableColumn<TTNode, String> outputF = new TableColumn("-F-");
									inputA.setCellValueFactory(new PropertyValueFactory<TTNode, String>("inputA"));
									inputB.setCellValueFactory(new PropertyValueFactory<TTNode, String>("inputB"));
									inputC.setCellValueFactory(new PropertyValueFactory<TTNode, String>("inputC"));
									inputD.setCellValueFactory(new PropertyValueFactory<TTNode, String>("inputD"));
									outputF.setCellValueFactory(new PropertyValueFactory<TTNode, String>("outputF"));
									if (length == 4)
										table.getColumns().addAll(inputA, inputB, inputC, inputD, outputF);
									else if (length == 3)
										table.getColumns().addAll(inputA, inputB, inputC, outputF);
									else if (length == 2)
										table.getColumns().addAll(inputA, inputB, outputF);
									else if (length == 1)
										table.getColumns().addAll(inputA, outputF);
									table.setEditable(true);
									table.setItems(list);
									table.setPrefSize(190, 404);
									nwstg.close();
									Scene scn = new Scene(table);
									nwstg.setScene(scn);
									nwstg.show();
								}
							}
						});

						ObservableList<String> option = FXCollections.observableArrayList("KMap size = 2",
								"KMap size = 3", "KMap size = 4", ".tt file");
						final ComboBox kmapSize = new ComboBox(option);
						Button openKarnaughMap = new Button("Open KarnaughMap");

						openKarnaughMap.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								if (kmapSize.getValue().equals("KMap size = 2")) {
									KarnaughMap kmap = new KarnaughMap(list, gridPane, logicExpression, 2,false);

								} else if (kmapSize.getValue().equals("KMap size = 4")) {
									KarnaughMap kmap = new KarnaughMap(list, gridPane, logicExpression, 4,false);
								}
								else if (kmapSize.getValue().equals(".tt file")){
									KarnaughMap kmap = new KarnaughMap(list, gridPane, logicExpression, 4,true);

								}
							
								for (int j = 0; j < KarnaughMap.btn.length; j++) {
									KarnaughMap.btn[j].setId("btnLogin");
								}
								KarnaughMap.compute.setId("btnLogin");
								KarnaughMap.reset.setId("btnLogin");
								logicExpression.setId("btnLogin");
							}
						});

						Button booleanEquation = new Button("booleanEquation");
						booleanEquation.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
									Simplify cttt=new Simplify();
									cttt.simplifier(simplyyy);
							}
						});
						// all add methods
						gridPane.setPrefSize(800, 600);
						gridPane.add(openTT, 1, 1);
						gridPane.add(ttSize, 2, 1);
						gridPane.add(openKarnaughMap, 1, 3);
						gridPane.add(kmapSize, 2, 3);
						gridPane.add(booleanEquation, 3, 1);
						// Reflection for gridPane
						Reflection r = new Reflection();
						r.setFraction(0.7f);
						gridPane.setEffect(r);

						// DropShadow effect
						DropShadow dropShadow = new DropShadow();
						dropShadow.setOffsetX(25);
						dropShadow.setOffsetY(25);

						// Adding text and DropShadow effect to it
						gridPane.setEffect(dropShadow);

						Scene scene = new Scene(root);
						// themeeee
						gray.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								scene.getStylesheets()
										.add(getClass().getClassLoader().getResource("grey.css").toExternalForm());
								primaryStage.setScene(scene);
							}
						});
						dark.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								scene.getStylesheets()
										.add(getClass().getClassLoader().getResource("dark.css").toExternalForm());
								primaryStage.setScene(scene);
							}
						});
						pink.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								scene.getStylesheets()
										.add(getClass().getClassLoader().getResource("white.css").toExternalForm());
								primaryStage.setScene(scene);
							}
						});
						// Last Scene
						root.setId("bp");
						gridPane.setId("root");
						openKarnaughMap.setId("btnLogin");
						openTT.setId("btnLogin");
						kmapSize.setId("btnLogin");
						ttSize.setId("btnLogin");
						booleanEquation.setId("btnLogin");
						scene.getStylesheets()
								.add(getClass().getClassLoader().getResource("login.css").toExternalForm());
						primaryStage.setScene(scene);
						primaryStage.show();
					} else if (go == 1) {
						lblMessage.setText("Incorrect user or pw.");
						lblMessage.setTextFill(Color.RED);
					}
				}

				txtUserName.setText("");
				pf.setText("");
			}
		});

		btnAbout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				lblMessage.setTextFill(Color.WHEAT);
				lblMessage.setFont(Font.font(10));
				lblMessage.setText("    -Anýl Dursun  -Abdulkadir Güvel\n                -Simge Erdoðan");
			}
		});
		// Add HBox and GridPane layout to BorderPane Layout
		bp.setTop(hb);
		bp.setCenter(gridPane);

		// Adding BorderPane to the scene and loading CSS
		Scene scene = new Scene(bp);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("login.css").toExternalForm());
		loginScreen.setScene(scene);
//		loginScreen.titleProperty()
//				.bind(scene.widthProperty().asString().concat(" : ").concat(scene.heightProperty().asString()));
		loginScreen.setResizable(false);
		loginScreen.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
