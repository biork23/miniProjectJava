package FibalProject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

// Clase abstracta para representar un automóvil
abstract class Automovil {
    private String nombre;
    private String tipo;
    private String imagen;

    public Automovil(String nombre, String tipo, String imagen) {
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

    public String getImagen() {
        return imagen;
    }

    public abstract void mostrarInformacion();
}

// Clase para un tipo específico de automóvil
class AutomovilEconomico extends Automovil {
    private String marca;
    private int anio;

    public AutomovilEconomico(String nombre, String tipo, String imagen, String marca, int anio) {
        super(nombre, tipo, imagen);
        this.marca = marca;
        this.anio = anio;
    }

    public String getMarca() {
        return marca;
    }

    public int getAnio() {
        return anio;
    }

    @Override
    public void mostrarInformacion() {
        String informacion = "Nombre: " + getNombre() + "\n" +
                             "Tipo: " + getTipo() + "\n" +
                             "Marca: " + getMarca() + "\n" +
                             "Año: " + getAnio();

        JOptionPane.showMessageDialog(null, informacion, "Información del automóvil", JOptionPane.INFORMATION_MESSAGE);
    }
}

// Clase para la GUI
public class ListaAutomovilesGUI extends JFrame {
    private List<Automovil> automoviles;
    private JPanel panelAutomoviles;
    private JTextField txtBusqueda;

    public ListaAutomovilesGUI() {
        automoviles = new ArrayList<>();
        cargarAutomoviles(); // Cargar la lista de automóviles

        setTitle("Lista de Automóviles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.CENTER));
        txtBusqueda = new JTextField(20);
        JButton btnBuscar = new JButton("Buscar");
        panelBusqueda.add(txtBusqueda);
        panelBusqueda.add(btnBuscar);

        // Panel de automóviles
        panelAutomoviles = new JPanel();
        panelAutomoviles.setLayout(new BoxLayout(panelAutomoviles, BoxLayout.Y_AXIS));

        // Crear los componentes para cada automóvil
        for (Automovil automovil : automoviles) {
            JPanel panelAutomovil = crearPanelAutomovil(automovil);
            panelAutomoviles.add(panelAutomovil);
        }

        JScrollPane scrollPane = new JScrollPane(panelAutomoviles);

        // Agregar los paneles al marco
        add(panelBusqueda, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Manejador de eventos del botón de búsqueda
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String busqueda = txtBusqueda.getText().toLowerCase();

                // Filtrar los automóviles según la búsqueda
                List<Automovil> automovilesFiltrados = filtrarAutomoviles(busqueda);

                // Actualizar la lista de automóviles mostrados
                actualizarPanelAutomoviles(automovilesFiltrados);
            }
        });

        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Cargar la lista de automóviles (se pueden agregar más automóviles)
    private void cargarAutomoviles() {
        automoviles.add(new AutomovilEconomico("Sedan", "Económico", "", "Toyota", 2022));
        automoviles.add(new AutomovilEconomico("Hatchback", "Económico", "hatchback.jpg", "Honda", 2021));
        automoviles.add(new AutomovilEconomico("SUV", "Económico", "suv.jpg", "Nissan", 2023));
        automoviles.add(new AutomovilEconomico("Coupé", "Económico", "coupe.jpg", "Mazda", 2020));
    }

    // Crear un panel para mostrar la información de un automóvil
    private JPanel crearPanelAutomovil(Automovil automovil) {
        JPanel panelAutomovil = new JPanel(new BorderLayout());

        // Cargar la imagen del automóvil
        ImageIcon imagenIcon = new ImageIcon(getClass().getResource(automovil.getImagen()));
        Image image = imagenIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel lblImagen = new JLabel(new ImageIcon(image));
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

        // Mostrar el nombre y el tipo del automóvil
        JLabel lblNombre = new JLabel(automovil.getNombre());
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblTipo = new JLabel(automovil.getTipo());
        lblTipo.setHorizontalAlignment(SwingConstants.CENTER);

        // Botón para mostrar más información del automóvil
        JButton btnMostrar = new JButton("Mostrar");
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                automovil.mostrarInformacion();
            }
        });

        // Panel contenedor
        JPanel panelInformacion = new JPanel(new GridLayout(2, 1));
        panelInformacion.add(lblNombre);
        panelInformacion.add(lblTipo);

        panelAutomovil.add(lblImagen, BorderLayout.NORTH);
        panelAutomovil.add(panelInformacion, BorderLayout.CENTER);
        panelAutomovil.add(btnMostrar, BorderLayout.SOUTH);
        panelAutomovil.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return panelAutomovil;
    }

    // Filtrar los automóviles según la búsqueda
    private List<Automovil> filtrarAutomoviles(String busqueda) {
        List<Automovil> automovilesFiltrados = new ArrayList<>();

        for (Automovil automovil : automoviles) {
            if (automovil.getNombre().toLowerCase().contains(busqueda)
                    || automovil.getTipo().toLowerCase().contains(busqueda)) {
                automovilesFiltrados.add(automovil);
            }
        }

        return automovilesFiltrados;
    }

    // Actualizar la lista de automóviles mostrados en el panel
    private void actualizarPanelAutomoviles(List<Automovil> automovilesMostrados) {
        panelAutomoviles.removeAll();

        for (Automovil automovil : automovilesMostrados) {
            JPanel panelAutomovil = crearPanelAutomovil(automovil);
            panelAutomoviles.add(panelAutomovil);
        }

        panelAutomoviles.revalidate();
        panelAutomoviles.repaint();
    }

    public static void main(String[] args) {
        new ListaAutomovilesGUI();
    }
}
