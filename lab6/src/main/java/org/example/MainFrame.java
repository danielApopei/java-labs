package org.example;

import javax.swing.*;
import java.awt.*;

/**
 * the whole window of the app
 */
public class MainFrame extends JFrame {

    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;
    public MainFrame() {
        super("Positional Game");
        init();
    }
    void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        configPanel = new ConfigPanel(this);
        add(configPanel, BorderLayout.NORTH);
        controlPanel = new ControlPanel(this);
        add(controlPanel, BorderLayout.SOUTH);
        canvas = new DrawingPanel(this);
        canvas.init(10,10);
        add(canvas, BorderLayout.CENTER);
        pack();
    }
    public void updateGraph(int size1, int size2) {
        canvas.deleteStones();
        canvas.init(size1, size2);
        canvas.revalidate(); // this ensures the layout manager is aware of the size change
        canvas.repaint();
    }

    public void setWarn(String warning) {
        controlPanel.setWarn(warning);
    }

    public void setHelp(String help) {
        controlPanel.setHelp(help);
    }
    public void exportPNG() {
        canvas.exportPNG();
    }
}
