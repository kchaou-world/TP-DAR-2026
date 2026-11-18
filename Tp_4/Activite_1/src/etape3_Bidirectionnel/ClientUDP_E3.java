package etape3_Bidirectionnel;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP_E3 {

    public static void main(String[] args) {

        try {
            DatagramSocket socket = new DatagramSocket();

            InetAddress adresseServeur =
                    InetAddress.getByName("localhost");

            Scanner clavier = new Scanner(System.in);

            System.out.print("Donner votre nom : ");
            String nom = clavier.nextLine();

            // Thread pour recevoir les messages
            Thread reception = new Thread(new Runnable() {

                public void run() {

                    try {
                        while (true) {

                            byte[] data = new byte[1024];

                            DatagramPacket packet =
                                    new DatagramPacket(data, data.length);

                            socket.receive(packet);

                            String message = new String(
                                    packet.getData(),
                                    0,
                                    packet.getLength());

                            System.out.println("\n" + message);
                            System.out.print("Message : ");
                        }

                    } catch (Exception e) {
                        System.out.println("Réception terminée.");
                    }
                }
            });

            reception.start();

            // Envoi des messages
            while (true) {

                System.out.print("Message : ");
                String message = clavier.nextLine();

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                String texte = "[" + nom + "] : " + message;

                byte[] data = texte.getBytes();

                DatagramPacket packet = new DatagramPacket(
                        data,
                        data.length,
                        adresseServeur,
                        1234);

                socket.send(packet);
            }

            socket.close();
            clavier.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}