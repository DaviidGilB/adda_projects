package problemaSuplentes.tipos;

import java.util.List;
import java.util.Map;

public interface SolucionSupl {
	Map<Integer, Jugador> getMapaJugadoresSolucion();
	List<Jugador> getListaJugadoresOriginal();
	List<Jugador> getListaJugadoresSolucion();
	List<String> getNombreJugadoresSolucion();
	List<Integer> getIDJugadoresSolucion();
	String solucion();
	Integer getS();
	Integer getM();
	double getValorFuncion();
	
}
