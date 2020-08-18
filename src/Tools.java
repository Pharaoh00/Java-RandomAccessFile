package src;

import java.io.RandomAccessFile;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;

public class Tools {

    /**
     * 
     * @param b_data            Array de bytes relativo a Pessoa.
     * @return                  Retorna um objeto Pessoa.
     * @throws IOException      Erro relativo ao DataInputStream.
     */
    public static Pessoa toObject(byte[] b_data) throws IOException {
        ByteArrayInputStream data = new ByteArrayInputStream(b_data);
        DataInputStream input = new DataInputStream(data);

        int id = input.readInt();
        String nome = input.readUTF();
        byte idade = input.readByte();

        Pessoa p = new Pessoa(id, nome, idade);
        return p;
    }

    /**
     * 
     * @param file              Nome relativo ao arquivo desejado para salvar os dados.
     * @param data              Array de bytes relativo aos dados para salvar no arquivo.
     * @throws IOException      Retorna erro se caso o nome do arquivo não existir.
     * 
     *                          Nota: 
     *                              Não há como dar erro de IOException, pois o RandomAccessFile,
     *                              vai tentar criar um, se caso o mesmo não existir.
     */
    public static void writeToFile(RandomAccessFile file, byte[] data) throws IOException {
        file.writeInt(data.length);
        file.write(data);
    }

    /**
     * 
     * @param file              Nome relativo ao arquivo desejado para ler os dados.
     * @return                  Retorna um ArrayList contendo todos os dados de Pessoa encontrado.
     * 
     *                          Nota:
     *                              É lido sequencialmente. Isso quer dizer que todos os dados
     *                              encontrados no arquivo serão lidos.
     *                              Se caso tiver algum erro o ArrayList retorna null.
     *                              Se caso não achar nenhum dado no arquivo, ArrayList retorna vazio.
     */
    public static ArrayList<Pessoa> readAll(RandomAccessFile file) {
        ArrayList<Pessoa> p = null;
        boolean running = true;
        byte[] b_data;
        try {
            p = new ArrayList<Pessoa>();
            while(running) {
                try {
                    int b_data_size = file.readInt();
                    b_data = new byte[b_data_size];
                    file.read(b_data);
                    Pessoa pessoa = Tools.toObject(b_data);
                    p.add(pessoa);
                    b_data = null; // Limpando o byte array. (Necessario?)
                }
                catch(EOFException eof) {
                    running = false;
                    System.out.println("Todos os dados do arquivo foram lidos.");
                    return p;
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    /**
     * 
     * @param file              Nome relativo ao arquivo desejado para salvar os dados.
     * @param nome              String relatava ao nome desejado para a pesquisa.
     * @return                  Retorna Pessoa encontrada.
     * 
     *                          Nota:
     *                              É feito uma busca sequencial.
     *                              Se a pessoa não for encontrada retorna null.
     *                              O nome desejado deve ser exatamente o que está salvo.
     * 
     * Obs:
     *      * Futuramente mudar a forma de pesquisa.
     *      * Retornar talvez uma lista das pessoas encontradas com o primeiro nome,
     *        retornar a pessoa com o nome proximo.
     *      * Olhar o nome sem acento ou caracteres especiais.
     */    
    public static Pessoa findS(RandomAccessFile file, String nome) {
        Pessoa p = null;
        boolean running = true;
        byte[] b_data;
        try {
            while(running) {
                try {
                    int b_data_size = file.readInt();
                    b_data = new byte[b_data_size];
                    file.read(b_data);
                    p = Tools.toObject(b_data);
                    if(p.getName().equals(nome)) {
                        return p;
                    }
                }
                catch(EOFException eof) {
                    running = false;
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return p;
    }
}