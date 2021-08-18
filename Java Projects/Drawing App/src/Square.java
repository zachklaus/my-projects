
public class Square extends PolygonPrimitive {
	
	public Square(int x, int y, int size) {
		
		super();
		
		// top left
		int x0 = x;
		int y0 = y;
		
		// top right
		int x1 = x + size;
		int y1 = y;
		
		// bottom right
		int x2 = x + size;
		int y2 = y + size;
		
		// bottom left
		int x3 = x;
		int y3 = y + size;
		
		this.xPoints = new int[4];
		this.yPoints = new int[4];
		
		this.xPoints[0] = x0;
		this.xPoints[1] = x1;
		this.xPoints[2] = x2;
		this.xPoints[3] = x3;
		this.yPoints[0] = y0;
		this.yPoints[1] = y1;
		this.yPoints[2] = y2;
		this.yPoints[3] = y3;
			
	}
	
}
