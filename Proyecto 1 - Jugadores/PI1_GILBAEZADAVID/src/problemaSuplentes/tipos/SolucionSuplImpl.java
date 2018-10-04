package problemaSuplentes.tipos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SolucionSuplImpl implements SolucionSupl {
	private Map<Integer, Jugador> mapaJugadores;
	private List<Jugador> listaJugadoresOriginal;
	private Integer S;
	private Integer M;
	private double valorFuncion;
	
	public SolucionSuplImpl (Map<Integer, Jugador> jugadoresSolucion, List<Jugador> jugadoresOriginales,
			Integer S, Integer M, double valorFuncion) {
		this.mapaJugadores = jugadoresSolucion;
		this.listaJugadoresOriginal = jugadoresOriginales;
		this.S = S;
		this.M = M;
		this.valorFuncion = valorFuncion;
	}

	public Map<Integer, Jugador> getMapaJugadoresSolucion() {
		return this.mapaJugadores;
	}

	public List<Jugador> getListaJugadoresOriginal() {
		return this.listaJugadoresOriginal;
	}

	public List<Jugador> getListaJugadoresSolucion() {
		return new ArrayList<Jugador> (this.getMapaJugadoresSolucion().values());
	}

	public List<String> getNombreJugadoresSolucion() {
		return this.getMapaJugadoresSolucion().values().stream().map(x->x.getNombre()).collect(Collectors.toList());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mapaJugadores == null) ? 0 : mapaJugadores.hashCode());
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
		SolucionSuplImpl other = (SolucionSuplImpl) obj;
		if (mapaJugadores == null) {
			if (other.mapaJugadores != null)
				return false;
		} else if (!mapaJugadores.equals(other.mapaJugadores))
			return false;
		return true;
	}

	public List<Integer> getIDJugadoresSolucion() {
		return new ArrayList<Integer> (this.getMapaJugadoresSolucion().keySet());
	}

	public String solucion() {
		String res = "Lista de jugadores completa: \n----\n";
		for(int i = 0; i < this.getListaJugadoresOriginal().size(); i++) {
			res = res + this.getListaJugadoresOriginal().get(i) + "\n";
		}
		res = res + "----\nSuplentes que se necesitan: " + this.getS().toString() + ", Presupuesto máximo: " + this.getM().toString() + "\n----\nSOLUCIÓN: \n";
		res = res + this.toString() + "\nValor de la función objetivo o fitness, según el caso: " + this.getValorFuncion() + "\n";
		return res;
	}

	public String toString() {
		String res = "";
		for(int i = 0; i < this.getListaJugadoresOriginal().size(); i++) {
			if(this.getMapaJugadoresSolucion().containsKey(i)) {
				res = res + i + " => " + "[" + this.getMapaJugadoresSolucion().get(i).toString() + "]\n";
			}
		}
		return res;
	}

	public Integer getS() {
		return this.S;
	}

	public Integer getM() {
		return this.M;
	}

	public double getValorFuncion() {
		return this.valorFuncion;
	}
	
}