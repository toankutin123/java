package com.example;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            tong2so form = new tong2so();
            form.setVisible(true);
        });
    }
}
