import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ToDoGUI extends JFrame {

    private TaskManager manager = new TaskManager();
    private JTextArea displayArea = new JTextArea();
    private JTextField inputField = new JTextField();
    private JTextField idField = new JTextField();

    public ToDoGUI() {

        // Makes gui look better
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        setTitle("To Do List");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));


        // sets the title
        JPanel top = new JPanel();
        top.setBackground(new Color(52, 152, 219));
        top.setBorder(new EmptyBorder(12, 12, 12, 12));
        JLabel title = new JLabel("To Do List");
        title.setFont(new Font("Dialog", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        top.add(title);
        add(top, BorderLayout.NORTH);

        // Left Sidebar with Buttons
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(6, 1, 10, 10));
        sidebar.setBorder(new EmptyBorder(15, 15, 15, 15));
        sidebar.setBackground(new Color(236, 240, 241));

        JButton addBtn = createModernButton("Add Task");
        JButton updateBtn = createModernButton("Update Task");
        JButton completeBtn = createModernButton("Mark As Done");
        JButton deleteBtn = createModernButton("Delete Task");
        JButton showBtn = createModernButton("Show All");
        JButton showCompletedBtn = createModernButton("Show Done");

        sidebar.add(addBtn);
        sidebar.add(updateBtn);
        sidebar.add(completeBtn);
        sidebar.add(deleteBtn);
        sidebar.add(showBtn);
        sidebar.add(showCompletedBtn);

        add(sidebar, BorderLayout.WEST);

        //show tasks
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Dialog", Font.PLAIN, 14));
        displayArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        displayArea.setBackground(new Color(250, 250, 250));
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(displayArea);
        add(scroll, BorderLayout.CENTER);


        // handles inputs (task desk and task id)
        JPanel bottom = new JPanel(new GridLayout(2, 2, 10, 10));
        bottom.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel descLabel = new JLabel("Task Description:");
        descLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        bottom.add(descLabel);
        bottom.add(inputField);

        JLabel idLabel = new JLabel("Task ID:");
        idLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        bottom.add(idLabel);
        bottom.add(idField);

        add(bottom, BorderLayout.SOUTH);


        //Makes buttons function
        addBtn.addActionListener(e -> {
            manager.addTask(inputField.getText());
            inputField.setText("");
            refresh(manager.getAllTasks());
        });

        updateBtn.addActionListener(e -> {
            int id = parseID(idField.getText());
            if (id != -1) manager.updateTask(id, inputField.getText());
            refresh(manager.getAllTasks());
        });

        completeBtn.addActionListener(e -> {
            int id = parseID(idField.getText());
            if (id != -1) manager.markCompleted(id);
            refresh(manager.getAllTasks());
        });

        deleteBtn.addActionListener(e -> {
            int id = parseID(idField.getText());
            if (id != -1) manager.deleteTask(id);
            refresh(manager.getAllTasks());
        });

        showBtn.addActionListener(e -> refresh(manager.getAllTasks()));
        showCompletedBtn.addActionListener(e -> refresh(manager.getCompletedTasks()));
    }

    private int parseID(String text) {
        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter valid ID number.");
            return -1;
        }
    }

    private JButton createModernButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Roboto", Font.PLAIN, 14));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(52, 152, 219));
        btn.setForeground(Color.BLACK);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        return btn;
    }

    private void refresh(java.util.List<Task> tasks) {
        displayArea.setText("");
        for (Task t : tasks) {
            String visual = t.isCompleted() ? "•" : "•";
            displayArea.append(visual + "  " + t.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        new ToDoGUI().setVisible(true);
    }
}
