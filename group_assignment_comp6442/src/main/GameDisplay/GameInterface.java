package main.GameDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameInterface {

    String lineBreaker = "\r\n";
    String inputText;

    JFrame window;
    //create panels, and other elements
    JPanel gameTitlePanel, startButtonPanel, informationPanel, textInputPanel,mainTextPanel;
    JLabel titleNameLabel, locationLabel, locationNameLabel;
    JTextArea textOutputArea;
    JButton startButton;
    JTextField inputTextField;

    //create action listener

    GameActionListener gal = new GameActionListener();

    Font largeFont = new Font("Times New Roman", Font.PLAIN,75);
    Font normalFont = new Font("Times New Roman",Font.PLAIN,20);

    public GameInterface() {
        //initialize the game
        initialize();
    }

    /**
     * initialize the game
     */
    public void initialize(){
        createInterface();
        //At initialization, only show title screen and hide game screen
        gameTitlePanel.setVisible(true);
        startButtonPanel.setVisible(true);
        informationPanel.setVisible(false);
        mainTextPanel.setVisible(false);
        textInputPanel.setVisible(false);
    }


    public  void createInterface(){

        //create a window
        window = new JFrame();
        window.setTitle("Pokemon World");
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setLayout(null);


        //create title at game initialization
        gameTitlePanel = new JPanel();
        gameTitlePanel.setBounds(50,100,700,120);
        gameTitlePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("POKEMON WORLD");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(largeFont);
        gameTitlePanel.add(titleNameLabel);

        //create start button at game initialization
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300,400,200,100);
        startButtonPanel.setBackground(Color.black);
        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.setFocusPainted(false);
        startButton.addActionListener(gal);
        startButton.setActionCommand("start");
        startButtonPanel.add(startButton);

        //add panel to the window so they will appear
        window.add(gameTitlePanel);
        window.add(startButtonPanel);


        //actual game window

        //set main text panel
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(15,80,760,400);
        mainTextPanel.setBackground(Color.black);
        window.add(mainTextPanel);


        textOutputArea = new JTextArea("Text will show up here");
        textOutputArea.setBounds(15,90,760,400);
        textOutputArea.setBackground(Color.black);
        textOutputArea.setForeground(Color.white);
        textOutputArea.setFont(normalFont);
        textOutputArea.setLineWrap(true);
        textOutputArea.setWrapStyleWord(true);
        textOutputArea.setEditable(false);
        //textOutputArea.getDocument().addDocumentListener();
        mainTextPanel.add(textOutputArea);

        //set information panel
        informationPanel = new JPanel();
        informationPanel.setBounds(15,5,760,50);
        informationPanel.setBackground(Color.black);
        informationPanel.setLayout(new GridLayout(1,4)); //divide information panel into grid
        window.add(informationPanel);

        // location label
        locationLabel = new JLabel("Location:");
        locationLabel.setFont(normalFont);
        locationLabel.setForeground(Color.white);
        informationPanel.add(locationLabel);

        // location name label
        locationNameLabel = new JLabel("[Town Square]");
        locationNameLabel.setForeground(Color.white);
        locationNameLabel.setFont(normalFont);
        informationPanel.add(locationNameLabel);

        //text input field
        textInputPanel = new JPanel();
        textInputPanel.setBounds(15,490,760,60);
        textInputPanel.setBackground(Color.black);
        window.add(textInputPanel);

        inputTextField = new JTextField(46);
        inputTextField.setFont(normalFont);
        inputTextField.setEditable(true);
        inputTextField.setBounds(15,490,760,60);
        inputTextField.setBackground(Color.black);
        inputTextField.setForeground(Color.white);
        inputTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputTextField.getText();

                if(input.length()>0){
                    System.out.println(input);
                    inputTextField.setText("");
                    textOutputArea.append(lineBreaker + lineBreaker + "> " + input);
                }
            }
        });
        textInputPanel.add(inputTextField);

        //make window visible
        window.setVisible(true);
    }

    /**
     *
     */
    class GameActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent a) {
            //after initialization, hide title screen and only show game screen
            gameTitlePanel.setVisible(false);
            startButtonPanel.setVisible(false);
            informationPanel.setVisible(true);
            mainTextPanel.setVisible(true);
            textInputPanel.setVisible(true);
        }
    }


    public JTextArea getTextOutputArea() {
        return textOutputArea;
    }

    public void changeMainText(String string){
        textOutputArea.setText(string);
    }

    public void changeLocationText(String string){
        locationNameLabel.setText(string);
    }
}
