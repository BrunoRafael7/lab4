import static org.junit.Assert.assertTrue;
import models.GradeCurricular;

import org.junit.Before;
import org.junit.Test;

public class GradeCurricularTest {
	static GradeCurricular grade;

	@Before
	public void start(){
		grade = new GradeCurricular();
	}

	@Test
	public void deveRetornarOsCreditosCorrespondentesCorretamente(){
		assertTrue(grade.get("Cálculo_Diferencial_e_Integral_I").getCreditos() == 4);	
		assertTrue(grade.get("Álgebra_Vetorial_e_Geometria_Analítica").getCreditos() == 4);
		assertTrue(grade.get("Leitura_e_Produção_de_Textos").getCreditos() == 4);
		assertTrue(grade.get("Programação_I").getCreditos() == 4);
		assertTrue(grade.get("Introdução_à_Computação").getCreditos() == 4);
		assertTrue(grade.get("Laboratório_de_Programação_I").getCreditos() == 4);
		assertTrue(grade.get("Cálculo_Diferencial_e_Integral_II").getCreditos() == 4);
		assertTrue(grade.get("Matemática_Discreta").getCreditos() == 4);
		assertTrue(grade.get("Metodologia_Científica").getCreditos() == 4);
		assertTrue(grade.get("Programação_II").getCreditos() == 4);
		assertTrue(grade.get("Teoria_dos_Grafos").getCreditos() == 2);
		assertTrue(grade.get("Fundamentos_de_Física_Clássica").getCreditos() == 4);
		assertTrue(grade.get("Laboratório_de_Programação_II").getCreditos() == 4);
		assertTrue(grade.get("Álgebra_Linear").getCreditos() == 4);
		assertTrue(grade.get("Probabilidade_e_Estatística").getCreditos() == 4);
		assertTrue(grade.get("Teoria_da_Computação").getCreditos() == 4);
		assertTrue(grade.get("Estruturas_de_Dados_e_Algoritmos").getCreditos() == 4);
		assertTrue(grade.get("Fundamentos_de_Física_Moderna").getCreditos() == 4);
		assertTrue(grade.get("Gerência_da_Informação").getCreditos() == 4);
		assertTrue(grade.get("Laboratório_de_Estruturas_de_Dados_e_Algoritmos").getCreditos() == 4);
		assertTrue(grade.get("Métodos_Estatísticos").getCreditos() == 4);
		assertTrue(grade.get("Paradigmas_de_Linguagens_de_Programação").getCreditos() == 2);
		assertTrue(grade.get("Lógica_Matemática").getCreditos() == 4);
		assertTrue(grade.get("Organização_e_Arquitetura_de_Computadores_I").getCreditos() == 4);
		assertTrue(grade.get("Engenharia_de_Software_I").getCreditos() == 4);
		assertTrue(grade.get("Sistemas_de_Informação_I").getCreditos() == 4);
		assertTrue(grade.get("Lab_de_Organização_e_Arquitetura_de_Computadores").getCreditos() == 4);
		assertTrue(grade.get("Informática_e_Sociedade").getCreditos() == 2);
		assertTrue(grade.get("Análise_e_Técnicas_de_Algoritmos").getCreditos() == 4);
		assertTrue(grade.get("Compiladores").getCreditos() == 4);
		assertTrue(grade.get("Redes_de_Computadores").getCreditos() == 4);
		assertTrue(grade.get("Bancos_de_Dados_I").getCreditos() == 4);
		assertTrue(grade.get("Sistemas_de_Informação_II").getCreditos() == 4);
		assertTrue(grade.get("Laboratório_de_Engenharia_de_Software").getCreditos() == 2);
		assertTrue(grade.get("Direito_e_Cidadania").getCreditos() == 4);
		assertTrue(grade.get("Sistemas_Operacionais").getCreditos() == 4);
		assertTrue(grade.get("Interconexão_de_Redes_de_Computadores").getCreditos() == 2);
		assertTrue(grade.get("Banco_de_Dados_II").getCreditos() == 4);
		assertTrue(grade.get("Inteligência_Artificial_I").getCreditos() == 4);
		assertTrue(grade.get("Laboratório_de_Interconexão_de_Redes_de_Computadores").getCreditos() == 2);
		assertTrue(grade.get("Optativa_1").getCreditos() == 4);
		assertTrue(grade.get("Optativa_2").getCreditos() == 4);
		assertTrue(grade.get("Métodos_e_Software_Numéricos").getCreditos() == 4);
		assertTrue(grade.get("Avaliação_de_Desempenho_de_Sistemas_Discretos").getCreditos() == 4);
		assertTrue(grade.get("Projeto_em_Computação_I").getCreditos() == 4);
		assertTrue(grade.get("Optativa_3").getCreditos() == 4);
		assertTrue(grade.get("Optativa_4").getCreditos() == 4);
		assertTrue(grade.get("Optativa_5").getCreditos() == 4);
		assertTrue(grade.get("Optativa_6").getCreditos() == 4);
		assertTrue(grade.get("Projeto_em_Computação_II").getCreditos() == 6);
		assertTrue(grade.get("Optativa_7").getCreditos() == 4);
		assertTrue(grade.get("Optativa_8").getCreditos() == 4);
		assertTrue(grade.get("Optativa_9").getCreditos() == 4);
		assertTrue(grade.get("Optativa_10").getCreditos() == 4);
		assertTrue(grade.get("Optativa_11").getCreditos() == 2);
	}
	
