import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Shiha on 11/11/2017.
 */
public class CrosswordGUI {
    private JPanel CWPanel;
    private JEditorPane acrossPane;
    private JEditorPane downPane;
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
    private ArrayList<JButton> buttonlist;
    private int[] colors;
    private int[] numbers;
    private Color dark = new Color(123,86,78);
    public CrosswordGUI()
    {
        //todo
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
    public JEditorPane getDownPane()
    {
        return this.downPane;
    }
    public JEditorPane getAcrossPane()
    {
        return this.acrossPane;
    }
    public JTextArea getLog()
    {
        return this.log;
    }
    public void fillGrid()
    {
        int[] temp;
        temp = this.getColors();
        for( int i = 0; i < 25; i++)
        {
            if( temp[i] == 1)
            {
                getbuttonlist().get(i).setBackground( dark);
            }
        }
    }

    public static void main(String[] args)
    {
        Grid g = new Grid();
        g.readGrid("crosswords/November 14, 2017.html");
        int [] colors = g.getColors();
        int index = 0;
        JFrame frame;
        frame = new JFrame("CS461 faglAIno Crossword");
        CrosswordGUI crossword = new CrosswordGUI();
        crossword.setColors(colors);
        crossword.fillGrid();
        frame.setContentPane(crossword.CWPanel);
        frame.setLocation(400,150);

        System.out.println(g.toString());
        for (int i = 0; i < 5; i ++){
            for (int j = 0; j < 5; j++)
                System.out.print(colors[index++] + " ");
            System.out.print("\n");
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);



    }
}