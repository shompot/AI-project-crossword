import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * Created by Shiha on 12/14/2017.
 */
public class TextComponentLogger implements ActivityLogger{
    private final JTextComponent target;
    public TextComponentLogger(JTextComponent target) {
        this.target = target;
    }

    public void logAction(final String message){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                target.setText(String.format("%s%s%n",
                        target.getText(),
                        message));
            }
        });
    }
}