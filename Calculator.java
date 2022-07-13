import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.xml.parsers.FactoryConfigurationError;

public class Calculator implements IFrameBuilder{
	JFrame appFrame;
	JPanel buttonPanel;
	JPanel displayPanel;
	JTextArea txtArea;

	public Calculator() {
		buildAppFrame();
	}

	@Override
	public JPanel buildButtonPanel() {
		buttonPanel = new JPanel(new GridLayout(0,3));
		ArrayList<String> buttonLabels = getButtonLabels();

		for(int i = 0; i < 15; i++) {
			String bLabel = buttonLabels.get(i);
			JButton newButton = new JButton(bLabel);


			// -- the button is clear (C) --
			if(i == 0 ) {
				newButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						txtArea.setText("");
						}
					});
			}

			// -- the button is an operation
			else if(i > 0 && i < 5) {
				newButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						txtArea.setText(txtArea.getText() + " " + bLabel + " ");
						}
					});
			}

			// -- the button is "equals" --
			else if(i == 5) {
				newButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String[] line = txtArea.getText().split(" ");
						
						ICompute op = getFunction(line[1]);
						double outcome = op.compute(Double.parseDouble(line[0]), Double.parseDouble(line[2]));
						txtArea.setText("" + outcome);
						}
					});
			}

			// -- the button is a number button --
			else {
				newButton.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {
			            txtArea.setText(txtArea.getText() + bLabel );
			         }
			      });
			}

			buttonPanel.add(newButton);
		}

		return buttonPanel;
	}

	@Override
	public JPanel buildDisplayPanel() {
		displayPanel = new JPanel();
		txtArea = new JTextArea();

		displayPanel.add(txtArea);

		return displayPanel;
	}

	@Override
	public JFrame buildAppFrame() {
		appFrame = new JFrame();
		appFrame.add(buildDisplayPanel(),"North");
		appFrame.add(buildButtonPanel());

		appFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		appFrame.setPreferredSize(new Dimension(250, 300));
		appFrame.pack();
		appFrame.setVisible(true);

		return appFrame;
	}

	public ICompute getFunction(String str) {
		ICompute function;
		FunctionFactory factory = new FunctionFactory();

		if(str.equals("+"))
			function = factory.new plus();
		else if(str.equals("-"))
			function = factory.new subtract();
		else if(str.equals("x")) {
			function = factory.new multiply();
		}
		else
			function = factory.new divide();

		return function;
	}

	public ArrayList<String> getButtonLabels(){
		ArrayList<String> buttonLabels = new ArrayList<String>();

		buttonLabels.add("C"); //clear button
		buttonLabels.add("%"); //index 1
		buttonLabels.add("x");
		buttonLabels.add("-");
		buttonLabels.add("+");
		buttonLabels.add("="); //index 5
		buttonLabels.add("1");
		buttonLabels.add("2");
		buttonLabels.add("3");
		buttonLabels.add("4");
		buttonLabels.add("5"); //index 10
		buttonLabels.add("6");
		buttonLabels.add("7");
		buttonLabels.add("8");
		buttonLabels.add("9"); //index 14

		return buttonLabels;
	}
}
