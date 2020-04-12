import java.util.ArrayList;
import java.util.Random;

public class JuegoVida {
    private static final String VIDA = "#";
    private static final String MUERTE = ".";
    
    public static void main(String... args) {        
        String[][] cuadricula = generarCuadricula(10,10);        
        int fase = 0;   
        int Norte, Sur, Este, Oeste;
        while(fase<11) {
            limpiarPantalla();
            imprimirCuadricula(cuadricula);           
            System.out.println("Fase " + (fase++) + "");              
            String[][] nuevacuadricula = copiarCuadricula(cuadricula); 
                for (int i = 0; i < cuadricula.length; i++) {
                for (int j = 0; j < cuadricula[i].length; j++) {
                     int encuentra_vida = 0;
                     ArrayList<String> vecino = new ArrayList<String>();
                    // encontrando celdas vecinas                    
                    Norte=j+1;
                    Sur=j-1;
                    Este=i+1;
                    Oeste=i-1;    
                    //se guardan en vecino los valores obtenidos de alrededor
                    vecino.add(cuadricula[i][Math.floorMod(Norte, cuadricula[i].length)]);//NORTE
                    vecino.add(cuadricula[i][Math.floorMod(Sur, cuadricula[i].length)]);//SUR
                    vecino.add(cuadricula[Math.floorMod(Este, cuadricula.length)][j]);//ESTE
                    vecino.add(cuadricula[Math.floorMod(Oeste, cuadricula.length)][j]);//OESTE
                    vecino.add(cuadricula[Math.floorMod(Oeste, cuadricula.length)][Math.floorMod(Norte, cuadricula[i].length)]);//NOROESTE
                    vecino.add(cuadricula[Math.floorMod(Oeste, cuadricula.length)][Math.floorMod(Sur, cuadricula[i].length)]);//SUROESTE
                    vecino.add(cuadricula[Math.floorMod(Este, cuadricula.length)][Math.floorMod(Sur, cuadricula[i].length)]);//SURESTE
                    vecino.add(cuadricula[Math.floorMod(Este, cuadricula.length)][Math.floorMod(Norte, cuadricula[i].length)]);//NORESTE
                   //se comprueba vecino y segun los valores se genera vida o no
                    for (int k = 0; k < vecino.size(); k++)
                        if (vecino.get(k).compareTo(VIDA) == 0)
                            encuentra_vida += 1;
    
                    if (cuadricula[i][j].compareTo(MUERTE) == 0) {
                        // se comprueba si se puede generar una nueva celda
                        if (encuentra_vida == 3) {
                            nuevacuadricula[i][j] = VIDA;
                        }
                    } else { 
                        // se muere por superpoblacion o porque no hay vida
                        if (encuentra_vida < 2 || encuentra_vida > 3) {
                            nuevacuadricula[i][j] = MUERTE;
                        }
                    }
                }
            }
            
            // generacion de la nueva cuadricula            
            cuadricula = copiarCuadricula(nuevacuadricula);
            
            // tiempo de espera para una mejor visualizacion
            try {
                Thread.sleep(500);
           } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            
        }

    }
        
    private static void limpiarPantalla() {
        for(int i = 0; i < 100; i++)
            System.out.println();
    }

    public static void imprimirCuadricula(String[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(" " + matriz[i][j] + " ");
            }
            System.out.println();
        }
    }    
    
    private static String[][] copiarCuadricula(String[][] cuadricula) {
        String[][] auxcuadricula = new String[cuadricula.length][cuadricula[0].length];

        for (int i = 0; i < cuadricula.length; i++) {
            for (int j = 0; j < cuadricula[i].length; j++) {
                auxcuadricula[i][j] = cuadricula[i][j];
            }
        }

        return auxcuadricula;
    }

    
    public static String[][] generarCuadricula(int ancho, int alto) {
        String[][] randoncuadricula = new String[alto][ancho];
        Random aleatorio = new Random();

        for (int i = 0; i < randoncuadricula.length; i++) {
            for (int j = 0; j < randoncuadricula[i].length; j++) {
                Boolean x = aleatorio.nextBoolean();

                if (x)
                    randoncuadricula[i][j] = VIDA;
                else
                    randoncuadricula[i][j] = MUERTE;
            }
        }

        return randoncuadricula;
    }

}
