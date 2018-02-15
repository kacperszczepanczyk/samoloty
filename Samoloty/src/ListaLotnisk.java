import java.util.ArrayList;
import java.util.Random;

public class ListaLotnisk {
	private ArrayList<Lotnisko> lotniska;

	public ListaLotnisk(int ilosc, Mapa mapa) {
		lotniska = new ArrayList<Lotnisko>(ilosc);
	}
	
	public int getIloscLotnisk(){
		return lotniska.size();
	}
	
	public Lotnisko getLotniskoZIndexu(int index){
		return lotniska.get(index);
	}

	public boolean czyNaPozycjiZnajdujeSieLotnisko(Pozycja pozycja) {
		for (int i = 0; i < lotniska.size(); i++) {
			if (lotniska.get(i).getPozycjaLotniska().getX() == pozycja.getX()
					&& lotniska.get(i).getPozycjaLotniska().getY() == pozycja.getY()) {
				return true;
			}
		}
		return false;
	}

	public Lotnisko wezLotniskoZPozycji(Pozycja pozycja) {
		for (int i = 0; i < lotniska.size(); i++) {
			if (lotniska.get(i).getPozycjaLotniska().getX() == pozycja.getX()
					&& lotniska.get(i).getPozycjaLotniska().getY() == pozycja.getY()) {
				return lotniska.get(i);
			}
		}
		return null;
	}

	public synchronized Lotnisko znajdzPotrzebujaceLotnisko() {
		for (int i = 0; i < lotniska.size(); i++) {
			if (lotniska.get(i).isPotrzebujeSamolotu() && !lotniska.get(i).isLeciDoNiegoSamolot()) {
				lotniska.get(i).setLeciDoNiegoSamolot(true);
				return lotniska.get(i);
			}
		}
		return new Lotnisko(new Pozycja(0, 0));
	}
	
	public Lotnisko znajdzLosoweLotnisko(int ignorowanyIndex){
		Random random = new Random();
		int losowa = random.nextInt(lotniska.size());
		while (losowa == ignorowanyIndex){
			losowa = random.nextInt(lotniska.size());
		}
		return lotniska.get(losowa);
	}
	
	public void add(Lotnisko lotnisko){
		lotniska.add(lotnisko);
	}	
}
