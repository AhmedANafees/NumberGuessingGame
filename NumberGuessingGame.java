
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author nafea8846
 */
public class NumberGuessingGame implements Runnable, ActionListener {

    // Class Variables  
    JPanel mainPanel;
    JLabel resultLabel;
    JLabel titleLabel;
    JTextField inputTextField;
    JButton submitButton;
    JButton newGameButton;

    Color greenCustom = new Color(46, 184, 46);
    Color yellowCustom = new Color(230, 184, 0);
    Color redCustom = new Color(204, 0, 0);

    int tries = 1;

    //generates random number
    int lowest = 0;
    int highest = 100;
    int randomNumber = (int) (Math.random() * (highest - lowest + 1) + lowest);

    /**
     * Method to assemble our GUI
     */
    @Override
    public void run() {
        // Creats a JFrame that is 800 pixels by 600 pixels, and closes when you click on the X
        JFrame frame = new JFrame("Nummber Guessing Game");
        // Makes the X button close the program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // makes the windows 800 pixel wide by 600 pixels tall
        frame.setSize(400, 200);
        // shows the window
        frame.setVisible(true);

        //Create panel, textfield, buttons and labels
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        
        //add instructions label
        titleLabel = new JLabel("Guess a number between 0 & 100");
        titleLabel.setBounds(90, 15, 192, 25);

        //add textfield to input guesses
        inputTextField = new JTextField();
        inputTextField.setBounds(10, 50, 365, 25);

        //add a button to submit guesses
        submitButton = new JButton("Submit");
        submitButton.setBounds(20, 87, 150, 25);
        submitButton.addActionListener(this);
        submitButton.setActionCommand("submit");
        
        //add a button to start a new game
        newGameButton = new JButton("New Number");
        newGameButton.setBounds(210, 87, 150, 25);
        newGameButton.addActionListener(this);
        newGameButton.setActionCommand("new");
        
        //add fesult label
        resultLabel = new JLabel(" ");
        resultLabel.setBounds(10, 115, 500, 50);

        //add items to JFrame
        mainPanel.add(resultLabel);
        mainPanel.add(newGameButton);
        mainPanel.add(submitButton);
        mainPanel.add(inputTextField);
        mainPanel.add(titleLabel);
        frame.add(mainPanel);
    }

    /**
     * Method called when a button is pressed
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // get the command from the action
        String command = e.getActionCommand();

        // actions to perform when submit button is pressed
        if (command.equals("submit")) {
            
            //read valueInt entered
            String guessString = inputTextField.getText();
            
            //print an error if textfeild is empty
            if (guessString.equals("")) {
                resultLabel.setText("Please enter a number Between 0 & 100");
                resultLabel.setForeground(new Color(230, 184, 0));
            }
            
            //turn the guess into a number
            int guessInt = Integer.parseInt(guessString);

            //checks if the number is between 1 and 100
            if (guessInt < 101 && guessInt > 0) {
                //sends valueInt of guessed number and random number generated to checker methood
                int valueInt = checker(guessInt, randomNumber);
                
                //prints various outputs depending on valueInt
                switch (valueInt) {
                    
                    //if the guess is correct
                    case 1:
                        resultLabel.setText("Your guess of " + guessInt + " is correct, you made " + tries + " atempts.");
                        resultLabel.setForeground(greenCustom);
                        submitButton.setEnabled(false);
                        inputTextField.setEnabled(false);
                        break;
                        
                    //if the guess is too low
                    case 2:
                        resultLabel.setText("Your guess of " + guessInt + " is too LOW, try again.");
                        resultLabel.setForeground(redCustom);
                        break;
                        
                    //if the guess is too high
                    case 3:
                        resultLabel.setText("your guess of " + guessInt + " is too HIGH, try again.");
                        resultLabel.setForeground(redCustom);
                        break;
                    default:
                        break;
                }
            
            //return an error message if the guess is greater than 100
            } else if (guessInt > 100) {
                resultLabel.setText(" This number is greater than 100, use a number between 0 & 100");
                resultLabel.setForeground(yellowCustom);
            
            //return an error message if the guess is less than 1
            } else {
                resultLabel.setText("This number is less then 0, use a number between 0 & 100");
                resultLabel.setForeground(yellowCustom);
            }

            inputTextField.setText("");
            tries++;
            inputTextField.requestFocusInWindow();

        }

        //resets the game when the new game button is pressed
        if (command.equals("new")) {
            resultLabel.setText("");
            inputTextField.setText("");
            tries = 1;
            submitButton.setEnabled(true);
            inputTextField.setEnabled(true);

            randomNumber = (int) (Math.random() * (highest - lowest + 1) + lowest);
        }
    }

    /**
     * checks if input is higher,lower or equal too the random number
     *
     * @param num1 number input by the user
     * @param num2 number generated by randomNumber
     * @return valueInt 1,2 or 3 depending on input
     */
    public int checker(int num1, int num2) {
        if (num1 == num2) {
            return 1;
        } else if (num1 < num2) {
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * Main method to start our program
     *
     * @param args
     */
    public static void main(String[] args) {
        // Creates an instance of our program
        NumberGuessingGame gui = new NumberGuessingGame();
        // Lets the computer know to start it in the event thread
        SwingUtilities.invokeLater(gui);
    }
}
