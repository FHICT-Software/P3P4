/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kolonistenvancatan;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;

/**
 *
 * @author Emiel Muit
 */
public class CvcServer {

    private static boolean createRegistry = true;
    private static int portNumber = 1099;
    private static String bindingName = "CvcServer";
    private Registry registry = null;
    private Lobby lobby = null;

    public CvcServer() {
        // Print port number for registry
        System.out.println("Server: Port number " + portNumber);

        try {
            lobby = new Lobby();
            System.out.println("Server: CvcServer created");
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create CvcServer");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            lobby = null;
        }

        if (createRegistry) {
            // Create registry at port number
            registry = createRegistry();

            // Bind mockEffectenbeurs using registry
            if (registry != null && lobby != null) {
                bindCvcServerUsingRegistry();
                System.out.println("Server: CvcServer bound to " + bindingName);
            } else {
                System.out.println("Server: CvcServer not bound");
            }
        } else {
            // Bind CvcServer using Naming
            if (lobby != null) {
                bindCvcServerUsingNaming();
                System.out.println("Server: CvcServer bound to " + bindingName);
            } else {
                System.out.println("Server: CvcServer not bound");
            }
        }
    }

    // Create registry
    private Registry createRegistry() {

        // Create registry at port number
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number " + portNumber);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            registry = null;
        }
        return registry;
    }

    // Bind CvcServer using registry
    private void bindCvcServerUsingRegistry() {
        try {
            registry.rebind(bindingName, lobby);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot bind CvcServer");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }
    }

    // Bind CvcServer using Naming
    private void bindCvcServerUsingNaming() {
        try {
            LocateRegistry.createRegistry(portNumber);
            Naming.rebind(bindingName, lobby);
        } catch (MalformedURLException ex) {
            System.out.println("Server: Cannot bind CvcServer");
            System.out.println("Server: MalformedURLException: " + ex.getMessage());
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot bind CvcServer");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }
    }

    // Print IP addresses and network interfaces
    private static void printIPAddresses() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("Server: IP Address: " + localhost.getHostAddress());
            // Just in case this host has multiple IP addresses....
            InetAddress[] allMyIps = InetAddress.getAllByName(localhost.getCanonicalHostName());
            if (allMyIps != null && allMyIps.length > 1) {
                System.out.println("Server: Full list of IP addresses:");
                for (int i = 0; i < allMyIps.length; i++) {
                    System.out.println("    " + allMyIps[i]);
                }
            }
        } catch (UnknownHostException ex) {
            System.out.println("Server: Cannot get IP address of local host");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }

        try {
            System.out.println("Server: Full list of network interfaces:");
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                System.out.println("    " + intf.getName() + " " + intf.getDisplayName());
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    System.out.println("        " + enumIpAddr.nextElement().toString());
                }
            }
        } catch (SocketException ex) {
            System.out.println("Server: Cannot retrieve network interface list");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Welcome message
        if (createRegistry) {
            System.out.println("SERVER USING CREATE REGISTRY");
        } else {
            System.out.println("SERVER USING NAMING");
        }

        // Print IP addresses and network interfaces
        printIPAddresses();

        // Create server
        CvcServer server = new CvcServer();
    }
}
