package gui_swing_events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingEventWindow extends JFrame implements ActionListener {

    // Declare the checked radio button variable flag:
    private String selectedOperation = "sum";

    // Create JPanel main container and sub container objects:
    JPanel mainPanel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();
    JPanel panel6 = new JPanel();

    // Create JComponents Items:
    JLabel lblMain = new JLabel("Excel Functions");
    JLabel lblRequest = new JLabel("Enter your numbers separated by spaces");
    JTextField txtNum = new JTextField(20);
    JRadioButton rdoSum = new JRadioButton("Auto Sum", true);
    JRadioButton rdoAverage = new JRadioButton("Average");
    JRadioButton rdoMaximum = new JRadioButton("Maximum");
    JRadioButton rdoMinimum = new JRadioButton("Minimum");
    ButtonGroup radioGroup = new ButtonGroup();
    JButton calculate = new JButton("Calculate");
    JLabel lblResult = new JLabel("Result: ");
    JTextField txtResult = new JTextField(20);

    // Class Constructor:
    public SwingEventWindow() {
        super("Excel Functions GUI"); // Setting Windows Title
        setSize(400, 300); // Setting Window Size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Setting the default operation

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));


        // Setting action commands for radio buttons
        rdoSum.setActionCommand("sum");
        rdoAverage.setActionCommand("average");
        rdoMaximum.setActionCommand("max");
        rdoMinimum.setActionCommand("min");

        // Just group the four related JRadioButton components:
        radioGroup.add(rdoSum);
        radioGroup.add(rdoAverage);
        radioGroup.add(rdoMaximum);
        radioGroup.add(rdoMinimum);

        // Adding the components to their panels:
        panel1.add(lblMain);
        panel2.add(lblRequest);
        panel3.add(txtNum);
        panel4.setLayout(new GridLayout(1, 4)); // GridLayout for radio buttons
        panel4.add(rdoSum);
        panel4.add(rdoAverage);
        panel4.add(rdoMaximum);
        panel4.add(rdoMinimum);
        panel5.add(calculate);
        panel6.setLayout(new GridLayout(1, 2)); // GridLayout for result
        panel6.add(lblResult);
        panel6.add(txtResult);

        // Adding all the sub panels to the main panel:
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(panel4);
        mainPanel.add(panel5);
        mainPanel.add(panel6);

        // Adding/attaching the required Events to some components:
        rdoSum.addActionListener(this);
        rdoAverage.addActionListener(this);
        rdoMaximum.addActionListener(this);
        rdoMinimum.addActionListener(this);
        calculate.addActionListener(this);

        // Adding the main panel to the JFrame:
        add(mainPanel);
        setVisible(true); // Set the Window visibility
    }

    // Implementing the event-handler methods
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JRadioButton) {
            selectedOperation = ((JRadioButton) source).getActionCommand();
        } else if (source == calculate) {
            String input = txtNum.getText().trim();

            // Check for invalid input
            if (!isValidInput(input)) {
                txtResult.setText("Error: Invalid input");
                return;
            }

            Excel excel = new Excel(input);

            double result = 0;
            switch (selectedOperation) {
                case "sum":
                    result = excel.findTotal();
                    break;
                case "average":
                    result = excel.findAverage();
                    break;
                case "max":
                    result = excel.findMax();
                    break;
                case "min":
                    result = excel.findMin();
                    break;
            }
            txtResult.setText(String.valueOf(result));
        }
    }



    private boolean isValidInput(String input) {
        if (input.isEmpty()) {
            return false;
        }

        String[] parts = input.split("\\s+");
        for (String part : parts) {
            try {
                Double.parseDouble(part);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }


}
