package com.example;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
           MainFrame form = new MainFrame();
            form.setVisible(true);
        });
    }
}
