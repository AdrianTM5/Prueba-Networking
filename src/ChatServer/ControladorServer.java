package ChatServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ControladorServer implements ActionListener 
{
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	Usuario u;
	ModeloServer ms;
	VistaServer vs;
	VistaServerLoginA vsl;
	Socket s = null;
	boolean cerrar = false;
	
	public ControladorServer(ModeloServer ms, VistaServer vs, VistaServerLoginA vsl)
	{
		this.ms = ms;
		this.vs = vs;
		this.vsl = vsl;
		inicializar();
	}
	
	private void inicializar()
	{
		vs.lanzar();
		vsl.lanzar();
		while(vs.ThreadS.isAlive() == true && vsl.ThreadSL.isAlive() == true)
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
		vsl.setVisible(true);
		vsl.setResizable(false);
		vsl.btnEnviar.addActionListener(this);
		//-----VistaServer-----
		vs.setVisible(true);
		vs.setResizable(false);
		vs.btnCerrar.addActionListener(this);
		vs.btnEnviar.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == vsl.btnEnviar)
		{
			u = new Usuario(vsl.textFieldUsername.getText(), true, null);
			ms.lista.add(u);
			vsl.dispose();
			vs.setVisible(true);
			try
			{
				ServerSocket ss = new ServerSocket(5432);
				while(!cerrar)
				{
					s = ss.accept();
					vs.textFieldConversación.setText(("El usuario: "+u.getNom_usu()+ " se conectó desde la IP: "+s.getInetAddress()));
					ois = new ObjectInputStream(s.getInputStream());
					oos = new ObjectOutputStream(s.getOutputStream());
				}
			}
			catch(IOException e2)
			{
				e2.printStackTrace();
			}
		}
		else if(e.getSource() == vs.btnCerrar)
		{
			cerrar = true;
		}
		else if(e.getSource() == vs.btnEnviar)
		{
			u.setMen_usu(vs.textFieldMsg.getText());
			try 
			{
				Usuario usu = (Usuario) ois.readObject();
				vs.textFieldConversación.setText(usu.getNom_usu()+": "+usu.getMen_usu()+" - "+usu.getMst_usu());
				
				
			}
			catch (ClassNotFoundException | IOException e1) 
			{
				e1.printStackTrace();
			}
		}
	}
}
