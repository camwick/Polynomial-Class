public class Polynomial implements Cloneable{
    private double[] poly;
    
    public Polynomial(){
        poly = new double[]{0};
    }

    public Polynomial(double a0){
    	poly = new double[]{a0};
    }

    public Polynomial(Polynomial p){
    	poly = new double[p.poly.length];

    	for(int i = 0; i < p.poly.length; i++){
    		poly[i] = p.poly[i];
    	}
    }

    public void add_to_coef(double amount, int exponent){

    }

    public static void main(String[] args){
    	Polynomial p1 = new Polynomial();

    	System.out.println(p1);
    }
}
