import javax.swing.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class azione implements Runnable{

    JTextField indirizzo;
    JTextField porta;

    JButton connect;
    JButton disconnect;

    JTextArea console;

    JTextField comandi;

    JButton execute;
    JButton interrupt;

    PrintWriter pw;
    Scanner sc;

    public azione(JTextField indirizzo, JTextField porta, JButton connect, JButton disconnect, JTextArea console, JTextField comandi, JButton execute, JButton interrupt, PrintWriter pw, Scanner sc) {

        this.indirizzo = indirizzo;
        this.porta = porta;
        this.connect = connect;
        this.disconnect = disconnect;
        this.console = console;
        this.comandi = comandi;
        this.execute = execute;
        this.interrupt = interrupt;

        this.sc = sc;
        this.pw = pw;



    }





    @Override
    public void run() {
        while (sc.hasNextLine()) {
            String risp = sc.nextLine();

            // 1. Gestione dei messaggi di protocollo speciali
            if (risp.equals("END")) {
                console.append("\n=== Download completato ===\n"); // Usa append
                interrupt.setEnabled(false);
                disconnect.setEnabled(true);
                execute.setEnabled(true);
                break; // Esci dal ciclo
            }
            else if (risp.equals("INTERRUPTED")) {
                console.append("\n=== Download interrotto ===\n");
                interrupt.setEnabled(false);
                disconnect.setEnabled(true);
                execute.setEnabled(true);
                break; // Esci dal ciclo
            }
            else if (risp.equals("ERROR")) {
                console.append("\n=== Comando errato ===\n");
                interrupt.setEnabled(false);
                disconnect.setEnabled(true);
                execute.setEnabled(true);
                break; // Esci dal ciclo
            }
            else if (risp.equals("File Not Found")) {
                // ATTENZIONE: Il protocollo dice che dopo "File Not Found" arriva comunque "END".
                // Quindi NON fare break qui, stampalo solo.
                console.append("File non trovato (attendere chiusura...)\n");
            }
            else {
                // 2. Per tutti gli altri dati (es. nomi dei file o contenuto file)
                // Usa append e aggiungi "\n" perché nextLine() lo aveva tolto
                console.append(risp + "\n");
            }

            // Forza lo scroll verso il basso (opzionale ma utile)
            console.setCaretPosition(console.getDocument().getLength());
        }
    }





}
