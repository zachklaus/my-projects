
public class RoundPrimitive extends Primitive {

	public int x, y, width, height;
	
	public void draw(UserInterface ui) {
		
		if (isFilled){
			ui.fillColor(color);
		}
		else{
			ui.lineColor(color);
		}
		ui.drawOval(x, y, width, height, isFilled);
	}
		
}
	

