/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed_opt;

import java.util.Random;

/**
 *
 * @author Betania Hdez-Ocaña. 
 * Problema del resorte de tension/compresion
 * la mejor solución conocida en el estado del arte f(x)*=0.012665
 */
public class ED_OPT {

    public static void main(String[] args) {
        Random rnd = new Random(System.currentTimeMillis());
        impresion imp = new impresion();
        evaluacionFO fobj = new evaluacionFO();
        poblacionInicial pi= new poblacionInicial();
        ordenamiento ord= new ordenamiento();
         
       
        int poblacion = 50;   ///calibrar por el usuario final
        int var = 3;  //variables del problema x1, x2 y x3
        int numRestricD = 4;  //restricciones de desigualdad
        int numRestricI = 0;  //no hay restricciones de igualdad
        double [][] rango = {{0.05, 2}, {0.25, 1.3}, {2, 15}};  /// RANGO DE VARIABLES
        double[][] matrizInd = new double[poblacion][var + 2];
        double[][] copia = new double[poblacion][var + 2];
        double F = 0.8; // valores a ajustar para ed MUTACION ///calibrar por el usuario final
        double CR = 0.7; //VALORES A AJUSTRA CRUZA ///calibrar por el usuario final
        int GMAX = 400;  //condicional de paro del algoritmo ///calibrar por el usuario final
                        //gmax [50, 1000]
        //int Evals = 40;  //condicional de paro del algoritmo
        int contEvals = 0;   //contador de la evaluaciones (individuo evaluado en la f.obj
        double [] convergencia=new double[GMAX];
        int iteraciones=10; //[ 10, 30]
 
        for (int iter=0; iter<=iteraciones; iter++){
        
        pi.crearPob(poblacion, var, matrizInd, rango);
System.out.println("Poblacion Inicial aleatoria");
        imp.imprimirPob(matrizInd, var);
        //evaluar la población en la funcion objetivo
        fobj.evaluacionFyR(matrizInd, numRestricD, numRestricI, var);
        contEvals=contEvals+poblacion;
        //imprimir matriz de poblacion inicial

System.out.println("Poblacion Inicial aleatoria evaluada");
        imp.imprimirPob(matrizInd, var);
        
        //el condicional de paro evaluacion o generaciones
        for (int g = 0; g < GMAX; g++) { //por generaciones ..condición de paro
        System.out.println("Generación "+ (g+1));
     //   while (contEvals < Evals) { //por evaluaciones...condición de paro
            for (int p = 0; p < poblacion; p++) {

                //selección de los 3 individuos aleatorios por cada ind de la población
                int ind1, ind2, ind3;
                ind1 = (int) rnd.nextInt(poblacion);
                ind2 = (int) rnd.nextInt(poblacion);
                ind3 = (int) rnd.nextInt(poblacion);

                while (ind1 == p) {
                    ind1 = rnd.nextInt(poblacion);
                }

                while (ind2 == ind1 || ind2 == p) {
                    ind2 = rnd.nextInt(poblacion);
                }

                while (ind3 == ind2 || ind3 == ind1 || ind3 == p) {
                    ind3 = rnd.nextInt(poblacion);
                }
                
                double[][] hijo = new double[1][var + 2];
                 
               // int jrand= (int)(Math.random()*var+1);
                double aleatorio;
                for (int i = 0; i < var; i++) {
                    aleatorio = Math.random();
                    if (aleatorio < CR) { //cruza
                         hijo[0][i] = matrizInd[ind1][i]+( F * (matrizInd[ind2][i] - matrizInd[ind3][i])); //mutación 
                         if (hijo[0][i]<rango[i][0] || hijo[0][i]>rango[i][1] ){
                              hijo[0][i] = matrizInd[p][i];
                         }
                    }else
                        hijo[0][i] = matrizInd[p][i];
                }
              
                //evaluar al hijo en la funcion objetivo
                fobj.evaluacionFyR(hijo,numRestricD, numRestricI, var);  
                contEvals++;
                
                //comparando hijo vs padre y reemplazar en la poblacion
                if (hijo[0][var+1]==0 && matrizInd[p][var+1]==0) {
                    if(hijo[0][var] <= matrizInd[p][var]){
                      //  System.out.println("si entro");
                    for (int i = 0; i < var+2; i++) {
                        matrizInd[p][i] = hijo[0][i];
                    }
                }
                }
                if (hijo[0][var+1]>0 && matrizInd[p][var+1]>0) {
                    if(hijo[0][var] <= matrizInd[p][var]){
                      //  System.out.println("si entro");
                    for (int i = 0; i < var+2; i++) {
                        matrizInd[p][i] = hijo[0][i];
                    }
                }
                }
                if (hijo[0][var+1]==0 && matrizInd[p][var+1]>0) {
                  //  System.out.println("si entro");
                    for (int i = 0; i < var+2; i++) {
                        matrizInd[p][i] = hijo[0][i];
                    }
                }
                
              }
            
            copia=ord.ordenarP(matrizInd, var); //esto es opcional, solo para ordenar a los ind en cada generación
            System.out.println("Mejor solución de la generación");
            imp.mejor(copia, var);
            //guardando valor de mejor ind por generacion para la grafica de convergencia
            convergencia[g]=copia[0][var];
               
        } //aqui termina ED
        
        
        } //aqui termina las iteraciones
        
        System.out.println("contadorEvaluaciones  " + contEvals);
        
        System.out.println("Población finalll ordenada");
        ord.ordenarP(matrizInd, var);
        imp.imprimirPob(matrizInd, var);
        
        System.out.println("Impresión de datos para gráfico de convergencia");
        for(int y=0;y<GMAX; y++){
            System.out.println(convergencia[y]);
        }
    }

}
