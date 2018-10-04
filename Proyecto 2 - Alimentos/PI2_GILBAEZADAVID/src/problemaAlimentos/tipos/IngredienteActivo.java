package problemaAlimentos.tipos;

import java.util.List;

public interface IngredienteActivo {
	List<Double> getCantidadNutrientesPorGramo();
	void setCantidadNutrientesPorGramo(List<Double> nutrientes);
	Double getCostePorGramo();
	void setCostePorGramo(Double coste);
}
