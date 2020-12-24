package AssignmentProject.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class Main {
    private static JLabel addressText;
    private static JLabel resultLabel;
    private static JTextArea resultToken;
    private static JTextArea addressInput;
    private static JButton btnParse;
    private static JButton btnExit;
    private static JButton btnAgain;

    public static void main(String[] args) {
	// write your code here
        createJframe();
    }

    private static void createJframe() {
        JFrame frame = new JFrame();
        frame.setSize(400, 490);
        frame.setDefaultCloseOperation(3);
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout((LayoutManager)null);
        addressText = new JLabel("Address");
        addressText.setBounds(10, 20, 80, 25);
        addressInput = new JTextArea();
        addressInput.setBounds(100, 20, 250, 100);
        addressInput.setLineWrap(true);
        addressInput.setWrapStyleWord(true);
        panel.add(addressText);
        panel.add(addressInput);
        resultLabel = new JLabel("Result");
        resultLabel.setBounds(10, 140, 80, 25);
        resultToken = new JTextArea();
        resultToken.setBounds(100, 140, 250, 250);
        resultToken.setLineWrap(true);
        resultToken.setWrapStyleWord(true);
        resultToken.setEnabled(false);
        resultToken.setDisabledTextColor(Color.red);
        panel.add(resultLabel);
        panel.add(resultToken);
        btnParse = new JButton("Parse");
        btnParse.setBounds(20, 400, 150, 40);
        btnParse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ButtonParse(e);
                //System.out.println("Parse button is press");
            }
        });
        btnExit = new JButton("Exit");
        btnExit.setBounds(200, 400, 150, 40);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnAgain = new JButton("Again");
        btnAgain.setBounds(20, 400, 150, 40);
        btnAgain.setVisible(false);
        btnAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Again button is press");

                addressInput.setText((String)null);
                resultToken.setText((String)null);
                resultToken.removeAll();
                //System.out.println(resultToken.getText());
                btnAgain.setVisible(false);
                btnParse.setVisible(true);
            }
        });
        panel.add(btnAgain);
        panel.add(btnParse);
        panel.add(btnExit);
        frame.setVisible(true);
    }
    private static void ButtonParse(ActionEvent e) {
        if (addressInput.getText().equals("")) {
            JOptionPane.showMessageDialog((Component)null, "Please insert the address");
        } else {

            String address = addressInput.getText();

            ScannerAddress scan = new ScannerAddress(address);
            Map data =  scan.printAll();
            for(Object key : data.keySet()){
                resultToken.append(key + " : "+data.get(key)+"\n");
            }
            btnParse.setVisible(false);
            btnAgain.setVisible(true);
        }
    }
}
