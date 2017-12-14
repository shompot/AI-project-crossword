import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Shiha on 11/11/2017.
 */

public class CrosswordGUI {
    public static final String[] options = { "Today", "Oct 24, 2017", "Nov 8, 2017", "Nov 14, 2017", "Nov 15, 2017",
                                                "Dec 12, 2017"};
    private JPanel CWPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton button18;
    private JButton button19;
    private JButton button20;
    private JButton button21;
    private JButton button22;
    private JButton button23;
    private JButton button24;
    private JButton button25;
    private JTextArea log;
    private JButton getCrossword;
    private JButton getHints;
    private JTextArea downHints;
    private JTextArea acrossHints;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea textArea4;
    private JTextArea textArea5;
    private JTextArea textArea6;
    private JTextArea textArea7;
    private JTextArea textArea8;
    private JTextArea textArea9;
    private JTextArea textArea10;
    private JTextArea textArea11;
    private JTextArea textArea12;
    private JTextArea textArea13;
    private JTextArea textArea14;
    private JTextArea textArea15;
    private JTextArea textArea16;
    private JTextArea textArea17;
    private JTextArea textArea18;
    private JTextArea textArea19;
    private JTextArea textArea20;
    private JTextArea textArea21;
    private JTextArea textArea22;
    private JTextArea textArea23;
    private JTextArea textArea24;
    private JTextArea textArea25;
    private JPanel box1;
    private JPanel box2;
    private JPanel box3;
    private JPanel box4;
    private JPanel box5;
    private JPanel box6;
    private JPanel box7;
    private JPanel box8;
    private JPanel box9;
    private JPanel box10;
    private JPanel box11;
    private JPanel box12;
    private JPanel box13;
    private JPanel box14;
    private JPanel box15;
    private JPanel box16;
    private JPanel box17;
    private JPanel box18;
    private JPanel box19;
    private JPanel box20;
    private JPanel box21;
    private JPanel box22;
    private JPanel box23;
    private JPanel box24;
    private JPanel box25;
    private ArrayList<String> acrossList = new ArrayList<String>();
    private ArrayList<String> downList = new ArrayList<String>();
    private ArrayList<JPanel> panelList = new ArrayList<JPanel>();
    private ArrayList<JButton> buttonlist = new ArrayList<JButton>();;
    private ArrayList<JTextArea> textlist = new ArrayList<JTextArea>();;
    public ArrayList<String> words = new ArrayList<String>();
    private int[] colors;
    private int[] numbers;
    private Color dark = new Color(123,86,78);
    public CrosswordGUI()
    {
        words.add( "KATE");
        words.add( "BAIT");
        words.add( "LATE");
        words.add( "FEET");
        words.add( "SASS");
        log.append( "\n Welcome! Starting project.");
        AbstractDocument doc1=(AbstractDocument)textArea1.getDocument();
        doc1.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc2=(AbstractDocument)textArea2.getDocument();
        doc2.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc3=(AbstractDocument)textArea3.getDocument();
        doc3.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc4=(AbstractDocument)textArea4.getDocument();
        doc4.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc5=(AbstractDocument)textArea5.getDocument();
        doc5.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc6=(AbstractDocument)textArea6.getDocument();
        doc6.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc7=(AbstractDocument)textArea7.getDocument();
        doc7.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc8=(AbstractDocument)textArea8.getDocument();
        doc8.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc9=(AbstractDocument)textArea9.getDocument();
        doc9.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc10=(AbstractDocument)textArea10.getDocument();
        doc10.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc11=(AbstractDocument)textArea11.getDocument();
        doc11.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc12=(AbstractDocument)textArea12.getDocument();
        doc12.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc13=(AbstractDocument)textArea13.getDocument();
        doc13.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc14=(AbstractDocument)textArea14.getDocument();
        doc14.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc15=(AbstractDocument)textArea15.getDocument();
        doc15.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc16=(AbstractDocument)textArea16.getDocument();
        doc16.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc17=(AbstractDocument)textArea17.getDocument();
        doc17.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc18=(AbstractDocument)textArea18.getDocument();
        doc18.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc19=(AbstractDocument)textArea19.getDocument();
        doc19.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc20=(AbstractDocument)textArea20.getDocument();
        doc20.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc21=(AbstractDocument)textArea21.getDocument();
        doc21.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc22=(AbstractDocument)textArea22.getDocument();
        doc22.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc23=(AbstractDocument)textArea23.getDocument();
        doc23.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc24=(AbstractDocument)textArea24.getDocument();
        doc24.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc25=(AbstractDocument)textArea25.getDocument();
        doc25.setDocumentFilter(new DocumentSizeFilter(1));
        getCrossword.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                fillGrid();
            }
        });
        getHints.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                fillHints();
            }
        });
    }
    public ArrayList<JButton> getButtonlist()
    {
        buttonlist.add( button1);
        buttonlist.add( button2);
        buttonlist.add( button3);
        buttonlist.add( button4);
        buttonlist.add( button5);
        buttonlist.add( button6);
        buttonlist.add( button7);
        buttonlist.add( button8);
        buttonlist.add( button9);
        buttonlist.add( button10);
        buttonlist.add( button11);
        buttonlist.add( button12);
        buttonlist.add( button13);
        buttonlist.add( button14);
        buttonlist.add( button15);
        buttonlist.add( button16);
        buttonlist.add( button17);
        buttonlist.add( button18);
        buttonlist.add( button19);
        buttonlist.add( button20);
        buttonlist.add( button21);
        buttonlist.add( button22);
        buttonlist.add( button23);
        buttonlist.add( button24);
        buttonlist.add( button25);
        return buttonlist;
    }
    public ArrayList<JTextArea> getTextlist() {
        textlist.add(textArea1);
        textlist.add(textArea2);
        textlist.add(textArea3);
        textlist.add(textArea4);
        textlist.add(textArea5);
        textlist.add(textArea6);
        textlist.add(textArea7);
        textlist.add(textArea8);
        textlist.add(textArea9);
        textlist.add(textArea10);
        textlist.add(textArea11);
        textlist.add(textArea12);
        textlist.add(textArea13);
        textlist.add(textArea14);
        textlist.add(textArea15);
        textlist.add(textArea16);
        textlist.add(textArea17);
        textlist.add(textArea18);
        textlist.add(textArea19);
        textlist.add(textArea20);
        textlist.add(textArea21);
        textlist.add(textArea22);
        textlist.add(textArea23);
        textlist.add(textArea24);
        textlist.add(textArea25);
        return textlist;
    }
    public ArrayList<JPanel> getPanellist() {
        panelList.add(box1);
        panelList.add(box2);
        panelList.add(box3);
        panelList.add(box4);
        panelList.add(box5);
        panelList.add(box6);
        panelList.add(box7);
        panelList.add(box8);
        panelList.add(box9);
        panelList.add(box10);
        panelList.add(box11);
        panelList.add(box12);
        panelList.add(box13);
        panelList.add(box14);
        panelList.add(box15);
        panelList.add(box16);
        panelList.add(box17);
        panelList.add(box18);
        panelList.add(box19);
        panelList.add(box20);
        panelList.add(box21);
        panelList.add(box22);
        panelList.add(box23);
        panelList.add(box24);
        panelList.add(box25);
        return panelList;
    }
    public int[] getColors()
    {
        return this.colors;
    }
    public int[] getNumbers()
    {
        return this.numbers;
    }
    public void setColors( int[] colors)
    {
        this.colors = colors;
    }
    public void setNumbers( int[] numbers)
    {
        this.numbers = numbers;
    }
    public JTextArea getDownHints()
    {
        return this.downHints;
    }
    public JTextArea getAcrossHints()
    {
        return this.acrossHints;
    }
    public JTextArea getLog()
    {
        return this.log;
    }
    public ArrayList<String> getAcrossList()
    {
        return this.acrossList;
    }
    public ArrayList<String> getDownList()
    {
        return this.downList;
    }
    public void setAcrossList( ArrayList<String> acrossList)
    {
        this.acrossList = acrossList;
    }
    public void setDownList( ArrayList<String> downList)
    {
        this.downList = downList;
    }

    public void fillGrid()
    {
        getLog().append( "\nRetrieve crossword...");
        int[] colorsArr;
        colorsArr = this.getColors();
        int[] numbersArr;
        numbersArr = this.getNumbers();
        for( int i = 0; i < 25; i++)
        {
            if( colorsArr[i] == 1)
            {
                getPanellist().get(i).setBackground(dark);
                getTextlist().get(i).setBackground( dark);
                getButtonlist().get(i).setBackground( dark);
            }
            if( numbersArr[i] != 0)
            {
                getButtonlist().get(i).setText("" + numbersArr[i]);
            }
        }
        getLog().append( "\nCrossword retrieval complete!");
        CheckWords c = new CheckWords();
        Thread t = new Thread(c);
        t.start();
    }
    public void fillHints()
    {
        String across = "";
        String down = "";
        getLog().append( "\nRetrieve hints...");
        for( int i = 0; i < this.acrossList.size(); i++)
        {
            across = across + this.acrossList.get(i) + "\n";
        }
        getAcrossHints().setText( across);
        getLog().append( "\nGot Across Hints!");
        for( int i = 0; i < this.downList.size(); i++)
        {
            down = down + this.downList.get(i) + "\n";
        }
        getDownHints().setText( down);
        getLog().append( "\nGot Down Hints!");
        getLog().append( "\nHints retrieval complete!");
    }

    class CheckWords implements Runnable
    {
        public void run()
        {
            check();
        }
        public void check()
        {
            for( int i = 0; i < words.size(); i++)
            {
                textArea2.setText( "" + words.get(i).charAt(0));
                textArea3.setText( "" + words.get(i).charAt(1));
                textArea4.setText( "" + words.get(i).charAt(2));
                textArea5.setText( "" + words.get(i).charAt(3));
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                if( words.get(i) != "BEST")
                {
                    textArea21.setForeground( Color.RED);
                    textArea22.setForeground( Color.RED);
                    textArea23.setForeground( Color.RED);
                    textArea24.setForeground( Color.RED);
                }
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                textArea21.setForeground( dark);
                textArea22.setForeground( dark);
                textArea23.setForeground( dark);
                textArea24.setForeground( dark);
            }
        }

    }
    public static void main(String[] args) throws IOException
    {
        SolvePuzzle solve = new SolvePuzzle();
        Crossword g = solve.getCrossword();
        CrosswordGUI crossword = new CrosswordGUI();
        JFrame frame0 = new JFrame("Which Crossword?");
        String option = (String) JOptionPane.showInputDialog(frame0,
                "Which crossword would you like to solve?",
                "Crossword to Solve",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]  );
        if( option == "Today") { System.out.println("Retrieving today's puzzle...Please wait"); g.readGridFromUrl();}
        else if( option == "Oct 24, 2017"){ System.out.println("Retrieving saved puzzle...Please wait"); g.readGridFromFile("crosswords/October 24, 2017.html");}
        else if( option == "Nov 8, 2017"){ System.out.println("Retrieving saved puzzle...Please wait");g.readGridFromFile("crosswords/November 8, 2017.html");}
        else if( option == "Nov 14, 2017") { System.out.println("Retrieving saved puzzle...Please wait");g.readGridFromFile("crosswords/November 14, 2017.html");}
        else if( option == "Nov 15, 2017") { System.out.println("Retrieving saved puzzle...Please wait");g.readGridFromFile("crosswords/November 15, 2017.html");}
        else if( option == "Dec 12, 2017") { System.out.println("Retrieving saved puzzle...Please wait");g.readGridFromFile("crosswords/December 12, 2017.html");}
        else { crossword.getLog().append( "Cannot display puzzle"); }

        JFrame frame;
        frame = new JFrame("CS461 faglAIno Crossword");
        crossword.setColors(g.getColors());
        crossword.setNumbers( g.getNumbers());
        crossword.setAcrossList( g.getAcrossHints());
        crossword.setDownList( g.getDownHints());
        frame.setContentPane(crossword.CWPanel);
        frame.setLocation(400,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        JFrame solveframe;
        solveframe = new JFrame("Solve");
        solveframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        solveframe.setContentPane( solve.solvePanel);
        solveframe.setLocation(20,150);
        solveframe.pack();
        solveframe.setVisible(true);

        SolutionGUI solution = new SolutionGUI( g.getColors(), g.getNumbers(), g.getSolutionArr());
        JFrame solutionframe;
        solutionframe = new JFrame("Solution");
        solutionframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        solutionframe.setContentPane( solution.CWPanel);
        solutionframe.setLocation(1020,150);
        solutionframe.pack();
        solutionframe.setVisible(true);
        solve.solve();
    }
}
