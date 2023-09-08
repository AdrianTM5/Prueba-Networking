package PruebaUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer 
{
	public static void main(String[] args) 
	{
		//creo el socket
		try 
		{
			DatagramSocket socket = new DatagramSocket(54321);
			while(true)
			{
				System.out.println("Esperando conexión...");
				//Buffer para recibir el nombre del cliente
				byte[] bRecibe = new byte[256];
				//Recibo el nombre del cliente
				DatagramPacket packet = new DatagramPacket(bRecibe, bRecibe.length);
				socket.receive(packet);
				System.out.println("Conexión recibida!");
				//Preparo el saludo para enviar
				String nombre = new String(packet.getData(), 0, packet.getLength());
				String saludo = new String("Hola mundo ("+nombre+")  -   "+System.currentTimeMillis());
				System.out.println("Voy a enviar: ["+saludo+"]...");
				//buffer para enviar saludo
				byte[] bEnvia = saludo.getBytes();
				//Envio de saludo
				InetAddress address = packet.getAddress();
				packet = new DatagramPacket(bEnvia, bEnvia.length, address, packet.getPort());
				socket.send(packet);
				System.out.println("Saludo enviado !!");
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
