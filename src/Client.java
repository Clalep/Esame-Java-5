import javax.swing.*;
import java.awt.*;

public class Client {

    public static void main(String[] args) {

        JFrame princ = new JFrame("Claudio Leppo 2172201");

        JTextField indirizzo = new JTextField("localhost");
        JTextField porta = new JTextField("4400");

        JButton connect = new JButton("connect");
        JButton disconnect = new JButton("disconnect");

        JPanel su = new JPanel();

        su.add(indirizzo);
        su.add(porta);
        su.add(connect);
        su.add(disconnect);

        JTextArea console = new JTextArea(80,25);

        JScrollPane scroll = new JScrollPane(console);

        scroll.setBorder(BorderFactory.createTitledBorder("Console"));

        JTextField comandi = new JTextField("comandi", 20);

        JButton execute = new JButton("execute");
        JButton interrupt = new JButton("interrupt");

        JPanel giu = new JPanel();
        giu.add(comandi);
        giu.add(execute);
        giu.add(interrupt);

        princ.setVisible(true);
        princ.pack();
        princ.setLayout(new BorderLayout());

        princ.add(su , BorderLayout.NORTH);
        princ.add(scroll, BorderLayout.CENTER);
        princ.add(giu,BorderLayout.SOUTH);
        princ.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        connect.setEnabled(true);
        disconnect.setEnabled(false);
        execute.setEnabled(false);
        interrupt.setEnabled(false);

        console.setEditable(false);

        ascolatore asc = new ascolatore(indirizzo,porta,connect,disconnect,console, comandi,execute,interrupt);

        connect.addActionListener(asc);
        disconnect.addActionListener(asc);

        execute.addActionListener(asc);
        interrupt.addActionListener(asc);




    }


}
