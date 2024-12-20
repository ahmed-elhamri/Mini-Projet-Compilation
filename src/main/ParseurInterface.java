/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author ELHAMRI
 */
public class ParseurInterface extends javax.swing.JFrame {
    private JTextArea inputArea;
    private JButton submitButton;
    private JButton clearButton;
    private JComboBox<String> exampleBox;

    /**
     * Creates new form ParseurInterface
     */
    public ParseurInterface() {
        // main panel 
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // input panel
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        JLabel titleLabel = new JLabel("Entrez votre phrase :");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        inputArea = new JTextArea(3, 30);
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(inputArea);
        
        // buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        submitButton = new JButton("Analyser");
        clearButton = new JButton("Effacer");
        
        // Style buttons
        submitButton.setPreferredSize(new Dimension(100, 30));
        clearButton.setPreferredSize(new Dimension(100, 30));
        
        // Create examples dropdown
        String[] examples = {
            "Sélectionner un exemple...",
            "le chat mange",
            "la souris mange le fromage",
            "aujourd'hui je mange une orange",
            "il court vite",
            "nous marchons lentement"
        };
        exampleBox = new JComboBox<>(examples);
        exampleBox.setPreferredSize(new Dimension(250, 30));
        
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        
        inputPanel.add(titleLabel, BorderLayout.NORTH);
        inputPanel.add(scrollPane, BorderLayout.CENTER);
        
        mainPanel.add(exampleBox, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Add action listeners
        submitButton.addActionListener(e -> testParse());
        clearButton.addActionListener(e -> inputArea.setText(""));
        exampleBox.addActionListener(e -> {
            if (exampleBox.getSelectedIndex() > 0) {
                inputArea.setText(exampleBox.getSelectedItem().toString());
            }
        });
        
        // Set frame properties
        pack();
        setMinimumSize(new Dimension(400, 300));
        setLocationRelativeTo(null);
    }
    private void testParse() {
        String input = inputArea.getText().trim();
        if (input.isEmpty()) {
            showMessage("Erreur", "Veuillez entrer une phrase!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            TokenManager tokenManager = new TokenManager(input);
            Parseur parser = new Parseur(tokenManager);
            parser.parse();
            
            JDialog successDialog = new JDialog(this, "Succès", true);
            successDialog.setLayout(new BorderLayout(10, 10));
            
            JPanel dialogPanel = new JPanel(new BorderLayout(10, 10));
            dialogPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            JLabel successLabel = new JLabel("La phrase est syntaxiquement correcte !");
            successLabel.setFont(new Font("Arial", Font.BOLD, 14));
            successLabel.setForeground(new Color(0, 150, 0));
            
            JButton okButton = new JButton("OK");
            okButton.addActionListener(e -> successDialog.dispose());
            
            dialogPanel.add(successLabel, BorderLayout.CENTER);
            dialogPanel.add(okButton, BorderLayout.SOUTH);
            
            successDialog.add(dialogPanel);
            successDialog.pack();
            successDialog.setLocationRelativeTo(this);
            successDialog.setVisible(true);
            
        } catch (RuntimeException ex) {
            JDialog errorDialog = new JDialog(this, "Erreur de syntaxe", true);
            errorDialog.setLayout(new BorderLayout(10, 10));
            
            JPanel dialogPanel = new JPanel(new BorderLayout(10, 10));
            dialogPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            JLabel errorLabel = new JLabel(ex.getMessage());
            errorLabel.setFont(new Font("Arial", Font.BOLD, 14));
            errorLabel.setForeground(new Color(150, 0, 0));
            
            JButton okButton = new JButton("OK");
            okButton.addActionListener(e -> errorDialog.dispose());
            
            dialogPanel.add(errorLabel, BorderLayout.CENTER);
            dialogPanel.add(okButton, BorderLayout.SOUTH);
            
            errorDialog.add(dialogPanel);
            errorDialog.pack();
            errorDialog.setLocationRelativeTo(this);
            errorDialog.setVisible(true);
        }
    }
    private void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ParseurInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParseurInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParseurInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParseurInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

      try {
            // Set system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new ParseurInterface().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
