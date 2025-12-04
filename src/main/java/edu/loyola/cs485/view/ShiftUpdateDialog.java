package edu.loyola.cs485.view;
import edu.loyola.cs485.controller.ShiftService;
import edu.loyola.cs485.model.entity.Shift;

import javax.swing.*;
import java.awt.event.*;

public class ShiftUpdateDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private Shift testing;
    private JTextField txtId;
    private JTextField txtStartShift;
    private JTextField txtEndShift;

    public ShiftUpdateDialog(Shift something) {
        testing = something;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonCancel);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });



        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        ShiftService service = new ShiftService();
        try {
            service.updateShift(testing);
            dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        dispose(); // dispose method from the superclass JDialog, closes the current dialog
    }


    public static void main(String[] args) {
        ShiftUpdateDialog dialog = new ShiftUpdateDialog(testing);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}

