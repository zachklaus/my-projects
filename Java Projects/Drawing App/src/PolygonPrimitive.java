
public class PolygonPrimitive extends Primitive {

	public int[] xPoints, yPoints;
	
	public void draw(UserInterface ui) {
		
		if (isFilled){
			ui.fillColor(color);
		}
		else {
			ui.lineColor(color);
		}
		ui.drawPolygon(xPoints, yPoints, isFilled);
		
	}
	
}
