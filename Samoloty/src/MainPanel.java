
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ListaLotnisk listaLotnisk;
	private ListaSamolotow listaSamolotow;

	public MainPanel(ListaLotnisk listaLotnisk, ListaSamolotow listaSamolotow) {
		this.listaLotnisk = listaLotnisk;
		this.listaSamolotow = listaSamolotow;
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Lotnisko lotnisko = new Lotnisko(new Pozycja(e.getX(), e.getY()));
				lotnisko.setIndex(listaLotnisk.getIloscLotnisk());
				lotnisko.start();
				listaLotnisk.add(lotnisko);
			}
		});
		Button dodajSamolot = new Button("Dodaj samolot");
		dodajSamolot.setEnabled(true);
		dodajSamolot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Samolot samolot = new Samolot(new Pozycja(0, 0), listaLotnisk);
				samolot.start();
				listaSamolotow.add(samolot);
			}
		});
		add(dodajSamolot);
		setDoubleBuffered(true);
		new Timer(10, paintTimer).start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		setBackground(new Color(255, 255, 255));
		g2d.drawString("Ilosc lotnisk: " + listaLotnisk.getIloscLotnisk(), 870, 10);
		g2d.drawString("Ilosc samolotow: " + (listaSamolotow.getIloscSamolotow() - Samolot.niezyweSamoloty), 870, 30);

		for (int i = 0; i < listaLotnisk.getIloscLotnisk(); i++) {
			g2d.drawImage(listaLotnisk.getLotniskoZIndexu(i).getIcon(),
					(int) listaLotnisk.getLotniskoZIndexu(i).getPozycjaLotniska().getX(),
					(int) listaLotnisk.getLotniskoZIndexu(i).getPozycjaLotniska().getY(), this);
			g2d.drawImage(listaLotnisk.getLotniskoZIndexu(i).getMarkIcon(),
					(int) listaLotnisk.getLotniskoZIndexu(i).getPozycjaLotniska().getX() + 2,
					(int) listaLotnisk.getLotniskoZIndexu(i).getPozycjaLotniska().getY() - 20, this);
			g2d.drawString(Integer.toString(listaLotnisk.getLotniskoZIndexu(i).getIndex() + 1),
					(int) listaLotnisk.getLotniskoZIndexu(i).getPozycjaLotniska().getX() + 4,
					(int) listaLotnisk.getLotniskoZIndexu(i).getPozycjaLotniska().getY() + 11);
		}
		for (int i = 0; i < listaSamolotow.getIloscSamolotow(); i++) {
			g2d.drawString(listaSamolotow.getSamolotZIndexu(i).getBakPaliwa(),
					(int) listaSamolotow.getSamolotZIndexu(i).getPozycjaSamolotu().getX() - 2,
					(int) listaSamolotow.getSamolotZIndexu(i).getPozycjaSamolotu().getY() - 2);
			g2d.drawImage(listaSamolotow.getSamolotZIndexu(i).getIcon(),
					(int) listaSamolotow.getSamolotZIndexu(i).getPozycjaSamolotu().getX(),
					(int) listaSamolotow.getSamolotZIndexu(i).getPozycjaSamolotu().getY(), this);
		}
		g.dispose();
	}

	Action paintTimer = new AbstractAction() {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	};
}
