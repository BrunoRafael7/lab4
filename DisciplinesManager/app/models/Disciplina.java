package models;

import java.util.List;

public class Disciplina{
	
	@Override
	public String toString() {
		return "Disciplina [nome=" + nome + ", preRequisitos=" + preRequisitos
				+ ", creditos=" + creditos + ", periodo=" + periodo + "]";
	}

	private String nome;
	private List<String> preRequisitos;
	private int creditos;
	private int periodo;
	private boolean alocada;
	
	public Disciplina(List<String> preRequisitos, String nome, int creditos, int periodo){
		this.nome = nome;
		this.preRequisitos = preRequisitos;
		this.creditos = creditos;
		this.periodo = periodo;
	}

	public String getNome() {
		return nome;
	}

	public List<String> getPreRequisitos() {
		return preRequisitos;
	}

	public int getCreditos() {
		return creditos;
	}

	public int getPeriodo() {
		return periodo;
	}

	public boolean isAlocada() {
		return alocada;
	}

	public void alocaDisciplina() {
			this.alocada = true;
	}
	
	public void desalocaDisciplina(){
		this.alocada = false;
	}
}