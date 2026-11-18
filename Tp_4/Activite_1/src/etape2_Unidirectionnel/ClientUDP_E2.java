package etape2_Unidirectionnel;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP_E2 {

    public static void main(String[] args) {

        try {
            DatagramSocket socket = new DatagramSocket();

            InetAddress adresseServeur =
                    InetAddress.getByName("localhost");

            Scanner clavier = new Scanner(System.in);

            System.out.print("Donner votre nom : ");
            String nom = clavier.nextLine();

            while (true) {

                System.out.print("Message : ");
                String message = clavier.nextLine();

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                String texte = "[" + nom + "] : " + message;

                byte[] data = texte.getBytes();

                DatagramPacket packet = new DatagramPacket(
                        data, data.length, adresseServeur, 1234);

                socket.send(packet);
            }

            socket.close();
            clavier.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}