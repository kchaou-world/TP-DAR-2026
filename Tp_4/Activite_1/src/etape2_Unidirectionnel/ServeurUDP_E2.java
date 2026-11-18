package etape2_Unidirectionnel;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ServeurUDP_E2 {

    public static void main(String[] args) {

        try {
            DatagramSocket socket =
                    new DatagramSocket(new InetSocketAddress(1234));

            System.out.println("Serveur UDP démarré...");
            System.out.println("En attente des messages...\n");

            while (true) {

                byte[] data = new byte[1024];

                DatagramPacket packet =
                        new DatagramPacket(data, data.length);

                socket.receive(packet);

                String message = new String(
                        packet.getData(), 0, packet.getLength());

                String adresse = packet.getAddress().getHostAddress();
                int port = packet.getPort();

                System.out.println("Client : " + adresse + ":" + port);
                System.out.println("Message : " + message);
                System.out.println("--------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}