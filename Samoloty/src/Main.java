
public class Main {

	public static void main(String[] args) {
		Mapa mapa = new Mapa(1000,1000);
		ListaLotnisk listaLotnisk = new ListaLotnisk(3, mapa);
		ListaSamolotow listaSamolotow = new ListaSamolotow(30, mapa, listaLotnisk); // zmiana
		new SwingAnimation(mapa, listaLotnisk, listaSamolotow); // zmiana 2
	}
}
