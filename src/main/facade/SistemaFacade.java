package main.facade;

import main.controller.SistemaController;

public class SistemaFacade {
	SistemaController sistema;
	
	public SistemaFacade() {
		this.sistema = new SistemaController();
	}
	/**
	 * Iniciar o sistema
	 * @param caixa
	 * @param taxa
	 * @throws Exception  caso caixa ou taxa seja negativo
	 */
	public void iniciaSistema(int caixa,double taxa) throws Exception {
		this.sistema.iniciarSistema(caixa, taxa);
	}
	/**
	 * Metodo para adicionar cenario
	 * @param descricao descricao do cenario
	 * @return a posicao 
	 * @throws Exception se a descricao do cenario for vazia ou nula
	 */
	public int cadastrarCenario(String descricao) throws Exception{
		return sistema.addCenario(descricao);
	}
	/**
	 * Exibi um cenario 
	 * @param cenario a posicao do cenario
	 * @return um cenario desejado
	 */
	public String exibirCenario(int cenario){
		return sistema.exibirCenario(cenario);
	}
	/**
	 * Exibi todos os cenarios
	 * @return todos os cenarios
	 */
	public String exibirTodosCenarios(){
		return sistema.exibirTodosCenarios();
		
	}
	/**
	 * Cadastra uma aposta
	 * @param cenario posicao do cenario
	 * @param apostador nome do apostador
	 * @param valor valor em reias da aposta
	 * @param previsao previsao se vai ocorrer ou nao
	 * @return
	 */
	public void cadastrarAposta(int cenario, String apostador,
			double valor, String previsao) throws Exception{
		sistema.cadastrarAposta(cenario, apostador, valor, previsao);
		
	}
	/**
	 * Valor total das apostas
	 * @param cenario posicao de um cenario
	 * @return retorna o valor em reais das apostas
	 */
	public int valorTotalDeApostas(int cenario){
		return sistema.valorTotalDeApostas(cenario);
		
	}
	/**
	 * Quantidade de apostadores em um unico cenario
	 * @param cenario posicao de um cenario desejado
	 * @return a quantidade de apostadores e se nao ouver cenario retorna 0
	 */
	public int totalDeApostas(int cenario){
		return sistema.totalDeApostadores(cenario);
		
	}
	/**
	 * Gera a representaçao textual das apostas de um cenario
	 * @return apostadores
	 */
	public String exibeApostas(int cenario){
		return sistema.exibeApostas(cenario);
		
	}
	/**
	 * Encerrar um cenário (indicando se ocorreu ou não).
	 * @param num posicao do cenario
	 * @param ocorreu se ocorreu ou nao
	 */
	public void fecharAposta(int cenario, boolean ocorreu){
		sistema.fecharSistema(cenario, ocorreu);
	}
	/**
	 * 
	 * @param num posicao do cenario
	 * @return Retornar o valor total de um cenário encerrado que será destinado a distribuição entre as apostas vencedoras
	 * 
	 * 
	 */
	public int getTotalRateioCenario(int cenario){
		return sistema.getTotalRateioCenario(cenario);
		
	}
	/**
	 * 
	 * @param num posicao do cenario
	 * @return Retornar o valor total de um cenário encerrado que será destinado ao caixa
	 */
	public int getCaixaCenario(int cenario){
		return sistema.getCaixaCenario(cenario);}
	
	public int getCaixa(){
		return sistema.getCaixa();
	}
}
