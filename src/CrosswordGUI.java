import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Shiha on 11/11/2017.
 */
public class CrosswordGUI {
    public static final String[] options = { "Today", "Oct 24, 2017", "Nov 8, 2017", "November 14,2017" };
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
    private ArrayList<String> acrossList = new ArrayList<String>();
    private ArrayList<String> downList = new ArrayList<String>();
    private ArrayList<JButton> buttonlist;
    private int[] colors;
    private int[] numbers;
    private Color dark = new Color(123,86,78);
    public CrosswordGUI()
    {
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
    public ArrayList<JButton> getbuttonlist()
    {
        ArrayList<JButton> buttonList= new ArrayList<JButton>();
        buttonList.add( button1);
        buttonList.add( button2);
        buttonList.add( button3);
        buttonList.add( button4);
        buttonList.add( button5);
        buttonList.add( button6);
        buttonList.add( button7);
        buttonList.add( button8);
        buttonList.add( button9);
        buttonList.add( button10);
        buttonList.add( button11);
        buttonList.add( button12);
        buttonList.add( button13);
        buttonList.add( button14);
        buttonList.add( button15);
        buttonList.add( button16);
        buttonList.add( button17);
        buttonList.add( button18);
        buttonList.add( button19);
        buttonList.add( button20);
        buttonList.add( button21);
        buttonList.add( button22);
        buttonList.add( button23);
        buttonList.add( button24);
        buttonList.add( button25);
        return buttonList;
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
                getbuttonlist().get(i).setBackground( dark);
            }
            if( numbersArr[i] != 0)
            {
                getbuttonlist().get(i).setText("" + numbersArr[i]);
            }
        }
        getLog().append( "\nCrossword retrieval complete!");
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
    public static void main(String[] args) throws IOException
    {
        Crossword g = new Crossword();
        CrosswordGUI crossword = new CrosswordGUI();
        JFrame frame0 = new JFrame("Which Crossword?");
        String option = (String) JOptionPane.showInputDialog(frame0,
                "Which crossword would you like to solve?",
                "Crossword to Solve",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if( option == "Today") {g.readGridFromUrl();}
        else if( option == "Oct 24, 2017"){ g.readGridFromFile("crosswords/October 24, 2017.html");}
        else if( option == "Nov 8, 2017"){ g.readGridFromFile("crosswords/November 8, 2017.html");}
        else if( option == "November 14,2017") { g.readGridFromFile("crosswords/November 14, 2017.html");}
        else { crossword.getLog().append( "Cannot display puzzle"); }

        int index = 0;
        JFrame frame;
        frame = new JFrame("CS461 faglAIno Crossword");
        crossword.setColors(g.getColors());
        crossword.setNumbers( g.getNumbers());
        crossword.setAcrossList( g.getAcrossHints());
        crossword.setDownList( g.getDownHints());
        frame.setContentPane(crossword.CWPanel);
        frame.setLocation(400,150);
//        System.out.println(g.toString());
//        for (int i = 0; i < 5; i ++){
//            for (int j = 0; j < 5; j++)
//                System.out.print(g.getColors()[index++] + " ");
//            System.out.print("\n");
//        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);



    }
}
