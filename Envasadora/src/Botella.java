public class Botella {

    private int numeroDeBotella = 0;
    private String colorDeBotella;

    public Botella(int numeroDeBotella, String colorDeBotella) {
        this.numeroDeBotella = numeroDeBotella;
        this.colorDeBotella = colorDeBotella;
    }

    public Botella() {
    }

    public int getNumeroDeBotella() {
        return numeroDeBotella;
    }

    public void setNumeroDeBotella(int numeroDeBotella) {
        this.numeroDeBotella = numeroDeBotella;
    }

    public String getColorDeBotella() {
        return colorDeBotella;
    }

    public void setColorDeBotella(String colorDeBotella) {
        this.colorDeBotella = colorDeBotella;
    }
}
