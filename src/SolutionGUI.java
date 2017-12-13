import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Shiha on 12/12/2017.
 */
public class SolutionGUI
{
    public JPanel solutionPanel;
    public JPanel CWPanel;
    private JPanel box1;
    private JButton button1;
    private JTextArea textArea1;
    private JPanel box2;
    private JButton button2;
    private JTextArea textArea2;
    private JPanel box3;
    private JButton button3;
    private JTextArea textArea3;
    private JPanel box4;
    private JButton button4;
    private JTextArea textArea4;
    private JPanel box5;
    private JButton button5;
    private JTextArea textArea5;
    private JPanel box6;
    private JButton button6;
    private JTextArea textArea6;
    private JPanel box7;
    private JButton button7;
    private JTextArea textArea7;
    private JPanel box8;
    private JButton button8;
    private JTextArea textArea8;
    private JPanel box9;
    private JButton button9;
    private JTextArea textArea9;
    private JPanel box10;
    private JButton button10;
    private JTextArea textArea10;
    private JPanel box11;
    private JButton button11;
    private JTextArea textArea11;
    private JPanel box12;
    private JButton button12;
    private JTextArea textArea12;
    private JPanel box13;
    private JButton button13;
    private JTextArea textArea13;
    private JPanel box14;
    private JButton button14;
    private JTextArea textArea14;
    private JPanel box15;
    private JButton button15;
    private JTextArea textArea15;
    private JPanel box16;
    private JButton button16;
    private JTextArea textArea16;
    private JPanel box17;
    private JButton button17;
    private JTextArea textArea17;
    private JPanel box18;
    private JButton button18;
    private JTextArea textArea18;
    private JPanel box19;
    private JButton button19;
    private JTextArea textArea19;
    private JPanel box20;
    private JButton button20;
    private JTextArea textArea20;
    private JPanel box21;
    private JButton button21;
    private JTextArea textArea21;
    private JPanel box22;
    private JButton button22;
    private JTextArea textArea22;
    private JPanel box23;
    private JButton button23;
    private JTextArea textArea23;
    private JPanel box24;
    private JButton button24;
    private JTextArea textArea24;
    private JPanel box25;
    private JButton button25;
    private JTextArea textArea25;
    private ArrayList<JPanel> panelList = new ArrayList<JPanel>();
    private ArrayList<JButton> buttonlist = new ArrayList<JButton>();;
    private ArrayList<JTextArea> textlist = new ArrayList<JTextArea>();;
    private int[] colors;
    private int[] numbers;
    private char[] solution;
    private Color dark = new Color(123,86,78);
    public SolutionGUI( int[] colors, int[] numbers, char[] solution)
    {
        this.colors = colors;
        this.numbers = numbers;
        this.solution = solution;
        fillGrid();
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
    public char[] getSolution()
    {
        return this.solution;
    }
    public void setColors( int[] colors)
    {
        this.colors = colors;
    }
    public void setNumbers( int[] numbers)
    {
        this.numbers = numbers;
    }
    public void setSolution( char[] solution)
    {
        this.solution = solution;
    }
    public void fillGrid()
    {
        int[] colorsArr;
        colorsArr = this.getColors();
        int[] numbersArr;
        numbersArr = this.getNumbers();
        char[] solution;
        solution = this.getSolution();
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
            getTextlist().get(i).setText("" + solution[i]);
        }
    }
}
