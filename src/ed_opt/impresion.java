
package ed_opt;

/**
 *
 * @author betania
 */
public class impresion {
    
    public impresion(){
        
    }
    
    public void imprimirPob(double [][]matrizInd, int var){
          System.out.println("               x1                   x2                  x3              f.obj            SVR");
        for (int f = 0; f < matrizInd.length; f++) {
            System.out.print("ind " + (f ));
            for (int c = 0; c <var+2; c++) {
                System.out.print("  " + matrizInd[f][c] + " ");
            }
            System.out.println("");
    }
    }
    
    public void mejor(double [][]matrizInd, int var){
          System.out.println("               x1                   x2                  x3              f.obj            SVR");
      
           
            for (int c = 0; c <var+2; c++) {
                System.out.print("  " + matrizInd[0][c] + " ");
            }
        System.out.println("");
    }
    
}
