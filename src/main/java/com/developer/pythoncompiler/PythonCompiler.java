package com.developer.pythoncompiler;

import GUI.MainFrame;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

public class PythonCompiler {

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        new MainFrame().setVisible(true);
    }
}
