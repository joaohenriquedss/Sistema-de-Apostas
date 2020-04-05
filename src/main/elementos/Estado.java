package main.elementos;

public enum Estado {
	FINALIZADO("Finalizado"), NAO_FINALIZADO("Nao finalizado");
	private String estado;

	Estado(String estado) {
		this.estado = estado;
	}

	public String getEstadoo() {
		return estado;
	}
	
}
