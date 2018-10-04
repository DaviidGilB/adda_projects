package problemaSuplentes.ag;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import us.lsi.ag.ValuesInRangeProblemAG;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.ag.ValuesInRangeChromosome;
import problemaSuplentes.tipos.Jugador;
import problemaSuplentes.tipos.Posicion;
import problemaSuplentes.tipos.SolucionSupl;
import problemaSuplentes.tipos.SolucionSuplImpl;

public class ProblemaSuplentesAG implements ValuesInRangeProblemAG<Integer, SolucionSupl> {

	private static List<Jugador> jugadores;
	private static Integer S;
	private static Integer M;
	
	public ProblemaSuplentesAG(List<Jugador> jugadores, Integer S, Integer M) {
		ProblemaSuplentesAG.jugadores = jugadores;
		ProblemaSuplentesAG.S = S;
		ProblemaSuplentesAG.M = M;
	}
	
	public static SolucionSupl resolver(List<Jugador> jugadores, Integer S,
			Integer M) {
		
		AlgoritmoAG.ELITISM_RATE  = 0.3;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 100;
		
		StoppingConditionFactory.NUM_GENERATIONS = 10000;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
		StoppingConditionFactory.FITNESS_MIN = 10000.;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.SolutionsNumber;
		
		ProblemaSuplentesAG p = new ProblemaSuplentesAG(jugadores, S, M);
		AlgoritmoAG ap = Algoritmos.createAG(ChromosomeType.Binary, p);
		ap.ejecuta();
		
		ValuesInRangeChromosome<Integer> cr = ChromosomeFactory.asValuesInRange(ap.getBestFinal());
		
		return p.getSolucion(cr);
	}
	
	public SolucionSupl getSolucion(ValuesInRangeChromosome<Integer> chromosome) {
		List<Integer> ls = chromosome.decode();
		Map<Integer, Jugador> m = Maps.newHashMap();
		for(int i = 0; i<ls.size(); i++) {
			if(ls.get(i).equals(1)) {
				m.put(i, ProblemaSuplentesAG.jugadores.get(i));
			}
		}
		return new SolucionSuplImpl(m, ProblemaSuplentesAG.jugadores, ProblemaSuplentesAG.S, ProblemaSuplentesAG.M,
				this.fitnessFunction(chromosome));
	}
	
	public List<Jugador> getSolucionAux(ValuesInRangeChromosome<Integer> chromosome) {
		List<Integer> ls = chromosome.decode();
		List<Jugador> res = Lists.newArrayList();
		for(int i = 0; i<ls.size(); i++) {
			if(ls.get(i).equals(1)) {
				Jugador j = ProblemaSuplentesAG.jugadores.get(i);
				res.add(j);
			}
		}
		return res;
	}

	public Integer getVariableNumber() {
		return ProblemaSuplentesAG.jugadores.size();
	}
	
    public Integer getMax(Integer index){
		return ProblemaSuplentesAG.jugadores.size()-1;	
	}

    public Integer getMin(Integer index){ 
		return 0;
	}

	public Double fitnessFunction(ValuesInRangeChromosome<Integer> ls) {
		List<Jugador> solucion = this.getSolucionAux(ls);
		
		double V = solucion.stream().mapToDouble(x->x.getTirosCortos() + x.getTirosLargos()).sum();
		
		double K = solucion.stream().mapToDouble(x->Math.pow(x.getCache(), 2)).sum();
		
		double R0 = solucion.size();
		
		double R1 = solucion.stream().filter(x->x.getPos1().equals(Posicion.Pivot) ||
				x.getPos2().equals(Posicion.Pivot)).count();
		
		double R2 = solucion.stream().filter(x->x.getPos1().equals(Posicion.Alero) ||
				x.getPos2().equals(Posicion.Alero)).count();
		
		double R3 = solucion.stream().mapToDouble(x->x.getCache()).sum();
		
		double R4 = solucion.stream().filter(x->x.getPos1().equals(Posicion.Base) ||
				x.getPos2().equals(Posicion.Base)).count();
		
		double var0 = (R0==S)?0:1;
		double var1 = (R1>=2)?0:1;
		double var2 = (R2>=3)?0:1;
		double var3 = (R3<=M)?0:1;
		double var4 = (R4==1)?0:1;
		
		double fitness = V - K*(var0 + var1 + var2 + var3 + var4);
		
		return fitness;
	}

}
