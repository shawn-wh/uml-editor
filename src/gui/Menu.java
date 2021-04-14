package gui;

import shape.BasicObj;
import shape.CompositeObj;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String;

public class Menu extends Window {
    protected JMenuBar menubar = new JMenuBar();
    private JMenu file = new JMenu("File");
    private JMenu edit = new JMenu("Edit");

    public Menu(Canvas canvas) {
        JMenuItem changeName = new JMenuItem("Change object name");
        JMenuItem groupObject = new JMenuItem("Group objects");
        JMenuItem unGroupObject = new JMenuItem("Ungroup objects");
        edit.add(changeName);
        edit.add(groupObject);
        edit.add(unGroupObject);
        menubar.add(file);
        menubar.add(edit);

        changeName.addActionListener(new ActionListener() { // Anonymous class
            public void actionPerformed(ActionEvent e) {
                String objectName = JOptionPane.showInputDialog(canvas, "Please enter an object name:",
                        "Change Object Name", JOptionPane.QUESTION_MESSAGE);
                if (Canvas.selectedList.size() != 1) {
                    JOptionPane.showMessageDialog(canvas, "Please select one object", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (Canvas.selectedList.size() == 1 && objectName != null &&
                        (! (Canvas.selectedList.get(0) instanceof CompositeObj))) { // only for a basic object
                    ((BasicObj)(Canvas.selectedList.get(0))).objectName = objectName;
                }
                canvas.repaint();
            }
        });

        groupObject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (canvas.currentMode.modeName.equals("select") && Canvas.selectedList.size() > 1) {
                    CompositeObj compObj = new CompositeObj();
                    compObj.selected = false;
                    Canvas.shapeList.add(compObj);
                    Canvas.selectedList.clear();
                }
            }
        });

        unGroupObject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (canvas.currentMode.modeName.equals("select") && Canvas.selectedList.size() == 1 &&
                        Canvas.selectedList.get(0) instanceof CompositeObj) {
                    CompositeObj compObj = (CompositeObj) Canvas.selectedList.get(0);
                    compObj.unGroupObjects();
                    Canvas.shapeList.remove(compObj);
                    Canvas.selectedList.clear();
                }
            }
        });
    }

}