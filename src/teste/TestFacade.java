package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.controller.SistemaController;
import main.exception.DescricaoNulaVazia;
import main.exception.NomeNuloOuVazio;
import main.exception.NumeroNegativoOuVazio;
import main.exception.PrevisaoNulaOuVazia;
import main.facade.SistemaFacade;

public class TestFacade {
	private SistemaFacade facade;
	private final static String CADASTRO_REALIZADO = "Cadastro realizado";
	private final static String CADASTRO_NAO_REALIZADO = "Cadastro nao realizado";
	private final static String CENARIO_NAO_EXISTE = "Cenario nao existe";
	
	@Before
	public void iniciar() throws Exception{
		facade = new SistemaFacade();
		facade.iniciaSistema(1000, 0.01);
		facade.cadastrarCenario("A maioria irá tirar mais do que 7 na prova!");
		facade.cadastrarCenario("Redson não vai perder o BV esse ano");
		facade.cadastrarAposta(2, "Joao", 200, "VAI ACONTECER");
		facade.cadastrarAposta(2, "Redson", 1.99, "VAI ACONTECER");
	}
	@Test
	public void testCadastrarCenario() throws Exception {
		assertEquals(3, facade.cadastrarCenario("Redson vai perde o bv esse ano"));
	}
	
	@Test
	public void testCadastrarCenarioException() throws Exception {
		try {
			facade.cadastrarCenario("");
		} catch (DescricaoNulaVazia e) {
			assertEquals("Descricao Nula ou Vazia", e.getMessage());
		}
	}

	@Test
	public void testExibirCenario() {
		assertEquals("2 - Redson não vai perder o BV esse ano"
				+ " - Nao finalizado", facade.exibirCenario(2));
	}

	@Test
	public void testExibirTodosCenarios() {
		facade.fecharAposta(2, false);
		assertEquals("1 - A maioria irá tirar mais do que 7 na prova! - Nao finalizado"
				+ "\n2 - Redson não vai perder o BV esse ano - Finalizado\n", facade.exibirTodosCenarios());
		
	}

	@Test
	public void testCadastrarApostaException() throws Exception {
		try {
			facade.cadastrarAposta(1, "", 5.5, "VAI ACONTECER");
		} catch (NomeNuloOuVazio e) {
			assertEquals("Nome Nulo Ou Vazio", e.getMessage());
		}
		try {
			facade.cadastrarAposta(2, "Henrique", -5.5, "VAI ACONTECER");
		} catch (NumeroNegativoOuVazio e) {
			assertEquals("Valor Negativo", e.getMessage());
		}
		try {
			facade.cadastrarAposta(2, "Henrique", 5.5, "");
		} catch (PrevisaoNulaOuVazia e) {
			assertEquals("Previsao Nula Ou Vazia", e.getMessage());
		}
	}
	@Test
	public void testExibeApostas() {
		assertEquals("Redson não vai perder o BV esse ano - "
				+ "Nao finalizado\nApostadore(s)"
				+ "\nJoao - R$200.0 - VAI ACONTECER"
				+ "\nRedson - R$1.99 - VAI ACONTECER\n", facade.exibeApostas(2));
	}

	@Test
	public void testFecharAposta() {
		facade.fecharAposta(2, false);
		assertEquals(201, facade.getCaixaCenario(2));
		assertEquals(199, facade.getTotalRateioCenario(2));
		assertEquals(1201, facade.getCaixa());
		facade.fecharAposta(1, true);
		assertEquals(0, facade.getCaixaCenario(1));
		assertEquals(0, facade.getTotalRateioCenario(1));
	}


}
