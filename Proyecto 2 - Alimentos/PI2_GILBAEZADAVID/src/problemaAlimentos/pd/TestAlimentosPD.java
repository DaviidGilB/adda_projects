package problemaAlimentos.pd;

import problemaAlimentos.tipos.Problema;
import problemaAlimentos.tipos.ProblemaAlimentos;
import problemaAlimentos.tipos.SolucionAlimentos;
import us.lsi.algoritmos.AbstractAlgoritmo;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.pd.AlgoritmoPD;
import us.lsi.pd.ProblemaPDR;

public class TestAlimentosPD {

	public static void main(String[] args) {
		ProblemaAlimentos problemaOriginal = Problema.create("ficheros/alimentos.txt", 1000);
		System.out.println(problemaOriginal);
		
		System.out.println("RESOLUCIÓN CON PROGRAMACIÓN DINÁMICA:");
		
		ProblemaAlimentosPD.problemaOriginal = problemaOriginal;
		AlgoritmoPD.isRandomize = false;

		ProblemaPDR<SolucionAlimentos, Integer> inicial =
				ProblemaAlimentosPD.create();
		AlgoritmoPD<SolucionAlimentos, Integer> a =
				Algoritmos.createPD(inicial);
		
		AlgoritmoPD.conFiltro=false;
		
		long startSinFiltro = System.currentTimeMillis();
		a.ejecuta();
		long tiempoSinFiltro = System.currentTimeMillis()-startSinFiltro;
		
		SolucionAlimentos solucionSinFiltro = a.getSolucion(inicial);
		System.out.println("SIN FILTRO => " + solucionSinFiltro);
		
		// DESCOMENTAR PARA GENERAR ARCHIVO .GV
		// a.showAllGraph("ficheros/GrafoAlimentos.gv", "Solucion", inicial);
		
		AbstractAlgoritmo.calculaMetricas();
		AlgoritmoPD.conFiltro=true;
		
		long startConFiltro = System.currentTimeMillis();
		a.ejecuta();
		long tiempoConFiltro = System.currentTimeMillis()-startConFiltro;
		
		SolucionAlimentos solucionConFiltro = a.getSolucion(inicial);
		System.out.println("CON FILTRO => " + solucionConFiltro);
		
		// DESCOMENTAR PARA GENERAR ARCHIVO .GV
		// a.showAllGraph("ficheros/GrafoAlimentosFiltro.gv", "Solucion", inicial);
		
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
