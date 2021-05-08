package CoordinatorPackage.containers;

/**
 * This class provides the basic functionalities to break down robotFootprint into ints and longs for gRPC communication
 */
public class MakeFootPrint {
	private int centerX;
    private int centerY;
    private int minVerts;
    private int maxVerts;
    private double minRadius;
    private double maxRadius;

    public MakeFootPrint(int centerX, int centerY, int minVerts, int maxVerts, double minRadius, double maxRadius) {
        this.setCenterX(centerX);
        this.setCenterY(centerY);
        this.setMinVerts(minVerts);
        this.setMaxVerts(maxVerts);
        this.setMinRadius(minRadius);
        this.setMaxRadius(maxRadius);
    }

    public int getCenterX() { return centerX; }
    public int getCenterY() { return centerY; }
    public int getMinVerts() { return minVerts; }
    public int getMaxVerts() { return maxVerts; }
    public double getMinRadius() { return minRadius; }
    public double getMaxRadius() { return maxRadius; }


    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setMinVerts(int minVerts) {
        this.minVerts = minVerts;
    }

    public void setMaxVerts(int maxVerts) {
        this.maxVerts = maxVerts;
    }

    public void setMinRadius(double minRadius) {
        this.minRadius = minRadius;
    }

    public void setMaxRadius(double maxRadius) {
        this.maxRadius = maxRadius;
    }
}
