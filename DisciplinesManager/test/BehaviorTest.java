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

	@Before
	public void start() {
		planoDeCurso = new PlanoDeCurso();
	}

	@Test
	public void verificaSeOPrimeiroPeriodoEstaIniciandoCorretamente() {
		List<Disciplina> disciplinasDoPrimeiroPeriodo = planoDeCurso
				.getDisciplinasDoPeriodo(1);

		Assert.assertEquals(6, disciplinasDoPrimeiroPeriodo.size());

		Assert.assertEquals("Leitura_e_Produção_de_Textos",
				disciplinasDoPrimeiroPeriodo.get(0).getNome());
		Assert.assertEquals("Álgebra_Vetorial_e_Geometria_Analítica",
				disciplinasDoPrimeiroPeriodo.get(1).getNome());
		Assert.assertEquals("Cálculo_Diferencial_e_Integral_I",
				disciplinasDoPrimeiroPeriodo.get(2).getNome());
		Assert.assertEquals("Programação_I", disciplinasDoPrimeiroPeriodo
				.get(3).getNome());
		Assert.assertEquals("Introdução_à_Computação",
				disciplinasDoPrimeiroPeriodo.get(4).getNome());
		Assert.assertEquals("Laboratório_de_Programação_I",
				disciplinasDoPrimeiroPeriodo.get(5).getNome());
	}

	@Test
	public void deveAlocarDisciplinaNoPeriodo() throws PreRequisitosException, LimiteDeCreditosException{
		int SEGUNDO_PERIODO = 2;
		
		Assert.assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		
		planoDeCurso.alocaDisciplina("Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Clássica", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Laboratório_de_Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Matemática_Discreta", SEGUNDO_PERIODO);
		
		Assert.assertEquals(16, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		
		//LISTA COM DISCIPLINAS ALOCADAS NO SEGUNDO PERIODO
		List<Disciplina> listaDisciplinaSegundoPeriodo = planoDeCurso.getDisciplinasDoPeriodo(SEGUNDO_PERIODO);
		
		Assert.assertEquals("Programação_II", listaDisciplinaSegundoPeriodo.get(0).getNome());
		Assert.assertEquals("Fundamentos_de_Física_Clássica", listaDisciplinaSegundoPeriodo.get(1).getNome());
		Assert.assertEquals("Laboratório_de_Programação_II", listaDisciplinaSegundoPeriodo.get(2).getNome());
		Assert.assertEquals("Matemática_Discreta", listaDisciplinaSegundoPeriodo.get(3).getNome());
		
	}
	
	@Test
	public void naoDevePermitirAddNovaDisciplinaAoPrimeiroPeriodo()
			throws PreRequisitosException, LimiteDeCreditosException {
		int PRIMEIRO_PERIODO = 1;

		Assert.assertEquals(24,
				planoDeCurso.getTotalDeCreditosDoPeriodo(PRIMEIRO_PERIODO));
		Assert.assertEquals(6,
				planoDeCurso.getDisciplinasDoPeriodo(PRIMEIRO_PERIODO).size());

		try {
			planoDeCurso.alocaDisciplina("Programação_I", PRIMEIRO_PERIODO); // DISCIPLINA
																				// REPETIDA
		} catch (PreRequisitosException e) {
			e.getMessage();
		}

		Assert.assertEquals(24,
				planoDeCurso.getTotalDeCreditosDoPeriodo(PRIMEIRO_PERIODO));
		Assert.assertEquals(6,
				planoDeCurso.getDisciplinasDoPeriodo(PRIMEIRO_PERIODO).size());

		try {
			planoDeCurso.alocaDisciplina("Matemática_Discreta",
					PRIMEIRO_PERIODO);
		} catch (PreRequisitosException e) {
			e.getMessage();
		}

		try {
			planoDeCurso.alocaDisciplina("Optativa_1", PRIMEIRO_PERIODO);
		} catch (PreRequisitosException e) {
			e.getMessage();
		}

		Assert.assertEquals(24,
				planoDeCurso.getTotalDeCreditosDoPeriodo(PRIMEIRO_PERIODO));
		Assert.assertEquals(6,
				planoDeCurso.getDisciplinasDoPeriodo(PRIMEIRO_PERIODO).size());
	}

	@Test
	public void deveAddNovaDisciplinaAoSegundoPeriodo()
			throws PreRequisitosException, LimiteDeCreditosException {
		int SEGUNDO_PERIODO = 2;
		Assert.assertEquals(0,
				planoDeCurso.getDisciplinasDoPeriodo(SEGUNDO_PERIODO).size());
		Assert.assertEquals(0,
				planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));

		planoDeCurso.alocaDisciplina("Matemática_Discreta", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Cálculo_Diferencial_e_Integral_II",
				SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Laboratório_de_Programação_II",
				SEGUNDO_PERIODO);

		Assert.assertEquals(4,
				planoDeCurso.getDisciplinasDoPeriodo(SEGUNDO_PERIODO).size());
		Assert.assertEquals(16,
				planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));

		planoDeCurso.alocaDisciplina("Álgebra_Linear", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Clássica",
				SEGUNDO_PERIODO);

		Assert.assertEquals(6,
				planoDeCurso.getDisciplinasDoPeriodo(SEGUNDO_PERIODO).size());
		Assert.assertEquals(24,
				planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
	}

	@Test
	public void deveAddNovaDisciplinaEmVariosPeriodos()
			throws PreRequisitosException, LimiteDeCreditosException {
		int SEGUNDO_PERIODO = 2;
		int TERCEIRO_PERIODO = 3;
		int QUARTO_PERIODO = 4;

		// SEGUNDO PERIODO
		Assert.assertEquals(0,
				planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		planoDeCurso.alocaDisciplina("Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Teoria_dos_Grafos", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Clássica",
				SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Laboratório_de_Programação_II",
				SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Matemática_Discreta", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Cálculo_Diferencial_e_Integral_II",
				SEGUNDO_PERIODO);
		Assert.assertEquals(22,
				planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));

		// TERCEIRO PERIODO
		Assert.assertEquals(0,
				planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));
		planoDeCurso.alocaDisciplina("Estruturas_de_Dados_e_Algoritmos",
				TERCEIRO_PERIODO);
		planoDeCurso.alocaDisciplina("Álgebra_Linear", TERCEIRO_PERIODO);
		planoDeCurso.alocaDisciplina("Teoria_da_Computação", TERCEIRO_PERIODO);
		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Moderna",
				TERCEIRO_PERIODO);
		planoDeCurso.alocaDisciplina(
				"Laboratório_de_Estruturas_de_Dados_e_Algoritmos",
				TERCEIRO_PERIODO);
		planoDeCurso
				.alocaDisciplina("Gerência_da_Informação", TERCEIRO_PERIODO);
		Assert.assertEquals(24,
				planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));

		// QUARTO PERIODO
		Assert.assertEquals(0,
				planoDeCurso.getTotalDeCreditosDoPeriodo(QUARTO_PERIODO));
		planoDeCurso.alocaDisciplina(
				"Organização_e_Arquitetura_de_Computadores_I", QUARTO_PERIODO);
		planoDeCurso.alocaDisciplina(
				"Lab_de_Organização_e_Arquitetura_de_Computadores",
				QUARTO_PERIODO);
		planoDeCurso.alocaDisciplina("Informática_e_Sociedade", QUARTO_PERIODO);
		planoDeCurso
				.alocaDisciplina("Sistemas_de_Informação_I", QUARTO_PERIODO);
		Assert.assertEquals(14,
				planoDeCurso.getTotalDeCreditosDoPeriodo(QUARTO_PERIODO));
	}

	@Test
	public void disciplinasDoPrimeiroPeriodoNaoDevemEstarDisponiveisParaAlocar()
			throws PreRequisitosException, LimiteDeCreditosException {
		int QUINTO_PERIODO = 5;

		Assert.assertEquals(0,
				planoDeCurso.getTotalDeCreditosDoPeriodo(QUINTO_PERIODO));

		try {
			planoDeCurso.alocaDisciplina("Programação_I", QUINTO_PERIODO);
		} catch (PreRequisitosException e) {
			e.getMessage();
		}

		try {
			planoDeCurso.alocaDisciplina(
					"Álgebra_Vetorial_e_Geometria_Analítica", QUINTO_PERIODO);
		} catch (PreRequisitosException e) {
			e.getMessage();
		}

		try {
			planoDeCurso.alocaDisciplina("Cálculo_Diferencial_e_Integral_I",
					QUINTO_PERIODO);
		} catch (PreRequisitosException e) {
			e.getMessage();
		}

		try {
			planoDeCurso.alocaDisciplina("Introdução_à_Computação",
					QUINTO_PERIODO);
		} catch (PreRequisitosException e) {
			e.getMessage();
		}

		try {
			planoDeCurso.alocaDisciplina("Laboratório_de_Programação_I",
					QUINTO_PERIODO);
		} catch (PreRequisitosException e) {
			e.getMessage();
		}

		try {
			planoDeCurso.alocaDisciplina("Leitura_e_Produção_de_Textos",
					QUINTO_PERIODO);
		} catch (PreRequisitosException e) {
			e.getMessage();
		}

		Assert.assertEquals(0,
				planoDeCurso.getTotalDeCreditosDoPeriodo(QUINTO_PERIODO));
	}

	@Test
	public void deveAtualizarQuantidadeDeCreditos()
			throws PreRequisitosException, LimiteDeCreditosException {
		int TERCEIRO_PERIODO = 3;
//
//		Assert.assertEquals(0,
//				planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));
//
//		planoDeCurso.alocaDisciplina("Estruturas_de_Dados_e_Algoritmos",
//				TERCEIRO_PERIODO);
//		Assert.assertEquals(4,
//				planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));
//
//		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Moderna",
//				TERCEIRO_PERIODO);
//		Assert.assertEquals(8,
//				planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));
//
//		planoDeCurso.alocaDisciplina(
//				"Laboratório_de_Estruturas_de_Dados_e_Algoritmos",
//				TERCEIRO_PERIODO);
//		Assert.assertEquals(12,
//				planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));
//
//		planoDeCurso.alocaDisciplina("Teoria_da_Computação", TERCEIRO_PERIODO);
//		Assert.assertEquals(16,
//				planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));
//
//		planoDeCurso.alocaDisciplina("Paradigmas_de_Linguagens_de_Programação",
//				TERCEIRO_PERIODO);
//		Assert.assertEquals(18,
//				planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));
	}

	@Test
	public void naoDeveExcederLimiteDeCreditos() throws PreRequisitosException,
			LimiteDeCreditosException {
		int SEGUNDO_PERIODO = 2;

		Assert.assertEquals(0,
				planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		planoDeCurso.alocaDisciplina("Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Optativa_1", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Clássica",
				SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Laboratório_de_Programação_II",
				SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Matemática_Discreta", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Cálculo_Diferencial_e_Integral_II",
				SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Direito_e_Cidadania", SEGUNDO_PERIODO);
		Assert.assertEquals(28,
				planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));

		try {
			planoDeCurso.alocaDisciplina("Teoria_dos_Grafos", SEGUNDO_PERIODO);
		} catch (LimiteDeCreditosException e) {
			e.getMessage();
		}
	}

	@Test
	public void naoDeveDesalocarDisciplinaDoPrimeiroPeriodo()
			throws PreRequisitosException, LimiteDeCreditosException {
		int PRIMEIRO_PERIODO = 1;

		Assert.assertEquals(24,
				planoDeCurso.getTotalDeCreditosDoPeriodo(PRIMEIRO_PERIODO));

		try {
			planoDeCurso.desalocaDisciplina("Programação_I", PRIMEIRO_PERIODO);
		} catch (PreRequisitosException e) {
			e.getMessage();
		}
		try {
			planoDeCurso.desalocaDisciplina("Cálculo_Diferencial_e_Integral_I",
					PRIMEIRO_PERIODO);
		} catch (PreRequisitosException e) {
			e.getMessage();
		}
		try {
			planoDeCurso.desalocaDisciplina(
					"Álgebra_Vetorial_e_Geometria_Analítica", PRIMEIRO_PERIODO);
		} catch (PreRequisitosException e) {
			e.getMessage();
		}
		try {
			planoDeCurso.desalocaDisciplina("Leitura_e_Produção_de_Textos",
					PRIMEIRO_PERIODO);
		} catch (PreRequisitosException e) {
			e.getMessage();
		}
		try {
			planoDeCurso.desalocaDisciplina("Introdução_à_Computação",
					PRIMEIRO_PERIODO);
		} catch (PreRequisitosException e) {
			e.getMessage();
		}
		try {
			planoDeCurso.desalocaDisciplina("Laboratório_de_Programação_I",
					PRIMEIRO_PERIODO);
		} catch (PreRequisitosException e) {
			e.getMessage();
		}

		Assert.assertEquals(24,
				planoDeCurso.getTotalDeCreditosDoPeriodo(PRIMEIRO_PERIODO));
	}

	@Test
	public void deveDesalocarDisciplina() throws PreRequisitosException,
			LimiteDeCreditosException {
		int SEGUNDO_PERIODO = 2;

		Assert.assertEquals(0,
				planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		planoDeCurso.alocaDisciplina("Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Teoria_dos_Grafos", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Clássica",
				SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Laboratório_de_Programação_II",
				SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Matemática_Discreta", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Direito_e_Cidadania", SEGUNDO_PERIODO);
		Assert.assertEquals(22,
				planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));

		List<Disciplina> listaDisciplinaSegundoPeriodo = planoDeCurso.getDisciplinasDoPeriodo(SEGUNDO_PERIODO);
		
		Assert.assertEquals(6, listaDisciplinaSegundoPeriodo.size());
		Assert.assertEquals("Programação_II", listaDisciplinaSegundoPeriodo.get(0).getNome());
		Assert.assertEquals("Teoria_dos_Grafos", listaDisciplinaSegundoPeriodo.get(1).getNome());
		Assert.assertEquals("Fundamentos_de_Física_Clássica", listaDisciplinaSegundoPeriodo.get(2).getNome());
		Assert.assertEquals("Laboratório_de_Programação_II", listaDisciplinaSegundoPeriodo.get(3).getNome());
		Assert.assertEquals("Matemática_Discreta", listaDisciplinaSegundoPeriodo.get(4).getNome());
		Assert.assertEquals("Direito_e_Cidadania", listaDisciplinaSegundoPeriodo.get(5).getNome());
		
		planoDeCurso.desalocaDisciplina("Direito_e_Cidadania", SEGUNDO_PERIODO);
		Assert.assertEquals(18,
				planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		planoDeCurso.desalocaDisciplina("Teoria_dos_Grafos", SEGUNDO_PERIODO);
		Assert.assertEquals(16,
				planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		
		Assert.assertEquals(4, listaDisciplinaSegundoPeriodo.size());
		
		Assert.assertEquals("Programação_II", listaDisciplinaSegundoPeriodo.get(0).getNome());
		Assert.assertEquals("Fundamentos_de_Física_Clássica", listaDisciplinaSegundoPeriodo.get(1).getNome());
		Assert.assertEquals("Laboratório_de_Programação_II", listaDisciplinaSegundoPeriodo.get(2).getNome());
		Assert.assertEquals("Matemática_Discreta", listaDisciplinaSegundoPeriodo.get(3).getNome());
	}

	@Test
	public void naoDeveAddEmOutroPeriodoSeAnteriorNaoTemMinimoDeCreditos() {

	}

	@Test
	public void deveVerificarOsPreRequisitos() {
		GradeCurricular grade = new GradeCurricular();
		Disciplina p2 = grade.get("Programação_II");
		Disciplina c2 = grade.get("Cálculo_Diferencial_e_Integral_II");
		Disciplina tc = grade.get("Teoria_da_Computação");
		Disciplina md = grade.get("Matemática_Discreta");
		Disciplina infosoc = grade.get("Informática_e_Sociedade");
		Disciplina msn = grade.get("Métodos_e_Software_Numéricos");
		Disciplina optativa1 = grade.get("Optativa_1");
		Disciplina proj1 = grade.get("Projeto_em_Computação_I");
		
		Assert.assertTrue(planoDeCurso.isPreRequisitosEstaoSatisfeitos(p2));
		Assert.assertTrue(planoDeCurso.isPreRequisitosEstaoSatisfeitos(c2));
		Assert.assertFalse(planoDeCurso.isPreRequisitosEstaoSatisfeitos(tc));
		Assert.assertTrue(planoDeCurso.isPreRequisitosEstaoSatisfeitos(md));
		Assert.assertTrue(planoDeCurso.isPreRequisitosEstaoSatisfeitos(infosoc));
		Assert.assertFalse(planoDeCurso.isPreRequisitosEstaoSatisfeitos(msn));
		Assert.assertTrue(planoDeCurso.isPreRequisitosEstaoSatisfeitos(optativa1));
		Assert.assertFalse(planoDeCurso.isPreRequisitosEstaoSatisfeitos(proj1));		
	}

	@Test
	public void deveRemoverDisciplinasSemPreRequisitoSatisfeito() {

	}
}