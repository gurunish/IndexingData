/**
 * Created by Nishant
 */

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Nishant
 * This class handles the GUI of the program, as well as invoking
 * the classes responsible for the algorithm
 */
public class Gui extends JFrame {
    private JLabel input, search, constant, result;
    private JTextArea TAinput, TAresult;
    private JTextField TFsearch, TFconstant;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem open, export, exit;
    private JButton start, read, stop;
    private JPanel north, center, south, innerNorth1, innerNorth2, innerNorth3;
    private String path;
    private ArrayList<TrieNode> seqIndices;

    //Default constructor that sets up the GUI
    public Gui() {
        menuBar = new JMenuBar();
        seqIndices = new ArrayList<>();
        menu = new JMenu("File");
        open = new JMenuItem("Open");
        export = new JMenuItem("Export results");
        exit = new JMenuItem("Exit");

        setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(open);
        menu.add(export);
        menu.add(exit);

        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                path = chooseFile();
                if (!path.equals("Invalid path")) {
                    try {
                        writeInput();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Invalid file");
                }
            }
        });

        export.addActionListener(new exportAction());
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });

        setLayout(new BorderLayout());
        addNorth();
        addCenter();
        addSouth();

        super.setTitle("Indexing uncertain data");
        setVisible(true);
        setResizable(false);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Method that opens the JFileChooser to allow user to select text file
    private String chooseFile() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text files", "txt", "rtf");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(getParent());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        } else {
            return "Invalid path";
        }
    }

    //Method that sets up the north JPanel
    private void addNorth() {
        input = new JLabel("Input           ");
        search = new JLabel("Search       ");
        constant = new JLabel("Constant k");
        TAinput = new JTextArea("A C G T" +
                "\n" + "1,0,0,0," + "\n" + "0,0.5,0.5,0," + "\n" + "0.25,0.25,0.25,0.25");

        TAinput.setRows(3);
        TAinput.setColumns(30);
        JScrollPane scroll = new JScrollPane(TAinput,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        TFsearch = new JTextField("aaaa");
        TFconstant = new JTextField("2");

        north = new JPanel();
        innerNorth1 = new JPanel();
        innerNorth2 = new JPanel();
        innerNorth3 = new JPanel();
        north.setLayout(new BorderLayout());

        innerNorth1.setLayout(new BorderLayout());
        innerNorth1.add(input, BorderLayout.WEST);
        innerNorth1.add(scroll, BorderLayout.CENTER);

        innerNorth2.setLayout(new BorderLayout());
        innerNorth2.add(search, BorderLayout.WEST);
        innerNorth2.add(TFsearch, BorderLayout.CENTER);

        innerNorth3.setLayout(new BorderLayout());
        innerNorth3.add(constant, BorderLayout.WEST);
        innerNorth3.add(TFconstant, BorderLayout.CENTER);

        north.add(innerNorth1, BorderLayout.NORTH);
        north.add(innerNorth2, BorderLayout.CENTER);
        north.add(innerNorth3, BorderLayout.SOUTH);

        add(north, BorderLayout.NORTH);
    }

    //Method that sets up the center JPanel
    private void addCenter() {
        start = new JButton("Start");
        read = new JButton("Read input");
        stop = new JButton("Stop");

        read.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String line = TAinput.getText().substring(7);
                generateSeqIndices(line);
            }
        });

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Algorithm wst = new Algorithm(seqIndices, Integer.parseInt(TFconstant.getText()),
                        TFsearch.getText());
                String result = wst.getResult();
                TAresult.setText(result);
            }
        });

        center = new JPanel();
        center.add(read);
        center.add(start);
        center.add(stop);

        add(center, BorderLayout.CENTER);
    }

    //Method that sets up the south panel
    private void addSouth() {
        result = new JLabel("Result");
        TAresult = new JTextArea();

        south = new JPanel();
        south.setLayout(new BorderLayout());
        south.add(result, BorderLayout.WEST);
        south.add(TAresult, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);
    }

    //Method that reads data from a text file chosen via the JFileChooser
    private void writeInput() throws IOException {
        seqIndices = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line, data;
        data = br.readLine() + "\n";

        while ((line = br.readLine()) != null) {
            data += line + "\n";
        }

        generateSeqIndices(line);
        TAinput.setText(data);
        br.close();
        pack();
    }

    public void generateSeqIndices(String line) {
        System.out.println("Input was " + line);

        //Store the weighted values into array doubleValues
        String[] values = line.split(",");
        double[] doubleValues = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            doubleValues[i] = Double.parseDouble(values[i]);
        }

        //Create TrieNode from every 4 value in doubleValues

        int i = 0;
        while (true) {
            TrieNode temp = new TrieNode(doubleValues[i], doubleValues[i + 1],
                    doubleValues[i + 2], doubleValues[i + 3]);
            seqIndices.add(temp);
            System.out.println("New TrieNode created");
            i += 4;
            if (i >= doubleValues.length) {
                break;
            }
        }
    }

    public class exportAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                File file = new File("Results.txt");
                FileWriter writer;
                writer = new FileWriter(file);
                writer.write(TAresult.getText());
                JOptionPane.showMessageDialog(getParent(),
                        "Results saved to" + file.getAbsolutePath());
            } catch (IOException er) {
                er.printStackTrace();
            }
        }
    }
}