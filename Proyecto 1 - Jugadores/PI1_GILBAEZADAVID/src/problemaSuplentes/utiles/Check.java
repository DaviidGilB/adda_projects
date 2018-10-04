package problemaSuplentes.utiles;

import java.util.List;
import problemaSuplentes.tipos.Jugador;
import problemaSuplentes.tipos.Posicion;
import problemaSuplentes.tipos.SolucionSupl;

public class Check {

	public static String compruebaSolucion(SolucionSupl s) {
		List<Jugador> jugadores = s.getListaJugadoresSolucion();
		String res = "";
		Boolean R0 = jugadores.size()==s.getS();
		Boolean R1 = jugadores.stream().filter(x->x.getPos1().equals(Posicion.Pivot) ||
				x.getPos2().equals(Posicion.Pivot)).count()>=2;
		Boolean R2 = jugadores.stream().filter(x->x.getPos1().equals(Posicion.Alero) ||
				x.getPos2().equals(Posicion.Alero)).count()>=3;
		Boolean R3 = jugadores.stream().mapToInt(x->x.getCache()).sum()<=s.getM();
		Boolean R4 = jugadores.stream().filter(x->x.getPos1().equals(Posicion.Base) ||
				x.getPos2().equals(Posicion.Base)).count()==1;
		res = res + "Cumplimiento de restricciones: \n----\n";
		if(R0) {
			res = res + "R0: Sí.\n";
		} else {
			res = res + "R0: No.\n";
		}
		if(R1) {
			res = res + "R1: Sí.\n";
		} else {
			res = res + "R1: No.\n";
		}
		if(R2) {
			res = res + "R2: Sí.\n";
		} else {
			res = res + "R2: No.\n";
		}
		if(R3) {
			res = res + "R3: Sí.\n";
		} else {
			res = res + "R3: No.\n";
		}
		if(R4) {
			res = res + "R4: Sí.\n\n";
		} else {
			res = res + "R4: No.\n\n";
		}
				
		return res;
	}
	
}
