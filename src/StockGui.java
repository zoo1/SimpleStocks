import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zach
 */
public class StockGui extends JPanel{

    private final GridLayout layout = new GridLayout(7,1);
    public StockGui() throws FileNotFoundException, IOException  {
        
        setLayout(layout);
        this.add(new Control(this));
        BufferedReader br = new BufferedReader(new FileReader("stocks")); 
        String line;
        while ((line = br.readLine()) != null) 
        {
            Singlestock temp =new Singlestock(Arrays.asList(line.split(" ")).get(0),Double.parseDouble(Arrays.asList(line.split(" ")).get(1)));
            this.add(temp);
            temp.addComponentListener(new StockRemoverListener(this));
        }
        br.close();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    createAndShowGUI();
                } catch (IOException ex) {
                    Logger.getLogger(StockGui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private static void createAndShowGUI() throws IOException {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StockGui ist = new StockGui();
        JScrollPane sp = new JScrollPane(ist);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        f.add(sp);
        f.pack();
        f.setVisible(true);

    }

}