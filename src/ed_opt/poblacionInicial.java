/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed_opt;

import java.util.Random;

/**
 *
 * @author betania
 */
public class poblacionInicial {
    public poblacionInicial(){
        
    }
    
    public void crearPob(double poblacion, int var, double[][] matrizInd, double [][]rango){
         Random rnd = new Random(System.currentTimeMillis());
         for (int f = 0; f < poblacion; f++) {
            for (int c = 0; c < var; c++) {
                matrizInd[f][c] = (Math.random()*(rango[c][1] - rango[c][0]))+rango[c][0];
                        

            }
        }
    }
    
}
