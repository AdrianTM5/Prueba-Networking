package ChatCliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;

public class VistaCliente extends JFrame 
{
	private JPanel contentPane;
	JTextField textFieldMsg;
	JTextField textFieldConversación;
	private JTable table;
	Thread ThreadS;
	JButton btnCerrar;
	JButton btnEnviar;
	JPanel panel;
	JPanel panel_1;
	/**
	 * Launch the application.
	 */
	void lanzar()
	{
		ThreadS = new Thread(new Runnable() {
			public void run() {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		ThreadS.start();
	}
	/**
	 * Create the frame.
	 */
	public VistaCliente() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(46, 26, 468, 433);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textFieldConversación = new JTextField();
		textFieldConversación.setEditable(false);
		textFieldConversación.setBounds(0, 0, 468, 433);
		panel.add(textFieldConversación);
		textFieldConversación.setColumns(10);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(46, 481, 100, 53);
		contentPane.add(btnEnviar);
		
		textFieldMsg = new JTextField();
		textFieldMsg.setBounds(156, 481, 358, 53);
		contentPane.add(textFieldMsg);
		textFieldMsg.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setBounds(524, 26, 150, 433);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 498, 130, -489);
		panel_1.add(table);
		
		btnCerrar = new JButton("Cerrar Sesión");
		btnCerrar.setBounds(546, 481, 100, 53);
		contentPane.add(btnCerrar);
	}
}
