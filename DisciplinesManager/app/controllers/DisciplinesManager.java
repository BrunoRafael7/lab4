package controllers;


public class DisciplinesManager{
	
	private DisciplinesManager disciplineManager;
	
	private DisciplinesManager(){}
	
	public DisciplinesManager getInstance(){
		if(disciplineManager == null){
			disciplineManager = new DisciplinesManager();
		}
		return disciplineManager;
	}
	
	public void addDisciplineToThePeriod(){
		
	}
	
}
