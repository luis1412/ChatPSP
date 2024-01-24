/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

/**
 *
 * @author DAM
 */
public class ClienteUDP2 {

  public static void main(String args[]) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    DatagramSocket clientSocket = new DatagramSocket();
    byte[] buffer = new byte[1024];

    InetAddress serverAddress = InetAddress.getLocalHost();
    int serverPort = 9876;


      // Receive and display response from the server
      DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
      clientSocket.receive(receivePacket);
      String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
      System.out.println("Server response: " + receivedMessage);
    }


  public void enviarMensaje(String message) throws SocketException, IOException {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    DatagramSocket clientSocket = new DatagramSocket();
    byte[] buffer = new byte[1024];

    InetAddress serverAddress = InetAddress.getLocalHost();
    int serverPort = 9876;

    // Registro del cliente al servidor
    String registrationMessage = "REGISTER";
    buffer = registrationMessage.getBytes();
    DatagramPacket registrationPacket = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);
    clientSocket.send(registrationPacket);

    buffer = message.getBytes();
    DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);
    clientSocket.send(sendPacket);

    // Receive and display response from the server
    DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
    clientSocket.receive(receivePacket);
    String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
    System.out.println("Server response: " + receivedMessage);
    
    
    clientSocket.close();
  }

}
