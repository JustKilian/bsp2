import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This Class implements a state machine to implement the following grammar:
 * <p>
 * S -> 0S | 0 | 1A
 * <p>
 * A -> 1S | 1 | 0A
 * @version 1.0
 * @since 2023-12-10
 * @author Celine J
 */
public class Start extends JFrame {
    // start attributes
    private final JTextField input;
    private final JLabel output;
    private boolean z0, z1;
    // end attributes

    /**
     * Constructor for the Start class
     */
    public Start() {
        // Frame init
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 527;
        int frameHeight = 300;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("Start");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);
        // start components

        input = new JTextField();
        input.setBounds(16, 16, 489, 33);
        input.setText("");
        cp.add(input);

        JButton check = new JButton();
        check.setBounds(16, 64, 489, 33);
        check.setText("Check");
        check.setMargin(new Insets(2, 2, 2, 2));
        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                check_ActionPerformed(evt);
            }
        });
        cp.add(check);

        output = new JLabel();
        output.setBounds(16, 112, 489, 33);
        output.setText("");
        cp.add(output);
        // end components

        setVisible(true);
        setZ0();
    } // end of public Start

    // start methods

    /**
     * Main method for the Start class
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        new Start();
    } // end of public static void main (String[] args)

    private void check_ActionPerformed(ActionEvent evt) {
        setZ0(); // reset the machine
        String inputString = input.getText(); // get the input string
        for (String s : inputString.split("")) { // split the input string into its characters and loop through them
            if (!s.equals("0") && !s.equals("1")) { // check if the character is a 0 or a 1
                output.setText("Please enter a valid string"); // if it is not, tell the user to enter a valid string
                return; // and return to exit the method
            } else { // if it is a 0 or a 1
                switch (getMachineState()) { // check the current state of the machine
                    case "z0": // if the machine is in state z0
                        if (s.equals("0")) { // and the current character is a 0
                            setZ0(); // set the machine to state z0
                        } else { // and the current character is a 1
                            setZ1(); // set the machine to state z1
                        }
                        break;
                    case "z1": // if the machine is in state z1
                        if (s.equals("0")) { // and the current character is a 0
                            setZ1(); // set the machine to state z0
                        } else { // and the current character is a 1
                            setZ0(); // set the machine to state z1
                        }
                        break;
                    default: // if the current state is not z0 or z1
                        output.setText("Error"); // tell the user that an error occurred
                        return; // and return to exit the method
                } // end of switch (getMachineState())
            } // end of if (!s.equals("0") && !s.equals("1"))
        } // end of for (String s : inputString.split(""))

        if (getMachineState().equals("z0")) { // check if the current state of the machine is z0
            output.setText("String is accepted"); // if it is, tell the user that the string is accepted
        } else { // if the current state of the machine is not z0
            output.setText("String is not accepted"); // tell the user that the string is not accepted
        } // end of if (getMachineState().equals("z0"))
    } // end of private void check_ActionPerformed(ActionEvent evt)

    /**
     * This method is called when the state of the machine is to be set to z0
     */
    private void setZ0() {
        z0 = true;
        z1 = false;
    } // end of private void setZ0()

    /**
     * This method is called when the state of the machine is to be set to z1
     */
    private void setZ1() {
        z0 = false;
        z1 = true;
    } // end of private void setZ1()

    /**
     * This method returns the current state of the machine as a String
     * @return The current state of the machine
     */
    private String getMachineState() {
        if (z0) {
            return "z0";
        } else if (z1) {
            return "z1";
        } else {
            return "Error";
        }
    } // end of private String getMachineState()
    // end methods
}
