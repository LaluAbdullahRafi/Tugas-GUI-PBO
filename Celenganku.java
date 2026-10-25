import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Celenganku extends JFrame implements ActionListener, ItemListener {
    private JTextField txtNominal;
    private JCheckBox chkKonfirmasi;
    private JButton btnSetor, btnTarik;
    private JLabel lblSaldo;
    
    private int saldo = 0;

    public Celenganku() {
        setTitle("Celengan Super Simpel - Tugas GUI");
        setSize(340, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 12));

        lblSaldo = new JLabel("Saldo: Rp 0");
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblSaldo);

        add(new JLabel("Masukkan Nominal (Rp):"));
        txtNominal = new JTextField(15);
        add(txtNominal);

        chkKonfirmasi = new JCheckBox("Aktifkan Transaksi Cepat");
        chkKonfirmasi.addItemListener(this);
        add(chkKonfirmasi);

        btnSetor = new JButton("Setor");
        btnTarik = new JButton("Tarik");

        btnSetor.addActionListener(this);
        btnTarik.addActionListener(this);

        add(btnSetor);
        add(btnTarik);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int jumlah = Integer.parseInt(txtNominal.getText().trim());
            
            if (e.getSource() == btnSetor) {
                saldo += jumlah;
                lblSaldo.setText("Saldo: Rp " + saldo);
                txtNominal.setText("");
            } else if (e.getSource() == btnTarik) {
                if (jumlah > saldo) {
                    JOptionPane.showMessageDialog(this, "Saldo tidak cukup!");
                } else {
                    saldo -= jumlah;
                    lblSaldo.setText("Saldo: Rp " + saldo);
                    txtNominal.setText("");
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!");
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (chkKonfirmasi.isSelected()) {
            setTitle("Celengan (Mode Cepat Aktif)");
        } else {
            setTitle("Celengan Super Simpel - Tugas GUI");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Celenganku().setVisible(true);
        });
    }
}