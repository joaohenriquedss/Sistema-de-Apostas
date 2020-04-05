package main.repository;

import java.util.ArrayList;
import java.util.List;
import main.elementos.Cenario;
import main.elementos.Estado;


public class CenarioRepository {
	private List<Cenario> cenarios;
	private Cenario cenario;

	public CenarioRepository() {
		this.cenarios = new ArrayList<>();
	}
	/**
	 * Metodo para adicionar cenario
	 * @param descricao descricao do cenario
	 * @return a posicao 
	 * @throws Exception se a descricao do cenario for vazia ou nula
	 */
	public int adicionar(String descricao) throws Exception  {
		Estado estado = Estado.NAO_FINALIZADO;
		cenario = new Cenario(descricao, estado);
		cenarios.add(cenario);
		return cenarios.size();
	}
	/**
	 * Metodo criado para exibir um cenario aparti da posicao
	 * @param num posicao do cenario na lista
	 * @return o toString do cenario
	 */
	public String exibir(int num){
		return num + " - " + cenarios.get(num - 1).toString();
	}
	/**
	 * Recupera um cenario
	 * @param num posicao do cenario
	 * @return retorna um cenario se ele existir se nao existir retorna null
	 */
	public Cenario recuperar(int num){
		if(cenarios.size() >= num){
			return cenarios.get(num - 1);
		}
		return null;
	}
	/**
	 * Exibi todos os cenarios
	 * @return
	 */
	public String exibirCenarios(){
		String todosOsCenarios = "";
		int posi = 1;
		for (Cenario cenario : cenarios) {
			todosOsCenarios += posi + " - " + cenario.toString() + "\n";
			posi ++ ;
		}
		return todosOsCenarios;
	}
}
