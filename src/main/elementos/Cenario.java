package main.elementos;

import java.util.ArrayList;
import java.util.List;

import main.controller.SistemaController;
import main.exception.DescricaoNulaVazia;

public class Cenario {
	private String descricao;
	private Estado estado;
	private List<Aposta> apostas;
	private Aposta aposta;
	private double dinheiroAposta;
	

	public Cenario(String descricao, Estado estado) throws Exception {
		if (descricao.trim().equals("")) {
			throw new DescricaoNulaVazia("Descricao Nula ou Vazia");
		}
		this.descricao = descricao;
		this.estado = estado;
		this.apostas = new ArrayList<>();
		this.dinheiroAposta = 0;

	}

	/**
	 * Adiciona Aposta a uma lista
	 * 
	 * @param apostador nome do apostador
	 * @param vai quantia em reais que ira apostar
	 * @param previsao se vai acontecer ou nao
	 * @throws Exception caso apostador ou previsao seja nula ou vazia, ou valor negativo
	 */
	public void adicionaAposta(String apostador, double valor, String previsao) throws Exception {
		aposta = new Aposta(apostador, valor, previsao);
		apostas.add(aposta);
	}

	/**
	 * 
	 * @return o total de apostas feitas em um cenario
	 */
	public int totalDeApostadores() {
		return apostas.size();
	}

	/**
	 * Gerar a representação textual das apostas de um cenario
	 * 
	 * @return apostadores
	 */
	public String exibeAposta() {
		String Apostadores = toString() + "\nApostadore(s)\n";
		for (Aposta aposta : apostas) {
			Apostadores += aposta.toString() + "\n";
		}
		return Apostadores;
	}

	/**
	 * Valor total das apostas
	 * 
	 * @return valor total das apostas
	 */
	public int valorTotalDeApostas() {
		int valorTotal = 0;
		for (Aposta aposta : apostas) {
			valorTotal += aposta.getValor();
		}
		return valorTotal;
	}

	public String getDescricao() {
		return descricao;
	}

	public Estado getEstado() {
		return estado;
	}
	public void setEstado() {
		this.estado = Estado.FINALIZADO;
	}

	@Override
	public String toString() {
		return descricao + " - " + estado.getEstadoo();
	}
	/**
	 * Metodo criado para verificar ganhadores e fazer a soma do valor das apostas dos perdedores
	 * @param ocorreu
	 * @return o somatorio dos valores das apostas perdedoras
	 */
	public double verificaGanhador(boolean ocorreu) {
		if (ocorreu) {
			for (Aposta aposta : apostas) {
				if (aposta.getPrevisao().equals("N VAI ACONTECER")) {
					dinheiroAposta += aposta.getValor();
				}
			}
		} else {
			for (Aposta aposta : apostas) {
				if (aposta.getPrevisao().equals("VAI ACONTECER")) {
					dinheiroAposta += aposta.getValor();
				}
			}
		}
		return dinheiroAposta;
	}
	
	public double getCaixaCenario() {
		return dinheiroAposta;
	}

}
