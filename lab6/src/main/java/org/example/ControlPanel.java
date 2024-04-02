package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * panel containing option s about saving/loading progress, exporting to png or exiting
 */
public class ControlPanel extends JPanel {
    final MainFrame frame;
    JLabel warnLabel = new JLabel("");
    JLabel helpLabel = new JLabel("HELP");
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton exportBtn = new JButton("Export to PNG");
    JButton exitBtn = new JButton("Exit");
    //create all buttons (Load, Exit, etc.)
    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        //change the default layout manager (just for fun)
         setLayout(new GridLayout(4, 1));
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //add all buttons ...TODO
        JPanel buttonPanel1 = new JPanel();
        JPanel buttonPanel2 = new JPanel();
        buttonPanel1.setLayout(new GridLayout(1, 2));
        buttonPanel2.setLayout(new GridLayout(1, 2));

        warnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        warnLabel.setFont(new Font(warnLabel.getFont().getName(), Font.PLAIN, 20));
        helpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(warnLabel);
        add(helpLabel);
        buttonPanel1.add(loadBtn);
        buttonPanel1.add(saveBtn);
        add(buttonPanel1);
        buttonPanel2.add(exportBtn);
        buttonPanel2.add(exitBtn);
        add(buttonPanel2);
        //configure listeners for all buttons
        saveBtn.addActionListener(this::saveStatus);
        loadBtn.addActionListener(this::loadStatus);

        exportBtn.addActionListener(this::exportPNG);
        exitBtn.addActionListener(this::exitGame);
    }
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

    private void exportPNG(ActionEvent e) {
        frame.exportPNG();
    }

    public void setWarn(String warning) {
        warnLabel.setText(warning);
    }

    public void setHelp(String help) {
        helpLabel.setText(help);
    }
    public void saveStatus(ActionEvent e) {
        frame.saveStatus();
    }

    public void loadStatus(ActionEvent e) {
        frame.loadStatus();
    }
}
