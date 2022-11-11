
package ed_opt;

/**
 *
 * @author MTRA. BETANIA
 */
public class evaluacionFO {
    public evaluacionFO(){
        
    }
    public double[][] evaluacionFyR(double[][] indv,  int numRestricD, int numRestricI, int numVariables){
    double[] restriccionD = new double[numRestricD];
        double[] restriccionI = new double[numRestricI];
        double svr = 0;
        for (int i = 0; i < indv.length; i++) {
         svr = 0;
            indv[i][numVariables] = (indv[i][2] + 2) * indv[i][1] * (indv[i][0] * indv[i][0]);
            //restricciones de desigualdad en la primer columna de la matriz de restricciones
             restriccionD[0] = 1.0-((Math.pow(indv[i][1],3))*indv[i][2])/(71785.0*(Math.pow(indv[i][0],4)));  
            restriccionD[1] = (((4.0* Math.pow(indv[i][1],2))-(indv[i][0]*indv[i][1]))/(12566.0*((indv[i][1]*Math.pow(indv[i][0],3))-Math.pow(indv[i][0],4))))+(1.0/(5108.0* Math.pow(indv[i][0],2)))-1.0; 
            restriccionD[2] = 1.0-((140.45*indv[i][0])/((indv[i][1]*indv[i][1])*indv[i][2]) );
            restriccionD[3] = ((indv[i][1] + indv[i][0] )/ 1.5) - 1;
          
            if (numRestricD != 0) { //evaluando restricciones de desigualdad
                for (int r = 0; r < numRestricD; r++) {
                    svr = svr + Math.max(0, restriccionD[r]);
                }
            }
            if (numRestricI != 0) {//evaluando restricciones de igualdad
                for (int r = 0; r < numRestricI; r++) {
                    svr = svr + Math.max(0, Math.abs(restriccionI[r]));
                }
            }
            indv[i][numVariables+1]=svr;
        }
        return indv;
    }
    
}
