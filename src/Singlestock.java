
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zach
 */
public class Singlestock extends javax.swing.JPanel {

    public Singlestock(String ticker, double d) {
        initComponents();
        TicketSymbol.setText(ticker);
        Target.setText(Double.toString(d));
        Update();
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Update = new javax.swing.JButton();
        TicketSymbol = new javax.swing.JLabel();
        Current = new javax.swing.JLabel();
        Target = new javax.swing.JLabel();
        Percentage = new javax.swing.JLabel();
        Delete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/refresh.png"))); // NOI18N
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        TicketSymbol.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        TicketSymbol.setText("TicketSymbol");

        Current.setText("Current");

        Target.setText("Target");

        Percentage.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        Percentage.setText("Percentage");

        Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GjK9w.gif"))); // NOI18N
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel1.setText("Buy:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Current)
                        .addGap(18, 18, 18)
                        .addComponent(Target)
                        .addGap(97, 97, 97)
                        .addComponent(Percentage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TicketSymbol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TicketSymbol)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Percentage)
                    .addComponent(Current)
                    .addComponent(Target))
                .addGap(36, 36, 36))
            .addGroup(layout.createSequentialGroup()
                .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        Update();
    }//GEN-LAST:event_UpdateActionPerformed

    /**
     * Triggers the Listener to remove this component
    */
    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_DeleteActionPerformed
    
    public String Getticker()
    {
        return TicketSymbol.getText();
    }
    /**
     * Updates the current data of the stock
    */
    public void Update()
    {
        try {
            URL oracle = new URL("http://finance.yahoo.com/d/quotes.csv?s="+TicketSymbol.getText()+"&f=sl1op");
            BufferedReader in = new BufferedReader( new InputStreamReader(oracle.openStream()));
            String Stats=in.readLine();
            double Price=Double.parseDouble(Arrays.asList(Stats.split(",")).get(1));
            Current.setText(String.format("%.2f", Price));
            double Pcent=100*(Price-Double.valueOf(Target.getText()))/Double.valueOf(Target.getText());
            Percentage.setText(String.format("%.2f", Pcent)+"%");
            in.close();
            if(Pcent>=0)
                Percentage.setForeground(Color.red);
            else
                Percentage.setForeground(new Color(34,139,34));
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Singlestock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Singlestock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Current;
    private javax.swing.JButton Delete;
    private javax.swing.JLabel Percentage;
    private javax.swing.JLabel Target;
    private javax.swing.JLabel TicketSymbol;
    private javax.swing.JButton Update;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
