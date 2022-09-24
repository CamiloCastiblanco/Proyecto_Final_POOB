package presentacion;


import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class About extends JDialog{

    /*User window sensitive dimension*/
    private static final int WIDTH = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int HEIGTH = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final Dimension DIMENSION = new Dimension((int) (WIDTH - WIDTH / 2.7), (int) (HEIGTH - HEIGTH / 4.5));

    JEditorPane editor = new JEditorPane();

    public About(POOBTrizGUI parent, boolean modal, String ruta){
        super(parent, modal);
        setSize(DIMENSION);
        setTitle("About POOBTriz");
        setLocationRelativeTo(null);

        JEditorPane panelEditor = new JEditorPane();
        panelEditor.setEditable(false);
        try {
            panelEditor.setPage(new URL(ruta));
        } catch (IOException ex) {
            Logger.getLogger(About.class.getName()).log(Level.SEVERE, null, ex);
        }

        JScrollPane editorScrollPane = new JScrollPane(panelEditor);

        this.getContentPane().add(editorScrollPane);
    }



}
