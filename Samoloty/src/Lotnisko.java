import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Lotnisko extends Thread {

	private Image pusteLotniskoIkona = new ImageIcon(getClass().getResource("/images/emptyAirport.png")).getImage();
	private Image mark = new ImageIcon(getClass().getResource("/images/mark.png")).getImage();
	private Pozycja pozycjaLotniska;
	private boolean potrzebujeSamolotu;
	private boolean leciDoNiegoSamolot;
	private boolean jestNaNimSamolot;
	private int index;

	public Lotnisko(Pozycja pozycjaLotniska) {
		this.pozycjaLotniska = pozycjaLotniska;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if (!potrzebujeSamolotu) {
				Random random = new Random();
				int losowa = random.nextInt(10000) + 3000;
				try {
					Thread.sleep(losowa);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				potrzebujeSamolotu = true;
			}
		}
	}
	
	public Image getIcon() {
		if (potrzebujeSamolotu) {
			return pusteLotniskoIkona;
		} else {
			return pusteLotniskoIkona;
		}
	}
	
	public Image getMarkIcon(){
		if (potrzebujeSamolotu) {
			return mark;
		}else{
			return null;
		}
	}

	public Pozycja getPozycjaLotniska() {
		return pozycjaLotniska;
	}

	public boolean isPotrzebujeSamolotu() {
		return potrzebujeSamolotu;
	}

	public void setPotrzebujeSamolotu(boolean potrzebujeSamolotu) {
		this.potrzebujeSamolotu = potrzebujeSamolotu;
	}

	public boolean isLeciDoNiegoSamolot() {
		return leciDoNiegoSamolot;
	}

	public void setLeciDoNiegoSamolot(boolean leciDoNiegoSamolot) {
		this.leciDoNiegoSamolot = leciDoNiegoSamolot;
	}

	public boolean isJestNaNimSamolot() {
		return jestNaNimSamolot;
	}

	public void setJestNaNimSamolot(boolean jestNaNimSamolot) {
		this.jestNaNimSamolot = jestNaNimSamolot;
	}

	public synchronized int getIndex() {
		return index;
	}

	public synchronized void setIndex(int index) {
		this.index = index;
	}
	
}
