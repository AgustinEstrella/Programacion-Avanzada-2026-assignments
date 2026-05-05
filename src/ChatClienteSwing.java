import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class ChatClienteSwing extends JFrame {

    private JTextArea historial;
    private JTextField input;
    private JLabel labelHora;
    private DefaultListModel<String> modeloClientes;
    private JList<String> listaClientes;

    private DataInputStream in;
    private DataOutputStream out;

    public ChatClienteSwing() {
        setTitle("Chat Cliente");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // PANEL IZQUIERDO (Clientes)
        modeloClientes = new DefaultListModel<>();
        listaClientes = new JList<>(modeloClientes);
        listaClientes.setBorder(BorderFactory.createTitledBorder("Clientes"));
        add(new JScrollPane(listaClientes), BorderLayout.WEST);

        // PANEL CENTRAL
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BorderLayout());

        input = new JTextField();
        input.setBorder(BorderFactory.createTitledBorder("Ingrese el mensaje"));
        panelCentro.add(input, BorderLayout.NORTH);

        historial = new JTextArea();
        historial.setEditable(false);
        historial.setBorder(BorderFactory.createTitledBorder("Historial"));
        panelCentro.add(new JScrollPane(historial), BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);

        // PANEL SUPERIOR
        JPanel panelTop = new JPanel(new BorderLayout());
        labelHora = new JLabel("Fecha y hora");
        panelTop.add(labelHora, BorderLayout.EAST);
        add(panelTop, BorderLayout.NORTH);

        // PANEL INFERIOR (botones)
        JPanel panelBotones = new JPanel();

        JButton btnHora = new JButton("Hora");
        JButton btnResolver = new JButton("Resolver");
        JButton btnClientes = new JButton("Ver Clientes");
        JButton btnEnviar = new JButton("Enviar");

        panelBotones.add(btnHora);
        panelBotones.add(btnResolver);
        panelBotones.add(btnClientes);
        panelBotones.add(btnEnviar);

        add(panelBotones, BorderLayout.SOUTH);

        conectar();

        // EVENTOS
        btnEnviar.addActionListener(e -> enviarMensaje());
        input.addActionListener(e -> enviarMensaje());

        btnHora.addActionListener(e -> {
            try { out.writeInt(1); } catch (Exception ex) {}
        });

        btnResolver.addActionListener(e -> {
            try {
                String op = JOptionPane.showInputDialog("Ej: RESOLVE \"5+3*2\"");
                out.writeInt(2);
                out.writeUTF(op);
            } catch (Exception ex) {}
        });

        btnClientes.addActionListener(e -> {
            try { out.writeInt(3); } catch (Exception ex) {}
        });
    }

    private void conectar() {
        try {
            Socket socket = new Socket("localhost", 6000);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            String msg = in.readUTF();
            String nombre = JOptionPane.showInputDialog(msg);
            out.writeUTF(nombre);

            new Thread(() -> {
                try {
                    while (true) {
                        String mensaje = in.readUTF();
                        procesarMensaje(mensaje);
                    }
                } catch (Exception e) {
                    historial.append("\nDesconectado");
                }
            }).start();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error conexión");
        }
    }

    private void procesarMensaje(String msg) {

        if (msg.startsWith("Clientes conectados:")) {
            modeloClientes.clear();
            String[] lineas = msg.split("\\n");
            for (int i = 1; i < lineas.length; i++) {
                modeloClientes.addElement(lineas[i].replace("- ", ""));
            }
        }
        else if (msg.matches("\\d{4}-.*")) {
            labelHora.setText(msg);
        }
        else {
            historial.append("\n" + msg);
        }
    }

    private void enviarMensaje() {
        try {
            String msg = input.getText();
            out.writeInt(4);
            out.writeUTF(msg);
            input.setText("");
        } catch (Exception e) {}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatClienteSwing().setVisible(true));
    }
}



