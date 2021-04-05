package Launch;

public class MakeFootPrint {


    int centerX;
    int centerY;
    int minVerts;
    int maxVerts;
    double minRadius;
    double maxRadius;

    public int getCenterX() { return centerX; }
    public int getCenterY() { return centerY; }
    public int getMinVerts() { return minVerts; }
    public int getMaxVerts() { return maxVerts; }
    public double getMinRadius() { return minRadius; }
    public double getMaxRadius() { return maxRadius; }

    public MakeFootPrint(int centerX, int centerY, int minVerts, int maxVerts, double minRadius, double maxRadius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.minVerts = minVerts;
        this.maxVerts = maxVerts;
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
    }

}
