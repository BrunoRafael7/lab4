package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/*
 * Pure Fabrication : Pois esta classe está sendo usada para "coletar"
 * as disciplinas e ela não faz parte do contexto do mundo real.
 */

/**
 * Classe que coleta os dados das disciplinas de um arquivo.
 *
 */
public class ColetorDeDisciplinas {
	
	private String path = "disciplinas";
	private List<Disciplina> disciplinasColetadas;
	
	/**
	 * Construtor
	 */
	public ColetorDeDisciplinas(){
		disciplinasColetadas = new LinkedList<Disciplina>();
	}
	
	/**
	 * Coleta as disciplinas do arquivo.
	 * @return lista com as disciplinas coletadas.
	 */
	public List<Disciplina> coletar(){
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(new File(path))
					)
			);
			while(reader.ready()){
				String[] line = reader.readLine().split("-");
				Disciplina disc = criarDisciplina(line);
				this.disciplinasColetadas.add(disc);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return disciplinasColetadas;
	}
	
	/**
	 * Método que auxilia a extração dos dados no momento da coleta
	 * @param line
	 * @return uma lista contendo nome, créditos e o período da disciplina.
	 */
	private Disciplina criarDisciplina(String[] line) {
		
		List<String> preRequisitos = criarListaDePreRequisitos(line[0]);
		String nome = line[1];
		int creditos = Integer.parseInt(line[2]);
		int periodo = Integer.parseInt(line[3]);
		String dificuldade = line[4];
		/*
		 * CREATOR : ColetorDeDisciplinas será o responsável por criar Disciplinas pois 
		 * ColetorDeDisciplinas tem as informações necessárias para a criação de uma Disciplina 
		 */
		return new Disciplina(preRequisitos, nome, creditos, periodo, dificuldade);
	}
	
	/**
	 * Método que auxilia a extração dos dados no momento da coleta
	 * @param line
	 * @return uma lista com os pré requisitos da disciplina
	 */
	private List<String> criarListaDePreRequisitos(String line) {

		line = line.replace("[", "");
		line = line.replace("]", "");

		String[] nomesDosPreRequisitos = line.split(",");
		List<String> preRequisitos = new LinkedList<String>();
		for(String nome : nomesDosPreRequisitos){
			if(!(nome.equals(""))){ //NAO ADICIONA STRING VAZIA NOS PRE-REQUISITOS
				preRequisitos.add(nome);
			}
		}
		return preRequisitos;
	}
	
	/**
	 * 
	 * @return uma lista contendo todas as disciplinas coletadas.
	 */
	public List<Disciplina> getDisciplinasColetadas() {
		return disciplinasColetadas;
	}
}