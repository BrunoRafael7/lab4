package models;

import java.util.LinkedList;
import java.util.List;

public class PlanoDeCurso {
//        private final int TOTAL_DE_PERIODOS = 10;
//        private final int MINIMO_DE_CREDITOS = 14;
        public final int MAXIMO_DE_CREDITOS_POR_PERIODO = 28;
        private List<Periodo> periodos;
        
        private GradeCurricular gradeCurricular;
        
        public PlanoDeCurso(){
                periodos = new LinkedList<Periodo>();
                gradeCurricular = new GradeCurricular();
                /*
                 * CREATOR : 
                 * Classe PlanoDeCurso registra objetos do tipo Periodo 
                 * pois planoDeCurso é composta de Periodos
                 */
                periodos.add(new Periodo(gradeCurricular.getDisciplinasDoPeriodo(1)));
        }
//        public void adicionaDisciplinaAPeriodo(Disciplina disciplina, int periodo){
//                if(this.verificaSePreRequisitosEstaoOK(disciplina, this.getPeriodos())){
//                        periodos.get(periodo - 1).adicionaUmaDisciplina(disciplina);
//                        gradeCurricular.getAllDisciplines().remove(disciplina)  ;
//                }
//        }
        
        public List<Periodo> getPeriodos() {
                return periodos;
        }
        
        public List<Disciplina> getDisciplinasDoPeriodo(int periodo){
        		int indiceDoPeriodo = periodo - 1;
                return periodos.get(indiceDoPeriodo).getDisciplinas();
        }
        
        //tem que ser modificado, pois não é ele quem vai calcular
        public int getTotalDeCreditosDoPeriodo(int periodo){
        	int indiceDoPeriodo = periodo - 1;
        	return periodos.get(indiceDoPeriodo).getTotalDeCreditos();
        }
        
//        private boolean verificaSePreRequisitosEstaoOK(Disciplina disciplina, int index){
//	        int contaPreRequisitos = 0;
//	        List<String> preRequisito = disciplina.getPreRequisitos();
//	        if(!(preRequisito.isEmpty())){ //SO ENTRA SE TIVER PRE-REQUISITO
//	        	
//	                        for(Disciplina disciplinaPeriodo : periodo.getDisciplinas()){
//	                                if(preRequisito.contains(disciplinaPeriodo.getNome())){
//	                                        contaPreRequisitos ++;
//	                                        
//	                                }
//	                        }
//	        }
//	        return (contaPreRequisitos == preRequisito.size());
//        }
        
        /*
         * INFORMATION EXPERT:
         * 	 Para que o sistema tenha Alta coesão e baixo acoplamento, 
         * 	 Classe Plano de curso é quem deve ter a responsablidade de "selecionar" 
         * 	 as disciplinas não alocadas.
         */
        public List<Disciplina> getDisciplinasNaoAlocadas(){
        	List<Disciplina> disciplinasNaoAlocadas = new LinkedList<Disciplina>();
        	for(Disciplina disc : gradeCurricular.todasAsDisciplinas()){
        		if(!disc.isAlocada()){
        			disciplinasNaoAlocadas.add(disc);
        		}
        	}
        	return disciplinasNaoAlocadas;
        }
        
//        private boolean estaComMinimoDeCreditos(List<Disciplina> disciplinas){
//                boolean resp = false;
//                int contaCredito = 0;
//                for(Disciplina disciplina : disciplinas){
//                        contaCredito += disciplina.getCreditos();
//                }
//                if(contaCredito >= MINIMO_DE_CREDITOS){
//                        resp = true;
//                }
//                return resp;
//        }
//        
//        /*
//         * Padrão Expert
//         */
//        private boolean estaComMaximoDeCreditos(List<Disciplina> disciplinas){
//                boolean resp = false;
//                int contaCredito = 0;
//                for(Disciplina disciplina : disciplinas){
//                        contaCredito += disciplina.getCreditos();
//                }
//                if(contaCredito <= MAXIMO_DE_CREDITOS){
//                        resp = true;
//                }
//                return resp;
//        }
//        
//        public boolean estaComQuantidadeDeCreditosPermitido(List<Disciplina> disciplinas){
//                return (this.estaComMinimoDeCreditos(disciplinas) && this.estaComMaximoDeCreditos(disciplinas));
//        }
}