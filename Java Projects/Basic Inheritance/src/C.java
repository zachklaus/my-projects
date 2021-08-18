
public class C extends B {

	public double z;
	
	public C(double x, double y, double z) {
		super(x, y);
		this.z = z;
	}
	
	public double sum() {
		return super.sum() + z;
	}
	
	public double product() {
		return super.product() * z;
	}
	
	public double power() {
		return Math.pow(super.power(), z);
	}
	
}
