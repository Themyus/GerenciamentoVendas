package model;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venda {
    private Cliente cliente;
    private List<ItemVenda> itens;
    private LocalDateTime dataHoraVenda;

    public Venda(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.dataHoraVenda = LocalDateTime.now(); // Captura a data e hora atuais
    }

    public void adicionarItem(ItemVenda item) {
        itens.add(item);
    }

    public double calcularTotal() {
        return itens.stream().mapToDouble(ItemVenda::getSubtotal).sum();
    }

    public void imprimirNota() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("\n--- NOTA FISCAL ---");
        System.out.println("Data/Hora: " + dataHoraVenda.format(formatter));
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Itens da Venda:");
        for (ItemVenda item : itens) {
            System.out.println("- " + item);
        }
        System.out.println("Total: R$" + String.format("%.2f", calcularTotal()));
        System.out.println("---------------------"); // Adicionar um separador
    }
}