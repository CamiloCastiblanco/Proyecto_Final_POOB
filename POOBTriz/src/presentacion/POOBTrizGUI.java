package presentacion;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class POOBTrizGUI extends JFrame {

    /*User window sensitive dimension*/
    private static final int WIDTH = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int HEIGTH = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final Dimension DIMENSION = new Dimension((int) (WIDTH - WIDTH / 2.7), (int) (HEIGTH - HEIGTH / 4.5));

    /*Buttons*/
    private JButton buttonStart;
    private JButton buttonColor;
    private JButton buttonGif;
    private JButton buttonOpen;
    private JButton buttonScoreboard;
    private JButton buttonAbout;

    /*Board*/
    private JPanel title;
    private JPanel button;
    private JPanel content;
    private JColorChooser colorPicker;

    /*Content*/
    private JLabel gameMode;
    private JLabel fieldColor;
    private JComboBox gameModeBox;
    private JLabel numBuffos;
    private JSpinner numBuffosSpinner;
    private JLabel desiredSpeed;
    private JComboBox speedBox;
    private ImageIcon img01;

    /*Players Information*/
    private String player1;
    private String player2;
    private Color colorPlayer1;
    private Color colorPlayer2;
    private String profileIA;
    private int numberBuffos;
    private int speed;

    //Elementos para Nueva Tipografia
    private Font font;

    //Elementos para archivos
    private File file;
    private String path;

    /*Constructor*/
    public POOBTrizGUI() throws IOException {
        super("POOBTriz");
        prepareElements();
        prepareActions();
    }

    private void prepareElements() throws IOException {
        setLayout(null);
        setSize(DIMENSION);
        this.getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("Panel.background", Color.WHITE);
        Image icon = Toolkit.getDefaultToolkit().getImage("src/presentacion/pictures/icono.png");
        setIconImage(icon);

        file = new File("");
        path = file.getAbsolutePath();

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(path + "\\src\\presentacion\\fontType\\PixelMplus12-Regular.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path + "\\src\\presentacion\\fontType\\PixelMplus12-Regular.ttf")));
        } catch (IOException | FontFormatException e) {
            //Doesn't catch anything
        }

        prepareButtons();
        creatorTitle();
        prepareElementsContent();
    }

    private void prepareButtons() {
        font = font.deriveFont(30f);
        buttonStart = new JButton("Start");
        buttonStart.setFont(font);
        buttonStart.setForeground(Color.WHITE);
        buttonStart.setBounds(260, 520, 150, 40);
        buttonStart.setBackground(Color.BLACK);
        buttonStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                buttonStart.setBackground((Color.LIGHT_GRAY));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                buttonStart.setBackground((Color.BLACK));
            }
        });
        add(buttonStart);

        buttonOpen = new JButton("Open");
        buttonOpen.setFont(font);
        buttonOpen.setForeground(Color.WHITE);
        buttonOpen.setBounds(50, 590, 150, 40);
        buttonOpen.setBackground(Color.BLACK);
        buttonOpen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                buttonOpen.setBackground((Color.LIGHT_GRAY));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                buttonOpen.setBackground((Color.BLACK));
            }
        });
        add(buttonOpen);

        buttonScoreboard = new JButton("Scoreboard");
        buttonScoreboard.setFont(font);
        buttonScoreboard.setForeground(Color.WHITE);
        buttonScoreboard.setBounds(235, 590, 200, 40);
        buttonScoreboard.setBackground(Color.BLACK);
        buttonScoreboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                buttonScoreboard.setBackground((Color.LIGHT_GRAY));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                buttonScoreboard.setBackground((Color.BLACK));
            }
        });
        add(buttonScoreboard);

        buttonAbout = new JButton("About");
        buttonAbout.setFont(font);
        buttonAbout.setForeground(Color.WHITE);
        buttonAbout.setBounds(470, 590, 150, 40);
        buttonAbout.setBackground(Color.BLACK);
        buttonAbout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                buttonAbout.setBackground((Color.LIGHT_GRAY));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                buttonAbout.setBackground((Color.BLACK));
            }
        });
        add(buttonAbout);


        font = font.deriveFont(20f);
        buttonColor = new JButton("Select color");
        buttonColor.setFont(font);
        buttonColor.setForeground(Color.WHITE);
        buttonColor.setBounds(380, 300, 250, 35);
        buttonColor.setBackground(Color.BLACK);
        buttonColor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                buttonColor.setBackground((Color.LIGHT_GRAY));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                buttonColor.setBackground((Color.BLACK));
            }
        });
        add(buttonColor);

        img01 = new ImageIcon(path + "\\src\\presentacion\\pictures\\banner.gif");
        Image scale = img01.getImage().getScaledInstance(320, 640, Image.SCALE_DEFAULT);
        img01 = new ImageIcon(scale);

        buttonGif = new JButton();
        buttonGif.setIcon(img01);
        buttonGif.setBounds(665, 9, 320, 640);
        buttonGif.setBackground(Color.black);
        add(buttonGif);
    }

    /**
     *
     */
    public void creatorTitle() {
        title = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                logo(g);
            }
        };
        title.setBounds(-20, 10, 710, 150);
        add(title);
    }

    private void logo(Graphics g) {
        Image image = null;
        try {
            image = ImageIO.read(new File("src/presentacion/pictures/POOBTriz.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image, 0, 0, 710, 150, this);

    }

    private void prepareElementsContent() {
        content = new JPanel();
        // Game mode
        gameMode = new JLabel("Game mode");
        JLabelChangeColor(gameMode);
        gameMode.setBounds(80, 220, 300, 45);
        add(gameMode);

        gameModeBox = new JComboBox();
        gameModeBox.setBounds(380, 230, 250, 35);
        gameModeBox.setBackground(Color.BLACK);
        font = font.deriveFont(20f);
        gameModeBox.setFont(font);
        gameModeBox.setForeground(Color.WHITE);
        gameModeBox.addItem("");
        gameModeBox.addItem("Single mode");
        gameModeBox.addItem("Player vs Player");
        gameModeBox.addItem("Player vs IA");
        add(gameModeBox);

        // Playing field color
        fieldColor = new JLabel("Playing field color");
        JLabelChangeColor(fieldColor);
        fieldColor.setBounds(80, 290, 300, 45);
        add(fieldColor);


        // Number of Buffos
        numBuffos = new JLabel("Number of Buffos");
        JLabelChangeColor(numBuffos);
        numBuffos.setBounds(80, 360, 300, 45);
        add(numBuffos);

        SpinnerModel sm = new SpinnerNumberModel(0, 0, 100, 1);
        numBuffosSpinner = new JSpinner(sm);
        font = font.deriveFont(20f);
        numBuffosSpinner.setFont(font);
        numBuffosSpinner.getEditor().getComponent(0).setForeground(Color.WHITE);
        numBuffosSpinner.getEditor().getComponent(0).setBackground(Color.BLACK);
        numBuffosSpinner.setBounds(380, 370, 250, 35);
        add(numBuffosSpinner);

        // Desired speed
        desiredSpeed = new JLabel("Desired speed");
        JLabelChangeColor(desiredSpeed);
        desiredSpeed.setBounds(80, 430, 300, 45);
        add(desiredSpeed);

        speedBox = new JComboBox();
        speedBox.setBounds(380, 440, 250, 35);
        speedBox.setBackground(Color.BLACK);
        font = font.deriveFont(20f);
        speedBox.setFont(font);
        speedBox.setForeground(Color.WHITE);
        speedBox.addItem("");
        speedBox.addItem("Uniform");
        speedBox.addItem("Accelerated");
        add(speedBox);

    }

    private void JLabelChangeColor(JLabel item){
        font = font.deriveFont(30f);
        item.setFont(font);
        item.setForeground(Color.WHITE);
    }

    private void prepareActions() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                confirmExit();
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });

        prepareActionsButtons();
        prepareActionsContent();

    }

    private void prepareActionsButtons() {

        buttonStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionStart(evt);
            }
        });

        buttonColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionSelectColor(evt);
            }
        });

        buttonOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionOpen(evt);
            }
        });

        buttonScoreboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionScoreboard(evt);
            }
        });

        buttonAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionAbout(evt);
            }
        });

    }

    /*Actions Start*/
    public void actionStart(ActionEvent evt){
        if (evt.getSource() == buttonStart) {
        	boolean accelerated; 
        	if(speedBox.getSelectedItem().equals("Accelerated")) {
        		accelerated = true; 
        		speed = 1; 
        	}else {
        		accelerated = false;
        	}  
        	
        	
            if(!(Objects.isNull(player2))) {
                GameTwo game = null;
                try {   
                	if(player1.equals("")) {
                		player1 = "Player1";
                	}
                	if(player2.equals("")) {
                		player2 = "Player2";
                	}
                	game = new GameTwo(colorPlayer1, colorPlayer2, player1, player2, speed * 1000 ,accelerated);
                	
                } catch (IOException e) {
                    e.printStackTrace();
                }
                game.setVisible(true);
                setVisible(false);
            } else {
                GameOne game = null;
                try {
                	if(player1.equals("")) {
                		player1 = "Player1";
                	}
                    game = new GameOne(this, true, colorPlayer1, player1, speed * 1000,accelerated );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                game.setVisible(true);
                setVisible(false);
            }
        }
    }

    /*Actions Playing field color*/
    public void actionSelectColor(ActionEvent evt) {
        if (evt.getSource() == buttonColor) {
            colorPicker = new JColorChooser(Color.BLACK);
            colorPicker.setBackground(Color.CYAN);
            if (!(Objects.isNull(player2))) {
                colorPlayer1 = colorPicker.showDialog(null, "Select a color Player1", Color.BLACK);
                colorPlayer2 = colorPicker.showDialog(null, "Select a color Player2", Color.BLACK);
            } else {
                colorPlayer1 = colorPicker.showDialog(null, "Select a color", Color.BLACK);
            }

            //System.out.println(colorPlayer1);
            //System.out.println(colorPlayer2);
        }
    }

    /*Actions Open*/
    public void actionOpen(ActionEvent evt) {
        if (evt.getSource() == buttonColor) {

        }
    }

    /*Actions Scoreboard*/
    public void actionScoreboard(ActionEvent evt) {
        if (evt.getSource() == buttonColor) {

        }
    }

    /*Actions About*/
    public void actionAbout(ActionEvent evt) {
        if (evt.getSource() == buttonAbout) {
            File paginaHTML = new File("src/presentacion/html/AboutPOOBTriz.html");
            String urlDocumento = "file://localhost/"+paginaHTML.getAbsolutePath();
            About miAbout = new About(this, true, urlDocumento);
            miAbout.setVisible(true);
        }
    }

    /*Confirm Exit*/
    public void confirmExit() {
        ImageIcon icon = new ImageIcon("src/presentacion/pictures/faceExit.png");
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(50, 50, 50);
        icon = new ImageIcon(newimg);

        JLabel title = new JLabel("Do you want to hang out?");
        font = font.deriveFont(18f);
        title.setFont(font);

        int valor = JOptionPane.showConfirmDialog(this, title,
                "WARNING", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, icon);
        if (valor == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else { }
    }


    private void prepareActionsContent() {

        gameModeBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == gameModeBox) {
                    String selected = (String) gameModeBox.getSelectedItem();
                    if (selected.equals("Single mode")) {
                        player2 = null;
                        profileIA = null;
                        colorPlayer2 = null;
                        actionSingleMode();
                    } else if (selected.equals("Player vs Player")) {
                        profileIA = null;
                        actionPlayerVsPlayer();
                    } else if (selected.equals("Player vs IA")) {
                        player2 = null;
                        colorPlayer2 = null;
                        actionPlayerVsIA();
                    }
                }
            }
        });

        numBuffosSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                actionNumBuffos();
            }
        });

        speedBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionSpeedBox(evt);
            }
        });

    }

    /*Actions Single Mode*/
    public void actionSingleMode() {
        ImageIcon icon = new ImageIcon("src/presentacion/pictures/player1.png");
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(50, 50, 50);
        icon = new ImageIcon(newimg);

        JLabel title = new JLabel("Choose your username");
        font = font.deriveFont(18f);
        title.setFont(font);
        JTextField player1Text = new JTextField();
        player1Text.setFont(font);
        final JComponent[] inputs = new JComponent[] {title, player1Text};

        int reply = JOptionPane.showConfirmDialog(null, inputs, "Single mode",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);

        if (reply == JOptionPane.OK_OPTION) {
            player1 = player1Text.getText();
        }

    }

    /*Actions Player vs Player*/
    public void actionPlayerVsPlayer() {
        ImageIcon icon = new ImageIcon("src/presentacion/pictures/player2.png");
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(50, 50, 50);
        icon = new ImageIcon(newimg);

        JLabel title = new JLabel("Choose your username");
        font = font.deriveFont(18f);
        title.setFont(font);
        JTextField player1Text = new JTextField();
        JTextField player2Text = new JTextField();
        player1Text.setFont(font);
        player2Text.setFont(font);
        JLabel playerOne = new JLabel("Player one");
        JLabel playerTwo = new JLabel("Player two");
        playerOne.setFont(font);
        playerTwo.setFont(font);
        final JComponent[] inputs = new JComponent[] {title, playerOne, player1Text, playerTwo, player2Text};

        int reply = JOptionPane.showConfirmDialog(null, inputs, "Player vs Player",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);

        if (reply == JOptionPane.OK_OPTION) {
            player1 = player1Text.getText();
            player2 = player2Text.getText();
        }

    }

    /*Actions Player vs IA*/
    public void actionPlayerVsIA() {
        // IA
        ImageIcon icon = new ImageIcon("src/presentacion/pictures/IA.png");
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(50, 50, 50);
        icon = new ImageIcon(newimg);
        //
        JLabel title = new JLabel("Pick and select");
        font = font.deriveFont(18f);
        title.setFont(font);
        JTextField player1Text = new JTextField();
        JComboBox playerIABox = new JComboBox();
        player1Text.setFont(font);
        playerIABox.setFont(font);
        playerIABox.addItem("");
        playerIABox.addItem("Beginner");
        playerIABox.addItem("Expert");
        JLabel playerOne = new JLabel("Player");
        JLabel playerTwo = new JLabel("AI profile");
        playerOne.setFont(font);
        playerTwo.setFont(font);
        final JComponent[] inputs = new JComponent[] {title, playerOne, player1Text, playerTwo, playerIABox};
        //
        int reply = JOptionPane.showConfirmDialog(null, inputs, "Player vs IA", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, icon);

        if (reply == JOptionPane.OK_OPTION) {
            player1 = player1Text.getText();
            profileIA = (String) playerIABox.getSelectedItem();
        }

    }

    /*Actions Number Buffos*/
    public void actionNumBuffos() {
        String nBuffos = numBuffosSpinner.getValue().toString();
        numberBuffos = Integer.parseInt(nBuffos);
    }

    /*Actions Desired speed*/
    public void actionSpeedBox(ActionEvent evt) {
        if (evt.getSource() == speedBox) {
            String selected = (String) speedBox.getSelectedItem();
            if(selected.equals("Uniform")){
                SpinnerModel speedModel = new SpinnerNumberModel(0, 0, 100, 1);
                JSpinner desiredSpeedSpinner = new JSpinner(speedModel);
                font = font.deriveFont(20f);
                desiredSpeedSpinner.setFont(font);

                ImageIcon icon = new ImageIcon("src/presentacion/pictures/speed.png");
                Image image = icon.getImage();
                Image newimg = image.getScaledInstance(50, 50, 50);
                icon = new ImageIcon(newimg);
                
                
                JLabel title = new JLabel("Desired speed");
                font = font.deriveFont(18f);
                title.setFont(font);
                final JComponent[] inputs = new JComponent[] {title, desiredSpeedSpinner};
                int reply = JOptionPane.showConfirmDialog(null, inputs, "Player vs IA", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, icon);

                if (reply == JOptionPane.OK_OPTION) {
                    String nSpeed = numBuffosSpinner.getValue().toString();
                    speed = Integer.parseInt(nSpeed);
                }
                
            }
        }
    }
    

    
    /*Principal function*/
    public static void main(String[] args) throws IOException {
        POOBTrizGUI gui = new POOBTrizGUI();
        gui.setVisible(true);
    }
    
    
}
