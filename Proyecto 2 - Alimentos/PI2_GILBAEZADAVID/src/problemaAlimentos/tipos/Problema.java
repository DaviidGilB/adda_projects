package problemaAlimentos.tipos;

import java.util.List;
import com.google.common.collect.Lists;
import us.lsi.stream.Stream2;

public class Problema implements ProblemaAlimentos {
	
	private List<IngredienteActivo> ingredientes;
	private List<Integer> nutrientes;
	private Integer cantidadMaxima;
	
	public static ProblemaAlimentos create(String path, Integer cantidadMaxima) {
		return new Problema(path, cantidadMaxima);
	}

	public Problema(String path, Integer cantidadMaxima) {
		List<String> lineas = Stream2.fromFile(path).toList();
		Integer index = lineas.indexOf("#NUTRIENTESMINIMOS#");
		List<String> ingredientes = lineas.subList(1, index);
		List<String> nutrientes = lineas.subList(index+1, lineas.size());
		List<IngredienteActivo> ing = Lists.newArrayList();
		for(int i = 0; i<ingredientes.size(); i++) {
			ing.add(new Ingrediente(ingredientes.get(i)));
		}
		List<Integer> nut = Lists.newArrayList();
		for(String s:nutrientes) {
			nut.add(Integer.valueOf(s.trim()));
		}
		this.ingredientes = ing;
		this.nutrientes = nut;
		this.cantidadMaxima = cantidadMaxima;
	}

	public List<IngredienteActivo> getIngredientesActivos() {
		return this.ingredientes;
	}

	public void setIngredientesActivos(List<IngredienteActivo> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public List<Integer> getCantidadMinimaNutrientes() {
		return this.nutrientes;
	}

	public void setCantidadMinimaNutrientes(List<Integer> cantidadMinima) {
		this.nutrientes = cantidadMinima;
	}

	public Integer getCantidadMaxima() {
		return this.cantidadMaxima;
	}

	public void setCantidadMaxima(Integer cantidadMaxima) {
		this.cantidadMaxima = cantidadMaxima;
	}

	public Integer getNIngredientes() {
		return this.ingredientes.size();
	}

	public Integer getNNutrientes() {
		return this.nutrientes.size();
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidadMaxima == null) ? 0 : cantidadMaxima.hashCode());
		result = prime * result + ((ingredientes == null) ? 0 : ingredientes.hashCode());
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
		Problema other = (Problema) obj;
		if (cantidadMaxima == null) {
			if (other.cantidadMaxima != null)
				return false;
		} else if (!cantidadMaxima.equals(other.cantidadMaxima))
			return false;
		if (ingredientes == null) {
			if (other.ingredientes != null)
				return false;
		} else if (!ingredientes.equals(other.ingredientes))
			return false;
		if (nutrientes == null) {
			if (other.nutrientes != null)
				return false;
		} else if (!nutrientes.equals(other.nutrientes))
			return false;
		return true;
	}

	public String toString() {
		return "Problema [ingredientes=" + ingredientes + ", nutrientes=" + nutrientes + ", cantidadMaxima="
				+ cantidadMaxima + "]";
	}
	
}
