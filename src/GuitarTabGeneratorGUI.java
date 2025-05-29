import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuitarTabGeneratorGUI extends JFrame {

    private JTextArea inputArea;
    private JTextArea outputArea;
    private JButton generateButton;

    public GuitarTabGeneratorGUI() {
        setTitle("Guitar Tab Generator");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout(10, 10));

        // Input Panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Chords (comma separated)"));

        inputArea = new JTextArea();
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        JScrollPane inputScroll = new JScrollPane(inputArea);

        inputPanel.add(inputScroll, BorderLayout.CENTER);

        // Output Panel
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Generated Guitar Tabs"));

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        JScrollPane outputScroll = new JScrollPane(outputArea);

        outputPanel.add(outputScroll, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        generateButton = new JButton("Generate Tabs");
        buttonPanel.add(generateButton);

        // Add panels to frame
        add(inputPanel, BorderLayout.NORTH);
        add(outputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button action
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateTabs();
            }
        });
    }

    private void generateTabs() {
        String inputText = inputArea.getText().trim();
        if (inputText.isEmpty()) {
            outputArea.setText("Please enter some chords.");
            return;
        }

        String[] chords = inputText.split(",");

        // Store each chord's tab lines
        String[][] tabsLines = new String[chords.length][];

        for (int i = 0; i < chords.length; i++) {
            String chord = chords[i].trim().toUpperCase();
            tabsLines[i] = getTabLinesForChord(chord);
        }

        StringBuilder outputBuilder = new StringBuilder();

        // Print chord names spaced evenly on top
        for (int i = 0; i < chords.length; i++) {
            outputBuilder.append(String.format("%-15s", chords[i].trim().toUpperCase()));
        }
        outputBuilder.append("\n");

        // Print tab lines horizontally aligned
        int numLines = tabsLines[0].length; // typically 6 lines

        for (int line = 0; line < numLines; line++) {
            for (int chordIdx = 0; chordIdx < chords.length; chordIdx++) {
                outputBuilder.append(String.format("%-15s", tabsLines[chordIdx][line]));
            }
            outputBuilder.append("\n");
        }

        outputArea.setText(outputBuilder.toString());
    }

    private String[] getTabLinesForChord(String chord) {
        switch (chord) {
            case "C":
                return new String[]{
                        "e|--0--",
                        "B|--1--",
                        "G|--0--",
                        "D|--2--",
                        "A|--3--",
                        "E|-----"
                };
            case "G":
                return new String[]{
                        "e|--3--",
                        "B|--0--",
                        "G|--0--",
                        "D|--0--",
                        "A|--2--",
                        "E|--3--"
                };
            case "D":
                return new String[]{
                        "e|--2--",
                        "B|--3--",
                        "G|--2--",
                        "D|--0--",
                        "A|-----",
                        "E|-----"
                };
            case "A":
                return new String[]{
                        "e|--0--",
                        "B|--2--",
                        "G|--2--",
                        "D|--2--",
                        "A|--0--",
                        "E|-----"
                };
            case "E":
                return new String[]{
                        "e|--0--",
                        "B|--0--",
                        "G|--1--",
                        "D|--2--",
                        "A|--2--",
                        "E|--0--"
                };
            default:
                return new String[]{
                        "Tab not found",
                        "for chord:",
                        chord,
                        "",
                        "",
                        ""
                };
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuitarTabGeneratorGUI gui = new GuitarTabGeneratorGUI();
            gui.setVisible(true);
        });
    }
}
