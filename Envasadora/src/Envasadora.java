import java.util.Stack;

public class Envasadora {

   private Stack<Botella> stack = new Stack<Botella>();

    public Envasadora(Stack<Botella> stack) {
        this.stack = stack;
    }

    public Envasadora() {
    }


    public Stack<Botella> getStack() {
        return stack;
    }

    public void setStack(Stack<Botella> stack) {
        this.stack = stack;
    }


    Botella botella1 = new Botella();
    Botella botella2 = new Botella();
    Botella botella3 = new Botella();
    Botella botella4 = new Botella();
    Botella botella5 = new Botella();
    Botella botella6 = new Botella();





    public void crearBotellas() {

        botella1.setNumeroDeBotella(1);
        botella1.setColorDeBotella("Rojo");

        botella2.setNumeroDeBotella(2);
        botella2.setColorDeBotella("Negro");

        botella3.setNumeroDeBotella(3);
        botella3.setColorDeBotella("Azul");

        botella4.setNumeroDeBotella(4);
        botella4.setColorDeBotella("Violeta");

        botella5.setNumeroDeBotella(5);
        botella5.setColorDeBotella("Verde");

        botella6.setNumeroDeBotella(6);
        botella6.setColorDeBotella("Amarillo");

    }

public void agregarBotellasPila(){

        stack.push(botella6);
        stack.push(botella3);
        stack.push(botella2);
        stack.push(botella4);
        stack.push(botella5);
        stack.push(botella1);

}





}
