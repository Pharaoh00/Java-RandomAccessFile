import src.*;

import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Pessoa p1 = new Pessoa(1, "Hamon-Rá Taveira", (byte)28);
        Pessoa p2 = new Pessoa(2, "João Wictor", (byte)21);
        Pessoa p3 = new Pessoa(3, "Igor Pinheiro", (byte)20);
        Pessoa p4 = new Pessoa(4, "Igor Cesar", (byte)23);
        Pessoa p5 = new Pessoa(5, "Leonardo Augusto", (byte)20);

        // Array com todas as Pessoa criadas, para gravar no arquivo.
        ArrayList<Pessoa> toFile = new ArrayList<Pessoa>();
        toFile.add(p1);
        toFile.add(p2);
        toFile.add(p3);
        toFile.add(p4);
        toFile.add(p5);

        try {
            RandomAccessFile file = new RandomAccessFile("test.db", "rw");

            byte[] b_data;
            for(Pessoa p : toFile) {
                b_data = p.toByteArray();
                Tools.writeToFile(file, b_data);
            }

            file.seek(0); // Resetando o ponteiro do arquivo para leitura.

            // Array com todas as Pessoa lidas do arquivo.
            ArrayList<Pessoa> pessoas = Tools.readAll(file);
            for(Pessoa p : pessoas) {
                System.out.println(p);
            }

            file.seek(0);

            System.out.println("\nEncontrando Pessoa no arquivo.");
            p1 = null;
            p1 = Tools.findS(file, "João Wictor");
            System.out.println(p1);
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}