package problemaAlimentos.bt;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import problemaAlimentos.tipos.ProblemaAlimentos;
import problemaAlimentos.tipos.Solucion;
import problemaAlimentos.tipos.SolucionAlimentos;
import us.lsi.bt.EstadoBT;

public class EstadoAlimentosBT implements EstadoBT<SolucionAlimentos, Integer> {

	//Propiedad compartida
	public static ProblemaAlimentos problemaOriginal;

	//Propiedades individuales
	private Integer index;
	private Integer cantidadAcumulada;
	private Double costeAcumulado;
	private List<Double> nutrientesAcumulados;
	private Integer cantidadRestante; //Derivada
	private List<Integer> listaAlternativas; //Basica
	
	public EstadoAlimentosBT(Integer index, Integer cantidadAcumulada,
			Double costeAcumulado, List<Double> nutrientesAcumulados,
			List<Integer> listaAlternativas) {
		this.index = index;
		this.cantidadAcumulada = cantidadAcumulada;
		this.costeAcumulado = costeAcumulado;
		this.nutrientesAcumulados = nutrientesAcumulados;
		this.cantidadRestante = EstadoAlimentosBT.problemaOriginal.getCantidadMaxima()
				- cantidadAcumulada;
		this.listaAlternativas = listaAlternativas;
	}
	
	public static EstadoAlimentosBT inicial() {
		List<Double> nutrientes = new ArrayList<Double> ();
		for(int i = 0; i<EstadoAlimentosBT.problemaOriginal.getNNutrientes(); i++) {
			nutrientes.add(0.);
		}
		List<Integer> listaAlternativas = new ArrayList<> ();
		return new EstadoAlimentosBT(0, 0, 0., nutrientes, listaAlternativas);
	}
	
	public Tipo getTipo() {
		return Tipo.Min;
	}

	public EstadoBT<SolucionAlimentos, Integer> getEstadoInicial() {
		return EstadoAlimentosBT.inicial();
	}

	public EstadoBT<SolucionAlimentos, Integer> avanza(Integer a) {
		this.costeAcumulado = Math.round((this.costeAcumulado + a*EstadoAlimentosBT.problemaOriginal.getIngredientesActivos()
			.get(this.index).getCostePorGramo()) * 100.0) / 100.0;
		List<Double> nutrientesAcumulados = new ArrayList<Double> ();
		for(int i = 0; i<EstadoAlimentosBT.problemaOriginal.getNNutrientes(); i++) {
			Double valor = Math.round((this.nutrientesAcumulados.get(i)) * 100.0) / 100.0;
			nutrientesAcumulados.add(valor + 
					Math.round((a*EstadoAlimentosBT.problemaOriginal.getIngredientesActivos().get(this.index)
					.getCantidadNutrientesPorGramo().get(i)) * 100.0) / 100.0);
		}
		this.nutrientesAcumulados = nutrientesAcumulados;
		this.cantidadAcumulada = this.cantidadAcumulada + a;
		this.listaAlternativas.add(a);
		this.cantidadRestante = EstadoAlimentosBT.problemaOriginal.getCantidadMaxima()- cantidadAcumulada;
		this.index++;
		return this;
	}

	//Math.round((coste) * 100.0) / 100.0
	
	public EstadoBT<SolucionAlimentos, Integer> retrocede(Integer a) {
		this.index--;
		List<Double> nutrientesAcumulados = new ArrayList<Double> ();
		for(int i = 0; i<EstadoAlimentosBT.problemaOriginal.getNNutrientes(); i++) {
			Double valor = Math.round((this.nutrientesAcumulados.get(i)) * 100.0) / 100.0;
			nutrientesAcumulados.add(valor - 
					Math.round((a*EstadoAlimentosBT.problemaOriginal.getIngredientesActivos().get(this.index)
					.getCantidadNutrientesPorGramo().get(i)) * 100.0) / 100.0);
		}
		this.nutrientesAcumulados = nutrientesAcumulados;
		this.cantidadAcumulada = this.cantidadAcumulada - a;
		this.costeAcumulado = Math.round((this.costeAcumulado - a*EstadoAlimentosBT.problemaOriginal.getIngredientesActivos()
		.get(this.index).getCostePorGramo()) * 100.0) / 100.0;
		this.listaAlternativas.remove(this.listaAlternativas.size()-1);
		this.cantidadRestante = EstadoAlimentosBT.problemaOriginal.getCantidadMaxima()- cantidadAcumulada;
		return this;
	}

