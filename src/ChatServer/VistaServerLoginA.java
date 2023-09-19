package ChatServer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VistaServerLoginA extends JFrame 
{
	private JPanel contentPane;
	JTextField textFieldUsername;
	ControladorServer CS;
	Thread ThreadSL;
	JButton btnEnviar;
	/**
	 * Launch the application.
	 */
	void lanzar()
	{
		ThreadSL = new Thread(new Runnable() {
			public void run() {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		ThreadSL.start();
	}
	/**
	 * Create the frame.
	 */
	public VistaServerLoginA() 
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(281, 426, 100, 53);
		contentPane.add(btnEnviar);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(169, 219, 358, 53);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lbUser = new JLabel("Username:");
		lbUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbUser.setBounds(86, 226, 74, 34);
		contentPane.add(lbUser);
		
		JLabel lbChat = new JLabel("Chat Admin");
		lbChat.setHorizontalAlignment(SwingConstants.CENTER);
		lbChat.setFont(new Font("Tahoma", Font.BOLD, 27));
		lbChat.setBounds(169, 64, 358, 34);
		contentPane.add(lbChat);
	}
}
