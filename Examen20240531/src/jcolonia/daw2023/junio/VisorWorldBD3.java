package jcolonia.daw2023.junio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

/**
 * Aplicación de ventanas con una única ventana principal y una tabla de datos
 * central utilizada para consultar una base de datos de países.
 * 
 * @author <a href="dmartin.jcolonia@gmail.com">David H. Martín</a>
 * @version 4.0 (20250530)
 */
public class VisorWorldBD3 extends JFrame implements ActionListener {
	/** Número de serie, asociado a la versión de la clase. */
	private static final long serialVersionUID = 20240530000L;
	/** El panel general exterior. */
	private JPanel panelGeneral;
	/** El panel general que agrupa todos los posibles paneles de cada pestaña. */
	private JTabbedPane panelPestañas;
	/** El panel de contenido central de la única pestaña, con márgenes. */
	private JPanel panelPrincipal;
	/** El panel con barras de desplazamiento para la tabla. */
	private JScrollPane panelTablaDeslizante;
	/** La tabla central para mostrar los datos de los países. */
	private JTable tablaPaíses;
	/** El modelo de datos de la tabla de países. */
	private ModeloTablaPaíses modeloPaíses;
	/** El panel inferior donde se sitúan los botones principales de la pestaña. */
	private JPanel jpanelDatos;

	/** Control general asociado a la aplicación/ventana. */
	private ControlWorldBD control;

	/** Indicador de preparación de ventana finalizada. */
	private boolean ventanaPreparada;
	private JTextArea barraEstado;
	private JMenuBar jmenuBar;
	private JMenu jmenuAyuda;
	private JMenu jmenuArchivo;
	private JMenuItem jitemSalirPrograma;
	private JMenuItem jitemCreditos;
	private JPanel jpanelTexto;
	private JTextField jtextoInformacion;
	private JPanel jpanelInformacion;
	private JPanel jpanelInsercion;
	private JTextField jtextPais;
	private JTextField textField_1;
	private JPanel jpanelBotones;
	private JButton jbotonInsertar;
	private JButton jbotonBuscar;
	
	private BaseDeDatos basePaises;
	private int contador;
	/**
	 * Lanza la aplicación. Establece la apariencia general de la ventana y registra
	 * el lanzador.
	 * 
	 * @param argumentos Opciones en línea de órdenes –no se usa–.
	 * 
	 */
	public static void main(String[] argumentos) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			/**
			 * Crea la ventana y la hace visible.
			 */
			public void run() {
				try {
					VisorWorldBD3 ventana = new VisorWorldBD3();
					ventana.setVisible(true);
					ventana.ventanaPreparada = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la aplicación e inicializa los componentes de la ventana.
	 */
	public VisorWorldBD3() {
		basePaises = new BaseDeDatos("jdbc:sqlite:world2.db", contador);
		ventanaPreparada = false;
		control = new ControlWorldBD();
		initialize();
	}

	/**
	 * Inicializa los componentes de la ventana.
	 */
	private void initialize() {
		setTitle("Ventana países");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 411);
		panelGeneral = new JPanel();
		panelGeneral.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelGeneral);
		panelGeneral.setLayout(new BorderLayout(0, 0));
		panelGeneral.add(getPanelPestañas(), BorderLayout.CENTER);
		panelGeneral.add(getMenuBar_1(), BorderLayout.NORTH);
	}

	/**
	 * Localiza –o inicializa si no se ha creado todavía– el panel general exterior,
	 * para las pestañas.
	 * 
	 * @return el panel indicado
	 */
	private JTabbedPane getPanelPestañas() {
		if (panelPestañas == null) {
			panelPestañas = new JTabbedPane(JTabbedPane.TOP);
			panelPestañas.setBorder(new EmptyBorder(10, 10, 10, 10));
			panelPestañas.addTab("Listado", null, getPanelPrincipal(), null);

		}
		return panelPestañas;
	}

