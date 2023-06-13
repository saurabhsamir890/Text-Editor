import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //Declaring properties of texteditor

    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut, copy, paste, selectAll,close;
    JTextArea textArea;


    TextEditor(){
        //Initialise a frame
        frame = new JFrame();
        menuBar = new JMenuBar(); // initialise menubar
        textArea = new JTextArea();

        // initialise menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Initialising file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile=new JMenuItem("Save File");

        //add actionlisteners to filemenu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialising edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //Adding actionListener to edit menuItems
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);


        menuBar.add(file);
        menuBar.add(edit);
        //set menubar to frame
        frame.setJMenuBar(menuBar);
        //add textArea to frame
        //frame.add(textArea);

        frame.setJMenuBar(menuBar);
        //create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);

        //Set dimensions of frame
        frame.setBounds(0,0,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true); // frame will not be hidden now
        frame.setLayout(null);
        // till here frame is created
        // go up and add textarea and menubar in frame


    }
    @Override

    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==copy){
            textArea.copy();
        }
        if(actionEvent.getSource()==cut){
            textArea.cut();
        }
        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile){
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //always opens in c drive
            int chooseOption = fileChooser.showOpenDialog(null);
            // if we click on open button below happens
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Getting selected file
                File file = fileChooser.getSelectedFile();
                // Get the path of selected file
                String filePath = file.getPath();

                try{
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //initialise buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String intermediate;
                    StringBuilder output = new StringBuilder();
                    //read contents of file line by line
                    while((intermediate = bufferedReader.readLine())!=null){
                        output.append(intermediate).append("\n");
                    }
                    //set the output string to text area
                    textArea.setText(output.toString());

                }
                catch(IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }

            }

        }
        if(actionEvent.getSource()==saveFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            // get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //create a new file with choosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".text");
                try{
                    //Initialise file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialise buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write contents of textArea to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }

        }
        //now functioning of newFile
        if(actionEvent.getSource()== newFile){
            TextEditor newTextEditor = new TextEditor();
        }


    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}