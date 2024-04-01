package org.example;

import javax.swing.*;
import java.awt.*;

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
    public void updateGraph(int size) {
        canvas.init(size, size);
        canvas.revalidate(); // This ensures the layout manager is aware of the size change
        canvas.repaint();
    }
}
