import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Samolot extends Thread {

	static int niezyweSamoloty = 0;
	private Image zaladowanyIkona = new ImageIcon(getClass().getResource("/images/loadedPlane.png")).getImage();
	private Image pustyIkona = new ImageIcon(getClass().getResource("/images/emptyPlane.png")).getImage();
	private Image niezywyIkona = new ImageIcon(getClass().getResource("/images/deadPlane.png")).getImage();
	private Pozycja pozycjaSamolotu, cel;
	private boolean zaladowany, leciDoLotniska, niezywy;
	private ListaLotnisk listaLotnisk;
	private int bakPaliwa, maxBak, indexLotniska;

	public Samolot(Pozycja pozycja, ListaLotnisk listaLotnisk) {
		this.pozycjaSamolotu = pozycja;
		this.listaLotnisk = listaLotnisk;
		Random random = new Random();
		maxBak = random.nextInt(1000) + 400;
		bakPaliwa = maxBak;
	}

	public void run() {
		while (true) {
			if (niezywy) {
				niezyweSamoloty++;
				break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			znajdzCel();
			if (celJestLotniskiem()) {
				leciDoLotniska = true;
			}
			zmianaPozycji(pozycjaSamolotu, cel);
		}
	}

	private boolean celJestLotniskiem() {
		return listaLotnisk.czyNaPozycjiZnajdujeSieLotnisko(cel);
	}

	private void znajdzCel() {
		if (!zaladowany) {
			Lotnisko lotnisko = listaLotnisk.znajdzPotrzebujaceLotnisko();
			cel = lotnisko.getPozycjaLotniska();
			indexLotniska = lotnisko.getIndex();
		} else {
			do {
				cel = listaLotnisk.znajdzLosoweLotnisko(indexLotniska).getPozycjaLotniska();
			} while (cel.equals(pozycjaSamolotu));
			leciDoLotniska = true;
		}
	}

	private void zmianaPozycji(Pozycja poczatkowa, Pozycja koncowa) {

		double steps = odlegloscOdCelu(poczatkowa, koncowa);
		double odlegloscX = Math.abs(poczatkowa.getX() - koncowa.getX());
		double odlegloscY = Math.abs(poczatkowa.getY() - koncowa.getY());

		for (int i = 0; i <= (int) steps; i++) {
			if (leciDoLotniska && !listaLotnisk.wezLotniskoZPozycji(koncowa).isJestNaNimSamolot()
					|| odlegloscOdCelu(pozycjaSamolotu, koncowa) > 30) {
				if (poczatkowa.getX() < koncowa.getX()) {
					pozycjaSamolotu.setX((pozycjaSamolotu.getX() + odlegloscX / steps));
				} else {
					pozycjaSamolotu.setX((pozycjaSamolotu.getX() - odlegloscX / steps));
				}

				if (poczatkowa.getY() < koncowa.getY()) {
					pozycjaSamolotu.setY((pozycjaSamolotu.getY() + odlegloscY / steps));
				} else {
					pozycjaSamolotu.setY((pozycjaSamolotu.getY() - odlegloscY / steps));
				}
			} else if (leciDoLotniska) {
				i--;
			}
			bakPaliwa--;
			if (bakPaliwa <= 0) {
				niezywy = true;
				listaLotnisk.wezLotniskoZPozycji(koncowa).setPotrzebujeSamolotu(true);
				listaLotnisk.wezLotniskoZPozycji(koncowa).setLeciDoNiegoSamolot(false);
				return;
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		bakPaliwa = maxBak;
		if (leciDoLotniska) {
			listaLotnisk.wezLotniskoZPozycji(koncowa).setJestNaNimSamolot(true);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			listaLotnisk.wezLotniskoZPozycji(koncowa).setJestNaNimSamolot(false);
			if (!zaladowany) {
				listaLotnisk.wezLotniskoZPozycji(koncowa).setPotrzebujeSamolotu(false);
				listaLotnisk.wezLotniskoZPozycji(koncowa).setLeciDoNiegoSamolot(false);
			}
			zaladowany = !zaladowany;
			leciDoLotniska = false;
		}
	}

	private double odlegloscOdCelu(Pozycja p1, Pozycja p2) {
		return Math.sqrt(Math.abs(p1.getX() - p2.getX()) * Math.abs(p1.getX() - p2.getX())
				+ Math.abs(p1.getY() - p2.getY()) * Math.abs(p1.getY() - p2.getY()));
	}

	public Image getIcon() {
		if (niezywy) {
			return niezywyIkona;
		}
		if (zaladowany) {
			return zaladowanyIkona;
		} else {
			return pustyIkona;
		}
	}

	public String getBakPaliwa() {
		return Integer.toString(bakPaliwa);
	}

	public Pozycja getPozycjaSamolotu() {
		return pozycjaSamolotu;
	}
}
