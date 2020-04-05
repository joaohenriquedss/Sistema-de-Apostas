package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.controller.SistemaController;
import main.exception.DescricaoNulaVazia;
import main.exception.NumeroNegativoOuVazio;

public class TestSistemaController {
	SistemaController sistema;

	@Before
	public void iniciar() throws Exception {
		sistema = new SistemaController();
		sistema.iniciarSistema(1000, 0.01);
		sistema.addCenario("A maioria irá tirar mais do que 7 na prova!");
		sistema.addCenario("O professor irá para a aula sobre GRASP com um café!");
		sistema.cadastrarAposta(1, "Joao", 200, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Redson", 1.99, "VAI ACONTECER");
	}

	@Test
	public void testAddCenarioException() throws Exception {
		try {
			sistema.addCenario("");
		} catch (DescricaoNulaVazia e) {
			assertEquals("Descricao Nula ou Vazia", e.getMessage());
		}
	}
	@Test
	public void testAddCenario() throws Exception {
		assertEquals(3,sistema.addCenario("A maioria irá tirar mais do que 5 na prova!"));
		assertEquals(4, sistema.addCenario("O professor irá para a aula sobre GRASP com um café!"));
	}
	@Test
	public void testIniciarSistemaExcption() throws Exception{
		try {
			sistema.iniciarSistema(-1, 0.1);
		} catch (NumeroNegativoOuVazio e) {
			assertEquals("Caixa ou Taxa : Com numeracao negativa", e.getMessage());
		}
		try {
			sistema.iniciarSistema(1, -0.1);
		} catch (NumeroNegativoOuVazio e) {
			assertEquals("Caixa ou Taxa : Com numeracao negativa", e.getMessage());
		}
	}
	@Test
	public void testFecharSistema() {
		sistema.fecharSistema(1, false);
		assertEquals(199, sistema.getTotalRateioCenario(1));
		assertEquals(201, sistema.getCaixaCenario(1));
	}

	@Test
	public void testCadastrarAposta() throws Exception {
		assertEquals("Cadastro nao realizado", sistema.cadastrarAposta(3, "Henrique", 99, "N VAI ACONTECER"));
		assertEquals("Cadastro realizado", sistema.cadastrarAposta(2, "Henrique", 99, "N VAI ACONTECER"));
		
	}

	@Test
	public void testValorTotalDeApostas() {
		assertEquals(201, sistema.valorTotalDeApostas(1));
		assertEquals(0, sistema.valorTotalDeApostas(1000));
		
	}

	@Test
	public void testTotalDeApostadores() {
		assertEquals(2, sistema.totalDeApostadores(1));
	}

	@Test
	public void testExibeApostas() {
		assertEquals("A maioria irá tirar mais do que 7 na prova! - "
				+ "Nao finalizado\nApostadore(s)"
				+ "\nJoao - R$200.0 - VAI ACONTECER"
				+ "\nRedson - R$1.99 - VAI ACONTECER\n", sistema.exibeApostas(1));
	}

	@Test
	public void testExibirCenario() {
		assertEquals("2 - O professor irá para a aula sobre GRASP com um café!"
				+ " - Nao finalizado", sistema.exibirCenario(2));
	}

	@Test
	public void testExibirTodosCenarios() {
		assertEquals("1 - A maioria irá tirar mais do que 7 na prova! - Nao finalizado"
				+ "\n2 - O professor irá para a aula sobre GRASP com um café! - Nao finalizado\n", sistema.exibirTodosCenarios());
		sistema.fecharSistema(1, false);
		assertEquals("1 - A maioria irá tirar mais do que 7 na prova! - Finalizado"
				+ "\n2 - O professor irá para a aula sobre GRASP com um café! - Nao finalizado\n", sistema.exibirTodosCenarios());

	}

	@Test
	public void testEstadoDoCenario() {
		assertEquals("Nao finalizado", sistema.estadoDoCenario(1));
		assertEquals("Nao finalizado", sistema.estadoDoCenario(2));
		assertEquals("Cenario nao existe", sistema.estadoDoCenario(3));
		sistema.fecharSistema(2, true);
		sistema.fecharSistema(1, false);
		assertEquals("Finalizado", sistema.estadoDoCenario(2));
		assertEquals("Finalizado", sistema.estadoDoCenario(1));
	}

	@Test
	public void testGetCaixa() {
		assertEquals(1000, sistema.getCaixa());
	}

	@Test
	public void testGetTaxa() {
		assertEquals(0.01, sistema.getTaxa(),0.5);
	
	}

}
