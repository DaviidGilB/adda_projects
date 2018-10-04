package problemaSuplentes.pli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import problemaSuplentes.tipos.Jugador;
import problemaSuplentes.tipos.Posicion;
import problemaSuplentes.tipos.SolucionSupl;
import problemaSuplentes.tipos.SolucionSuplImpl;
import us.lsi.pl.AlgoritmoPLI;

public class ProblemaSuplentesPLI {
	
	public static SolucionSupl resolver(List<Jugador> jugadores, int S, int M) {
		AlgoritmoPLI a = AlgoritmoPLI.create();
		a.setConstraints(getConstraints(jugadores, S, M));
		a.ejecuta();
		Map<Integer, Jugador> mapa = new HashMap<> ();
		for (int i = 0; i < jugadores.size(); i++){
			double x = Math.round(a.getSolucion()[i]);
			if(x == 1) {
				mapa.put(i, jugadores.get(i));
			}
		}
		return new SolucionSuplImpl(mapa, jugadores, S, M, Math.round(a.getObjetivo()));
	}
	
	@SuppressWarnings("unused")
	private static void muestraConstraints(List<Jugador> j,
			int S, int M) {
		System.out.println(getConstraints(j, S, M));
	}
	
	public static String getConstraints(List<Jugador> jugadores,
			int S, int M) {
		int N = jugadores.size();
		
		String res = "max: ";
		
		for(int i = 0; i < N; i++) {
			String tc = jugadores.get(i).getTirosCortos().toString();
			String tl = jugadores.get(i).getTirosLargos().toString();
			String var = AlgoritmoPLI.getVariable("x", i);
			res = res + tc + var + " + " + tl + var;
			if(i!=N-1) {
				res = res + " + ";
			} else {
				res = res + "; // Función objetivo\n\n";
			}
		}
		
		for(int i = 0; i < N; i++) {
			res = res + AlgoritmoPLI.getVariable("x", i);
			if(i!=N-1) {
				res = res + " + ";
			} else {
				res = res + " = " + S + "; // Restricción num suplentes\n\n";
			}
		}
		
		List<String> varPivot = new ArrayList<> ();
		for(int i = 0; i < N; i++) {
			Posicion pos1 = jugadores.get(i).getPos1();
			Posicion pos2 = jugadores.get(i).getPos2();
			if(pos1.equals(Posicion.Pivot) || 
					pos2.equals(Posicion.Pivot)) {
				varPivot.add(AlgoritmoPLI.getVariable("x", i));
			}
		}
		if(varPivot.size()!=0) {
			for(int j = 0; j < varPivot.size(); j++) {
				if(j!=varPivot.size()-1) {
					res = res + varPivot.get(j) + " + ";
				} else {
					res = res + varPivot.get(j) + " >= 2; // Restricción Pivot\n\n";
				}
			}
		}
		
		List<String> varAlero = new ArrayList<> ();
		for(int i = 0; i < N; i++) {
			Posicion pos1 = jugadores.get(i).getPos1();
			Posicion pos2 = jugadores.get(i).getPos2();
			if(pos1.equals(Posicion.Alero) || 
					pos2.equals(Posicion.Alero)) {
				varAlero.add(AlgoritmoPLI.getVariable("x", i));
			}
		}
		if(varAlero.size()!=0) {
			for(int j = 0; j < varAlero.size(); j++) {
				if(j!=varAlero.size()-1) {
					res = res + varAlero.get(j) + " + ";
				} else {
					res = res + varAlero.get(j) + " >= 3; // Restricción Alero\n\n";
				}
			}
		}
		
		List<String> varBase = new ArrayList<> ();
		for(int i = 0; i < N; i++) {
			Posicion pos1 = jugadores.get(i).getPos1();
			Posicion pos2 = jugadores.get(i).getPos2();
			if(pos1.equals(Posicion.Base) || 
					pos2.equals(Posicion.Base)) {
				varBase.add(AlgoritmoPLI.getVariable("x", i));
			}
		}
		if(varBase.size()!=0) {
			for(int j = 0; j < varBase.size(); j++) {
				if(j!=varBase.size()-1) {
					res = res + varBase.get(j) + " + ";
				} else {
					res = res + varBase.get(j) + " = 1; // Restricción Base\n\n";
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			String cache = jugadores.get(i).getCache().toString();
			res = res + cache + AlgoritmoPLI.getVariable("x", i);
			if(i!=N-1) {
				res = res + " + ";
			} else {
				res = res + " <= " + M + "; // Restricción presupuesto\n\n";
			}
		}
		
		res = res + "bin ";
		for(int i = 0; i < N; i++) {
			res = res + AlgoritmoPLI.getVariable("x", i);
			if(i!=N-1) {
				res = res + ", ";
			} else {
				res = res + "; // Declaración variables";
			}
		}
		
		return res;
	}

}
