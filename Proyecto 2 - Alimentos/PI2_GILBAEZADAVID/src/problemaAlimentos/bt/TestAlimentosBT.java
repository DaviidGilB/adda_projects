package problemaAlimentos.bt;

import problemaAlimentos.tipos.Problema;
import problemaAlimentos.tipos.ProblemaAlimentos;
import problemaAlimentos.tipos.SolucionAlimentos;
import us.lsi.algoritmos.AbstractAlgoritmo;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.bt.AlgoritmoBT;

public class TestAlimentosBT {

	public static void main(String[] args) {
		ProblemaAlimentos problemaOriginal = Problema.create("ficheros/alimentos.txt", 1000);
		System.out.println(problemaOriginal);
		
		System.out.println("RESOLUCIÓN CON BACKTRACKING:");
		
		EstadoAlimentosBT.problemaOriginal = problemaOriginal;
		
		AlgoritmoBT.numeroDeSoluciones = 1;
		AlgoritmoBT.isRandomize = false;
		
		EstadoAlimentosBT inicial = EstadoAlimentosBT.inicial();
		AlgoritmoBT<SolucionAlimentos, Integer> a =
				Algoritmos.createBT(inicial);
		
		AlgoritmoBT.conFiltro=false;
		
		long startSinFiltro = System.currentTimeMillis();
		a.ejecuta();
		long tiempoSinFiltro = System.currentTimeMillis()-startSinFiltro;
		
		if (a.getSoluciones().isEmpty()) 
			System.out.println("No se encuentra ninguna solución.");
		else 
			System.out.println("SIN FILTRO => " + a.getSolucion());
		
		AbstractAlgoritmo.calculaMetricas();
		AlgoritmoBT.conFiltro=true;
		
		long startConFiltro = System.currentTimeMillis();
		a.ejecuta();
		long tiempoConFiltro = System.currentTimeMillis()-startConFiltro;
		
		if (a.getSoluciones().isEmpty()) 
			System.out.println("No se encuentra ninguna solución.");
		else 
			System.out.println("CON FILTRO => " + a.getSolucion());
		
		long mejora = tiempoSinFiltro-tiempoConFiltro;
		String res;
		if(mejora>0) {
			res = "Sí, el tiempo de ejecución se ha reducido en " + mejora + "ms.";
		} else if(mejora==0) {
			res = "No, el tiempo de ejecución ha sido exactamente el mismo.";
		} else {
			res = "No, el tiempo de ejecución ha aumentado en " + Math.abs(mejora) + "ms.";
		}
		
		System.out.println("¿Se ha mejorado el tiempo de ejecución con el filtro?: " + res);
	}
}