	/**
	 * Localiza –o inicializa si no se ha creado todavía– el panel de contenido
	 * central de la única pestaña, con márgenes.
	 * 
	 * @return el panel indicado
	 */
	private JPanel getPanelPrincipal() {
		if (panelPrincipal == null) {
			panelPrincipal = new JPanel();
			panelPrincipal.setBorder(new EmptyBorder(10, 0, 0, 0));
			panelPrincipal.setLayout(new BorderLayout(0, 10));

			panelPrincipal.add(getJpanelDatos(), BorderLayout.SOUTH);
			panelPrincipal.add(getPanelTablaDeslizante(), BorderLayout.CENTER);
		}
		return panelPrincipal;
	}

	/**
	 * Localiza –o inicializa si no se ha creado todavía– el panel con barras de
	 * desplazamiento para la tabla.
	 * 
	 * @return el panel indicado
	 */
	private JScrollPane getPanelTablaDeslizante() {
		if (panelTablaDeslizante == null) {
			panelTablaDeslizante = new JScrollPane();
			panelTablaDeslizante.setBackground(Color.ORANGE);
			panelTablaDeslizante.setViewportView(getTablaPaíses());
		}
		return panelTablaDeslizante;
	}

	/**
	 * Localiza –o inicializa si no se ha creado todavía– la tabla central para
	 * mostrar los datos de los países.
	 * 
	 * @return la tabla indicada
	 */
	private JTable getTablaPaíses() {
		if (tablaPaíses == null) {
			tablaPaíses = new JTable();
			tablaPaíses.setFillsViewportHeight(true);
			tablaPaíses.setShowVerticalLines(true);
			tablaPaíses.setShowHorizontalLines(true);
			tablaPaíses.setGridColor(new Color(255, 228, 181));
			tablaPaíses.setBorder(new LineBorder(new Color(0, 0, 0)));
			tablaPaíses.setAutoCreateRowSorter(true);
			tablaPaíses.setModel(getModeloPaíses());
		}
		return tablaPaíses;
	}

	/**
	 * Localiza –o inicializa si no se ha creado todavía– el modelo de datos de la
	 * tabla de países.
	 * 
	 * @return el modelo de datos indicado
	 */
	public ModeloTablaPaíses getModeloPaíses() {
		if (modeloPaíses == null) {
			modeloPaíses = new ModeloTablaPaíses();
		}
		return modeloPaíses;
	}

