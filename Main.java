import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Сложение двух чисел");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);

        // Текстовые поля для ввода чисел
        JTextField number1Field = new JTextField(10);
        JTextField number2Field = new JTextField(10);

        // Кнопка для выполнения сложения
        JButton addButton = new JButton("Сложить");

        // Метка для отображения результата
        JLabel resultLabel = new JLabel("Результат: ");

        // Обработка нажатия кнопки
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double number1 = Double.parseDouble(number1Field.getText());
                    double number2 = Double.parseDouble(number2Field.getText());
                    double sum = number1 + number2;
                    resultLabel.setText("Результат: " + sum);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Пожалуйста, введите корректные числа.");
                }
            }
        });

        // Панель для размещения элементов
        JPanel panel = new JPanel();
        panel.add(new JLabel("Число 1:"));
        panel.add(number1Field);
        panel.add(new JLabel("Число 2:"));
        panel.add(number2Field);
        panel.add(addButton);
        panel.add(resultLabel);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}