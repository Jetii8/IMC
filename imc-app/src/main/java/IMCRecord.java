import java.time.LocalDateTime;

public class IMCRecord {
    private int weight;
    private int height;
    private double imc;
    private LocalDateTime createdOn;

    // Constructor, getters, and setters
    public IMCRecord(int weight, int height, double imc) {
        this.weight = weight;
        this.height = height;
        this.imc = imc;
        this.createdOn = LocalDateTime.now();
    }

    // Getters
    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public double getImc() {
        return imc;
    }

    public String getCreatedOn() {
        return createdOn.toString();
    }
}
