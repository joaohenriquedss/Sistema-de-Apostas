package main.elementos;

import main.exception.NomeNuloOuVazio;
import main.exception.NumeroNegativoOuVazio;
import main.exception.PrevisaoNulaOuVazia;

public class Aposta {
	private String nome;
	private double valor;
	private String previsao;

	public Aposta(String nome, double valor, String previsao) throws Exception{
		if (nome.trim().equals("") || nome == null) {
			throw new NomeNuloOuVazio("Nome Nulo Ou Vazio");
		}
		if (valor < 0) {
			throw new NumeroNegativoOuVazio("Valor Negativo");
		}
		if (previsao.trim().equals("") || nome == null) {
			throw new PrevisaoNulaOuVazia("Previsao Nula Ou Vazia");
		}
		this.nome = nome;
		this.valor = valor;
		this.previsao = previsao;
	}

	public String getNome() {
		return nome;
	}

	public double getValor() {
		return valor;
	}

	public String getPrevisao() {
		return previsao;
	}

	@Override
	public String toString() {
		return nome + " - R$" + valor + " - " + previsao;
	}

}
