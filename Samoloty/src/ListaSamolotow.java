import java.util.ArrayList;

public class ListaSamolotow {
	private ArrayList<Samolot> samoloty;

	public ListaSamolotow(int ilosc, Mapa mapa, ListaLotnisk listaLotnisk) {
		samoloty = new ArrayList<Samolot>(ilosc);
	}

	public int getIloscSamolotow() {
		return samoloty.size();
	}
	
	public void add(Samolot samolot){
		samoloty.add(samolot);
	}
	
	public Samolot getSamolotZIndexu(int index){
		return samoloty.get(index);
	}
}