	/**
	 * Localiza –o inicializa si no se ha creado todavía– el panel inferior donde se
	 * sitúan los botones principales de la pestaña.
	 * 
	 * @return el panel indicado
	 */
	private JPanel getJpanelDatos() {
		if (jpanelDatos == null) {
			jpanelDatos = new JPanel();
			jpanelDatos.setBorder(new EmptyBorder(0, 0, 0, 0));
			jpanelDatos.setLayout(new BorderLayout(0, 0));
			jpanelDatos.add(getJpanelTexto(), BorderLayout.NORTH);
			jpanelDatos.add(getJpanelBotones(), BorderLayout.SOUTH);
		}
		return jpanelDatos;
	}
	private class JitemCreditosActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mostrarEstado("Rubén Lacalle 2024");
		}
	}
	private class JitemSalirProgramaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Aqui iria la opcion para cerrar el programa
		}
	}
	private class JbotonInsertarActionListener implements ActionListener {
			/**
			 * Carga la tabla con los datos de la base de datos de países.
			 * 
			 * @param ev el evento causante
			 */
			public void actionPerformed(ActionEvent ev) {
				ModeloTablaPaíses modelo = getModeloPaíses();
				control.cargarDatos();
				control.sincronizarListaPaíses(modelo);
				mostrarEstado(String.format("Numero de elementos mostrados: %S", control.consultarTamaño()));
			}
	}
	private class JbotonBuscarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			basePaises.setNombre(jtextPais.getText());
			basePaises.ejecutarConsulta();
			
			ModeloTablaPaíses modelo = getModeloPaíses();
			modelo.addRow(basePaises.getPais());
		}
	}
	private JTextArea getBarraEstado() {
		if (barraEstado == null) {
			barraEstado = new JTextArea();
		}
		return barraEstado;
	}
	
	private void mostrarEstado(String mensaje) {
		barraEstado.setForeground(new Color(0, 0, 0));
		barraEstado.setText(mensaje);
	}
	
	private void mostrarAviso(String mensaje) {
		barraEstado.setForeground(new Color(255, 0, 0));
		barraEstado.setText(mensaje);
	}
	private JMenuBar getMenuBar_1() {
		if (jmenuBar == null) {
			jmenuBar = new JMenuBar();
			jmenuBar.setToolTipText("Menú");
			jmenuBar.add(getJmenuAyuda());
			jmenuBar.add(getJmenuArchivo());
		}
		return jmenuBar;
	}
	public void actionPerformed(ActionEvent e) {
	}
	private JMenu getJmenuAyuda() {
		if (jmenuAyuda == null) {
			jmenuAyuda = new JMenu("Ayuda");
			jmenuAyuda.add(getJitemCreditos());
		}
		return jmenuAyuda;
	}
	private JMenu getJmenuArchivo() {
		if (jmenuArchivo == null) {
			jmenuArchivo = new JMenu("Archivo");
			jmenuArchivo.add(getJitemSalirPrograma());
		}
		return jmenuArchivo;
	}
	private JMenuItem getJitemSalirPrograma() {
		if (jitemSalirPrograma == null) {
			jitemSalirPrograma = new JMenuItem("Cerrar Programa");
			jitemSalirPrograma.addActionListener(new JitemSalirProgramaActionListener());
		}
		return jitemSalirPrograma;
	}
	private JMenuItem getJitemCreditos() {
		if (jitemCreditos == null) {
			jitemCreditos = new JMenuItem("Creditos");
			jitemCreditos.addActionListener(new JitemCreditosActionListener());
		}
		return jitemCreditos;
	}
	private JPanel getJpanelTexto() {
		if (jpanelTexto == null) {
			jpanelTexto = new JPanel();
			jpanelTexto.setLayout(new GridLayout(0, 2, 0, 0));
			jpanelTexto.add(getJpanelInformacion());
			jpanelTexto.add(getJpanelInsercion());
		}
		return jpanelTexto;
	}
	private JTextField getJtextoInformacion() {
		if (jtextoInformacion == null) {
			jtextoInformacion = new JTextField();
			jtextoInformacion.setEditable(false);
			jtextoInformacion.setText("ESTADO:");
			jtextoInformacion.setColumns(10);
		}
		return jtextoInformacion;
	}
	private JPanel getJpanelInformacion() {
		if (jpanelInformacion == null) {
			jpanelInformacion = new JPanel();
			jpanelInformacion.setLayout(new BorderLayout(0, 0));
			jpanelInformacion.add(getJtextoInformacion(), BorderLayout.WEST);
			jpanelInformacion.add(getBarraEstado(), BorderLayout.CENTER);
		}
		return jpanelInformacion;
	}
	private JPanel getJpanelInsercion() {
		if (jpanelInsercion == null) {
			jpanelInsercion = new JPanel();
			jpanelInsercion.setLayout(new BorderLayout(0, 0));
			jpanelInsercion.add(getJtextPais(), BorderLayout.WEST);
			jpanelInsercion.add(getTextField_1(), BorderLayout.CENTER);
		}
		return jpanelInsercion;
	}
	private JTextField getJtextPais() {
		if (jtextPais == null) {
			jtextPais = new JTextField();
			jtextPais.setText("País:");
			jtextPais.setEditable(false);
			jtextPais.setToolTipText("");
			jtextPais.setColumns(10);
		}
		return jtextPais;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setColumns(10);
		}
		return textField_1;
	}
	private JPanel getJpanelBotones() {
		if (jpanelBotones == null) {
			jpanelBotones = new JPanel();
			jpanelBotones.setLayout(new GridLayout(0, 2, 0, 0));
			jpanelBotones.add(getJbotonInsertar());
			jpanelBotones.add(getJbotonBuscar());
		}
		return jpanelBotones;
	}
	private JButton getJbotonInsertar() {
		if (jbotonInsertar == null) {
			jbotonInsertar = new JButton("Insertar");
			jbotonInsertar.addActionListener(new JbotonInsertarActionListener());
		}
		return jbotonInsertar;
	}
	private JButton getJbotonBuscar() {
		if (jbotonBuscar == null) {
			jbotonBuscar = new JButton("Filtrar país");
			jbotonBuscar.addActionListener(new JbotonBuscarActionListener());
		}
		return jbotonBuscar;
	}
}
