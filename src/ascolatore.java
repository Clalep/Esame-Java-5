import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ascolatore implements ActionListener {


    JTextField indirizzo;
    JTextField porta;

    JButton connect;
    JButton disconnect;

    JTextArea console;

    JTextField comandi;

    JButton execute;
    JButton interrupt;

    Socket s;
    PrintWriter pw;
    Scanner sc;


    public ascolatore(JTextField indirizzo, JTextField porta, JButton connect, JButton disconnect, JTextArea console, JTextField comandi, JButton execute, JButton interrupt) {

        this.indirizzo = indirizzo;
        this.porta = porta;
        this.connect = connect;
        this.disconnect = disconnect;
        this.console = console;
        this.comandi = comandi;
        this.execute = execute;
        this.interrupt = interrupt;



    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("connect")) {

            try {
                s = new Socket(indirizzo.getText(), Integer.parseInt(porta.getText()));
                pw = new PrintWriter(s.getOutputStream());
                sc = new Scanner(s.getInputStream());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


            connect.setEnabled(false);
            disconnect.setEnabled(true);
            execute.setEnabled(true);
            interrupt.setEnabled(false);

            console.setText("");


        }



        if(e.getActionCommand().equals("execute")) {

            pw.println(comandi.getText());
            pw.flush();

            azione pr = new azione(indirizzo,porta,connect,disconnect,console, comandi,execute,interrupt, pw,sc);
            Thread prova = new Thread(pr);
            prova.start();

        }

        if(e.getActionCommand().equals("interrupt")) {

            pw.println("INTERRUPT");
            pw.flush();



        }


        if(e.getActionCommand().equals("disconnect")) {


            pw.println("DISCONNECT");
            pw.flush();

            sc.close();
            pw.close();
            try {
                s.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            connect.setEnabled(true);
            disconnect.setEnabled(false);
            execute.setEnabled(false);
            interrupt.setEnabled(false);


        }


    }
}
