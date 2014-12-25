
import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * @author zach
 */
public class Control extends javax.swing.JPanel {
    StockGui goo;

    public Control(StockGui gui) {
        goo=gui;
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        StockTextfield = new javax.swing.JTextField();
        addstock = new javax.swing.JButton();
        RefreshAll = new javax.swing.JButton();
        TextPrice = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Error = new javax.swing.JLabel();
        Buy = new javax.swing.JComboBox();

        addstock.setText("Add Stock");
        addstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addstockActionPerformed(evt);
            }
        });

        RefreshAll.setText("Refresh All Stocks");
        RefreshAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshAllActionPerformed(evt);
            }
        });

        jLabel1.setText("Stock:");

        jLabel2.setText("Price:");

        Error.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        Error.setForeground(java.awt.Color.red);

        Buy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Buy", "Sell" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TextPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(StockTextfield))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Buy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addstock))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(RefreshAll)))
                .addGap(42, 42, 42))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(Error)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StockTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(Buy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addstock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(RefreshAll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Error))
        );
    }// </editor-fold>//GEN-END:initComponents

    /** 
     * Refreshes all the current single stocks in the application
    */
    private void RefreshAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshAllActionPerformed
        Component[] components=goo.getComponents();
        for (Component component : components) {
            if (!component.equals(this)) {
                ((Singlestock) component).Update();
            }
        }
    }//GEN-LAST:event_RefreshAllActionPerformed

    /**
     * When the add stock button is press verifies all the data in the stock and price field are correct, 
     * creates the component and updates storage file
     */
    private void addstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addstockActionPerformed
        try {
            //BlankSpace or Null Error checking
            if(StockTextfield.getText().contains(" ")||StockTextfield.getText().equals("")||TextPrice.getText().equals("")||TextPrice.getText().contains(" "))
            {
                throw new IOException("Blankspace or null");
            }
            //Already Contained Error checking
            Component[] components=goo.getComponents();
            for (Component component : components) {
                if (!component.equals(this)) {
                    if (StockTextfield.getText().equals(((Singlestock) component).Getticker()))
                        if(!((((Singlestock) component).IsBuy())^(Buy.getSelectedIndex()==0)))
                                {
                        throw new Exception("contained");
                    }
                }
            }
            //Actual Stock Error checking
            URL oracle = new URL("http://finance.yahoo.com/d/quotes.csv?s="+StockTextfield.getText()+"&f=sl1op");
            BufferedReader in = new BufferedReader( new InputStreamReader(oracle.openStream()));
            String Stats=in.readLine();
            if((Arrays.asList(Stats.split(",")).get(1)).contains("N/A"))
                throw new IOException("Invalid Stock");
            //Adds the component to the gui
            Singlestock temp=new Singlestock(StockTextfield.getText(),Double.parseDouble(TextPrice.getText()),Buy.getSelectedIndex()==0);
            goo.add(temp,1);
            temp.addComponentListener(new StockRemoverListener(goo));
            goo.updateUI();
            
            //Adds the stock data to the file
            BufferedReader filein= new BufferedReader(new FileReader("stocks"));
            String wholef=Buy.getSelectedIndex()+ " "+StockTextfield.getText()+" "+TextPrice.getText()+"\n",line;
            while ((line = filein.readLine()) != null) {
                wholef=wholef+line+"\n";
            }
            filein.close();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("stocks"));
            bufferedWriter.write(wholef);
            bufferedWriter.close();
            
        } catch (MalformedURLException ex) {
            Error.setText("Error: Could not verify Symbol");
        } catch (IOException ex) {
            Error.setText("Error: Could not verify Symbol");
        } catch (NumberFormatException ex) {
            Error.setText("Error: The price is incorrect");
        } catch (Exception ex) {
            Error.setText("Error: This Symbol is already used");
        }       
    }//GEN-LAST:event_addstockActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Buy;
    private javax.swing.JLabel Error;
    private javax.swing.JButton RefreshAll;
    private javax.swing.JTextField StockTextfield;
    private javax.swing.JTextField TextPrice;
    private javax.swing.JButton addstock;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
