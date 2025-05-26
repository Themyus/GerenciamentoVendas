package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int pin;
    private LocalDate data;
    private Cliente cliente;
    private List<ItemVenda> itens;

    public Venda(int pin, Cliente cliente) {
        this.pin = pin;
        this.data = LocalDate.now();
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemVenda item) {
        itens.add(item);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemVenda item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }
}