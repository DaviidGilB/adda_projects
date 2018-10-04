package problemaAlimentos.tipos;

import java.util.List;

public interface ProblemaAlimentos {
	
	List<IngredienteActivo> getIngredientesActivos();
	void setIngredientesActivos(List<IngredienteActivo> ingredientes);
	List<Integer> getCantidadMinimaNutrientes();
	void setCantidadMinimaNutrientes(List<Integer> cantidadMinima);
	Integer getCantidadMaxima();
	void setCantidadMaxima(Integer cantidadMaxima);
	Integer getNIngredientes(); //Derivada
	Integer getNNutrientes(); //Derivada
}
