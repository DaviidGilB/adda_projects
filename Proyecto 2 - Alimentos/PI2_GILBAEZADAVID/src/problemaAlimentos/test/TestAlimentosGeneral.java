package problemaAlimentos.test;

import problemaAlimentos.bt.EstadoAlimentosBT;
import problemaAlimentos.pd.ProblemaAlimentosPD;
import problemaAlimentos.tipos.Problema;
import problemaAlimentos.tipos.ProblemaAlimentos;
import problemaAlimentos.tipos.SolucionAlimentos;
import us.lsi.algoritmos.AbstractAlgoritmo;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.bt.AlgoritmoBT;
import us.lsi.pd.AlgoritmoPD;
import us.lsi.pd.ProblemaPDR;

public class TestAlimentosGeneral {

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
		
		System.out.println("RESOLUCIÓN CON BACKTRACKING:");
		
		EstadoAlimentosBT.problemaOriginal = problemaOriginal;
		
		AlgoritmoBT.numeroDeSoluciones = 1;
		AlgoritmoBT.isRandomize = false;
		
		EstadoAlimentosBT inicial2 = EstadoAlimentosBT.inicial();
		AlgoritmoBT<SolucionAlimentos, Integer> a2 =
				Algoritmos.createBT(inicial2);
		
		AlgoritmoBT.conFiltro=false;
		
		long startSinFiltro2 = System.currentTimeMillis();
		a2.ejecuta();
		long tiempoSinFiltro2 = System.currentTimeMillis()-startSinFiltro2;
		
		if (a2.getSoluciones().isEmpty()) 
			System.out.println("No se encuentra ninguna solución.");
		else 
			System.out.println("SIN FILTRO => " + a2.getSolucion());
		
		AbstractAlgoritmo.calculaMetricas();
		AlgoritmoBT.conFiltro=true;
		
		long startConFiltro2 = System.currentTimeMillis();
		a.ejecuta();
		long tiempoConFiltro2 = System.currentTimeMillis()-startConFiltro2;
		
		if (a2.getSoluciones().isEmpty()) 
			System.out.println("No se encuentra ninguna solución.");
		else 
			System.out.println("CON FILTRO => " + a2.getSolucion());
		
		long mejora2 = tiempoSinFiltro2-tiempoConFiltro2;
		String res2;
		if(mejora2>0) {
			res2 = "Sí, el tiempo de ejecución se ha reducido en " + mejora2 + "ms.";
		} else if(mejora2==0) {
			res2 = "No, el tiempo de ejecución ha sido exactamente el mismo.";
		} else {
			res2 = "No, el tiempo de ejecución ha aumentado en " + Math.abs(mejora2) + "ms.";
		}
		
		System.out.println("¿Se ha mejorado el tiempo de ejecución con el filtro?: " + res2);
	}

}
