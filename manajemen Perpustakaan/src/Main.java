import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Buku {
    private String judul;
    private String pengarang;
    private int tahunTerbit;

    public Buku(String judul, String pengarang, int tahunTerbit) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
    }

    public String toString() {
        return "Judul: " + judul + ", Pengarang: " + pengarang + ", Tahun Terbit: " + tahunTerbit;
    }
}

class Perpustakaan {
    private List<Buku> koleksiBuku;

    public Perpustakaan() {
        this.koleksiBuku = new ArrayList<>();
    }

    public void tambahBuku(Buku buku) {
        koleksiBuku.add(buku);
    }

    public List<Buku> getKoleksiBuku() {
        return koleksiBuku;
    }
}

class PerpustakaanGUI extends JFrame {

    private Perpustakaan perpustakaan;
    private JTextArea outputArea;
    private JTextField judulField, pengarangField, tahunTerbitField;

    public PerpustakaanGUI() {
        super("Manajemen Perpustakaan");

        perpustakaan = new Perpustakaan();

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        outputArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane);

        add(new JLabel("Judul: "));
        judulField = new JTextField();
        add(judulField);

        add(new JLabel("Pengarang: "));
        pengarangField = new JTextField();
        add(pengarangField);

        add(new JLabel("Tahun Terbit: "));
        tahunTerbitField = new JTextField();
        add(tahunTerbitField);

        JButton tambahButton = new JButton("Tambah Buku");
        tambahButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tambahBuku();
            }
        });
        add(tambahButton);

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void tambahBuku() {
        String judul = judulField.getText();
        String pengarang = pengarangField.getText();
        int tahunTerbit = Integer.parseInt(tahunTerbitField.getText());

        Buku buku = new Buku(judul, pengarang, tahunTerbit);
        perpustakaan.tambahBuku(buku);

        refreshOutputArea();
        clearInputFields();
    }

    private void refreshOutputArea() {
        outputArea.setText("");
        for (Buku buku : perpustakaan.getKoleksiBuku()) {
            outputArea.append(buku.toString() + "\n");
        }
    }

    private void clearInputFields() {
        judulField.setText("");
        pengarangField.setText("");
        tahunTerbitField.setText("");
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PerpustakaanGUI());
    }
}
