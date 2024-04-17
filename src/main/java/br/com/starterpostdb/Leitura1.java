package br.com.starterpostdb;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Scanner;

public class Leitura1 {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "123";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Scanner sc = new Scanner(System.in);

            // Solicitar dados do usuário
            System.out.println("Informe o seu nome, pressione enter em seguida");
            String nome = sc.nextLine();

            System.out.println("Informe a sua idade, pressione enter em seguida");
            int idade = sc.nextInt();
            sc.nextLine(); // Consumir a quebra de linha após a entrada de idade

            // Preparar e executar a query SQL para inserir dados
            String sql = "INSERT INTO usuarios(nome, idade) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, nome);
                preparedStatement.setInt(2, idade);

                // Executar a query
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " linhas inseridas no banco de dados com sucesso");
            } catch (SQLException e) {
                System.err.println("Erro ao gravar no banco de dados, consultar suporte técnico||| Código: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao estabelecer conexão com Banco de Dados, por favor contate suporte técnico||| Código: " + e.getMessage());
        }
    }
}



