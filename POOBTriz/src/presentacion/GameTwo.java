package presentacion;

import java.awt.*;

import dominio.Tetris.GameArea;
import dominio.Tetris.GameThread;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class GameTwo extends JFrame {

    /*User window sensitive dimension*/
    private static final int WIDTH = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int HEIGTH = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final Dimension DIMENSION = new Dimension(((int) (WIDTH/2.8)*2)-8, (int) (HEIGTH/1.2));

    private Color colorPlayer1;
    private Color colorPlayer2;

    /*Starts*/
    private JButton botonStart;

    /*Board*/
    private JPanel boardPlayer1;
    private JPanel boardPlayer2;

    /*Menu*/
    private JMenuBar bar;
    private JMenu menuFile;
    private JMenu menuAbout;
    private JMenuItem menuItemNew;
    private JMenuItem menuItemOpen;
    private JMenuItem menuItemSave;
    private JMenuItem menuItemImport;
    private JMenuItem menuItemExport;
    private JMenuItem menuItemExit;

    /*
     * New Typography Elements
     */
    private Font font;

    //Files elements
    private File file;
    private String path;

    //POOBTriz elements
    private int width = 205 , height = 400, score1, level1, score2, level2;
    private GameArea ga1;
    private GameArea ga2;
    private JLabel namePlayer1;
    private JLabel levelText1;
    private JLabel scoreText1;
    private JLabel namePlayer2;
    private JLabel levelText2;
    private JLabel scoreText2;
    private String namedPlayer1; 
    private String namedPlayer2;
    private int speed; 
    private boolean accelerated; 
    
    private GameThread hilo1 , hilo2 ; 
    
    /*Constructor*/
    public GameTwo(Color colorPlayer1, Color colorPlayer2, String namedPlayer1, String namedPlayer2, int speed , boolean accelerated) throws IOException {
    	
        super("POOBTriz");
        this.accelerated = accelerated; 
        this.speed = speed; 
        this.colorPlayer1 = colorPlayer1;
        this.colorPlayer2 = colorPlayer2;
        this.namedPlayer1 = namedPlayer1;
        this.namedPlayer2 = namedPlayer2;
        prepareElements();
        prepareActions();
    }

    private void prepareElements() throws IOException {
        setLayout(null);
        setSize(DIMENSION);
        this.getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("Panel.background", Color.WHITE);

        file = new File("");
        path = file.getAbsolutePath();

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(path + "\\src\\presentacion\\fontType\\PixelMplus12-Regular.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path + "\\src\\presentacion\\fontType\\PixelMplus12-Regular.ttf")));
        } catch (IOException | FontFormatException e) {
            //Doesn't catch anything
        }

        ga1 = new dominio.Tetris.GameArea(10, width, height, colorPlayer1);
        initControls1();
        ga1.setBounds(105, 170, width, height);
        this.add(ga1);
        startGame1(ga1);

        ga2 = new dominio.Tetris.GameArea(10, width, height, colorPlayer2);
        initControls2();
        ga2.setBounds(655, 170, width, height);
        this.add(ga2);
        startGame2(ga2);
        
        //Player1
        namePlayer1 = new JLabel(namedPlayer1);
        namePlayer1.setBounds(370, 50, 300, 300);
        font = font.deriveFont(30f);
        namePlayer1.setFont(font);
        add(namePlayer1);

        scoreText1 = new JLabel("Score: " + ga1.getScore());
        scoreText1.setBounds(380, 100, 300, 300);
        font = font.deriveFont(30f);
        scoreText1.setFont(font);
        add(scoreText1);

        levelText1 = new JLabel("Level: " + ga1.getLevel());
        levelText1.setBounds(380, 150, 300, 300);
        font = font.deriveFont(30f);
        levelText1.setFont(font);
        add(levelText1);
        
      //Player2
        namePlayer2 = new JLabel(namedPlayer2);
        namePlayer2.setBounds(910, 50, 300, 300);
        font = font.deriveFont(30f);
        namePlayer2.setFont(font);
        add(namePlayer2);

        scoreText2 = new JLabel("Score: " + ga2.getScore());
        scoreText2.setBounds(920, 100, 300, 300);
        font = font.deriveFont(30f);
        scoreText2.setFont(font);
        add(scoreText2);

        levelText2 = new JLabel("Level: " + ga2.getLevel());
        levelText2.setBounds(920, 150, 300, 300);
        font = font.deriveFont(30f);
        levelText2.setFont(font);
        add(levelText2);


        prepareElementsMenu();
        boardCreator();
        //prepareButtons();
    }

    /**
     * This method draw the image in the new JPanel
     */
    public void boardCreator() {
        boardPlayer1 = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                logoPlayer1(g);
            }
        };
        
        boardPlayer2 = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                logoPlayer2(g);
            }
        };
        
        boardPlayer1.setBounds(0, -10, getWidth()/2, getHeight());
        boardPlayer1.setBackground(colorPlayer1);
        add(boardPlayer1);
        
        boardPlayer2.setBounds((getWidth()/2)-18, -10, getWidth()/2, getHeight());
        boardPlayer2.setBackground(colorPlayer2);
        add(boardPlayer2);
    }

    private void logoPlayer1(Graphics g) {
        Image image1 = null;
        try {
            image1 = ImageIO.read(new File("src/presentacion/pictures/player1Board.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image1, 0, -5, getWidth()/2, getHeight(), this);

    }
    
    private void logoPlayer2(Graphics g) {
        Image image2 = null;
        try {
            image2 = ImageIO.read(new File("src/presentacion/pictures/player1Board.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image2, 0, -5, getWidth()/2, getHeight(), this);

    }

    private void prepareButtons() {
        setLayout(null);
        botonStart = new JButton("Start");
        botonStart.setBounds(300, 250, 100, 30);
        add(botonStart);
    }

    /**
     * Prepare the items for the menu
     */
    private void prepareElementsMenu() {

        UIManager.put("MenuBar.background", Color.BLACK);
        UIManager.put("Menu.background", Color.BLACK);
        UIManager.put("MenuItem.background", Color.BLACK);

        bar = new JMenuBar();
        menuFile = new JMenu("File");
        font = font.deriveFont(18f);
        menuFile.setFont(font);
        menuFile.setForeground(Color.WHITE);
        menuAbout = new JMenu("About");
        menuAbout.setFont(font);
        menuAbout.setForeground(Color.WHITE);
        menuItemNew = new JMenuItem("New");
        menuItemOpen = new JMenuItem("Open");
        menuItemSave = new JMenuItem("Save");
        menuItemImport = new JMenuItem("Import");
        menuItemExport = new JMenuItem("Export");
        menuItemExit = new JMenuItem("Exit");

        menuItemChangeColor(menuItemNew);
        menuItemChangeColor(menuItemOpen);
        menuItemChangeColor(menuItemSave);
        menuItemChangeColor(menuItemImport);
        menuItemChangeColor(menuItemExport);
        menuItemChangeColor(menuItemExit);

        menuFile.add(menuItemNew);
        menuFile.addSeparator();
        menuFile.add(menuItemOpen);
        menuFile.add(menuItemSave);
        menuFile.addSeparator();
        menuFile.add(menuItemImport);
        menuFile.add(menuItemExport);
        menuFile.addSeparator();
        menuFile.add(menuItemExit);
        bar.add(menuFile);
        bar.add(menuAbout);
        setJMenuBar(bar);

    }

    private void menuItemChangeColor(JMenuItem item){
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

        prepareActionsMenu();

    }

    private void prepareActionsButtons() {

        botonStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == botonStart) {
                    System.exit(0);
                }
            }
        });

    }

    private void prepareActionsMenu() {

        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionExit();
            }
        });

        menuItemSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionSave();
            }
        });

        menuItemNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionNew();
            }
        });

        menuItemOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionOpen();
            }
        });

        menuItemImport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionImport();
            }
        });

        menuItemExport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionExport();
            }
        });

    }

    /*Actions Exit*/
    public void actionExit() {
        confirmExit();
    }

    /*Actions Save*/
    public void actionSave() {

    }

    /*Actions New*/
    public void actionNew() {

    }

    /*Actions Open*/
    public void actionOpen() {

    }

    /*Actions Import*/
    public void actionImport() {

    }

    /*Actions Export*/
    public void actionExport() {

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

    

    public void startGame1(dominio.Tetris.GameArea ga){
    	hilo1 = new dominio.Tetris.GameThread(ga , this, accelerated); 
    	hilo1.setPause(speed);
    	hilo1.start();
    	 
    }
    public void startGame2(dominio.Tetris.GameArea ga){
    	hilo2 = new dominio.Tetris.GameThread(ga , this, accelerated); 
    	hilo2.setPause(speed);
    	hilo2.start();
    	 
    }

    /*
     * This method read keyboard input for player 1
     */
    private void initControls1(){
    	InputMap in = this.getRootPane().getInputMap();
        ActionMap am = this.getRootPane().getActionMap();
        in.put(KeyStroke.getKeyStroke("A"),"a" );
        in.put(KeyStroke.getKeyStroke("W"),"w" );
        in.put(KeyStroke.getKeyStroke("S"),"s" );
        in.put(KeyStroke.getKeyStroke("D"),"d" );
        am.put("d", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga1.moveBlockRight();
                ga1.changeColor();
            }
        });
        am.put("a", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga1.moveBlockLeft();
                ga1.changeColor();
            }
        });
        am.put("w", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga1.rotateBlock();
                ga1.changeColor();
            }
        });
        am.put("s", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga1.dropBlock();
                ga1.unlock(); 
                
            }
        });
    }

    /*
     * These 2 methods allow us to update and show player 1 data in screen
     */
    public int getScore1(){
        return score1;
    }
    public int getLevel1(){
        return level1;
    }

    /*
     * These 2 methods allow us to update and show player 1 data in screen
     */
    public void updateScore1(){
        scoreText1.setText("Score: " + ga1.getScore());
    }
    public void updateLevel1(){

        levelText1.setText("Level: " + ga1.getLevel());
    }
    

    /*
     * This method read keyboard input for player 2
     */
    private void initControls2(){
        InputMap in = this.getRootPane().getInputMap();
        ActionMap am = this.getRootPane().getActionMap();
        in.put(KeyStroke.getKeyStroke("RIGHT"),"right" );
        in.put(KeyStroke.getKeyStroke("LEFT"),"left" );
        in.put(KeyStroke.getKeyStroke("UP"),"up" );
        in.put(KeyStroke.getKeyStroke("DOWN"),"down" );
        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga2.moveBlockRight();
                ga2.changeColor();
            }
        });
        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga2.moveBlockLeft();
                ga2.changeColor();
            }
        });
        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga2.rotateBlock();
                ga2.changeColor();               
            }
        });
        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga2.dropBlock();
                ga2.unlock(); 
                
            }
        });
    }
    
    /*
     * These 2 methods allow us to update and show player 1 data in screen
     */
    public int getScore2(){
        return score2;
    }
    public int getLevel2(){
        return level2;
    }

    /*
     * These 2 methods allow us to update and show player 1 data in screen
     */
    public void updateScore2(){

        scoreText2.setText("Score: " + ga2.getScore());
    }
    public void updateLevel2( ){

        levelText2.setText("Level: " + ga2.getLevel());
    }


}
