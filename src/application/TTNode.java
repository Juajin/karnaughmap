package application;

import javafx.beans.property.SimpleStringProperty;

public class TTNode {
	private final SimpleStringProperty inputA;
	private final SimpleStringProperty inputB;
	private final SimpleStringProperty inputC;
	private final SimpleStringProperty inputD;
	private final SimpleStringProperty outputF;

	public TTNode(String inputA, String inputB, String inputC, String inputD, String outputF) {
		super();
		this.inputA = new SimpleStringProperty(inputA);
		this.inputB = new SimpleStringProperty(inputB);
		this.inputC = new SimpleStringProperty(inputC);
		this.inputD = new SimpleStringProperty(inputD);
		this.outputF = new SimpleStringProperty(outputF);
	}

	public TTNode(String inputA, String inputB, String inputC, String inputD) {
		super();
		this.inputA = new SimpleStringProperty(inputA);
		this.inputB = new SimpleStringProperty(inputB);
		this.inputC = new SimpleStringProperty(inputC);
		this.inputD = new SimpleStringProperty(inputD);
		this.outputF = new SimpleStringProperty(null);
	}

	public void removeInputs() {
		this.inputA.set("X");
		this.inputB.set("X");
		this.inputC.set("X");
		this.inputD.set("X");
	}

	public String getInputA() {
		return inputA.get();
	}

	public String getInputB() {
		return inputB.get();
	}

	public String getInputC() {
		return inputC.get();
	}

	public String getInputD() {
		return inputD.get();
	}

	public String getOutputF() {
		return outputF.get();
	}

	public String getInput(int a) {
		if (a == 0) {
			return inputA.get();
		} else if (a == 1) {
			return inputB.get();
		} else if (a == 2) {
			return inputC.get();
		} else {
			return inputD.get();
		}
	}
}
