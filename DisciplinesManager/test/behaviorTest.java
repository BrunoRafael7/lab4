import java.util.List;

import models.Disciplina;
import models.PlanoDeCurso;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class BehaviorTest {
	
	private PlanoDeCurso planoDeCurso;
	
	@Before
	public void start(){
		planoDeCurso = new PlanoDeCurso(); 
	}
	
	@Test
	public void verificaSeOPrimeiroPeriodoEstaIniciandoCorretamente(){
		List<Disciplina> disciplinasDoPrimeiroPeriodo = planoDeCurso.getDisciplinasDoPeriodo(1);
		
		Assert.assertEquals(6, disciplinasDoPrimeiroPeriodo.size());
				
		Assert.assertEquals("Leitura_e_Produção_de_Textos", disciplinasDoPrimeiroPeriodo.get(0).getNome());
		Assert.assertEquals("Álgebra_Vetorial_e_Geometria_Analítica", disciplinasDoPrimeiroPeriodo.get(1).getNome());
		Assert.assertEquals("Calculo_Diferencial_e_Integral_I", disciplinasDoPrimeiroPeriodo.get(2).getNome());
		Assert.assertEquals("Programação_I", disciplinasDoPrimeiroPeriodo.get(3).getNome());
		Assert.assertEquals("Introdução_à_Computação", disciplinasDoPrimeiroPeriodo.get(4).getNome());
		Assert.assertEquals("Laboratório_de_Programação_I", disciplinasDoPrimeiroPeriodo.get(5).getNome());
	}
	
	@Test
	public void naoDevePermitirAddNovaDisciplinaAoPrimeiroPeriodo(){
		int PRIMEIRO_PERIODO = 1;
		
		Assert.assertEquals(24, planoDeCurso.getTotalDeCreditosDoPeriodo(PRIMEIRO_PERIODO));
		Assert.assertEquals(6, planoDeCurso.getDisciplinasDoPeriodo(PRIMEIRO_PERIODO).size());
		
		planoDeCurso.alocaDisciplina("Programação_I", PRIMEIRO_PERIODO); //DISCIPLINA REPETIDA
		
		Assert.assertEquals(24, planoDeCurso.getTotalDeCreditosDoPeriodo(PRIMEIRO_PERIODO));
		Assert.assertEquals(6, planoDeCurso.getDisciplinasDoPeriodo(PRIMEIRO_PERIODO).size());
		
		planoDeCurso.alocaDisciplina("Matemática_Discreta", PRIMEIRO_PERIODO);
		planoDeCurso.alocaDisciplina("Optativa_1", PRIMEIRO_PERIODO);
		
		Assert.assertEquals(24, planoDeCurso.getTotalDeCreditosDoPeriodo(PRIMEIRO_PERIODO));
		Assert.assertEquals(6, planoDeCurso.getDisciplinasDoPeriodo(PRIMEIRO_PERIODO).size());
	}
	
	@Test
	public void deveAddNovaDisciplinaAoSegundoPeriodo(){
		int SEGUNDO_PERIODO = 2;
		Assert.assertEquals(0, planoDeCurso.getDisciplinasDoPeriodo(SEGUNDO_PERIODO).size());
		Assert.assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
		
		planoDeCurso.alocaDisciplina("Matemática_Discreta", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Programação_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Cálculo_Diferencial_e_Integral_II", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Laboratório_de_Programação_II", SEGUNDO_PERIODO);
		
		Assert.assertEquals(4, planoDeCurso.getDisciplinasDoPeriodo(SEGUNDO_PERIODO).size());
		Assert.assertEquals(16, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));

		planoDeCurso.alocaDisciplina("Álgebra_Linear", SEGUNDO_PERIODO);
		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Clássica", SEGUNDO_PERIODO);
		
		Assert.assertEquals(6, planoDeCurso.getDisciplinasDoPeriodo(SEGUNDO_PERIODO).size());
		Assert.assertEquals(24, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));
	}
	
	@Test
	public void deveAddNovaDisciplinaEmVariosPeriodos(){
		
	}
	
	@Test
	public void disciplinasDoPrimeiroPeriodoNaoDevemEstarDisponiveisParaAlocar(){
		int QUINTO_PERIODO = 5;
		
		Assert.assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(QUINTO_PERIODO));
		
		planoDeCurso.alocaDisciplina("Programação_I", QUINTO_PERIODO);
		planoDeCurso.alocaDisciplina("Álgebra_Vetorial_e_Geometria_Analítica", QUINTO_PERIODO);
		planoDeCurso.alocaDisciplina("Calculo_Diferencial_e_Integral_I", QUINTO_PERIODO);
		planoDeCurso.alocaDisciplina("Introdução_à_Computação", QUINTO_PERIODO);
		planoDeCurso.alocaDisciplina("Laboratório_de_Programação_I", QUINTO_PERIODO);
		planoDeCurso.alocaDisciplina("Leitura_e_Produção_de_Textos", QUINTO_PERIODO);
		
		Assert.assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(QUINTO_PERIODO));
	}
	
	@Test
	public void deveAtualizarQuantidadeDeCreditos(){
		int TERCEIRO_PERIODO = 3;
		
		Assert.assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));
		
		planoDeCurso.alocaDisciplina("Estruturas_de_Dados_e_Algoritmos", TERCEIRO_PERIODO);
		Assert.assertEquals(4, planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));

		planoDeCurso.alocaDisciplina("Fundamentos_de_Física_Moderna", TERCEIRO_PERIODO);
		Assert.assertEquals(8, planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));

		planoDeCurso.alocaDisciplina("Laboratório_de_Estruturas_de_Dados_e_Algoritmos", TERCEIRO_PERIODO);
		Assert.assertEquals(12, planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));

		planoDeCurso.alocaDisciplina("Teoria_da_Computação", TERCEIRO_PERIODO);
		Assert.assertEquals(16, planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));

		planoDeCurso.alocaDisciplina("Paradigmas_de_Linguagens_de_Programação", TERCEIRO_PERIODO);
		Assert.assertEquals(18, planoDeCurso.getTotalDeCreditosDoPeriodo(TERCEIRO_PERIODO));
	}
	
	@Test
	public void deveDesalocarDisciplina(){
		
	}
	
	@Test
	public void deveVerificarOsPreRequisitos(){
		
	}
	
	@Test
	public void deveRemoverDisciplinasSemPreRequisitoSatisfeito(){
		
	}
}
