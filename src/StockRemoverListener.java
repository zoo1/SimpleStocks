
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zach
 */
public class StockRemoverListener implements ComponentListener{

    StockGui goo;
    StockRemoverListener(StockGui gui)
    {
        goo=gui;
    }
    @Override
    public void componentResized(ComponentEvent ce) {}

    @Override
    public void componentMoved(ComponentEvent ce) {}

    @Override
    public void componentShown(ComponentEvent ce) {}
    
    @Override
                public void componentHidden(ComponentEvent ce) {
                    
                        
                    try{    
                        BufferedReader filein;
                        filein = new BufferedReader(new FileReader("stocks"));
                        String wholef="";
                        String line, ticker;
                        if(((Singlestock)(ce.getComponent())).IsBuy())
                            ticker="0 "+((Singlestock)(ce.getComponent())).Getticker();
                        else
                            ticker="1 "+((Singlestock)(ce.getComponent())).Getticker();
                        while ((line = filein.readLine()) != null) {
                            if(!line.contains(ticker))
                                wholef=wholef+line+"\n";
                        }
                        filein.close();
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("stocks"));
                        bufferedWriter.write(wholef);
                        bufferedWriter.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(StockGui.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(StockGui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    goo.remove(ce.getComponent());
                    goo.updateUI();
                }
    
}
