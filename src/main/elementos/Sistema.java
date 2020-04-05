package main.elementos;

import main.exception.NumeroNegativoOuVazio;

public class Sistema {
	
	private int caixa;
	private double taxa;
	
	public Sistema(int caixa, double taxa) throws Exception{
		if (caixa < 0 || taxa < 0) {
			throw new NumeroNegativoOuVazio("Caixa ou Taxa : Com numeracao negativa");
		}
		this.caixa = caixa;
		this.taxa = taxa;
	}
	public int getCaixa() {
		return caixa;
	}
	public void setCaixa(int caixa) {
		this.caixa += caixa;
	}
	public double getTaxa() {
		return taxa;
	}
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}
	
	
	
}
