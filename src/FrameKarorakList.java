import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class FrameKarorakList extends JFrame {
    private KaroraDB db;

    private JList<Karora> karoraJList;
    private DefaultListModel<Karora> karoraDefaultListModel;
    private JScrollPane karoraScrollPane;

    private JPanel mainPanel;
    private JButton btnHozzaad, btnModosit, btnTorol;

    public FrameKarorakList() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        karoraDefaultListModel = new DefaultListModel<>();


        try {
            db = new KaroraDB();
            loadKarorak();
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(null, "Hiba a kapcsolódáskor :c");
            dispose();
            return;
        }

        this.setTitle("Karórák");
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        mainPanel = (JPanel) (this.getContentPane());

        karoraJList = new JList<>(karoraDefaultListModel);
        karoraJList.setFont(new Font("Courier New", Font.PLAIN, 14));
        karoraJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        karoraScrollPane = new JScrollPane(karoraJList);
        karoraScrollPane.setBounds(20, 20, 540, 300);
        mainPanel.add(karoraScrollPane);

        btnHozzaad = new JButton("Hozzáad");
        btnHozzaad.setBounds(20, 330, 100, 20);
        btnHozzaad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormKarora insertForm =new FormKarora(db);
                setVisible(false);
                insertForm.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setVisible(true);
                        try {
                            loadKarorak();
                        } catch (SQLException throwables) {
                            JOptionPane.showMessageDialog(null, "Hiba a lista frissítésekor\n"+throwables.getMessage());
                        }
                        super.windowClosed(e);
                    }
                });

            }
        });
        mainPanel.add(btnHozzaad);

        btnTorol = new JButton("Törlés");
        btnTorol.setBounds(140, 330, 100, 20);
        btnTorol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (karoraJList.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(null, "Nincs kijelölve karóra");
                    return;
                }
                Karora torlendo = karoraJList.getSelectedValue();
                int biztos = JOptionPane.showConfirmDialog(null,
                        "Biztos kívánja törölni az alábbi karórát? - " + torlendo.getModell() + " " + torlendo.getModell());
                if (biztos == JOptionPane.YES_OPTION) {
                    try {
                        int sikeres = db.deleteKarora(torlendo.getId());
                        loadKarorak();
                        String uzenet = String.format("%s törlés ", sikeres > 0 ? "Sikeres" : "Sikertelen");
                        JOptionPane.showMessageDialog(null, uzenet);

                    } catch (SQLException throwables) {
                        JOptionPane.showMessageDialog(null, "Sikertelen törlés\n" + throwables.getMessage());
                    }
                }
            }
        });
        mainPanel.add(btnTorol);

        btnModosit = new JButton("Módosít");
        btnModosit.setBounds(260, 330, 100, 20);
        btnModosit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (karoraJList.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(null, "Nincs kijelölve karóra");
                    return;
                }
                Karora modositando = karoraJList.getSelectedValue();

                FormKarora modositForm =new FormKarora(db,modositando);
                setVisible(false);
                modositForm.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setVisible(true);
                        try {
                            loadKarorak();
                        } catch (SQLException throwables) {
                            JOptionPane.showMessageDialog(null, "Hiba a lista frissítésekor\n"+throwables.getMessage());
                        }
                        super.windowClosed(e);
                    }
                });
            }
        });
        mainPanel.add(btnModosit);


        this.setVisible(true);
    }

    private void loadKarorak() throws SQLException {
        ArrayList<Karora> karoraArrayList = db.getKarorak();
        karoraDefaultListModel.clear();
        for (Karora k : karoraArrayList) {
            karoraDefaultListModel.addElement(k);
        }
    }
}
