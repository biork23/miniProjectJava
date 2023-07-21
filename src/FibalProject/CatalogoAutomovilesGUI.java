package FibalProject;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

class Automovil1 {
    private String nombre;
    private String tipo;
    private File imagen;

    public Automovil1(String nombre, String tipo, File imagen) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public File getImagen() {
        return imagen;
    }
}

public class CatalogoAutomovilesGUI extends JFrame {
    private List<Automovil1> automoviles;
    private DefaultListModel<String> listModel;
    private JList<String> listaAutomoviles;

    public CatalogoAutomovilesGUI() {
        automoviles = new ArrayList<>();
        listModel = new DefaultListModel<>();
        listaAutomoviles = new JList<>(listModel);

        setTitle("Catálogo de Automóviles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de lista de automóviles
        JPanel panelLista = new JPanel();
        panelLista.setLayout(new BorderLayout());

        listaAutomoviles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaAutomoviles);
        panelLista.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelLista.add(panelBotones, BorderLayout.SOUTH);

        // Panel de información del automóvil seleccionado
        JPanel panelInformacion = new JPanel(new GridLayout(2, 1));
        JLabel lblNombre = new JLabel();
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblTipo = new JLabel();
        lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
        panelInformacion.add(lblNombre);
        panelInformacion.add(lblTipo);

        // Panel de imagen del automóvil
        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

        // Panel contenedor de la información e imagen
        JPanel panelDetalleAutomovil = new JPanel(new BorderLayout());
        panelDetalleAutomovil.add(panelInformacion, BorderLayout.NORTH);
        panelDetalleAutomovil.add(lblImagen, BorderLayout.CENTER);
        panelDetalleAutomovil.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Agregar paneles al marco
        add(panelLista, BorderLayout.WEST);
        add(panelDetalleAutomovil, BorderLayout.CENTER);

        // Manejador de eventos del botón "Agregar"
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarAutomovil();
            }
        });

        // Manejador de eventos del botón "Eliminar"
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarAutomovil();
            }
        });

        // Manejador de eventos de selección de un automóvil en la lista
        listaAutomoviles.addListSelectionListener(e -> {
            int index = listaAutomoviles.getSelectedIndex();
            if (index != -1) {
                Automovil1 automovilSeleccionado = automoviles.get(index);
                lblNombre.setText(automovilSeleccionado.getNombre());
                lblTipo.setText(automovilSeleccionado.getTipo());

                try {
                    Image image = ImageIO.read(automovilSeleccionado.getImagen());
                    ImageIcon imagenIcon = new ImageIcon(image);
                    lblImagen.setIcon(imagenIcon);
                } catch (Exception ex) {
                    lblImagen.setIcon(null);
                }
            }
        });

        setSize(800, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void agregarAutomovil() {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del automóvil:", "Agregar Automóvil",
                JOptionPane.PLAIN_MESSAGE);
        if (nombre != null && !nombre.isEmpty()) {
            String tipo = JOptionPane.showInputDialog(this, "Ingrese el tipo del automóvil:", "Agregar Automóvil",
                    JOptionPane.PLAIN_MESSAGE);
            if (tipo != null && !tipo.isEmpty()) {
                File imagen = seleccionarImagen();
                if (imagen != null) {
                    Automovil1 automovil = new Automovil1(nombre, tipo, imagen);
                    automoviles.add(automovil);
                    listModel.addElement(nombre);
                }
            }
        }
    }

    private File seleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    private void eliminarAutomovil() {
        int index = listaAutomoviles.getSelectedIndex();
        if (index != -1) {
            automoviles.remove(index);
            listModel.remove(index);
            listaAutomoviles.clearSelection();
            resetInformacion();
        }
    }

    private void resetInformacion() {
        AbstractButton lblNombre = null;
		lblNombre.setText("");
        AbstractButton lblTipo = null;
		lblTipo.setText("");
        AbstractButton lblImagen = null;
		lblImagen.setIcon(null);
    }

    public static void main(String[] args) {
        new CatalogoAutomovilesGUI();
    }
}

