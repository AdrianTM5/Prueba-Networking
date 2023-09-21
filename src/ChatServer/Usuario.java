package ChatServer;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Usuario implements Serializable
{
	private String nom_usu;
	String msg_usu;
	private boolean adm_usu;
	private String mst_usu;
	
	public Usuario(String nom, boolean adm, String msg)
	{
		nom_usu = nom;
		adm_usu = adm;
		msg_usu = msg;
	}
	
	public String getNom_usu() 
	{
		return nom_usu;
	}


	public String getMen_usu() 
	{
		return msg_usu;
	}

	public void setMen_usu(String men) 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		String time = sdf.format(System.currentTimeMillis());
		mst_usu = time;
		this.msg_usu = men;
	}

	public String getMst_usu() 
	{
		return mst_usu;
	}
}