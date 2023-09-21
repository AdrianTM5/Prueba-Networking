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
	ServerSocket ss;
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
		vs.setVisible(false);
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
				ss = new ServerSocket(5432);
			}
			catch(IOException e2)
			{
				e2.printStackTrace();
			}
		}
		while(!cerrar)
		{
			try 
			{
				s = ss.accept();
				vs.textFieldConversación.setText("Te conectaste exitosamente!");
				ois = new ObjectInputStream(s.getInputStream());
				oos = new ObjectOutputStream(s.getOutputStream());
				Usuario usu = (Usuario)ois.readObject();
				vs.textFieldConversación.setText(("El usuario: "+usu.getNom_usu()+ " se conectó desde la IP: "+s.getInetAddress()));
				oos.writeObject(usu);
				vs.textFieldConversación.setText((usu.getNom_usu()+": "+usu.getMen_usu()+" - "+usu.getMst_usu()));
				if(e.getSource() == vs.btnCerrar)
				{
					cerrar = true;
				}
				else if(e.getSource() == vs.btnEnviar)
				{
					u.setMen_usu(vs.textFieldMsg.getText());
					try 
					{
						oos.writeObject(u);
						vs.textFieldConversación.setText(u.getNom_usu()+": "+u.getMen_usu()+" - "+u.getMst_usu());
					}
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				}
			} 
			catch (IOException | ClassNotFoundException e1) 
			{
				e1.printStackTrace();
			}
			finally
			{
					try 
					{
						if(oos != null) oos.close();
						if(ois != null) ois.close();
						if(s != null) s.close();
					} 
					catch (IOException e2) 
					{ 
						e2.printStackTrace();
					}
			}
		}
	}
}
