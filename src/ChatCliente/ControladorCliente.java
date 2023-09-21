package ChatCliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import ChatServer.ModeloServer;
import ChatServer.Usuario;
import ChatServer.VistaServer;
import ChatServer.VistaServerLoginA;

public class ControladorCliente implements ActionListener 
{
	ChatCliente.Usuario u;
	VistaCliente vc;
	VistaClienteLogin vcl;
	ModeloCliente mc;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	Socket s = null;
	boolean cerrar = false;
	InetAddress LastIP;
	
	public ControladorCliente(ModeloCliente ms, VistaCliente vc, VistaClienteLogin vcl)
	{
		this.mc = mc;
		this.vc = vc;
		this.vcl = vcl;
		inicializar();
	}
	
	public void inicializar()
	{
		vc.lanzar();
		vcl.lanzar();
		while(vc.ThreadS.isAlive() == true && vcl.ThreadSL.isAlive() == true)
		{
			try 
			{
				Thread.sleep(100);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			System.out.println(1);
		}
		//-----VistaServerLogin-----
		vcl.setVisible(true);
		vcl.setResizable(false);
		vcl.btnEnviar.addActionListener(this);
		//-----VistaServer-----
		vc.setVisible(false);
		vc.setResizable(false);
		vc.btnCerrar.addActionListener(this);
		vc.btnEnviar.addActionListener(this);
		
		Ejecutar();
	}

	private void Ejecutar() 
	{
		while(!cerrar)
		{
			if(s.getInetAddress() != LastIP)
			{
				Usuario usu;
				try 
				{
					usu = (Usuario)ois.readObject();
					vc.textFieldConversación.setText(("El usuario: "+usu.getNom_usu()+ " se conectó"));
				}
				catch (ClassNotFoundException | IOException e) 
				{
					e.printStackTrace();
				}
			}
			else
			{
				
			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == vcl.btnEnviar)
		{
			u = new ChatCliente.Usuario(vcl.textFieldUsername.getText(), true, null);
			mc.lista.add(u);
			try
			{
				s = new Socket("192.1680.120",5432);
				LastIP = s.getInetAddress();
				oos = new ObjectOutputStream(s.getOutputStream());
				ois = new ObjectInputStream(s.getInputStream());
				
				oos.writeObject(u);
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
			finally
			{
				
			}
			vcl.dispose();
			vc.setVisible(true);
		}
		else if(e.getSource() == vc.btnCerrar)
		{
			cerrar = true;
		}
		else if(e.getSource() == vc.btnEnviar)
		{
			u.setMen_usu(vc.textFieldMsg.getText());
			try 
			{
				oos.writeObject(u);
				vc.textFieldConversación.setText(u.getNom_usu()+": "+u.getMen_usu()+" - "+u.getMst_usu());
			}
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
		}
	}
}
