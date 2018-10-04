package problemaAlimentos.tipos;

import java.util.HashMap;
import java.util.Map;
import us.lsi.bt.SolucionBT;

public class Solucion implements SolucionAlimentos, SolucionBT {
	
	private Map<Integer, Integer> solucion;
	private Double costeTotal;
	
	public static SolucionAlimentos create() {
		return new Solucion();
	}
	
	public Solucion(Map<Integer, Integer> solucion, Double costeTotal) {
		this.solucion = solucion;
		this.costeTotal = costeTotal;
	}
	
	public Solucion() {
		this.solucion = new HashMap<Integer, Integer> ();
		this.costeTotal = 0.;
	}

	public Map<Integer, Integer> getSolucion() {
		return this.solucion;
	}

	public void addIngrediente(Integer index, Integer cantidad) {
		this.solucion.put(index, cantidad);
	}

	public String toString() {
		return "Solucion: " + this.solucion + " - Coste: " + this.costeTotal + "€";
	}

	public Double getCosteTotal() {
		return this.costeTotal;
	}

	public void setCosteTotal(Double costeTotal) {
		this.costeTotal = costeTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((costeTotal == null) ? 0 : costeTotal.hashCode());
		result = prime * result + ((solucion == null) ? 0 : solucion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Solucion other = (Solucion) obj;
		if (costeTotal == null) {
			if (other.costeTotal != null)
				return false;
		} else if (!costeTotal.equals(other.costeTotal))
			return false;
		if (solucion == null) {
			if (other.solucion != null)
				return false;
		} else if (!solucion.equals(other.solucion))
			return false;
		return true;
	}

	@Override
	public Double getObjetivo() {
		return this.costeTotal;
	}

}
