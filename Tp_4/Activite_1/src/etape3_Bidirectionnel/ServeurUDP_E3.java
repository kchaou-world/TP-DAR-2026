package etape3_Bidirectionnel;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.ArrayList;

public class ServeurUDP_E3 {

    public static void main(String[] args) {

        try {
            DatagramSocket socket = new DatagramSocket(1234);

            ArrayList<SocketAddress> clients = new ArrayList<>();

            System.out.println("Serveur UDP démarré...");
            System.out.println("En attente des clients...\n");

            while (true) {

                byte[] data = new byte[1024];

                DatagramPacket packet =
                        new DatagramPacket(data, data.length);

                socket.receive(packet);

                String message = new String(
                        packet.getData(), 0, packet.getLength());

                SocketAddress adresseClient = packet.getSocketAddress();

                // Ajouter le client s'il n'existe pas déjà
                if (!clients.contains(adresseClient)) {
                    clients.add(adresseClient);
                    System.out.println("Nouveau client : " + adresseClient);
                }

                System.out.println(message);

                // Diffuser le message aux autres clients
                for (SocketAddress destinataire : clients) {

                    if (!destinataire.equals(adresseClient)) {

                        byte[] messageData = message.getBytes();

                        DatagramPacket packetEnvoi =
                                new DatagramPacket(
                                        messageData,
                                        messageData.length);

                        packetEnvoi.setSocketAddress(destinataire);

                        socket.send(packetEnvoi);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
