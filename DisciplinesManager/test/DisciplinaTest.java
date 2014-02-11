import static org.junit.Assert.*;


import models.GradeCurricular;

import org.junit.Before;
import org.junit.Test;

public class DisciplinaTest {

	GradeCurricular grade;
	
	@Before
	public void start(){
		grade = new GradeCurricular();
	}
	
	@Test
	public void deveVerificarNome(){
		assertEquals("Cálculo_Diferencial_e_Integral_II" , grade.get("Cálculo_Diferencial_e_Integral_II").getNome());
		assertEquals("Programação_II", grade.get("Programação_II").getNome());
		assertEquals("Paradigmas_de_Linguagens_de_Programação", grade.get("Paradigmas_de_Linguagens_de_Programação").getNome());
		assertEquals("Organização_e_Arquitetura_de_Computadores_I", grade.get("Organização_e_Arquitetura_de_Computadores_I").getNome());
		assertEquals("Inteligência_Artificial_I", grade.get("Inteligência_Artificial_I").getNome());
	}
	
	@Test
	public void deveVerificarPreRequisitos(){
		assertEquals("[Cálculo_Diferencial_e_Integral_I]" , grade.get("Cálculo_Diferencial_e_Integral_II").getPreRequisitos().toString());
		assertEquals("[Programação_I, Laboratório_de_Programação_I, Introdução_à_Computação]", grade.get("Programação_II").getPreRequisitos().toString());		
		assertEquals("[Teoria_da_Computação, Estruturas_de_Dados_e_Algoritmos, Laboratório_de_Estruturas_de_Dados_e_Algoritmos]", grade.get("Paradigmas_de_Linguagens_de_Programação").getPreRequisitos().toString());	
		assertEquals("[Fundamentos_de_Física_Moderna, Estruturas_de_Dados_e_Algoritmos, Laboratório_de_Estruturas_de_Dados_e_Algoritmos]", grade.get("Organização_e_Arquitetura_de_Computadores_I").getPreRequisitos().toString());	
		assertEquals("[Análise_e_Técnicas_de_Algoritmos, Paradigmas_de_Linguagens_de_Programação, Métodos_Estatísticos]", grade.get("Inteligência_Artificial_I").getPreRequisitos().toString());
	}
	
	@Test
	public void deveVerificarQuantidadeDeCreditos(){
		assertEquals(4, grade.get("Cálculo_Diferencial_e_Integral_I").getCreditos());
		assertEquals(4, grade.get("Programação_II").getCreditos());
		assertEquals(2, grade.get("Paradigmas_de_Linguagens_de_Programação").getCreditos());
		assertEquals(4, grade.get("Organização_e_Arquitetura_de_Computadores_I").getCreditos());
		assertEquals(4, grade.get("Inteligência_Artificial_I").getCreditos());
	}
	
	@Test
	public void deveVerificarPeriodo(){
		assertEquals(1, grade.get("Cálculo_Diferencial_e_Integral_I").getPeriodo());
		assertEquals(2, grade.get("Programação_II").getPeriodo());
		assertEquals(4, grade.get("Paradigmas_de_Linguagens_de_Programação").getPeriodo());
		assertEquals(4, grade.get("Organização_e_Arquitetura_de_Computadores_I").getPeriodo());
		assertEquals(6, grade.get("Inteligência_Artificial_I").getPeriodo());
	}
}