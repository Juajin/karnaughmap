package application;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class KarnaughMap {

	static String output = "";
	static int A[][] = new int[4][4];
	static int checked[][] = new int[4][4];
	static int value[] = new int[16];
	public static Button btn[] = new Button[16];
	Label lbl[] = new Label[8];
	Label text = new Label();
	VBox lableleft = new VBox();
	HBox labletop = new HBox();
	HBox buttons1 = new HBox();
	HBox buttons2 = new HBox();
	HBox buttons3 = new HBox();
	HBox buttons4 = new HBox();
	BorderPane buttomright = new BorderPane();
	VBox buttomButtons = new VBox();
	VBox buttomleft = new VBox();
	public static Button compute = new Button("Compute");
	public static Button reset = new Button("Reset");
	static Stage newstg = new Stage();
	Scene newscn;

	public KarnaughMap(ObservableList<TTNode> list, GridPane layoutButtom, ListView logicExpression, int kmapsize,boolean control) {
		newscn = new Scene(logicExpression);

		for (int i = 0; i < 16; i++) {
			btn[i] = new Button("X");
			btn[i].setPrefSize(100, 100);
		}
		if (kmapsize == 4) {
			lbl[0] = new Label("          00                      ");
			lbl[1] = new Label("01                   ");
			lbl[2] = new Label("11                    ");
			lbl[3] = new Label("10                   ");
			lbl[4] = new Label("00\n\n\n\n\n");
			lbl[5] = new Label("01\n\n\n\n\n");
			lbl[6] = new Label("11\n\n\n\n");
			lbl[7] = new Label("10\n\n\n\n\n");
			lbl[0].setFont(Font.font("SanSerif", 15));
			lbl[1].setFont(Font.font("SanSerif", 15));
			lbl[2].setFont(Font.font("SanSerif", 15));
			lbl[3].setFont(Font.font("SanSerif", 15));
			lbl[4].setFont(Font.font("SanSerif", 15));
			lbl[5].setFont(Font.font("SanSerif", 15));
			lbl[6].setFont(Font.font("SanSerif", 15));
			lbl[7].setFont(Font.font("SanSerif", 15));
			lableleft.getChildren().addAll(lbl[4], lbl[5], lbl[6], lbl[7]);
			labletop.getChildren().addAll(lbl[0], lbl[1], lbl[2], lbl[3]);
			for (int i = 0; i < 16; i++) {
				btn[i] = new Button("X");
				btn[i].setPrefSize(100, 100);
				if (i < 4) {
					buttons1.getChildren().add(btn[i]);
				} else if (4 <= i && i < 8) {
					buttons2.getChildren().add(btn[i]);
				} else if (8 <= i && i < 12) {
					buttons3.getChildren().add(btn[i]);
				} else {
					buttons4.getChildren().add(btn[i]);
				}

				value[i] = 2;
			}
		}
		if (kmapsize == 2) {
			lbl[0] = new Label("          0                      ");
			lbl[1] = new Label("1                   ");
			lbl[4] = new Label("0\n\n\n\n\n");
			lbl[5] = new Label("1\n\n\n\n\n");
			lableleft.getChildren().addAll(lbl[4], lbl[5]);
			labletop.getChildren().addAll(lbl[0], lbl[1]);
			for (int i = 0; i < 4; i++) {
				if (i < 2) {
					buttons1.getChildren().add(btn[i]);
				} else if (2 <= i && i < 4) {
					buttons2.getChildren().add(btn[i]);
				}
				value[i] = 2;

			}
			for (int i = 4; i < 16; i++) {
				btn[i].setText("0");
				value[i] = 0;
			}
		}
		int[] cntrlArray = { 0, 1, 3, 2, 4, 5, 7, 6, 12, 13, 15, 14, 8, 9, 11, 10 };

		if (list != null&&control) {

			for (int i = 0; i < list.size(); i++) {
				int count = 0;
				count = Integer.parseInt(list.get(i).getInputA()) * 8 + Integer.parseInt(list.get(i).getInputB()) * 4
						+ Integer.parseInt(list.get(i).getInputC()) * 2 + Integer.parseInt(list.get(i).getInputD()) * 1;
				for (int j = 0; j < cntrlArray.length; j++) {
					if(cntrlArray[j]==count){
						count=j;
						break;
					}
				}
				btn[count].setText(list.get(i).getOutputF());
				if (list.get(i).getOutputF().equalsIgnoreCase( "0")) {
					value[count] = 0;
				} else if (list.get(i).getOutputF() .equalsIgnoreCase("1")) {
					value[count] = 1;
				} else {
					value[count] = 2;
				}
			}

		}
		buttomButtons.getChildren().addAll(buttons1, buttons2, buttons3, buttons4);

		buttomleft.getChildren().addAll(compute, reset);
		buttomleft.setAlignment(Pos.CENTER);
		buttomright.setTop(labletop);
		buttomright.setLeft(lableleft);
		buttomright.setCenter(buttomButtons);
		layoutButtom.add(compute, 1, 5);
		layoutButtom.add(reset, 2, 5);
		layoutButtom.add(buttomright, 10, 6);

		// layoutButtom.getChildren().addAll(buttomleft, buttomright);
		btn[0].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				value[0]++;
				value[0] %= 3;
				if (value[0] == 0) {
					btn[0].setText("0");
				}
				if (value[0] == 1) {
					btn[0].setText("1");
				}
				if (value[0] == 2) {
					btn[0].setText("X");
				}
			}
		});
		btn[1].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				value[1]++;
				value[1] %= 3;
				if (value[1] == 0) {
					btn[1].setText("0");
				}
				if (value[1] == 1) {
					btn[1].setText("1");
				}
				if (value[1] == 2) {
					btn[1].setText("X");
				}
			}
		});
		btn[2].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				value[2]++;
				value[2] %= 3;
				if (value[2] == 0) {
					btn[2].setText("0");
				}
				if (value[2] == 1) {
					btn[2].setText("1");
				}
				if (value[2] == 2) {
					btn[2].setText("X");
				}
			}
		});
		btn[3].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				value[3]++;
				value[3] %= 3;
				if (value[3] == 0) {
					btn[3].setText("0");
				}
				if (value[3] == 1) {
					btn[3].setText("1");
				}
				if (value[3] == 2) {
					btn[3].setText("X");
				}
			}
		});
		btn[4].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				value[4]++;
				value[4] %= 3;
				if (value[4] == 0) {
					btn[4].setText("0");
				}
				if (value[4] == 1) {
					btn[4].setText("1");
				}
				if (value[4] == 2) {
					btn[4].setText("X");
				}
			}
		});
		btn[5].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				value[5]++;
				value[5] %= 3;
				if (value[5] == 0) {
					btn[5].setText("0");
				}
				if (value[5] == 1) {
					btn[5].setText("1");
				}
				if (value[5] == 2) {
					btn[5].setText("X");
				}
			}
		});
		btn[6].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				value[6]++;
				value[6] %= 3;
				if (value[6] == 0) {
					btn[6].setText("0");
				}
				if (value[6] == 1) {
					btn[6].setText("1");
				}
				if (value[6] == 2) {
					btn[6].setText("X");
				}
			}
		});
		btn[7].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				value[7]++;
				value[7] %= 3;
				if (value[7] == 0) {
					btn[7].setText("0");
				}
				if (value[7] == 1) {
					btn[7].setText("1");
				}
				if (value[7] == 2) {
					btn[7].setText("X");
				}
			}
		});
		btn[8].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				value[8]++;
				value[8] %= 3;
				if (value[8] == 0) {
					btn[8].setText("0");
				}
				if (value[8] == 1) {
					btn[8].setText("1");
				}
				if (value[8] == 2) {
					btn[8].setText("X");
				}
			}
		});
		btn[9].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				value[9]++;
				value[9] %= 3;
				if (value[9] == 0) {
					btn[9].setText("0");
				}
				if (value[9] == 1) {
					btn[9].setText("1");
				}
				if (value[9] == 2) {
					btn[9].setText("X");
				}
			}
		});
		btn[10].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				value[10]++;
				value[10] %= 3;
				if (value[10] == 0) {
					btn[10].setText("0");
				}
				if (value[10] == 1) {
					btn[10].setText("1");
				}
				if (value[10] == 2) {
					btn[10].setText("X");
				}
			}
		});
		btn[11].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				value[11]++;
				value[11] %= 3;
				if (value[11] == 0) {
					btn[11].setText("0");
				}
				if (value[11] == 1) {
					btn[11].setText("1");
				}
				if (value[11] == 2) {
					btn[11].setText("X");
				}
			}
		});
		btn[12].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				value[12]++;
				value[12] %= 3;
				if (value[12] == 0) {
					btn[12].setText("0");
				}
				if (value[12] == 1) {
					btn[12].setText("1");
				}
				if (value[12] == 2) {
					btn[12].setText("X");
				}
			}
		});
		btn[13].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				value[13]++;
				value[13] %= 3;
				if (value[13] == 0) {
					btn[13].setText("0");
				}
				if (value[13] == 1) {
					btn[13].setText("1");
				}
				if (value[13] == 2) {
					btn[13].setText("X");
				}
			}
		});
		btn[14].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				value[14]++;
				value[14] %= 3;
				if (value[14] == 0) {
					btn[14].setText("0");
				}
				if (value[14] == 1) {
					btn[14].setText("1");
				}
				if (value[14] == 2) {
					btn[14].setText("X");
				}
			}
		});
		btn[15].setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				value[15]++;
				value[15] %= 3;
				if (value[15] == 0) {
					btn[15].setText("0");
				}
				if (value[15] == 1) {
					btn[15].setText("1");
				}
				if (value[15] == 2) {
					btn[15].setText("X");
				}
			}
		});
		// reset
		reset.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				text.setText("");
				output = "";

				for (int i = 0; i < 16; i++) {
					btn[i].setText("X");
					;
					value[i] = 2;
				}

			}
		});

		// compute
		compute.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				newstg.close();
				compute();
				text.setText("");
				text.setText("F=" + output.substring(1));
				logicExpression.getItems().clear();
				logicExpression.getItems().add(text);
				newstg.setScene(newscn);
				newstg.show();
			}
		});

	}

	static void compute() {
		initialize();
		// algo bigins

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (A[i][j] == 1 && checked[i][j] == 0) {
					int pass8 = check8(i, j);
					if (pass8 == 0) {
						int pass4 = check4(i, j);
						if (pass4 == 0) {
							int pass2 = check2(i, j);
							if (pass2 == 0) {
								nogrouping(i, j);
							}
						}
					}
				}
			}
		}

	}

	static void initialize() {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				A[i][j] = value[count];
				checked[i][j] = 0;
				count++;
			}
		}
		checked[3][2] = 1;
		checked[3][1] = 1;
	}

	static int check8(int r, int c) {
		int result = 0;
		// case1
		if (A[r][0] == 1 && A[r][1] == 1 && A[r][2] == 1 && A[r][3] == 1 && A[(r + 1) % 4][0] == 1
				&& A[(r + 1) % 4][1] == 1 && A[(r + 1) % 4][2] == 1 && A[(r + 1) % 4][3] == 1) {
			String local = "";
			if (r == 0) {
				local = "A'";
			}
			if (r == 1) {
				local = "B";
			}
			if (r == 2) {
				local = "A";
			}
			if (r == 3) {
				local = "B'";
			}
			output = output + "+" + local;
			result = 1;
			// make checked
			checked[r][0] = 1;
			checked[r][1] = 1;
			checked[r][2] = 1;
			checked[r][3] = 1;
			checked[(r + 1) % 4][0] = 1;
			checked[(r + 1) % 4][1] = 1;
			checked[(r + 1) % 4][2] = 1;
			checked[(r + 1) % 4][3] = 1;
			// make it color
			// makecolor(r,c);
		} else
		// case2
		if (A[r][0] == 1 && A[r][1] == 1 && A[r][2] == 1 && A[r][3] == 1 && A[(4 + (r - 1)) % 4][0] == 1
				&& A[(4 + (r - 1)) % 4][1] == 1 && A[(4 + (r - 1)) % 4][2] == 1 && A[(4 + (r - 1)) % 4][3] == 1) {
			String local = "";
			if (r == 0) {
				local = "B'";
			}
			if (r == 1) {
				local = "A'";
			}
			if (r == 2) {
				local = "B";
			}
			if (r == 3) {
				local = "A";
			}
			output = output + "+" + local;
			result = 1;
			// make checked
			checked[r][0] = 1;
			checked[r][1] = 1;
			checked[r][2] = 1;
			checked[r][3] = 1;
			checked[(4 + (r - 1)) % 4][0] = 1;
			checked[(4 + (r - 1)) % 4][1] = 1;
			checked[(4 + (r - 1)) % 4][2] = 1;
			checked[(4 + (r - 1)) % 4][3] = 1;
		} else
		// case3
		if (A[0][c] == 1 && A[1][c] == 1 && A[2][c] == 1 && A[3][c] == 1 && A[0][(c + 1) % 4] == 1
				&& A[1][(c + 1) % 4] == 1 && A[2][(c + 1) % 4] == 1 && A[3][(c + 1) % 4] == 1) {
			String local = "";
			if (c == 0) {
				local = "C'";
			}
			if (c == 1) {
				local = "D";
			}
			if (c == 2) {
				local = "C";
			}
			if (c == 3) {
				local = "D'";
			}
			output = output + "+" + local;
			result = 1;
			// make checked
			checked[0][c] = 1;
			checked[1][c] = 1;
			checked[2][c] = 1;
			checked[3][c] = 1;
			checked[0][(c + 1) % 4] = 1;
			checked[1][(c + 1) % 4] = 1;
			checked[2][(c + 1) % 4] = 1;
			checked[3][(c + 1) % 4] = 1;
		} else
		// case 4
		if (A[0][c] == 1 && A[1][c] == 1 && A[2][c] == 1 && A[3][c] == 1 && A[0][(4 + (c - 1)) % 4] == 1
				&& A[1][(4 + (c - 1)) % 4] == 1 && A[2][(4 + (c - 1)) % 4] == 1 && A[3][(4 + (c - 1)) % 4] == 1) {
			String local = "";
			if (c == 0) {
				local = "D'";
			}
			if (c == 1) {
				local = "C'";
			}
			if (c == 2) {
				local = "D";
			}
			if (c == 3) {
				local = "C";
			}
			output = output + "+" + local;
			result = 1;
			// make checked
			checked[0][c] = 1;
			checked[1][c] = 1;
			checked[2][c] = 1;
			checked[3][c] = 1;
			checked[0][(4 + (c - 1)) % 4] = 1;
			checked[1][(4 + (c - 1)) % 4] = 1;
			checked[2][(4 + (c - 1)) % 4] = 1;
			checked[3][(4 + (c - 1)) % 4] = 1;
		}

		return result;
	}

	// ends of check8
	// check4 start
	static int check4(int r, int c) {
		int result = 0;
		String local = "";
		// case1
		if (A[r][0] == 1 && A[r][1] == 1 && A[r][2] == 1 && A[r][3] == 1) {
			if (r == 0) {
				local = "A'B'";
			}
			if (r == 1) {
				local = "A'B";
			}
			if (r == 2) {
				local = "AB";
			}
			if (r == 3) {
				local = "AB'";
			}
			output = output + "+" + local;
			result = 1;
			// make checked
			checked[r][0] = 1;
			checked[r][1] = 1;
			checked[r][2] = 1;
			checked[r][3] = 1;
		} else
		// case2
		if (A[0][c] == 1 && A[1][c] == 1 && A[2][c] == 1 && A[3][c] == 1) {
			if (c == 0) {
				local = "C'D'";
			}
			if (c == 1) {
				local = "C'D";
			}
			if (c == 2) {
				local = "CD";
			}
			if (c == 3) {
				local = "CD'";

			}
			output = output + "+" + local;
			result = 1;
			// make checked
			checked[0][c] = 1;
			checked[1][c] = 1;
			checked[2][c] = 1;
			checked[3][c] = 1;
		} else
		// case3 row+ col+
		if (A[r][c] == 1 && A[r][(c + 1) % 4] == 1 && A[(r + 1) % 4][c] == 1 && A[(r + 1) % 4][(c + 1) % 4] == 1) {
			if (r == 0) {
				local = "A'";
			}
			if (r == 1) {
				local = "B";
			}
			if (r == 2) {
				local = "A";
			}
			if (r == 3) {
				local = "B'";
			}
			if (c == 0) {
				local = local + "C'";
			}
			if (c == 1) {
				local = local + "D";
			}
			if (c == 2) {
				local = local + "C";
			}
			if (c == 3) {
				local = local + "D'";
			}
			output = output + "+" + local;
			result = 1;
			// make checked
			checked[r][c] = 1;
			checked[r][(c + 1) % 4] = 1;
			checked[(r + 1) % 4][c] = 1;
			checked[(r + 1) % 4][(c + 1) % 4] = 1;
		} else
		// case4 row+ col--
		if (A[r][(4 + (c - 1)) % 4] == 1 && A[r][c] == 1 && A[(r + 1) % 4][(4 + (c - 1)) % 4] == 1
				&& A[(r + 1) % 4][c] == 1) {
			if (r == 0) {
				local = "A'";
			}
			if (r == 1) {
				local = "B";
			}
			if (r == 2) {
				local = "A";
			}
			if (r == 3) {
				local = "B'";
			}
			if (c == 0) {
				local = local + "D'";
			}
			if (c == 1) {
				local = local + "C'";
			}
			if (c == 2) {
				local = local + "D";
			}
			if (c == 3) {
				local = local + "C'";
			}
			output = output + "+" + local;
			result = 1;
			// make checked
			checked[r][(4 + (c - 1)) % 4] = 1;
			checked[r][c] = 1;
			checked[(r + 1) % 4][(4 + (c - 1)) % 4] = 1;
			checked[(r + 1) % 4][c] = 1;

		} else
		// case5 row - and col -
		if (A[(4 + (r - 1)) % 4][(4 + (c - 1)) % 4] == 1 && A[(4 + (r - 1)) % 4][c] == 1 && A[r][(4 + (c - 1)) % 4] == 1
				&& A[r][c] == 1) {
			if (r == 0) {
				local = "B'";
			}
			if (r == 1) {
				local = "A'";
			}
			if (r == 2) {
				local = "B";
			}
			if (r == 3) {
				local = "A";
			}
			if (c == 0) {
				local = local + "D'";
			}
			if (c == 1) {
				local = local + "C'";
			}
			if (c == 2) {
				local = local + "D";
			}
			if (c == 3) {
				local = local + "C'";
			}
			output = output + "+" + local;
			result = 1;
			// make checked
			checked[(4 + (r - 1)) % 4][(4 + (c - 1)) % 4] = 1;
			checked[(4 + (r - 1)) % 4][c] = 1;
			checked[r][(4 + (c - 1)) % 4] = 1;
			checked[r][c] = 1;
		} else
		// case6 row- col+
		if (A[(4 + (r - 1)) % 4][c] == 1 && A[(4 + (r - 1)) % 4][(c + 1) % 4] == 1 && A[r][c] == 1
				&& A[r][(c + 1) % 4] == 1) {
			if (r == 0) {
				local = "B'";
			}
			if (r == 1) {
				local = "A'";
			}
			if (r == 2) {
				local = "B";
			}
			if (r == 3) {
				local = "A";
			}
			if (c == 0) {
				local = local + "C'";
			}
			if (c == 1) {
				local = local + "D";
			}
			if (c == 2) {
				local = local + "C";
			}
			if (c == 3) {
				local = local + "D'";
			}
			output = output + "+" + local;
			result = 1;
			// make checked
			checked[(4 + (r - 1)) % 4][c] = 1;
			checked[(4 + (r - 1)) % 4][(c + 1) % 4] = 1;
			checked[r][c] = 1;
			checked[r][(c + 1) % 4] = 1;
		}

		return result;
	}

	// check for 2 check2
	static int check2(int r, int c) {
		int result = 0;
		String local = "";
		// case 1 col++
		if (A[r][c] == 1 && A[r][(c + 1) % 4] == 1) {
			if (r == 0) {
				local = "A'B'";
			}
			if (r == 1) {
				local = "A'B";
			}
			if (r == 2) {
				local = "AB";
			}
			if (r == 3) {
				local = "AB'";
			}
			if (c == 0) {
				local = local + "C'";
			}
			if (c == 1) {
				local = local + "D";
			}
			if (c == 2) {
				local = local + "C";
			}
			if (c == 3) {
				local = local + "D'";
			}
			output = output + "+" + local;
			result = 1;
			// make checked
			checked[r][c] = 1;
			checked[r][(c + 1) % 4] = 1;

		} else
		// case 2 col--
		if (A[r][(4 + (c - 1)) % 4] == 1 && A[r][c] == 1) {
			if (r == 0) {
				local = "A'B'";
			}
			if (r == 1) {
				local = "A'B";
			}
			if (r == 2) {
				local = "AB";
			}
			if (r == 3) {
				local = "AB'";
			}
			if (c == 0) {
				local = local + "D'";
			}
			if (c == 1) {
				local = local + "C'";
			}
			if (c == 2) {
				local = local + "D";
			}
			if (c == 3) {
				local = local + "C";
			}
			output = output + "+" + local;
			result = 1;
			// make checked
			checked[r][(4 + (c - 1)) % 4] = 1;
			checked[r][c] = 1;
		} else
		// case3 row++
		if (A[r][c] == 1 && A[(r + 1) % 4][c] == 1) {
			if (r == 0) {
				local = "A'";
			}
			if (r == 1) {
				local = "B";
			}
			if (r == 2) {
				local = "A";
			}
			if (r == 3) {
				local = "B'";
			}
			if (c == 0) {
				local = local + "C'D'";
			}
			if (c == 1) {
				local = local + "C'D";
			}
			if (c == 2) {
				local = local + "CD";
			}
			if (c == 3) {
				local = local + "CD'";
			}
			output = output + "+" + local;
			result = 1;
			// make checked
			checked[r][c] = 1;
			checked[(r + 1) % 4][c] = 1;
		} else
		// case4 row--
		if (A[r][c] == 1 && A[(4 + (r - 1)) % 4][c] == 1) {
			if (r == 0) {
				local = "B'";
			}
			if (r == 1) {
				local = "A'";
			}
			if (r == 2) {
				local = "B";
			}
			if (r == 3) {
				local = "A";
			}
			if (c == 0) {
				local = local + "C'D'";
			}
			if (c == 1) {
				local = local + "C'D";
			}
			if (c == 2) {
				local = local + "CD";
			}
			if (c == 3) {
				local = local + "CD'";
			}
			output = output + "+" + local;
			result = 1;
			// make checked
			checked[r][c] = 1;
			checked[(4 + (r - 1)) % 4][c] = 1;
		}
		return result;
	}

	// no grouping
	static void nogrouping(int r, int c) {
		String local = "";
		if (r == 0) {
			local = "A'B'";
		}
		if (r == 1) {
			local = "A'B";
		}
		if (r == 2) {
			local = "AB";
		}
		if (r == 3) {
			local = "AB'";
		}
		if (c == 0) {
			local = local + "C'D'";
		}
		if (c == 1) {
			local = local + "C'D";
		}
		if (c == 2) {
			local = local + "CD";
		}
		if (c == 3) {
			local = local + "CD'";
		}
		output = output + "+" + local;

		checked[r][c] = 1;
	}

}
