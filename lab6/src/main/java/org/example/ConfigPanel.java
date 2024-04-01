package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner spinner;
    JButton btn;
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    public void updateGraph(ActionEvent actionEvent) {
        int size = (Integer) spinner.getValue(); // Get the spinner value
        frame.updateGraph(size);
    }
    private void init() {
        label = new JLabel("Grid size: ");
        spinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        btn = new JButton("Start");
        add(label);
        add(spinner);
        add(btn);
        btn.addActionListener(this::updateGraph);
    }

}
