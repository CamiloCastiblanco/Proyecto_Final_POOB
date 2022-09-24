package presentacion;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import dominio.Tetris.GameArea;
import dominio.Tetris.GameThread;

public class GameOne extends JDialog {

    /*User window sensitive dimension*/
    private static final int WIDTH = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int HEIGTH = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final Dimension DIMENSION = new Dimension((int) (WIDTH/2.8), (int) (HEIGTH/1.2));

    private Color color;

    /*Button*/
    private JButton buttonPause;
    private JButton buttonReanude;

    /*Board*/
    private JPanel board;

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
    
    private GameThread hilo; 

    /*
     * New Typography Elements
     */
    private Font font;

    /*
     * Files elements
     */
    private File file;
    private String path;

    /*
     * POOBTriz elements
     */
    private int width = 205 , height = 400, score, level;
    private GameArea ga;
    private JLabel namePlayer;
    private JLabel levelText;
    private JLabel scoreText;
    private String name;
    private int speed;
    private boolean accelerated ; 
    
    /*Constructor*/
    public GameOne(POOBTrizGUI parent, boolean modal, Color color, String name, int speed, boolean accelerated) throws IOException {
        super(parent, modal);
        this.speed = speed; 
        this.color = color;
        this.name = name;
        this.accelerated = accelerated; 
        prepareElements();
        prepareActions();
    }

    private void prepareElements() throws IOException {
        setLayout(null);
        setTitle("POOBTriz");
        setSize(DIMENSION);
        this.getContentPane().setBackground(this.color);
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


        ga = new dominio.Tetris.GameArea(10, width, height, color);
        initControls();
        ga.setBounds(108, 165, width, height);
        this.add(ga);
        startGame(ga);

        namePlayer = new JLabel(name);
        namePlayer.setBounds(350, 50, 300, 300);
        font = font.deriveFont(30f);
        namePlayer.setFont(font);
        add(namePlayer);

        scoreText = new JLabel("Score: " + ga.getScore());
        scoreText.setBounds(360, 100, 300, 300);
        font = font.deriveFont(30f);
        scoreText.setFont(font);
        add(scoreText);

        levelText = new JLabel("Level: " + ga.getLevel());
        levelText.setBounds(360, 150, 300, 300);
        font = font.deriveFont(30f);
        levelText.setFont(font);
        add(levelText);

        prepareElementsMenu();
        prepareButtons(); 
        boardCreator();
        
        
        
    }

    /**
     *This method draw the image in the new JPanel
     */
    public void boardCreator() {
        board = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                logo(g);
            }
        };
        board.setBounds(0, -20, getWidth(), getHeight());
        board.setBackground(this.color);
        add(board);
    }

    private void logo(Graphics g) {
        Image image = null;
        try {
            image = ImageIO.read(new File("src/presentacion/pictures/player1Board.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

    }

    private void prepareButtons() {
        font = font.deriveFont(30f);
        buttonPause = new JButton("Pause");
        buttonPause.setFont(font);
        buttonPause.setForeground(Color.WHITE);
        buttonPause.setBounds(350, 360, 150, 40);
        buttonPause.setBackground(Color.BLACK);
        buttonPause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                buttonPause.setBackground((Color.LIGHT_GRAY));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                buttonPause.setBackground((Color.BLACK));
            }
        });
        add(buttonPause);
        
        buttonReanude = new JButton("Resume");
        buttonReanude.setFont(font);
        buttonReanude.setForeground(Color.WHITE);
        buttonReanude.setBounds(350, 420, 150, 40);
        buttonReanude.setBackground(Color.BLACK);
        buttonReanude.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
            	buttonReanude.setBackground((Color.LIGHT_GRAY));
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	buttonReanude.setBackground((Color.BLACK));
            }
        });
        add(buttonReanude);
        
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
        prepareActionsButtons();

    }

    private void prepareActionsButtons() {

        buttonPause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionPause();
            }
        });
        
        buttonReanude.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionReanude();
            }
        });

    }
    
    /*Actions Pause*/
    public void actionPause() {
    	pause();

    }

    /*Actions Open*/
    public void actionReanude() {
    	resume(); 
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
    


    public void startGame(dominio.Tetris.GameArea ga){
        hilo = new dominio.Tetris.GameThread(ga , this, accelerated );
        hilo.setPause(speed); 
        hilo.start();
        
    }

    /*
     * This method read keyboard input
     */
    private void initControls(){
        InputMap in = this.getRootPane().getInputMap();
        ActionMap am = this.getRootPane().getActionMap();
        in.put(KeyStroke.getKeyStroke("RIGHT"),"right" );
        in.put(KeyStroke.getKeyStroke("LEFT"),"left" );
        in.put(KeyStroke.getKeyStroke("UP"),"up" );
        in.put(KeyStroke.getKeyStroke("DOWN"),"down" );
        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockRight();
                ga.changeColor();
            }
        });
        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockLeft();
                ga.changeColor();
            }
        });
        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.rotateBlock();
                ga.changeColor();               
            }
        });
        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.dropBlock();
                ga.unlock(); 
            }
        });
    }


    //These 2 methods allow us to update and show data in screen
    public int getScore(){
        return score;
    }
    public int getLevel(){
        return level;
    }


    //These 2 methods allow us to update and show data in screen
    public void updateScore(){
        scoreText.setText("Score: " + ga.getScore());
    }
    public void updateLevel(){
        levelText.setText("Level: " + ga.getLevel());
    }
     
    public void pause() {
    	hilo.suspend();
    }
    public void resume() {
    	hilo.resume(); 
    }
}

