package problemaAlimentos.tipos;

import java.util.List;

import com.google.common.collect.Lists;

public class Ingrediente implements IngredienteActivo {
	
	private List<Double> nutrientes;
	private Double coste;
	
	public Ingrediente(String s) {
		String[] datos = s.split("#");
		this.coste = Double.valueOf(datos[1].trim());
		String datos2 = datos[0].trim();
		List<Double> nutrientes = Lists.newArrayList();
		String[] cantidades = datos2.split("/");
		for(int i = 0; i<cantidades.length; i++) {
			nutrientes.add(Double.valueOf(cantidades[i].trim()));
		}
		this.nutrientes = nutrientes;
	}

	public List<Double> getCantidadNutrientesPorGramo() {
		return this.nutrientes;
	}

	public void setCantidadNutrientesPorGramo(List<Double> nutrientes) {
		this.nutrientes = nutrientes;
	}

	public Double getCostePorGramo() {
		return this.coste;
	}

	public void setCostePorGramo(Double coste) {
		this.coste = coste;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coste == null) ? 0 : coste.hashCode());
		result = prime * result + ((nutrientes == null) ? 0 : nutrientes.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingrediente other = (Ingrediente) obj;
		if (coste == null) {
			if (other.coste != null)
				return false;
		} else if (!coste.equals(other.coste))
			return false;
		if (nutrientes == null) {
			if (other.nutrientes != null)
				return false;
		} else if (!nutrientes.equals(other.nutrientes))
			return false;
		return true;
	}

	public String toString() {
		return "Ingrediente [nutrientes=" + nutrientes + ", coste=" + coste + "]";
	}
	
	

}