	@Test
	public void deveRetornarOPeriodoCorreto(){
		assertTrue(grade.get("Cálculo_Diferencial_e_Integral_I").getPeriodo() == 1);	
		assertTrue(grade.get("Álgebra_Vetorial_e_Geometria_Analítica").getPeriodo() == 1);
		assertTrue(grade.get("Leitura_e_Produção_de_Textos").getPeriodo() == 1);
		assertTrue(grade.get("Programação_I").getPeriodo() == 1);
		assertTrue(grade.get("Introdução_à_Computação").getPeriodo() == 1);
		assertTrue(grade.get("Laboratório_de_Programação_I").getPeriodo() == 1);
		assertTrue(grade.get("Cálculo_Diferencial_e_Integral_II").getPeriodo() == 2);
		assertTrue(grade.get("Matemática_Discreta").getPeriodo() == 2);
		assertTrue(grade.get("Metodologia_Científica").getPeriodo() == 2);
		assertTrue(grade.get("Programação_II").getPeriodo() == 2);
		assertTrue(grade.get("Teoria_dos_Grafos").getPeriodo() == 2);
		assertTrue(grade.get("Fundamentos_de_Física_Clássica").getPeriodo() == 2);
		assertTrue(grade.get("Laboratório_de_Programação_II").getPeriodo() == 2);
		assertTrue(grade.get("Álgebra_Linear").getPeriodo() == 3);
		assertTrue(grade.get("Probabilidade_e_Estatística").getPeriodo() == 3);
		assertTrue(grade.get("Teoria_da_Computação").getPeriodo() == 3);
		assertTrue(grade.get("Estruturas_de_Dados_e_Algoritmos").getPeriodo() == 3);
		assertTrue(grade.get("Fundamentos_de_Física_Moderna").getPeriodo() == 3);
		assertTrue(grade.get("Gerência_da_Informação").getPeriodo() == 3);
		assertTrue(grade.get("Laboratório_de_Estruturas_de_Dados_e_Algoritmos").getPeriodo() == 3);
		assertTrue(grade.get("Métodos_Estatísticos").getPeriodo() == 4);
		assertTrue(grade.get("Paradigmas_de_Linguagens_de_Programação").getPeriodo() == 4);
		assertTrue(grade.get("Lógica_Matemática").getPeriodo() == 4);
		assertTrue(grade.get("Organização_e_Arquitetura_de_Computadores_I").getPeriodo() == 4);
		assertTrue(grade.get("Engenharia_de_Software_I").getPeriodo() == 4);
		assertTrue(grade.get("Sistemas_de_Informação_I").getPeriodo() == 4);
		assertTrue(grade.get("Lab_de_Organização_e_Arquitetura_de_Computadores").getPeriodo() == 4);
		assertTrue(grade.get("Informática_e_Sociedade").getPeriodo() == 5);
		assertTrue(grade.get("Análise_e_Técnicas_de_Algoritmos").getPeriodo() == 5);
		assertTrue(grade.get("Compiladores").getPeriodo() == 5);
		assertTrue(grade.get("Redes_de_Computadores").getPeriodo() == 5);
		assertTrue(grade.get("Bancos_de_Dados_I").getPeriodo() == 5);
		assertTrue(grade.get("Sistemas_de_Informação_II").getPeriodo() == 5);
		assertTrue(grade.get("Laboratório_de_Engenharia_de_Software").getPeriodo() == 5);
		assertTrue(grade.get("Direito_e_Cidadania").getPeriodo() == 6);
		assertTrue(grade.get("Sistemas_Operacionais").getPeriodo() == 6);
		assertTrue(grade.get("Interconexão_de_Redes_de_Computadores").getPeriodo() == 6);
		assertTrue(grade.get("Banco_de_Dados_II").getPeriodo() == 6);
		assertTrue(grade.get("Inteligência_Artificial_I").getPeriodo() == 6);
		assertTrue(grade.get("Laboratório_de_Interconexão_de_Redes_de_Computadores").getPeriodo() == 6);
		assertTrue(grade.get("Optativa_1").getPeriodo() == 6);
		assertTrue(grade.get("Optativa_2").getPeriodo() == 6);
		assertTrue(grade.get("Métodos_e_Software_Numéricos").getPeriodo() == 7);
		assertTrue(grade.get("Avaliação_de_Desempenho_de_Sistemas_Discretos").getPeriodo() == 7);
		assertTrue(grade.get("Projeto_em_Computação_I").getPeriodo() == 7);
		assertTrue(grade.get("Optativa_3").getPeriodo() == 7);
		assertTrue(grade.get("Optativa_4").getPeriodo() == 7);
		assertTrue(grade.get("Optativa_5").getPeriodo() == 7);
		assertTrue(grade.get("Optativa_6").getPeriodo() == 7);
		assertTrue(grade.get("Projeto_em_Computação_II").getPeriodo() == 8);
		assertTrue(grade.get("Optativa_7").getPeriodo() == 8);
		assertTrue(grade.get("Optativa_8").getPeriodo() == 8);
		assertTrue(grade.get("Optativa_9").getPeriodo() == 8);
		assertTrue(grade.get("Optativa_10").getPeriodo() == 8);
		assertTrue(grade.get("Optativa_11").getPeriodo() == 8);
	}
}
