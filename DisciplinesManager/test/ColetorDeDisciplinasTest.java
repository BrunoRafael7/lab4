import models.ColetorDeDisciplinas;

import org.junit.Before;

public class ColetorDeDisciplinasTest {
	private ColetorDeDisciplinas disciplinas;

	@Before
	public void start(){
		disciplinas = new ColetorDeDisciplinas();
		disciplinas.coletar();
	}

	/*
	 * TESTES CONCLUÌDOS e passando na classe GradeCurricularTest
	 */
//	@Test
//	public void deveRetornarOsCreditosCorrespondentesCorretamente(){
//		assertTrue(disciplinas.get("Calculo_Diferencial_e_Integral_I").getCreditos() == 4);	
//		assertTrue(disciplinas.get("Álgebra_Vetorial_e_Geometria_Analítica").getCreditos() == 4);
//		assertTrue(disciplinas.get("Leitura_e_Produção_de_Textos").getCreditos() == 4);
//		assertTrue(disciplinas.get("Programação_I").getCreditos() == 4);
//		assertTrue(disciplinas.get("Introdução_à_Computação").getCreditos() == 4);
//		assertTrue(disciplinas.get("Laboratório_de_Programação_I").getCreditos() == 4);
//		assertTrue(disciplinas.get("Cálculo_Diferencial_e_Integral_II").getCreditos() == 4);
//		assertTrue(disciplinas.get("Matemática_Discreta").getCreditos() == 4);
//		assertTrue(disciplinas.get("Metodologia_Científica").getCreditos() == 4);
//		assertTrue(disciplinas.get("Programação_II").getCreditos() == 4);
//		assertTrue(disciplinas.get("Teoria_dos_Grafos").getCreditos() == 2);
//		assertTrue(disciplinas.get("Fundamentos_de_Física_Clássica").getCreditos() == 4);
//		assertTrue(disciplinas.get("Laboratório_de_Programação_II").getCreditos() == 4);
//		assertTrue(disciplinas.get("Álgebra_Linear").getCreditos() == 4);
//		assertTrue(disciplinas.get("Probabilidade_e_Estatística").getCreditos() == 4);
//		assertTrue(disciplinas.get("Teoria_da_Computação").getCreditos() == 4);
//		assertTrue(disciplinas.get("Estruturas_de_Dados_e_Algoritmos").getCreditos() == 4);
//		assertTrue(disciplinas.get("Fundamentos_de_Física_Moderna").getCreditos() == 4);
//		assertTrue(disciplinas.get("Gerência_da_Informação").getCreditos() == 4);
//		assertTrue(disciplinas.get("Laboratório_de_Estruturas_de_Dados_e_Algoritmos").getCreditos() == 4);
//		assertTrue(disciplinas.get("Métodos_Estatísticos").getCreditos() == 4);
//		assertTrue(disciplinas.get("Paradigmas_de_Linguagens_de_Programação").getCreditos() == 2);
//		assertTrue(disciplinas.get("Lógica_Matemática").getCreditos() == 4);
//		assertTrue(disciplinas.get("Organização_e_Arquitetura_de_Computadores_I").getCreditos() == 4);
//		assertTrue(disciplinas.get("Engenharia_de_Software_I").getCreditos() == 4);
//		assertTrue(disciplinas.get("Sistemas_de_Informação_I").getCreditos() == 4);
//		assertTrue(disciplinas.get("Lab_de_Organização_e_Arquitetura_de_Computadores").getCreditos() == 4);
//		assertTrue(disciplinas.get("Informática_e_Sociedade").getCreditos() == 2);
//		assertTrue(disciplinas.get("Análise_e_Técnicas_de_Algoritmos").getCreditos() == 4);
//		assertTrue(disciplinas.get("Compiladores").getCreditos() == 4);
//		assertTrue(disciplinas.get("Redes_de_Computadores").getCreditos() == 4);
//		assertTrue(disciplinas.get("Bancos_de_Dados_I").getCreditos() == 4);
//		assertTrue(disciplinas.get("Sistemas_de_Informação_II").getCreditos() == 4);
//		assertTrue(disciplinas.get("Laboratório_de_Engenharia_de_Software").getCreditos() == 2);
//		assertTrue(disciplinas.get("Direito_e_Cidadania").getCreditos() == 4);
//		assertTrue(disciplinas.get("Sistemas_Operacionais").getCreditos() == 4);
//		assertTrue(disciplinas.get("Interconexão_de_Redes_de_Computadores").getCreditos() == 2);
//		assertTrue(disciplinas.get("Banco_de_Dados_II").getCreditos() == 4);
//		assertTrue(disciplinas.get("Inteligência_Artificial_I").getCreditos() == 4);
//		assertTrue(disciplinas.get("Laboratório_de_Interconexão_de_Redes_de_Computadores").getCreditos() == 2);
//		assertTrue(disciplinas.get("Optativa_1").getCreditos() == 4);
//		assertTrue(disciplinas.get("Optativa_2").getCreditos() == 4);
//		assertTrue(disciplinas.get("Métodos_e_Software_Numéricos").getCreditos() == 4);
//		assertTrue(disciplinas.get("Avaliação_de_Desempenho_de_Sistemas_Discretos").getCreditos() == 4);
//		assertTrue(disciplinas.get("Projeto_em_Computação_I").getCreditos() == 4);
//		assertTrue(disciplinas.get("Optativa_3").getCreditos() == 4);
//		assertTrue(disciplinas.get("Optativa_4").getCreditos() == 4);
//		assertTrue(disciplinas.get("Optativa_5").getCreditos() == 4);
//		assertTrue(disciplinas.get("Optativa_6").getCreditos() == 4);
//		assertTrue(disciplinas.get("Projeto_em_Computação_II").getCreditos() == 6);
//		assertTrue(disciplinas.get("Optativa_7").getCreditos() == 4);
//		assertTrue(disciplinas.get("Optativa_8").getCreditos() == 4);
//		assertTrue(disciplinas.get("Optativa_9").getCreditos() == 4);
//		assertTrue(disciplinas.get("Optativa_10").getCreditos() == 4);
//		assertTrue(disciplinas.get("Optativa_11").getCreditos() == 2);
//	}
//	
//	@Test
//	public void deveRetornarOPeriodoCorreto(){
//		assertTrue(disciplinas.get("Calculo_Diferencial_e_Integral_I").getPeriodo() == 1);	
//		assertTrue(disciplinas.get("Álgebra_Vetorial_e_Geometria_Analítica").getPeriodo() == 1);
//		assertTrue(disciplinas.get("Leitura_e_Produção_de_Textos").getPeriodo() == 1);
//		assertTrue(disciplinas.get("Programação_I").getPeriodo() == 1);
//		assertTrue(disciplinas.get("Introdução_à_Computação").getPeriodo() == 1);
//		assertTrue(disciplinas.get("Laboratório_de_Programação_I").getPeriodo() == 1);
//		assertTrue(disciplinas.get("Cálculo_Diferencial_e_Integral_II").getPeriodo() == 2);
//		assertTrue(disciplinas.get("Matemática_Discreta").getPeriodo() == 2);
//		assertTrue(disciplinas.get("Metodologia_Científica").getPeriodo() == 2);
//		assertTrue(disciplinas.get("Programação_II").getPeriodo() == 2);
//		assertTrue(disciplinas.get("Teoria_dos_Grafos").getPeriodo() == 2);
//		assertTrue(disciplinas.get("Fundamentos_de_Física_Clássica").getPeriodo() == 2);
//		assertTrue(disciplinas.get("Laboratório_de_Programação_II").getPeriodo() == 2);
//		assertTrue(disciplinas.get("Álgebra_Linear").getPeriodo() == 3);
//		assertTrue(disciplinas.get("Probabilidade_e_Estatística").getPeriodo() == 3);
//		assertTrue(disciplinas.get("Teoria_da_Computação").getPeriodo() == 3);
//		assertTrue(disciplinas.get("Estruturas_de_Dados_e_Algoritmos").getPeriodo() == 3);
//		assertTrue(disciplinas.get("Fundamentos_de_Física_Moderna").getPeriodo() == 3);
//		assertTrue(disciplinas.get("Gerência_da_Informação").getPeriodo() == 3);
//		assertTrue(disciplinas.get("Laboratório_de_Estruturas_de_Dados_e_Algoritmos").getPeriodo() == 3);
//		assertTrue(disciplinas.get("Métodos_Estatísticos").getPeriodo() == 4);
//		assertTrue(disciplinas.get("Paradigmas_de_Linguagens_de_Programação").getPeriodo() == 4);
//		assertTrue(disciplinas.get("Lógica_Matemática").getPeriodo() == 4);
//		assertTrue(disciplinas.get("Organização_e_Arquitetura_de_Computadores_I").getPeriodo() == 4);
//		assertTrue(disciplinas.get("Engenharia_de_Software_I").getPeriodo() == 4);
//		assertTrue(disciplinas.get("Sistemas_de_Informação_I").getPeriodo() == 4);
//		assertTrue(disciplinas.get("Lab_de_Organização_e_Arquitetura_de_Computadores").getPeriodo() == 4);
//		assertTrue(disciplinas.get("Informática_e_Sociedade").getPeriodo() == 5);
//		assertTrue(disciplinas.get("Análise_e_Técnicas_de_Algoritmos").getPeriodo() == 5);
//		assertTrue(disciplinas.get("Compiladores").getPeriodo() == 5);
//		assertTrue(disciplinas.get("Redes_de_Computadores").getPeriodo() == 5);
//		assertTrue(disciplinas.get("Bancos_de_Dados_I").getPeriodo() == 5);
//		assertTrue(disciplinas.get("Sistemas_de_Informação_II").getPeriodo() == 5);
//		assertTrue(disciplinas.get("Laboratório_de_Engenharia_de_Software").getPeriodo() == 5);
//		assertTrue(disciplinas.get("Direito_e_Cidadania").getPeriodo() == 6);
//		assertTrue(disciplinas.get("Sistemas_Operacionais").getPeriodo() == 6);
//		assertTrue(disciplinas.get("Interconexão_de_Redes_de_Computadores").getPeriodo() == 6);
//		assertTrue(disciplinas.get("Banco_de_Dados_II").getPeriodo() == 6);
//		assertTrue(disciplinas.get("Inteligência_Artificial_I").getPeriodo() == 6);
//		assertTrue(disciplinas.get("Laboratório_de_Interconexão_de_Redes_de_Computadores").getPeriodo() == 6);
//		assertTrue(disciplinas.get("Optativa_1").getPeriodo() == 6);
//		assertTrue(disciplinas.get("Optativa_2").getPeriodo() == 6);
//		assertTrue(disciplinas.get("Métodos_e_Software_Numéricos").getPeriodo() == 7);
//		assertTrue(disciplinas.get("Avaliação_de_Desempenho_de_Sistemas_Discretos").getPeriodo() == 7);
//		assertTrue(disciplinas.get("Projeto_em_Computação_I").getPeriodo() == 7);
//		assertTrue(disciplinas.get("Optativa_3").getPeriodo() == 7);
//		assertTrue(disciplinas.get("Optativa_4").getPeriodo() == 7);
//		assertTrue(disciplinas.get("Optativa_5").getPeriodo() == 7);
//		assertTrue(disciplinas.get("Optativa_6").getPeriodo() == 7);
//		assertTrue(disciplinas.get("Projeto_em_Computação_II").getPeriodo() == 8);
//		assertTrue(disciplinas.get("Optativa_7").getPeriodo() == 8);
//		assertTrue(disciplinas.get("Optativa_8").getPeriodo() == 8);
//		assertTrue(disciplinas.get("Optativa_9").getPeriodo() == 8);
//		assertTrue(disciplinas.get("Optativa_10").getPeriodo() == 8);
//		assertTrue(disciplinas.get("Optativa_11").getPeriodo() == 8);
//	}
//
}
