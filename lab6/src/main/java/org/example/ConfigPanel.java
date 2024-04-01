package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * the panel where you can set the size of the board
 */
public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner spinner1, spinner2;
    JButton btn;
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    public void updateGraph(ActionEvent actionEvent) {
        int size1 = (Integer) spinner1.getValue(); // Get the spinner value
        int size2 = (Integer) spinner2.getValue();
        frame.updateGraph(size1, size2);
    }
    private void init() {
        label = new JLabel("Grid size: ");
        spinner1 = new JSpinner(new SpinnerNumberModel(10, 2, 50, 1));
        spinner2 = new JSpinner(new SpinnerNumberModel(10, 2, 50, 1));
        btn = new JButton("Start");
        add(label);
        add(spinner1);
        add(spinner2);
        add(btn);
        btn.addActionListener(this::updateGraph);
    }

}
