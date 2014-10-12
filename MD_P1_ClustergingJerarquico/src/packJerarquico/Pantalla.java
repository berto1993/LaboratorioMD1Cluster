package packJerarquico;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Color;

class Pantalla extends Frame {

public Pantalla() {
this.setSize(200,150 );
this.setVisible( true );
}

public void paint(Graphics g) {
g.setColor(Color.RED);
g.drawLine(50, 50, 100, 50);
}

public static void main( String[] args ) {
Pantalla p = new Pantalla();
}

}
