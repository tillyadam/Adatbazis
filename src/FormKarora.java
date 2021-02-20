import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class FormKarora extends JFrame {
    KaroraDB db;

    private JPanel mainPanel;

    private JLabel lblMarka, lblModell, lblSzin, lblAr;
    private JTextField tfMarka, tfModell, tfSzin;
    private JSpinner sAr;
    private JButton btnHozzaad;

    private Karora karora;
    private boolean modosit;

    public FormKarora(KaroraDB db) {
        this.db = db;
        modosit = false;

        this.setTitle("Karóra hozzáadása");
        this.setSize(300, 260);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mainPanel = (JPanel) (this.getContentPane());

        lblMarka = new JLabel("Márka:");
        lblMarka.setBounds(20, 20, 100, 20);
        mainPanel.add(lblMarka);

        lblModell = new JLabel("Modell:");
        lblModell.setBounds(20, 60, 100, 20);
        mainPanel.add(lblModell);

        lblSzin = new JLabel("Szín:");
        lblSzin.setBounds(20, 100, 100, 20);
        mainPanel.add(lblSzin);

        lblAr = new JLabel("Ár (€):");
        lblAr.setBounds(20, 140, 100, 20);
        mainPanel.add(lblAr);

        tfMarka = new JTextField();
        tfMarka.setBounds(120, 20, 140, 20);
        mainPanel.add(tfMarka);

        tfModell = new JTextField();
        tfModell.setBounds(120, 60, 140, 20);
        mainPanel.add(tfModell);

        tfSzin = new JTextField();
        tfSzin.setBounds(120, 100, 140, 20);
        mainPanel.add(tfSzin);

        SpinnerModel arSpinnerModel = new SpinnerNumberModel(0, 0, 999999999, 1);
        sAr = new JSpinner(arSpinnerModel);
        sAr.setBounds(120, 140, 140, 20);
        mainPanel.add(sAr);

        btnHozzaad = new JButton("Hozzáad");
        btnHozzaad.setBounds(160, 180, 100, 20);
        btnHozzaad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String marka = tfMarka.getText().trim();
                if (marka.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Márka megadása kötelező");
                    return;
                }

                String modell = tfModell.getText().trim();
                if (modell.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Modell megadása kötelező");
                    return;
                }

                String szin = tfSzin.getText().trim();
                if (szin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Szín megadása kötelező");
                    return;
                }

                int ar = (int) sAr.getValue();
                if (modosit) {
                    try {
                        int id = karora.getId();
                        int sikeres = db.updateKarora(id, marka, modell, szin, ar);
                        String uzenet = String.format("%s módosítás", sikeres > 0 ? "Sikeres" : "Sikertelen");
                        JOptionPane.showMessageDialog(null, uzenet);
                    } catch (SQLException throwables) {
                        JOptionPane.showMessageDialog(null, "Adatbázis hiba\n" + throwables.getMessage());
                    }
                    dispose();
                } else {
                    try {
                        int sikeres = db.insertKarora(marka, modell, szin, ar);
                        String uzenet = String.format("%s hozzáadás", sikeres > 0 ? "Sikeres" : "Sikertelen");
                        JOptionPane.showMessageDialog(null, uzenet);
                    } catch (SQLException throwables) {
                        JOptionPane.showMessageDialog(null, "Adatbázis hiba\n" + throwables.getMessage());
                    }
                }
            }
        });
        mainPanel.add(btnHozzaad);

        this.setVisible(true);
    }

    public FormKarora(KaroraDB db, Karora k) {
        this(db);
        this.karora = k;
        this.setTitle(karora.getModell()+" adatainak módosítása");
        this.btnHozzaad.setText("Módosít");
        this.modosit = true;
        this.tfMarka.setText(karora.getMarka());
        this.tfModell.setText(karora.getModell());
        this.tfSzin.setText(karora.getSzin());
        this.sAr.setValue(karora.getAr());
    }
}
