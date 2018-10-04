package problemaSuplentes.tipos;

import java.util.Arrays;
import java.util.List;
import com.google.common.collect.Lists;
import us.lsi.stream.Stream2;

public class FactoriaJugador {

	public static Jugador create(Posicion pos1, Posicion pos2,
			Integer cache, Integer tirosCortos,
			Integer tirosLargos) {
		return new JugadorImpl(pos1, pos2, cache, tirosCortos, tirosLargos);
	}
	
	public static Jugador create(String nombre, Posicion pos1, Posicion pos2,
			Integer cache, String nacion, Integer minJugados, 
			Integer tirosCortos, Integer tirosLargos) {
		return new JugadorImpl(nombre, pos1, pos2, cache, nacion, minJugados, tirosCortos, tirosLargos);
	}
	
	public static List<Jugador> creaJugadores(String path) {
		List<String> lineas = Stream2.fromFile(path).toList();
		List<Jugador> jugadores	= Lists.newArrayList();
		for(int i = 0; i < lineas.size(); i++) {
			jugadores.add(new JugadorImpl(lineas.get(i)));
		}
		return jugadores;
	}
	
	public static List<Jugador> creaJugadores() {
		Jugador j0 = create("Alex", Posicion.Alero, Posicion.Escolta,
				1, "España", 2, 5, 1);
		Jugador j1 = create("Carlos", Posicion.AleroPivot, Posicion.Pivot,
				4, "España", 4, 4, 4);
		Jugador j2 = create("Jordi", Posicion.Pivot, Posicion.AleroPivot,
				3, "España", 5, 3, 3);
		Jugador j3 = create("Victor", Posicion.Escolta, Posicion.AleroPivot,
				1, "España", 1, 3, 1);
		Jugador j4 = create("Fran", Posicion.AleroPivot, Posicion.Escolta,
				2, "España", 2, 5, 2);
		Jugador j5 = create("Michael", Posicion.Base, Posicion.Escolta,
				3, "USA", 3, 3, 5);
		Jugador j6 = create("Drazen", Posicion.Pivot, Posicion.Escolta,
				1, "Croacia", 2,  1, 4);
		Jugador j7 = create("Emanuel", Posicion.Base, Posicion.Pivot,
				2, "Argentina", 2, 3, 2);
		Jugador j8 = create("Toni", Posicion.Alero, Posicion.Pivot,
				2, "Croacia", 2, 5, 2);
		Jugador j9 = create("Yao", Posicion.AleroPivot, Posicion.Alero,
				3, "Francia", 3, 3, 3);
		Jugador j10 = create("Pablo", Posicion.Base, Posicion.Escolta,
				4, "Argentina", 4, 4, 4);
		Jugador j11 = create("Dino", Posicion.Pivot, Posicion.Pivot,
				2, "Croacia", 2, 2, 2);
		Jugador j12 = create("Lamarcus", Posicion.Base, Posicion.AleroPivot,
				2, "USA", 2, 2, 2);
		Jugador j13 = create("Mark", Posicion.Alero, Posicion.Pivot,
				1, "USA", 1, 5, 3);
		Jugador j14 = create("Juan", Posicion.Base, Posicion.Base,
				3, "Argentina", 3, 3, 3);
		Jugador j15 = create("Homero", Posicion.Pivot, Posicion.AleroPivot,
				4, "Argentina", 4, 2, 4);
		Jugador j16 = create("Chris", Posicion.Base, Posicion.Base,
				5, "USA", 5, 5, 5);
		Jugador j17 = create("Joseph", Posicion.AleroPivot, Posicion.Escolta,
				1, "Francia", 1, 5, 3);
		Jugador j18 = create("Zoran", Posicion.Pivot, Posicion.Alero,
				2, "Croacia", 4, 3, 2);
		Jugador j19 = create("Laurent", Posicion.Base, Posicion.Escolta,
				3, "Francia", 3, 3, 3);
		
		return Arrays.asList(j0, j1, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19);
	}
}
