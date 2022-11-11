/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed_opt;

/**
 *
 * @author MTRA. BETANIA
 */
public class ordenamiento {

    public ordenamiento() {

    }

    public double [][] ordenarP(double[][] indv, int numVariables) {
         
        double[] aux = new double[numVariables + 2];
        
        for (int i = 0; i < indv.length; i++) {
            
            for (int j = 0; j < (indv.length-1); j++) {
                if ((indv[j][numVariables + 1] == 0) && (indv[j + 1][ numVariables + 1] == 0)) {
                    if (indv[j][numVariables] > indv[j + 1][numVariables]) {
                        for (int k = 0; k < indv[0].length; k++) {
                            aux[k] = indv[j][k];
                            indv[j][k] = indv[j + 1][k];
                            indv[j + 1][k] = aux[k];
                        }
                    }
                }
                if ((indv[j][numVariables + 1] > 0) && (indv[j + 1][numVariables + 1] > 0)) {
                    if (indv[j][numVariables+1] > indv[j + 1][numVariables+1]) {
                        for (int k = 0; k < indv[0].length; k++) {
                            aux[k] = indv[j][k];
                            indv[j][k] = indv[j + 1][k];
                            indv[j + 1][k] = aux[k];
                        }
                    }
                }
                if ((indv[j][numVariables + 1]) > 0 && (indv[j + 1][numVariables + 1] == 0)) {
                    for (int k = 0; k < indv[0].length; k++) {
                        aux[k] = indv[j][k];
                        indv[j][k] = indv[j + 1][k];
                        indv[j + 1][k] = aux[k];
                    }
                }

            }
        }
        
        return indv;
    }

}
