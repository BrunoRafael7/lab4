import static org.junit.Assert.*;

import java.util.List;

import models.Disciplina;
import models.PlanoDeCurso;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class behaviorTest {
	
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
		
	}
	
	@Test
	public void deveAddNovaDisciplinaAoSegundoPeriodo(){
		int SEGUNDO_PERIODO = 2;
		Assert.assertEquals(0, planoDeCurso.getDisciplinasDoPeriodo(SEGUNDO_PERIODO).size());
		Assert.assertEquals(0, planoDeCurso.getTotalDeCreditosDoPeriodo(SEGUNDO_PERIODO));;
		
	
		
	}
	
	@Test
	public void disciplinasDoPrimeiroPeriodoNaoDevemEstarDisponiveisParaAlocar(){
		
	}
	
	
	@Test
	public void deveVerificarOsPreRequisitos(){
		
	}
	
	@Test
	public void deveRemoverDisciplinasSemPreRequisitoSatisfeito(){
		
	}
	
	@Test
	public void deveAtualizarQuantidadeDeCreditos(){
		
	}

}
