package pingball.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import physics.Vect;
import pingball.datatypes.Board;
import warmup.Ball;


/**
 * Creates and manages a world of Pingball boards for multiple users
 * @author AlexR
 * Thread safety argument:
 *    + The threads in the system are:
 *       - Main thread that creates the world and accepts new connections
 *       - Helper thread that constantly listens for input from the console (admin) to join boards
 *       - One thread per connected user, handling only that user
 *       
 *    + The serverSocket Object is confined to the main thread
 *    + The Socket object for a client is confined to that client's thread;
 *      the main thread loses its reference to the object right after starting
 *      the client thread.
 *      
 *    + All accesses to the game's world happen within World methods,
 *      which are all guarded by World's lock
 *    + All other variables are confined to each individual thread
 *
 */
public class PingballServer {
    private final ServerSocket serverSocket;
    private final World world;
    
    /**
     * Make a Pingball server that listens for connections on port.
     * 
     * @param port port number, requires 0 <= port <= 65535
     * @param world where the game will be handled
     * @throws IOException 
     */
    public PingballServer(int port, World world) throws IOException{
        serverSocket = new ServerSocket(port);
        this.world = world;
    }
    /**
     * Run the server, listening for client connections and handling them.
     * Never returns unless an exception is thrown.
     * 
     * @throws IOException if the main server socket is broken
     *                     (IOExceptions from individual clients do *not* terminate serve())
     */
    public void serve() throws IOException {
        Thread consoleInput = new Thread (new Runnable(){
            @Override
            public void run() {
              //Constantly wait for user input to join boards
                BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
                try {
                    for (String line = fromUser.readLine(); line != null; line = fromUser.readLine()) {
                        String output = handleRequest(line);
                            System.out.println(output);
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
        });
        consoleInput.start();
            
        while (true) {
            // block until a client connects
            Socket ClientSocket = serverSocket.accept();
            new PingballClientThread(ClientSocket, world).start();
            System.out.println(world);
        } 
    }
    
    /**
     * Start a PingballServer using the given arguments.
     * 
     * Usage: PingballServer [--port PORT]
     * 
     * 
     * PORT is an optional integer in the range 0 to 65535 inclusive, 
     * specifying the port where the server should listen for incoming connections. 
     * The default port is 10987. E.g. "PingballServer --port 1234"
     * starts the server listening on port 1234.
     * 
     */
    public static void main(String[] args) throws IOException {
        int port = 10987; //default port
        // Read the input argument port
        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        try {
            while ( ! arguments.isEmpty()) {
                String flag = arguments.remove();
                try {
                    if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        if (port < 0 || port > 65535) {
                            throw new IllegalArgumentException("port " + port + " out of range");
                        }
                    }
                 } catch (NoSuchElementException nsee) {
                    throw new IllegalArgumentException("missing argument for " + flag);
                 }
            }
        }
            catch (IllegalArgumentException iae) {
                System.err.println(iae.getMessage());
                System.err.println("usage: PingballServer [--port PORT]");
                return;
            }
        
        try {
            runPingballServer(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
        
    /**
     * Start a MinesweeperServer running on the specified port
     * @param port The network port on which the server should listen.
     * @throws IOException
     */
    public static void runPingballServer(int port) throws IOException{
        World worl = new World();
        PingballServer server = new PingballServer(port, worl);
        server.serve();
    }
    
    /**
     * Handler for user input (only the server admin), performing requested operations 
     * and returning an output message in case of invalid input
     * 
     * Possible commands are h Name_Left Name_Right to join NAME_left's right wall with NAME_right's left wall.
     * and v Name_top Name_bottom to join NAME_top's bottom wall with NAME_bottom's top wall.
     * @param input message from client
     * @return message to client
     */
    private String handleRequest(String input) {
        String help = "Invalid input, please use  'h NAME_left NAME_right' or 'v NAME_top NAME_bottom' to join boards\n";
        String joined = "Boards joined";
        String noBoard = "Please use two boards that are currently connected to the server";
        String regex = "(h|v)\\s[a-zA-Z0-9_ ]*";
        String[] tokens = input.split(" ");
        if ( !input.matches(regex) || tokens.length != 3) {
            // invalid input
            return help;
        }
        
        
        if (!world.containsBoard(tokens[1]) || !world.containsBoard(tokens[2])){
            return noBoard;
        }
        if (tokens[0].equals("h")) {
            world.joinHorizontal(tokens[1], tokens[2]); 
            return "";
        } else if (tokens[0].equals("v")) {
            world.joinVertical(tokens[1], tokens[2]);
            return "";
        // Should never get here
        }
        throw new UnsupportedOperationException();
    }
}
