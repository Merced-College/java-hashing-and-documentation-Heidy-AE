//package hashingAndDocumentation;//comment this out if you do not need a package

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

//Heidy Acosta Emeterio 
//04/15/2025

/*  this class is responsible for loading song data from a CSV file, storing it in a HashMap, and providing methods to retrieve and display song information.
// It also includes a GUI for searching songs by ID.
// The CSV file is expected to have a header row, and the data is parsed into SongRecord objects.
// The main method demonstrates loading songs from a CSV file and retrieving a song by ID.
// The GUI allows users to search for songs by entering an ID, and displays the song details if found.*/

public class SongProgram {

    // HashMap to store SongRecords with the song's ID as the key
    
    private HashMap<String, SongRecord> songMap;

    /* Constructor
    // Initializes the HashMap to store SongRecords
    // The constructor does not take any parameters and initializes the songMap.*/

    public SongProgram() {
        songMap = new HashMap<>();
    }

    /*  Method to load songs from a CSV file
    // Takes the file path as a parameter and reads the CSV file line by line
    // Each line is parsed into a SongRecord object, which is then stored in the HashMap*/
   
    public void loadSongsFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            //read in first line and do nothing with it
           
            br.readLine();

            // Read each line of the CSV file
            while ((line = br.readLine()) != null) {
            	
            	//System.out.println(line);//for testing
                // Create a SongRecord from the line and add it to the map
                SongRecord song = new SongRecord(line);
                songMap.put(song.getId(), song);
            }
            // Print a message indicating successful loading
            System.out.println("Songs successfully loaded from CSV.");
        } catch (IOException e) {
            // Print an error message if there is an issue reading the file
            // This will help in debugging if the file path is incorrect or the file is not accessible
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    // Method to retrieve a SongRecord by ID
    public SongRecord getSongById(String id) {
        // Check if the ID exists in the map and return the corresponding SongRecord
        return songMap.get(id);

    }

    // Method to print all songs (for debugging or display purposes)
    public void printAllSongs() {
        // Iterate through the HashMap and print each SongRecord
        for (SongRecord song : songMap.values()) {
            // Print the song details
            System.out.println(song);
        }
    }
    
    // GUI method to search for a song by ID
    public void openSearchGui() {
        // Create the main frame
        JFrame frame = new JFrame("Song Lookup");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold input and button
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Label, Text Field, and Button
        JLabel label = new JLabel("Enter Song ID:");
        JTextField idField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        // Add label, text field, and button to panel
        panel.add(label);
        panel.add(idField);
        panel.add(searchButton);

        // Result area to display song details
        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane);

        // Add action listener for the search button
        searchButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                // Get the ID from the text field and search for the song
                String id = idField.getText();
                SongRecord song = getSongById(id);
                // If the song is found, display its details; otherwise, show a not found message
                if (song != null) {
                    resultArea.setText("Song Found:\n" + song.toString());
                } else {
                    resultArea.setText("Song with ID " + id + " not found.");
                }
            }
        });

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }

    // Main method to demonstrate functionality and open GUI
    public static void main2(String[] args) {
        SongProgram program = new SongProgram();

        // Load songs from a CSV file
        String filePath = "data.csv";  // replace with actual file path
        program.loadSongsFromCSV(filePath);

        // Open GUI for searching songs by ID
        program.openSearchGui();
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        // Create an instance of SongProgram
        // This instance will be used to load songs and perform operations
        SongProgram program = new SongProgram();

        // Load songs from a CSV file
        String filePath = "data.csv";  // replace with actual file path
        program.loadSongsFromCSV(filePath);

        // Demonstrate retrieving a song by ID
        String testId = "4BJqT0PrAfrxzMOxytFOIz";  // replace with an actual ID from your file
        SongRecord song = program.getSongById(testId);
        // Print the retrieved song or a not found message
        if (song != null) {
            System.out.println("Retrieved song: " + song);
        } else {
            System.out.println("Song with ID " + testId + " not found.");
        }

        // Print all songs
        program.printAllSongs();
    }
}