	public int size() {
		return EstadoAlimentosBT.problemaOriginal.getNIngredientes() - this.index + 1;
	}

	public boolean esCasoBase() {
		return EstadoAlimentosBT.problemaOriginal.getNIngredientes() == this.index;
	}

	public List<Integer> getAlternativas() {
		return IntStream.rangeClosed(0, this.cantidadRestante)
				.boxed()
				.collect(Collectors.toList());
	}

	private boolean cumpleMinimos() {
		Boolean res = true;
		for(int i = 0; i<EstadoAlimentosBT.problemaOriginal.getNNutrientes(); i++) {
			if(this.nutrientesAcumulados.get(i)>=EstadoAlimentosBT.problemaOriginal.getCantidadMinimaNutrientes().get(i)) {
				res = res && true;
			} else {
				res = res && false;
			}
		}
		return res;
	}
	
	public SolucionAlimentos getSolucion() {
		if(this.cumpleMinimos()) {
			Map<Integer, Integer> sol = new HashMap<> ();
			for(int i = 0; i<this.listaAlternativas.size(); i++) {	
				sol.put(i, this.listaAlternativas.get(i));
			}
			return new Solucion(sol, this.costeAcumulado);
		} else {
			return null;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidadAcumulada == null) ? 0 : cantidadAcumulada.hashCode());
		result = prime * result + ((cantidadRestante == null) ? 0 : cantidadRestante.hashCode());
		result = prime * result + ((costeAcumulado == null) ? 0 : costeAcumulado.hashCode());
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((listaAlternativas == null) ? 0 : listaAlternativas.hashCode());
		result = prime * result + ((nutrientesAcumulados == null) ? 0 : nutrientesAcumulados.hashCode());
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
		EstadoAlimentosBT other = (EstadoAlimentosBT) obj;
		if (cantidadAcumulada == null) {
			if (other.cantidadAcumulada != null)
				return false;
		} else if (!cantidadAcumulada.equals(other.cantidadAcumulada))
			return false;
		if (cantidadRestante == null) {
			if (other.cantidadRestante != null)
				return false;
		} else if (!cantidadRestante.equals(other.cantidadRestante))
			return false;
		if (costeAcumulado == null) {
			if (other.costeAcumulado != null)
				return false;
		} else if (!costeAcumulado.equals(other.costeAcumulado))
			return false;
		if (index == null) {
			if (this.index != null)
				return false;
		} else if (!index.equals(this.index))
			return false;
		if (listaAlternativas == null) {
			if (other.listaAlternativas != null)
				return false;
		} else if (!listaAlternativas.equals(other.listaAlternativas))
			return false;
		if (nutrientesAcumulados == null) {
			if (other.nutrientesAcumulados != null)
				return false;
		} else if (!nutrientesAcumulados.equals(other.nutrientesAcumulados))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
		separadoresPersonalizados.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("#.####", separadoresPersonalizados);
		if(!this.esCasoBase()) {
			return "Ingrediente: " + this.index + ", " + df.format(this.costeAcumulado) + "€";
		} else {
			String res = "No.";
			if(this.cumpleMinimos()) {
				res = "Sí.";
			}
			return df.format(this.costeAcumulado) + "€, ¿Cumple mínimos?: " + res;
		}
	}

	@Override
	public Double getObjetivoEstimado(Integer a) {
		return this.costeAcumulado + this.getCota(a);
	}
	
	public Integer getCota(Integer a) {
		return (int) (EstadoAlimentosBT.problemaOriginal.getIngredientesActivos()
				.get(this.index).getCostePorGramo()*a);
	}

}
