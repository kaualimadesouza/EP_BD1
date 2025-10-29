package org.example.goalytics.service;

import org.example.goalytics.model.Cartao;
import org.example.goalytics.repository.CartaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaoService {

    private final CartaoRepository cartaoRepository;

    public CartaoService(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    public List<Cartao> listarCartoes() {
        return cartaoRepository.listarTodos();
    }

    public void excluirCartao(Integer numEvento) {
        if (!cartaoRepository.existePorNumEvento(numEvento)) {
            throw new RuntimeException("Cartao com num_evento " + numEvento + " não encontrado.");
        }
        cartaoRepository.excluirCartao(numEvento);
    }

    public List<String> obterColunas() {
        return cartaoRepository.listarNomeColunas();
    }

    public void inserirCartao(Cartao cartao) {
        cartaoRepository.inserir(cartao);
    }

    public void atualizarCartao(Cartao cartao, Integer numEvento) {
        if (cartaoRepository.existePorNumEvento(numEvento)) {
            cartaoRepository.atualizar(cartao, numEvento);
        } else {
            throw new RuntimeException("Cartao com num_evento " + numEvento + " não encontrado.");
        }
    }

    public Cartao obterCartaoPorNumEvento(Integer numEvento) {
        return cartaoRepository.obterPorNumEvento(numEvento);
    }
}
