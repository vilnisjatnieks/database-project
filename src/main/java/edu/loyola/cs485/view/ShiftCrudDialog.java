package edu.loyola.cs485.view;

import edu.loyola.cs485.controller.ShiftService;
import edu.loyola.cs485.model.entity.Shift;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class ShiftCrudDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton deleteButton;
    private JButton updateButton;
    private JList lstShiftUI;

    public ShiftCrudDialog() {
        setContentPane(contentPane);
        setModal(true);
        //getRootPane().setDefaultButton(buttonOK);
        populateUI();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newClick();
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
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteClick();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClick();
            }
        });
    }

    private void newClick() {
        // add your code here
        ShiftInfoDialog dialog = new ShiftInfoDialog();
        dialog.pack();
        dialog.setVisible(true);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void deleteClick() {
        try{
            ShiftService service = new ShiftService();
            Shift c = (Shift) lstShiftUI.getSelectedValue();
            if (c != null) {
                service.deleteShift(c.getID());
                lstShiftUI.clearSelection();

                // Repopulate the JList to get new data
                populateUI(); // fetch everything again from the DB
            }

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void populateUI() {
        try {
            ShiftService service = new ShiftService();
            List<Shift> lstdata = service.getAllShifts();

            lstShiftUI.setListData( lstdata.toArray() );

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void updateClick() {
        try{
            ShiftService service = new ShiftService();
            // Left as an exercise for you to practice

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ShiftCrudDialog dialog = new ShiftCrudDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}
