package problemaSuplentes.tipos;

public interface Jugador {
	String getNombre();
	void setNombre(String nombre);
	Posicion getPos1();
	void setPos1(Posicion pos1);
	Posicion getPos2();
	void setPos2(Posicion pos2);
	Integer getCache();
	void setCache(Integer cache);
	String getNacion();
	void setNacion(String nacion);
	Integer getMinJugados();
	void setMinJugados(Integer minJugados);
	Integer getTirosCortos();
	void setTirosCortos(Integer tirosCortos);
	Integer getTirosLargos();
	void setTirosLargos(Integer tirosLargos);
	
}
