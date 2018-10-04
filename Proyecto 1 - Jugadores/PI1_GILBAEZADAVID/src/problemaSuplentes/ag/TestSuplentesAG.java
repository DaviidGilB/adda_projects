package problemaSuplentes.ag;

import java.util.List;
import problemaSuplentes.tipos.FactoriaJugador;
import problemaSuplentes.tipos.Jugador;
import problemaSuplentes.tipos.SolucionSupl;
import problemaSuplentes.utiles.Check;

public class TestSuplentesAG {


	public static void main(String[] args){
		
		//Creo la lista de jugadores a partir del fichero suplentes.txt
		List<Jugador> jugadores = FactoriaJugador.creaJugadores("ficheros/suplentes.txt");
				
		//Resuelvo el problema de AG, cuya solución es de tipo SolucionSupl para este problema
		SolucionSupl s = ProblemaSuplentesAG.resolver(jugadores, 7, 10); //7 y 10 para el ejemplo del problema
				
		//Imprimo la solución gracias al método solucion() de la clase SolucionSupl
		System.out.println("Problema resuelto por AG:\n"
			+ "--------\n" 
			+ s.solucion());
		
		//Comprobamos la solución
		System.out.println(Check.compruebaSolucion(s));
	}	

}
