import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExpenseTracker {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ExpenseTracker::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 300);
        frame.setLocationRelativeTo(null); // Center window

        JPanel panel = new JPanel(new BorderLayout());

        // Table Model with columns: Item | Amount | Date | Time
        DefaultTableModel model = new DefaultTableModel(new String[]{"Item", "Amount", "Date", "Time"}, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Input Panel
        JPanel inputPanel = new JPanel();
        JTextField itemField = new JTextField(10);
        JTextField amountField = new JTextField(7);
        JButton addButton = new JButton("Add Expense");
        JButton deleteButton = new JButton("Delete Expense");

        inputPanel.add(new JLabel("Item:"));
        inputPanel.add(itemField);
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        // Add expense action
        addButton.addActionListener(e -> {
            String item = itemField.getText().trim();
            String amount = amountField.getText().trim();
            if (!item.isEmpty() && !amount.isEmpty()) {
                LocalDateTime now = LocalDateTime.now();
                String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                model.addRow(new Object[]{item, amount, date, time});
                itemField.setText("");
                amountField.setText("");
            }
        });

        // Delete selected expense
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(selectedRow);
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
    }
}
