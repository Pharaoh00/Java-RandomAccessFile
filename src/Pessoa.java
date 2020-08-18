package src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class Pessoa {
    private int id;
    private String nome;
    private byte idade;

    // Construtor padrão
    public Pessoa() {
        this.id = -1;
        this.nome = null;
        this.idade = (byte)-1;
    }

    /**
     * 
     * @param id        Id de um usuario, note que este id deve ser unico. Não sendo zero.
     * @param nome      Nome do usuario. Podendo ser um usuario com caracteres especiais UTF-8.
     * @param idade     Idade do usuario. Sendo byte, serão somente aceitos valores maiores que 1.
     * 
     *                  Note que valores iguais ou menos que zero representam idades invalidas.
     */
    public Pessoa(int id, String nome, byte idade) {
        // Obs:
        //      Error handling
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    /*
     * Metodo para retornar Pessoa em um byte array. 
     * Retorna um array de bytes relativo aos dados de Pessoa.
     */
    public byte[] toByteArray() {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            DataOutputStream data = new DataOutputStream(output);

            data.writeInt(this.id);
            data.writeUTF(this.nome);
            data.writeByte(this.idade);

            return output.toByteArray();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * @return          Retorna uma String equivalente ao nome de Pessoa.
     */
    public String getName() {
        return this.nome;
    }

    // Metodo para sobrepor o System.out.println() do java original.
    // Significa que:
    //              Quando usado System.out.println(Pessoa) o que irá ser printando na tela
    //              será o que está sendo retornado na classe.
    @Override
    public String toString() {
        return "\nID: " + this.id +
               "\nNome: " + this.nome +
               "\nIdade " + this.idade;
    }
}