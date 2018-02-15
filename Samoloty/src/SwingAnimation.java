
import javax.swing.JFrame;

public class SwingAnimation extends JFrame{

	private static final long serialVersionUID = 1L;
	private MainPanel mPanel;

	public SwingAnimation(Mapa mapa, ListaLotnisk listaLotnisk, ListaSamolotow listaSamolotow){
		super("Samoloty");
		mPanel = new MainPanel(listaLotnisk, listaSamolotow);
		setSize(mapa.getRozmiarX(), mapa.getRozmiarY());
		setResizable(false);
		add(mPanel);
		setVisible(true);
	}
}
