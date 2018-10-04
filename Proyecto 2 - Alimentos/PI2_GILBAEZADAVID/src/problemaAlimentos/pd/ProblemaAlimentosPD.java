package problemaAlimentos.pd;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.collect.Lists;

import problemaAlimentos.tipos.ProblemaAlimentos;
import problemaAlimentos.tipos.Solucion;
import problemaAlimentos.tipos.SolucionAlimentos;
import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.ProblemaPD;
import us.lsi.pd.ProblemaPDR;

public class ProblemaAlimentosPD implements ProblemaPDR<SolucionAlimentos, Integer> {
	
	//Propiedad compartida
	public static ProblemaAlimentos problemaOriginal;
	
	//Propiedades individuales;
	private Integer index;
	private Integer cantidadAcumulada;
	private Double costeAcumulado;
	private List<Double> nutrientesAcumulados;
	private Integer cantidadRestante; //Derivada
	
	public ProblemaAlimentosPD(Integer index, Integer cantidadAcumulada, Double costeAcumulado,
			List<Double> nutrientesAcumulados) {
		this.index = index;
		this.cantidadAcumulada = cantidadAcumulada;
		this.costeAcumulado = costeAcumulado;
		this.nutrientesAcumulados = nutrientesAcumulados;
		this.cantidadRestante = ProblemaAlimentosPD.problemaOriginal.getCantidadMaxima()
				- cantidadAcumulada;
	}
	
	public static ProblemaAlimentosPD create() {
		List<Double> nutrientesAcumulados = Lists.newArrayList();
		for(int i = 0; i<ProblemaAlimentosPD.problemaOriginal.getNNutrientes(); i++) {
			nutrientesAcumulados.add(0.);
		}
		return new ProblemaAlimentosPD(0, 0, 0., nutrientesAcumulados);
	}

	public Tipo getTipo() {
		return Tipo.Min;
	}

	public int size() {
		return ProblemaAlimentosPD.problemaOriginal.getNIngredientes()-this.index+1;
	}

	public boolean esCasoBase() {
		return this.index == ProblemaAlimentosPD.problemaOriginal.getNIngredientes();
	}

	private boolean cumpleMinimos() {
		Boolean res = true;
		for(int i = 0; i<ProblemaAlimentosPD.problemaOriginal.getNNutrientes(); i++) {
			if(this.nutrientesAcumulados.get(i)>=ProblemaAlimentosPD.problemaOriginal
					.getCantidadMinimaNutrientes().get(i)) {
				res = res && true;
			} else {
				res = res && false;
				break;
			}
		}
		return res;
	}
	
	public Sp<Integer> getSolucionParcialCasoBase() {
		if(this.cumpleMinimos()) {
			return Sp.create(null, 0.);
		} else {
			return null;
		}
	}

	public Sp<Integer> getSolucionParcial(List<Sp<Integer>> ls) {
		return ls.stream().filter(x-> x.propiedad!=null).min(Comparator.naturalOrder()).orElse(null);
	}

	public List<Integer> getAlternativas() {
		return IntStream.rangeClosed(0, this.cantidadRestante)
				.boxed()
				.collect(Collectors.toList());
	}

	public SolucionAlimentos getSolucionReconstruidaCasoBase(Sp<Integer> sp) {
		return Solucion.create();
	}

	public ProblemaPD<SolucionAlimentos, Integer> getSubProblema(Integer a) {
		List<Double> nutrientes = Lists.newArrayList();
		for(int i = 0; i<ProblemaAlimentosPD.problemaOriginal.getNNutrientes(); i++) {
			Double valor = this.nutrientesAcumulados.get(i);
			nutrientes.add(valor + a*ProblemaAlimentosPD.problemaOriginal.getIngredientesActivos().get(this.index)
					.getCantidadNutrientesPorGramo().get(i));
		}
		
		int index = this.index+1;
		Integer cantidadAcumulada = this.cantidadAcumulada + a;
		Double costeAcumulado = this.costeAcumulado + a*ProblemaAlimentosPD.problemaOriginal.getIngredientesActivos()
			.get(this.index).getCostePorGramo();
		
		return new ProblemaAlimentosPD(index,
				cantidadAcumulada, costeAcumulado, nutrientes);
	}

	public Sp<Integer> getSolucionParcialPorAlternativa(Integer a, Sp<Integer> sp) {
		Double valor = a*ProblemaAlimentosPD.problemaOriginal.getIngredientesActivos()
				.get(this.index).getCostePorGramo()+sp.propiedad;
		return Sp.create(a, valor);
	}

	public SolucionAlimentos getSolucionReconstruidaCasoRecursivo(Sp<Integer> sp, SolucionAlimentos ls) {
		ls.addIngrediente(this.index, sp.alternativa);
		ls.setCosteTotal(this.costeAcumulado + sp.propiedad);
		return ls;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidadAcumulada == null) ? 0 : cantidadAcumulada.hashCode());
		result = prime * result + ((cantidadRestante == null) ? 0 : cantidadRestante.hashCode());
		result = prime * result + ((costeAcumulado == null) ? 0 : costeAcumulado.hashCode());
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((nutrientesAcumulados == null) ? 0 : nutrientesAcumulados.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProblemaAlimentosPD other = (ProblemaAlimentosPD) obj;
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
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (nutrientesAcumulados == null) {
			if (other.nutrientesAcumulados != null)
				return false;
		} else if (!nutrientesAcumulados.equals(other.nutrientesAcumulados))
			return false;
		return true;
	}

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

	//COTA
	@Override
	public Double getObjetivoEstimado(Integer a) {
		return this.costeAcumulado + this.getCota(a);
	}
	
	public Integer getCota(Integer a) {
		return (int) (ProblemaAlimentosPD.problemaOriginal.getIngredientesActivos()
				.get(this.index).getCostePorGramo()*a);
	}

	@Override
	public Double getObjetivo() {
		Double r = null;
		if (esCasoBase()) {
			r = this.costeAcumulado;
		}
		return r;
	}
	
}
