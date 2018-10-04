package problemaSuplentes.tipos;

public class JugadorImpl implements Jugador {
	
	private String nombre;
	private Posicion pos1;
	private Posicion pos2;
	private Integer cache;
	private String nacion;
	private Integer minJugados;
	private Integer tirosCortos;
	private Integer tirosLargos;
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cache == null) ? 0 : cache.hashCode());
		result = prime * result + ((minJugados == null) ? 0 : minJugados.hashCode());
		result = prime * result + ((nacion == null) ? 0 : nacion.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((pos1 == null) ? 0 : pos1.hashCode());
		result = prime * result + ((pos2 == null) ? 0 : pos2.hashCode());
		result = prime * result + ((tirosCortos == null) ? 0 : tirosCortos.hashCode());
		result = prime * result + ((tirosLargos == null) ? 0 : tirosLargos.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JugadorImpl other = (JugadorImpl) obj;
		if (cache == null) {
			if (other.cache != null)
				return false;
		} else if (!cache.equals(other.cache))
			return false;
		if (minJugados == null) {
			if (other.minJugados != null)
				return false;
		} else if (!minJugados.equals(other.minJugados))
			return false;
		if (nacion == null) {
			if (other.nacion != null)
				return false;
		} else if (!nacion.equals(other.nacion))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (pos1 != other.pos1)
			return false;
		if (pos2 != other.pos2)
			return false;
		if (tirosCortos == null) {
			if (other.tirosCortos != null)
				return false;
		} else if (!tirosCortos.equals(other.tirosCortos))
			return false;
		if (tirosLargos == null) {
			if (other.tirosLargos != null)
				return false;
		} else if (!tirosLargos.equals(other.tirosLargos))
			return false;
		return true;
	}

	public String toString() {
		return "(" + nombre + "), Posiciones: " + pos1 + " y " + pos2;
	}

	public JugadorImpl(Posicion pos1, Posicion pos2,
			Integer cache, Integer tirosCortos,
			Integer tirosLargos) {
		this.nombre = null;
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.cache = cache;
		this.nacion = null;
		this.minJugados = null;
		this.tirosCortos = tirosCortos;
		this.tirosLargos = tirosLargos;
		
	}
		
	public JugadorImpl(String nombre, Posicion pos1, Posicion pos2,
			Integer cache, String nacion, Integer minJugados, 
			Integer tirosCortos, Integer tirosLargos) {
		this.nombre = nombre;
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.cache = cache;
		this.nacion = nacion;
		this.minJugados = minJugados;
		this.tirosCortos = tirosCortos;
		this.tirosLargos = tirosLargos;
		
	}
	
	public JugadorImpl(String s) {
		String[] trozos = s.split("#");
		
		this.nombre = trozos[0].trim();
		this.pos1 = Posicion.valueOf(trozos[1].trim());
		this.pos2 = Posicion.valueOf(trozos[2].trim());
		this.cache = new Integer(trozos[3].trim());
		this.nacion = trozos[4].trim();
		this.minJugados = new Integer(trozos[5].trim());
		this.tirosCortos = new Integer(trozos[6].trim());
		this.tirosLargos = new Integer(trozos[7].trim());
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Posicion getPos1() {
		return this.pos1;
	}

	public void setPos1(Posicion pos1) {
		this.pos1 = pos1;
	}

	public Posicion getPos2() {
		return this.pos2;
	}

	public void setPos2(Posicion pos2) {
		this.pos2 = pos2;
	}

	public Integer getCache() {
		return this.cache;
	}

	public void setCache(Integer cache) {
		this.cache = cache;
	}

	public String getNacion() {
		return this.nacion;
	}

	public void setNacion(String nacion) {
		this.nacion = nacion;
	}

	public Integer getMinJugados() {
		return this.minJugados;
	}

	public void setMinJugados(Integer minJugados) {
		this.minJugados = minJugados;
	}

	public Integer getTirosCortos() {
		return this.tirosCortos;
	}

	public void setTirosCortos(Integer tirosCortos) {
		this.tirosCortos = tirosCortos;
	}

	public Integer getTirosLargos() {
		return this.tirosLargos;
	}

	public void setTirosLargos(Integer tirosLargos) {
		this.tirosLargos = tirosLargos;
	}

}
