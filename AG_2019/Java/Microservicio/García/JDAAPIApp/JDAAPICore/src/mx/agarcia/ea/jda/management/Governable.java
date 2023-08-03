package mx.agarcia.ea.jda.management;

/**
 * 
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public abstract class Governable implements IGovernable {
	protected boolean isActive;
	protected String name;

	public String getName() {
		return this.name;
	}

	public void setName(String pName) {
		this.name = pName;
	}
}
