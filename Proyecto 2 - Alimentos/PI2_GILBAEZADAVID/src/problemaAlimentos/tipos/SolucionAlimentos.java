package problemaAlimentos.tipos;

import java.util.Map;
import us.lsi.bt.SolucionBT;

public interface SolucionAlimentos extends SolucionBT {

	Map<Integer, Integer> getSolucion();
	void addIngrediente(Integer index, Integer cantidad);
	Double getCosteTotal();
	void setCosteTotal(Double costeTotal);
	
}
