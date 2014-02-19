import static org.junit.Assert.*;

import java.util.List;

import models.Disciplina;
import models.GradeCurricular;
import models.LimiteDeCreditosException;
import models.PlanoDeCurso;
import models.PreRequisitosException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BehaviorTest {

	private PlanoDeCurso planoDeCurso;
	private int PRIMEIRO_PERIODO;
	private int SEGUNDO_PERIODO;
	private int TERCEIRO_PERIODO;
	private int QUARTO_PERIODO;
	private int QUINTO_PERIODO;
	
	
	@Before
	public void start() {
		planoDeCurso = new PlanoDeCurso();
		PRIMEIRO_PERIODO = 1;
		SEGUNDO_PERIODO = 2;
		TERCEIRO_PERIODO = 3;
		QUARTO_PERIODO = 4;
		QUINTO_PERIODO = 5;
	}

	@Test
	public void verificaSeOPrimeiroPeriodoEstaIniciandoCorretamente() {
		List<Disciplina> disciplinasDoPrimeiroPeriodo = planoDeCurso.getDisciplinasDoPeriodo(PRIMEIRO_PERIODO);

		assertEquals(6, disciplinasDoPrimeiroPeriodo.size());
		assertEquals(24, planoDeCurso.getTotalDeCreditosDoPeriodo(PRIMEIRO_PERIODO));
		
		assertEquals("Leitura_e_Produção_de_Textos",	disciplinasDoPrimeiroPeriodo.get(0).getNome());
		assertEquals("Álgebra_Vetorial_e_Geometria_Analítica", disciplinasDoPrimeiroPeriodo.get(1).getNome());
		assertEquals("Cálculo_Diferencial_e_Integral_I", disciplinasDoPrimeiroPeriodo.get(2).getNome());
		assertEquals("Programação_I", disciplinasDoPrimeiroPeriodo.get(3).getNome());
		assertEquals("Introdução_à_Computação", disciplinasDoPrimeiroPeriodo.get(4).getNome());
		assertEquals("Laboratório_de_Programação_I", disciplinasDoPrimeiroPeriodo.get(5).getNome());
	}

	@Test
	public void deveAlocarDisciplinaNoPeriodo() throws PreRequisitosException, LimiteDeCreditosException{
		
		assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
	
		planoDeCurso.alocaDisciplina("Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Clássica", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Laboratório_de_Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Matemática_Discreta", SEGUNDO_PERIODO);
		
		assertEquals(16, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		
		//LISTA COM DISCIPLINAS ALOCADAS NO SEGUNDO PERIODO
		List<Disciplina> listaDisciplinaSegundoPeriodo = planoDeCurso.getDisciplinasDoPeriodo(SEGUNDO_PERIODO);
		
		assertEquals("Programação_II", listaDisciplinaSegundoPeriodo.get(0).getNome());
		assertEquals("Fundamentos_de_Física_Clássica", listaDisciplinaSegundoPeriodo.get(1).getNome());
		assertEquals("Laboratório_de_Programação_II", listaDisciplinaSegundoPeriodo.get(2).getNome());
		assertEquals("Matemática_Discreta", listaDisciplinaSegundoPeriodo.get(3).getNome());
		
	}
	
	@Test
	public void naoDevePermitirAddNovaDisciplinaAoPrimeiroPeriodo()	throws PreRequisitosException, LimiteDeCreditosException {

		assertEquals(24,	planoDeCurso.getTotalDeCreditosDoPeriodo(PRIMEIRO_PERIODO));
		assertEquals(6, planoDeCurso.getDisciplinasDoPeriodo(PRIMEIRO_PERIODO).size());

		try {
			planoDeCurso.alocaDisciplina("Programação_I", PRIMEIRO_PERIODO); // DISCIPLINA REPETIDA																				
		} catch (PreRequisitosException e) {
			e.getMessage();
		}

		assertEquals(24, planoDeCurso.getTotalDeCreditosDoPeriodo(PRIMEIRO_PERIODO));
		assertEquals(6, planoDeCurso.getDisciplinasDoPeriodo(PRIMEIRO_PERIODO).size());

		try {
			planoDeCurso.alocaDisciplina("Matemática_Discreta", PRIMEIRO_PERIODO);
		} catch (PreRequisitosException e) {
			e.getMessage();
		}

		try {
			planoDeCurso.alocaDisciplina("Optativa_1", PRIMEIRO_PERIODO);
		} catch (PreRequisitosException e) {
			e.getMessage();
		}

		assertEquals(24, planoDeCurso.getTotalDeCreditosDoPeriodo(PRIMEIRO_PERIODO));
		assertEquals(6, planoDeCurso.getDisciplinasDoPeriodo(PRIMEIRO_PERIODO).size());
	}

	@Test
	public void deveAddNovaDisciplinaAoSegundoPeriodo()	throws PreRequisitosException, LimiteDeCreditosException {
		assertEquals(0, planoDeCurso.getDisciplinasDoPeriodo(SEGUNDO_PERIODO).size());
		assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));

		planoDeCurso.alocaDisciplina("Matemática_Discreta", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Cálculo_Diferencial_e_Integral_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Laboratório_de_Programação_II", SEGUNDO_PERIODO);

		assertEquals(4, planoDeCurso.getDisciplinasDoPeriodo(SEGUNDO_PERIODO).size());
		assertEquals(16, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));

		planoDeCurso.alocaDisciplina("Álgebra_Linear", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Clássica", SEGUNDO_PERIODO);

		assertEquals(6, planoDeCurso.getDisciplinasDoPeriodo(SEGUNDO_PERIODO).size());
		assertEquals(24, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
	}

	@Test
	public void deveAddNovaDisciplinaEmVariosPeriodos() throws PreRequisitosException, LimiteDeCreditosException {

		// SEGUNDO PERIODO
		assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		planoDeCurso.alocaDisciplina("Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Teoria_dos_Grafos", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Clássica", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Laboratório_de_Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Matemática_Discreta", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Cálculo_Diferencial_e_Integral_II", SEGUNDO_PERIODO);
		assertEquals(22, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));

		// TERCEIRO PERIODO
		assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));
		planoDeCurso.alocaDisciplina("Estruturas_de_Dados_e_Algoritmos", TERCEIRO_PERIODO);
		planoDeCurso.alocaDisciplina("Álgebra_Linear", TERCEIRO_PERIODO);
		planoDeCurso.alocaDisciplina("Teoria_da_Computação", TERCEIRO_PERIODO);
		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Moderna", TERCEIRO_PERIODO);
		planoDeCurso.alocaDisciplina("Laboratório_de_Estruturas_de_Dados_e_Algoritmos", TERCEIRO_PERIODO);
		planoDeCurso.alocaDisciplina("Gerência_da_Informação", TERCEIRO_PERIODO);
		assertEquals(24, planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));

		// QUARTO PERIODO
		assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(QUARTO_PERIODO));
		planoDeCurso.alocaDisciplina("Organização_e_Arquitetura_de_Computadores_I", QUARTO_PERIODO);
		planoDeCurso.alocaDisciplina("Lab_de_Organização_e_Arquitetura_de_Computadores", QUARTO_PERIODO);
		planoDeCurso.alocaDisciplina("Informática_e_Sociedade", QUARTO_PERIODO);
		planoDeCurso.alocaDisciplina("Sistemas_de_Informação_I", QUARTO_PERIODO);
		assertEquals(14, planoDeCurso.getTotalDeCreditosDoPeriodo(QUARTO_PERIODO));
	}

	@Test
	public void disciplinasDoPrimeiroPeriodoNaoDevemEstarDisponiveisParaAlocar() throws PreRequisitosException, LimiteDeCreditosException {
	
		assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		try {
			planoDeCurso.alocaDisciplina("Programação_I", SEGUNDO_PERIODO);
		} catch (PreRequisitosException e) {
		}

		try {
			planoDeCurso.alocaDisciplina("Álgebra_Vetorial_e_Geometria_Analítica", SEGUNDO_PERIODO);
		} catch (PreRequisitosException e) {
		}

		try {
			planoDeCurso.alocaDisciplina("Cálculo_Diferencial_e_Integral_I", SEGUNDO_PERIODO);
		} catch (PreRequisitosException e) {
		}

		try {
			planoDeCurso.alocaDisciplina("Introdução_à_Computação",	SEGUNDO_PERIODO);
		} catch (PreRequisitosException e) {
		}

		try {
			planoDeCurso.alocaDisciplina("Laboratório_de_Programação_I", SEGUNDO_PERIODO);
		} catch (PreRequisitosException e) {
		}

		try {
			planoDeCurso.alocaDisciplina("Leitura_e_Produção_de_Textos", SEGUNDO_PERIODO);
			fail();
		} catch (PreRequisitosException e) {
		}
		assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
			
	}

	@Test
	public void deveAtualizarQuantidadeDeCreditos()	throws PreRequisitosException, LimiteDeCreditosException {
		assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		
		planoDeCurso.alocaDisciplina("Programação_II", SEGUNDO_PERIODO);
		assertEquals(4, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		planoDeCurso.alocaDisciplina("Teoria_dos_Grafos", SEGUNDO_PERIODO);
		assertEquals(6, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		planoDeCurso.alocaDisciplina("Laboratório_de_Programação_II", SEGUNDO_PERIODO);
		assertEquals(10, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Clássica", SEGUNDO_PERIODO);
		assertEquals(14, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		planoDeCurso.alocaDisciplina("Álgebra_Linear", SEGUNDO_PERIODO);
		assertEquals(18, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
	}

	@Test
	public void naoDeveExcederLimiteDeCreditos() throws PreRequisitosException, LimiteDeCreditosException {

		assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		planoDeCurso.alocaDisciplina("Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Optativa_1", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Clássica", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Laboratório_de_Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Matemática_Discreta", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Cálculo_Diferencial_e_Integral_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Direito_e_Cidadania", SEGUNDO_PERIODO);
		assertEquals(28,	planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));

		try {
				planoDeCurso.alocaDisciplina("Teoria_dos_Grafos", SEGUNDO_PERIODO);
		} catch (LimiteDeCreditosException e) {
			e.getMessage();
		}
		assertEquals(28,	planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));

	}

	@Test
	public void naoDeveDesalocarDisciplinaDoPrimeiroPeriodo(){

		assertEquals(24, planoDeCurso.getTotalDeCreditosDoPeriodo(PRIMEIRO_PERIODO));

		try {
			planoDeCurso.desalocaDisciplina("Programação_I", PRIMEIRO_PERIODO);
			fail();
		} catch (PreRequisitosException e) {
		} catch (LimiteDeCreditosException e) { }

		try {
			planoDeCurso.desalocaDisciplina("Cálculo_Diferencial_e_Integral_I",	PRIMEIRO_PERIODO);
			fail();
		} catch (PreRequisitosException e) {
		} catch (LimiteDeCreditosException e) { }
		
		try {
			planoDeCurso.desalocaDisciplina("Álgebra_Vetorial_e_Geometria_Analítica", PRIMEIRO_PERIODO);
			fail();
		} catch (PreRequisitosException e) {
		} catch (LimiteDeCreditosException e) { }
		
		try {
			planoDeCurso.desalocaDisciplina("Leitura_e_Produção_de_Textos",	PRIMEIRO_PERIODO);
			fail();
		} catch (PreRequisitosException e) {
		} catch (LimiteDeCreditosException e) {	}
		
		try {
			planoDeCurso.desalocaDisciplina("Introdução_à_Computação", PRIMEIRO_PERIODO);
			fail();
		} catch (PreRequisitosException e) {
		} catch (LimiteDeCreditosException e) { 
			
		}
		
		try {
			planoDeCurso.desalocaDisciplina("Laboratório_de_Programação_I", PRIMEIRO_PERIODO);
			fail();
		} catch (PreRequisitosException e) {
		} catch (LimiteDeCreditosException e) { 
		}
		
		assertEquals(24, planoDeCurso.getTotalDeCreditosDoPeriodo(PRIMEIRO_PERIODO));
	}
	
	@Test
	public void naoDeveDesalocarDisciplinaSeElaEstiverInseridaEmAlgumPrerequisito(){
		
		try {
			planoDeCurso.alocaDisciplina("Cálculo_Diferencial_e_Integral_II", 2);
			planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Clássica", 2);
			
			planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Moderna", 3);
			
			planoDeCurso.disciplinaPodeSerDesalocada("Fundamentos_de_Física_Clássica", 2);
			fail();
			
		}catch(PreRequisitosException e){
			e.printStackTrace();
		}catch(LimiteDeCreditosException e){
			fail();
		}
		
		try{
			planoDeCurso.alocaDisciplina("Matemática_Discreta", 2);
			planoDeCurso.alocaDisciplina("Gerência_da_Informação", 2);
			planoDeCurso.alocaDisciplina("Teoria_dos_Grafos", SEGUNDO_PERIODO);
			planoDeCurso.alocaDisciplina("Programação_II", SEGUNDO_PERIODO);
			planoDeCurso.alocaDisciplina("Laboratório_de_Programação_II", SEGUNDO_PERIODO);
			
			planoDeCurso.alocaDisciplina("Teoria_da_Computação", 3);
			planoDeCurso.alocaDisciplina("Estruturas_de_Dados_e_Algoritmos", 3);
			planoDeCurso.alocaDisciplina("Laboratório_de_Estruturas_de_Dados_e_Algoritmos", 3);
			planoDeCurso.alocaDisciplina("Probabilidade_e_Estatística", 3);
			
			planoDeCurso.alocaDisciplina("Organização_e_Arquitetura_de_Computadores_I", 4);
			planoDeCurso.alocaDisciplina("Lab_de_Organização_e_Arquitetura_de_Computadores", 4);
			planoDeCurso.alocaDisciplina("Sistemas_de_Informação_I", QUARTO_PERIODO);
			planoDeCurso.alocaDisciplina("Lógica_Matemática", 4);
			planoDeCurso.alocaDisciplina("Paradigmas_de_Linguagens_de_Programação", 4);

			planoDeCurso.alocaDisciplina("Compiladores", 5);
			planoDeCurso.alocaDisciplina("Redes_de_Computadores", 5);
			

			planoDeCurso.disciplinaPodeSerDesalocada("Fundamentos_de_Física_Clássica", 2);
			fail();
			
		} catch (PreRequisitosException e) {
			e.printStackTrace();
			assertTrue(e.getMessage().equals("<div>" +
											 "<status>confirm</status>" +
											 "<message>Existem disciplinas dependentes alocadas</message>" +
											 "<mensagem2>Deseja realmente desalocar as seguintes disciplinas : </mensagem2>" +
											 "<disciplinas>Fundamentos_de_Física_Moderna " +
											 "Organização_e_Arquitetura_de_Computadores_I " +
											 "Lab_de_Organização_e_Arquitetura_de_Computadores " +
											 "Compiladores " +
											 "Redes_de_Computadores </disciplinas>?</div>"));
		} catch (LimiteDeCreditosException e) {
			fail();
		}
		
	}

	@Test
	public void deveDesalocarDisciplina() {

		assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		try {
			
			planoDeCurso.alocaDisciplina("Programação_II", SEGUNDO_PERIODO);
			planoDeCurso.alocaDisciplina("Teoria_dos_Grafos", SEGUNDO_PERIODO);
			planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Clássica", SEGUNDO_PERIODO);
			planoDeCurso.alocaDisciplina("Laboratório_de_Programação_II", SEGUNDO_PERIODO);
			planoDeCurso.alocaDisciplina("Matemática_Discreta", SEGUNDO_PERIODO);
			planoDeCurso.alocaDisciplina("Direito_e_Cidadania", SEGUNDO_PERIODO);
			planoDeCurso.alocaDisciplina("Gerência_da_Informação", TERCEIRO_PERIODO);
			
		} catch (PreRequisitosException e) {
			fail();
		} catch (LimiteDeCreditosException e) {
			fail();
		}catch(Exception e){
			e.printStackTrace();
		}
		assertEquals(22, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));

		List<Disciplina> listaDisciplinaSegundoPeriodo = planoDeCurso.getDisciplinasDoPeriodo(SEGUNDO_PERIODO);

		
		try {
			planoDeCurso.desalocaDisciplina("Direito_e_Cidadania", SEGUNDO_PERIODO);
		} catch (PreRequisitosException e) {
			fail();
		} catch (LimiteDeCreditosException e) {
			fail();
		}
		
		assertEquals(18, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		
		try {
			planoDeCurso.desalocaDisciplina("Teoria_dos_Grafos", SEGUNDO_PERIODO);
		} catch (PreRequisitosException e) {
			fail();
		} catch (LimiteDeCreditosException e) {
			fail();
		}

		assertEquals(16, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		assertEquals(4, listaDisciplinaSegundoPeriodo.size());
		
		assertEquals("Programação_II", listaDisciplinaSegundoPeriodo.get(0).getNome());
		assertEquals("Fundamentos_de_Física_Clássica", listaDisciplinaSegundoPeriodo.get(1).getNome());
		assertEquals("Laboratório_de_Programação_II", listaDisciplinaSegundoPeriodo.get(2).getNome());
		assertEquals("Matemática_Discreta", listaDisciplinaSegundoPeriodo.get(3).getNome());
	}
	
	@Test
	public void verificaSeDisciplinaPodeSerDesalocada() throws PreRequisitosException, LimiteDeCreditosException{
		planoDeCurso.alocaDisciplina("Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Laboratório_de_Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Teoria_dos_Grafos", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Matemática_Discreta", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Cálculo_Diferencial_e_Integral_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Clássica", SEGUNDO_PERIODO);
		
		Assert.assertTrue(planoDeCurso.disciplinaPodeSerDesalocada("Programação_II", SEGUNDO_PERIODO));
		Assert.assertTrue(planoDeCurso.disciplinaPodeSerDesalocada("Laboratório_de_Programação_II", SEGUNDO_PERIODO));
		Assert.assertTrue(planoDeCurso.disciplinaPodeSerDesalocada("Teoria_dos_Grafos", SEGUNDO_PERIODO));
		Assert.assertTrue(planoDeCurso.disciplinaPodeSerDesalocada("Matemática_Discreta", SEGUNDO_PERIODO));
		Assert.assertTrue(planoDeCurso.disciplinaPodeSerDesalocada("Cálculo_Diferencial_e_Integral_II", SEGUNDO_PERIODO));
		Assert.assertTrue(planoDeCurso.disciplinaPodeSerDesalocada("Fundamentos_de_Física_Clássica", SEGUNDO_PERIODO));

		planoDeCurso.alocaDisciplina("Probabilidade_e_Estatística", TERCEIRO_PERIODO);
		planoDeCurso.alocaDisciplina("Estruturas_de_Dados_e_Algoritmos", TERCEIRO_PERIODO);
		planoDeCurso.alocaDisciplina("Laboratório_de_Estruturas_de_Dados_e_Algoritmos", TERCEIRO_PERIODO);
		planoDeCurso.alocaDisciplina("Gerência_da_Informação", TERCEIRO_PERIODO);
		planoDeCurso.alocaDisciplina("Teoria_da_Computação", TERCEIRO_PERIODO);
		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Moderna", TERCEIRO_PERIODO);
		
		Assert.assertTrue(planoDeCurso.disciplinaPodeSerDesalocada("Probabilidade_e_Estatística", TERCEIRO_PERIODO));
		Assert.assertTrue(planoDeCurso.disciplinaPodeSerDesalocada("Estruturas_de_Dados_e_Algoritmos", TERCEIRO_PERIODO));
		Assert.assertTrue(planoDeCurso.disciplinaPodeSerDesalocada("Laboratório_de_Estruturas_de_Dados_e_Algoritmos", TERCEIRO_PERIODO));
		Assert.assertTrue(planoDeCurso.disciplinaPodeSerDesalocada("Gerência_da_Informação", TERCEIRO_PERIODO));
		Assert.assertTrue(planoDeCurso.disciplinaPodeSerDesalocada("Teoria_da_Computação", TERCEIRO_PERIODO));
		Assert.assertTrue(planoDeCurso.disciplinaPodeSerDesalocada("Fundamentos_de_Física_Moderna", TERCEIRO_PERIODO));

		//VERIFICA SE PODE DESALOCAR DISCIPLINA QUE É PRÉ-REQUISITO
		try{
			planoDeCurso.disciplinaPodeSerDesalocada("Programação_II", SEGUNDO_PERIODO);
		}catch(PreRequisitosException e){
			e.getMessage();
		}
		try{
			planoDeCurso.disciplinaPodeSerDesalocada("Laboratório_de_Programação_II", SEGUNDO_PERIODO);
		}catch(PreRequisitosException e){
			e.getMessage();
		}
		try{	
			planoDeCurso.disciplinaPodeSerDesalocada("Teoria_dos_Grafos", SEGUNDO_PERIODO);
		}catch(PreRequisitosException e){
			e.getMessage();
		}
		try{
			planoDeCurso.disciplinaPodeSerDesalocada("Matemática_Discreta", SEGUNDO_PERIODO);
		}catch(PreRequisitosException e){
			e.getMessage();
		}
		try{
			planoDeCurso.disciplinaPodeSerDesalocada("Cálculo_Diferencial_e_Integral_II", SEGUNDO_PERIODO);
		}catch(PreRequisitosException e){
			e.getMessage();
		}
	}

	@Test
	public void deveVerificarPreRequisitosEstaoSatisfeitos() throws PreRequisitosException, LimiteDeCreditosException {
		GradeCurricular grade = new GradeCurricular();
		Disciplina p2 = grade.get("Programação_II");
		Disciplina c2 = grade.get("Cálculo_Diferencial_e_Integral_II");
		Disciplina tc = grade.get("Teoria_da_Computação");
		Disciplina md = grade.get("Matemática_Discreta");
		Disciplina infosoc = grade.get("Informática_e_Sociedade");
		Disciplina msn = grade.get("Métodos_e_Software_Numéricos");
		Disciplina optativa1 = grade.get("Optativa_1");
		Disciplina proj1 = grade.get("Projeto_em_Computação_I");
		Disciplina eda = grade.get("Estruturas_de_Dados_e_Algoritmos");
		Disciplina leda = grade.get("Laboratório_de_Estruturas_de_Dados_e_Algoritmos");
		
		try {
			assertTrue(planoDeCurso.disciplinaPodeSerAlocada(p2.getNome(), 2));
			assertTrue(planoDeCurso.disciplinaPodeSerAlocada(c2.getNome(), 2));
		} catch (LimiteDeCreditosException e) {
			e.printStackTrace();
		} catch (PreRequisitosException e) {
			e.printStackTrace();
		}
		
		assertFalse(planoDeCurso.preRequisitosEstaoSatisfeitos(tc, SEGUNDO_PERIODO));
		
		assertTrue(planoDeCurso.preRequisitosEstaoSatisfeitos(md,SEGUNDO_PERIODO));
		assertTrue(planoDeCurso.preRequisitosEstaoSatisfeitos(infosoc,SEGUNDO_PERIODO));
		
		assertFalse(planoDeCurso.preRequisitosEstaoSatisfeitos(msn,QUARTO_PERIODO));
		
		assertTrue(planoDeCurso.preRequisitosEstaoSatisfeitos(optativa1,TERCEIRO_PERIODO));
		
		assertFalse(planoDeCurso.preRequisitosEstaoSatisfeitos(proj1, QUINTO_PERIODO));
		
		assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		
		planoDeCurso.alocaDisciplina("Matemática_Discreta", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Laboratório_de_Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Teoria_dos_Grafos", SEGUNDO_PERIODO);
		
		Assert.assertEquals(14, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		
		Assert.assertTrue(planoDeCurso.preRequisitosEstaoSatisfeitos(tc , TERCEIRO_PERIODO));
		Assert.assertTrue(planoDeCurso.preRequisitosEstaoSatisfeitos(eda, TERCEIRO_PERIODO));
		Assert.assertTrue(planoDeCurso.preRequisitosEstaoSatisfeitos(leda, TERCEIRO_PERIODO));
	}
	
	@Test
	public void deveAddDisciplinaSomenteSePreRequisitoEstiverAlocado() throws PreRequisitosException, LimiteDeCreditosException{
		
		//ALOCANDO PARA SEGUNDO PERÍODO
		assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		planoDeCurso.alocaDisciplina("Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Laboratório_de_Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Teoria_dos_Grafos", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Matemática_Discreta", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Clássica", SEGUNDO_PERIODO);
		assertEquals(18, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		
		try{
			planoDeCurso.alocaDisciplina("Estruturas_de_Dados_e_Algoritmos", SEGUNDO_PERIODO);
		}catch(PreRequisitosException e){
			e.getMessage();
		}
		try{
			planoDeCurso.alocaDisciplina("Laboratório_de_Estruturas_de_Dados_e_Algoritmos", SEGUNDO_PERIODO);
		}catch(PreRequisitosException e){
			e.getMessage();
		}
		try{
			planoDeCurso.alocaDisciplina("Laboratório_de_Engenharia_de_Software", SEGUNDO_PERIODO);
		}catch(PreRequisitosException e){
			e.getMessage();
		}
		try{
			planoDeCurso.alocaDisciplina("Sistemas_de_Informação_II", SEGUNDO_PERIODO);
		}catch(PreRequisitosException e){
			e.getMessage();
		}
		try{
			planoDeCurso.alocaDisciplina("Sistemas_de_Informação_I", SEGUNDO_PERIODO);
		}catch(PreRequisitosException e){
			e.getMessage();
		}
		
		assertEquals(18, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		
		
		//ALOCANDO PARA TERCEIRO PERÍODO
		assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));
		planoDeCurso.alocaDisciplina("Estruturas_de_Dados_e_Algoritmos", TERCEIRO_PERIODO);
		planoDeCurso.alocaDisciplina("Laboratório_de_Estruturas_de_Dados_e_Algoritmos", TERCEIRO_PERIODO);
		planoDeCurso.alocaDisciplina("Gerência_da_Informação", TERCEIRO_PERIODO);
		planoDeCurso.alocaDisciplina("Álgebra_Linear", TERCEIRO_PERIODO);
		planoDeCurso.alocaDisciplina("Metodologia_Científica", TERCEIRO_PERIODO);
		assertEquals(20, planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));

		
		try{ //AINDA NÃO PAGOU CÁLCULO 2, ENTÃO NÃO DEVE PODER ALOCAR
			planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Moderna", TERCEIRO_PERIODO);
		}catch(PreRequisitosException e){
			e.getMessage();
		}
		try{ //NÃO PAGOU TEORIA DA COMPUTAÇÃO, ENTÃO NÃO DEVE PODER ALOCAR
			planoDeCurso.alocaDisciplina("Lógica_Matemática", TERCEIRO_PERIODO);
		}catch(PreRequisitosException e){
			e.getMessage();
		}
		try{ //NÃO PAGOU PROBABILIDADE, ENTÃO NÃO DEVE PODER ALOCAR
			planoDeCurso.alocaDisciplina("Engenharia_de_Software_I", TERCEIRO_PERIODO);
		}catch(PreRequisitosException e){
			e.getMessage();
		}
		assertEquals(20, planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));

		
		//ALOCANDO PARA QUARTO PERÍODO
		assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(QUARTO_PERIODO));
		planoDeCurso.alocaDisciplina("Cálculo_Diferencial_e_Integral_II", QUARTO_PERIODO);
		planoDeCurso.alocaDisciplina("Sistemas_de_Informação_I", QUARTO_PERIODO);
		planoDeCurso.alocaDisciplina("Informática_e_Sociedade", QUARTO_PERIODO);
		planoDeCurso.alocaDisciplina("Teoria_da_Computação", QUARTO_PERIODO);
		planoDeCurso.alocaDisciplina("Direito_e_Cidadania", QUARTO_PERIODO);
		assertEquals(18, planoDeCurso.getTotalDeCreditosDoPeriodo(QUARTO_PERIODO));

		try{ //NÃO PAGOU CÁLCULO II(Só a partir do próximo período), ENTÃO NÃO DEVE PODER ALOCAR
			planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Moderna", QUARTO_PERIODO);
		}catch(PreRequisitosException e){
			e.getMessage();
		}
		try{ //NÃO PAGOU TC(Só a partir do próximo período), ENTÃO NÃO DEVE PODER ALOCAR
			planoDeCurso.alocaDisciplina("Lógica_Matemática", QUARTO_PERIODO);
		}catch(PreRequisitosException e){
			e.getMessage();
		}
		assertEquals(18, planoDeCurso.getTotalDeCreditosDoPeriodo(QUARTO_PERIODO));

		
		//ALOCANDO PARA QUINTO PERÍODO
		assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(QUINTO_PERIODO));
		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Moderna", QUINTO_PERIODO );
		planoDeCurso.alocaDisciplina("Lógica_Matemática", QUINTO_PERIODO );
		planoDeCurso.alocaDisciplina("Sistemas_de_Informação_II", QUINTO_PERIODO);
		planoDeCurso.alocaDisciplina("Probabilidade_e_Estatística", QUINTO_PERIODO);
		planoDeCurso.alocaDisciplina("Paradigmas_de_Linguagens_de_Programação", QUINTO_PERIODO);
		planoDeCurso.alocaDisciplina("Bancos_de_Dados_I", QUINTO_PERIODO);
		assertEquals(22, planoDeCurso.getTotalDeCreditosDoPeriodo(QUINTO_PERIODO));

		try{ //NÃO PAGOU FISICA MODERNA(Só a partir do próximo período), ENTÃO NÃO DEVE PODER ALOCAR
			planoDeCurso.alocaDisciplina("Organização_e_Arquitetura_de_Computadores_I", QUINTO_PERIODO);
		}catch (PreRequisitosException e){
			e.getMessage();
		}
		try{ //NÃO PAGOU FISICA MODERNA(Só a partir do próximo período), ENTÃO NÃO DEVE PODER ALOCAR
			planoDeCurso.alocaDisciplina("Lab_de_Organização_e_Arquitetura_de_Computadores", QUINTO_PERIODO);
		}catch (PreRequisitosException e){
			e.getMessage();
		}
		try{ //NÃO PAGOU PROBABILIDADE(Só a partir do próximo período), ENTÃO NÃO DEVE PODER ALOCAR
			planoDeCurso.alocaDisciplina("Engenharia_de_Software_I", QUINTO_PERIODO);
		}catch (PreRequisitosException e){
			e.getMessage();
		}
	}
}
