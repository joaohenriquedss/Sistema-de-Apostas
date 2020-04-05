package main.controller;
//joao
import main.elementos.Cenario;
import main.elementos.Sistema;
import main.repository.CenarioRepository;

public class SistemaController {

	private Cenario cena;
	private CenarioRepository repositorio;
	private Sistema sistema;
	private final static String CADASTRO_REALIZADO = "Cadastro realizado";
	private final static String CADASTRO_NAO_REALIZADO = "Cadastro nao realizado";
	private final static String CENARIO_NAO_EXISTE = "Cenario nao existe";
	
	public SistemaController() {
		this.repositorio = new CenarioRepository();

	}
	/**
	 * Iniciar o sistema
	 * @param caixa
	 * @param taxa
	 * @throws Exception 
	 */
	public boolean iniciarSistema(int caixa,double taxa) throws Exception{
		this.sistema = new Sistema(caixa, taxa);
		return true;
	}
	/**
	 * Encerrar um cenário (indicando se ocorreu ou não).
	 * @param num posicao do cenario
	 * @param ocorreu se ocorreu ou nao
	 */
	public void fecharSistema(int num, boolean ocorreu) {
		if(repositorio.recuperar(num) != null){
			cena = repositorio.recuperar(num);
			cena.verificaGanhador(ocorreu);
			cena.setEstado();
		}
		
	}
	/**
	 * Cadastra uma aposta
	 * @param cenario posicao do cenario
	 * @param apostador nome do apostador
	 * @param valor valor em reias da aposta
	 * @param previsao previsao se vai ocorrer ou nao
	 * @return
	 */
	public String cadastrarAposta(int cenario, String apostador, double valor, String previsao) throws Exception {
		if(repositorio.recuperar(cenario) != null){
			cena = repositorio.recuperar(cenario);
			cena.adicionaAposta(apostador, valor, previsao);
			return CADASTRO_REALIZADO;
		}
		return CADASTRO_NAO_REALIZADO;
	}
	/**
	 * Valor total das apostas
	 * @param cenario posicao de um cenario
	 * @return retorna o valor em reais das apostas
	 */
	public int valorTotalDeApostas(int cenario){
		if(repositorio.recuperar(cenario) != null){
			cena = repositorio.recuperar(cenario);
			return cena.valorTotalDeApostas();
		}
		return 0;
	}
	/**
	 * Quantidade de apostadores em um unico cenario
	 * @param cenario posicao de um cenario desejado
	 * @return a quantidade de apostadores e se nao ouver cenario retorna 0
	 */
	public int totalDeApostadores(int cenario){
		if(repositorio.recuperar(cenario) != null){
			cena = repositorio.recuperar(cenario);
			return cena.totalDeApostadores();
		}
		return 0;
	}
	/**
	 * Gera a representaçao textual das apostas de um cenario
	 * @return apostadores
	 */
	public String exibeApostas(int cenario){
		if(repositorio.recuperar(cenario) != null){
			cena = repositorio.recuperar(cenario);
			return cena.exibeAposta();
		}
		return CENARIO_NAO_EXISTE;
		
	}
	/**
	 * Adiciona cenario a uma lista 
	 * @param descricao do cenario
	 * @return a posicao do cenario adicionado
	 * @throws Exception se estiver nula ou vazia
	 */
	public int addCenario(String descricao) throws Exception {
		return repositorio.adicionar(descricao);
		
	}
	/**
	 * Exibi um cenario
	 * @param pos posicao do cenario
	 * @return um cenario 
	 */
	public String exibirCenario(int pos){
		return repositorio.exibir(pos);
	}
	/**
	 * Exibi todos os cenarios
	 */
	public String exibirTodosCenarios(){
		return repositorio.exibirCenarios();
	}
	/**
	 * Exibe o estado de um cenario 
	 * @param num posicao de um cenario
	 * @return o estado do cenario
	 */
	public String estadoDoCenario(int num) {
		if(repositorio.recuperar(num) != null){
			cena = repositorio.recuperar(num);
			return cena.getEstado().getEstadoo();
		}
		return CENARIO_NAO_EXISTE;
		
	}
	/**
	 * 
	 * @param num posicao do cenario
	 * @return Retornar o valor total de um cenário encerrado que será destinado a distribuição entre as apostas vencedoras
	 * 
	 *  */
	public int getTotalRateioCenario(int num){
		if(repositorio.recuperar(num) != null){
			cena = repositorio.recuperar(num);
			return (int) ( cena.getCaixaCenario() - (cena.getCaixaCenario() * getTaxa()));
		}
		return 0;
	}
	/**
	 * 
	 * @param num posicao do cenario
	 * @return Retornar o valor total de um cenário encerrado que será destinado ao caixa
	 */
	public int getCaixaCenario (int num){
		if (repositorio.recuperar(num) != null) {
			cena = repositorio.recuperar(num);
			sistema.setCaixa((int) ((cena.getCaixaCenario() * getTaxa()) * 100));
			return (int) ((cena.getCaixaCenario() * getTaxa()) * 100);
		}
		return 0;
	}
	/**
	 * 
	 * @return o valor do caixa do sistema
	 */
	public int getCaixa(){
		return sistema.getCaixa();
	}
	/**
	 * 
	 * @return o valor da taxa do sistema
	 */
	public double getTaxa(){
		return sistema.getTaxa();
	}
}
