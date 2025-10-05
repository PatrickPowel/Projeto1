package br.com.rpires;

import br.com.rpires.dao.ClienteMapDAO;
import br.com.rpires.dao.IClienteDAO;
import br.com.rpires.domain.Cliente;

import javax.swing.*;

public class App {
    private static IClienteDAO iClienteDAO;

    static void main(String[] args) {
        iClienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair",
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        while (!isOpcaoValida(opcao)) {
            if ("".equals(opcao)) {
                sair();
            } else if (isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Opção Inválida digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alterar cadastro ou 5 para sair",
                        "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
            }
        }

        while (isOpcaoValida(opcao)) {
            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente por vírgula, conforme exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade e Estado ",
                        "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
            } else if (isConsultar(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite o CPF ",
                        "Consultar", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);
            } else if (isExcluir(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite o CPF para exclusão ",
                        "Excluir", JOptionPane.INFORMATION_MESSAGE);
                excluir(dados);
            } else if (isAlterar(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente para alteração separados por vírgula (Nome, CPF, Telefone, Endereço, Número, Cidade e Estado)",
                        "Alterar", JOptionPane.INFORMATION_MESSAGE);
                alterar(dados);
            }

            opcao = JOptionPane.showInputDialog(null,
                    "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void consultar(String dados) {
        Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados));
        if (cliente != null) {
            JOptionPane.showMessageDialog(null,
                    "Cliente encontrado: " + cliente, "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Nenhum cliente encontrado com o CPF informado.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1],
                dadosSeparados[2], dadosSeparados[3], dadosSeparados[4],
                dadosSeparados[5], dadosSeparados[6]);
        boolean isCadastrado = iClienteDAO.cadastrar(cliente);
        if (isCadastrado) {
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente já se encontra cadastrado",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void excluir(String dados) {
        long cpf = Long.parseLong(dados);
        Cliente cliente = iClienteDAO.consultar(cpf);
        if (cliente != null) {
            iClienteDAO.excluir(cpf);
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Nenhum cliente encontrado com o CPF informado.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void alterar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1],
                dadosSeparados[2], dadosSeparados[3], dadosSeparados[4],
                dadosSeparados[5], dadosSeparados[6]);

        Cliente clienteExistente = iClienteDAO.consultar(Long.parseLong(dadosSeparados[1]));
        if (clienteExistente != null) {
            iClienteDAO.alterar(cliente);
            JOptionPane.showMessageDialog(null,
                    "Cliente alterado com sucesso", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Nenhum cliente encontrado com o CPF informado.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- validadores de opção ---
    private static boolean isConsultar(String opcao) {
        return "2".equals(opcao);
    }

    private static boolean isCadastro(String opcao) {
        return "1".equals(opcao);
    }

    private static boolean isExcluir(String opcao) {
        return "3".equals(opcao);
    }

    private static boolean isAlterar(String opcao) {
        return "4".equals(opcao);
    }

    private static boolean isOpcaoSair(String opcao) {
        return "5".equals(opcao);
    }

    private static boolean isOpcaoValida(String opcao) {
        return "1".equals(opcao) || "2".equals(opcao)
                || "3".equals(opcao) || "4".equals(opcao)
                || "5".equals(opcao);
    }

    private static void sair() {
        JOptionPane.showMessageDialog(null,
                "Até logo! ", "Sair",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
