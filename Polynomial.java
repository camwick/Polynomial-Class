/**
	Adds basic creation and manipulation of polynomial functions.
	
	@author Cameron Wickersham
*/
public class Polynomial{
    private double[] poly;
    
    /**
		Default constructor that creates a polynomial of 0.
    */
    public Polynomial(){
        poly = new double[]{0};
    }

    /**
    	Constructor that creates a polynomial with a single x^0 term with coefficient <code>double a0</code>.
    	@param a0
    		a double that represents the coefficient of x^0
    */
    public Polynomial(double a0){
    	poly = new double[]{a0};
    }

    /**
		Constructor that creates a polynomial that is a copy of <code>Polynomial p</code>.
		@param p
			a Polynomial object 
    */
    public Polynomial(Polynomial p){
    	poly = new double[p.poly.length];

    	for(int i = 0; i < p.poly.length; i++)
    		poly[i] = p.poly[i];
    }

    /**
		Adds the given amount to the coefficient of the specified exponent.
		@param amount
			a double, representing the amount to be added to the coefficient
		@param exponent
			The exponent referencing which coefficient the amount should be added to. 
    */
    public void add_to_coef(double amount, int exponent){
    	if(poly.length - 1 < exponent){
    		double[] new_obj = new double[exponent + 5];

    		for(int i = 0; i < poly.length; i++)
    			new_obj[i] = poly[i];

    		poly = new_obj;
    	}

    	poly[exponent] += amount;
    }

    /**
		Sets the coefficient of the specified exponent to the coefficient given.
		@param coefficient
			a double, representing the number that is assigned to the exponent
		@param exponent
			The exponent referencing which coefficient to be changed.
    */
    public void assign_coef(double coefficient, int exponent){
    	if(poly.length - 1 < exponent){
    		double[] new_obj = new double[exponent + 5];

    		for(int i = 0; i < poly.length; i++)
    			new_obj[i] = poly[i];

    		poly = new_obj;
    	}

		poly[exponent] = coefficient;    	
    }

    /**
		Returns the coefficient of the specified exponent.
		@param exponent
			The exponent that specified the returning coefficient
		@return 
			a double representing a coefficient
    */
    public double coefficient(int exponent){
    	if(poly.length - 1 < exponent)
    		return 0;
    	else 
    		return poly[exponent];
    }

    /**
		Evaluates a polynomial by a given <code>double x</code>.
		@param x
			Specifies the <code>x parameter</code> to be plugged into the polynomial.
		@return
			a double that is calculated from the polynomial
    */
    public double eval(double x){
        // using Honer's Rule to evaluate the polynomial 
        double ans = 0;
        for(int i = poly.length - 1; i >= 0; i--)
            ans = poly[i] + (x * ans);
        return ans;
    }

    /**
		Overrides the default <code>toString</code> method to return the polynomial expression from the array.
    */
    public String toString(){
    	String expression = "";

    	// checks if polynomial is empty
    	if (poly.length == 1 && poly[0] == 0)
    		return expression + "0";
    	else {
	    	// finding first non-zero element -> allows me to skip unnecessary indexes
	    	int highestDeg = -1;
	    	for(int i = poly.length - 1; i >= 0; i--)
	    		if(poly[i] != 0){
	    			highestDeg = i;
	    			break;
	    		}

	    	// adding the coefficients and exponents to the string as long as the polynomial actually has x^i
	    	if(highestDeg != 0){
                // Using the fencepost problem/solution to print out the string
                if(highestDeg == 1)
                    expression += poly[highestDeg] + "x";
                else if(highestDeg != 0 && poly[highestDeg] != 1)
                    expression += poly[highestDeg] + "x^" + highestDeg;
                else
                    expression += "x^" + highestDeg;  

		    	for(int i = highestDeg - 1; i >= 0; i--){
		    		// skips elements that equal 0
		    		if(poly[i] == 0) 
		    			continue;

                    if(i == 1)
                        expression += " + " + poly[i] + "x";
		    		else if(i != 0 && poly[i] != 1)
		    			expression += " + " + poly[i] + "x^" + i;
		    		else if(i != 0 && poly[i] == 1)
		    			expression += " + " + "x^" + i;
		    		else
		    			expression += " + " + poly[i];
		    	}

		    	return expression;
		    }
		    // returns just the last element if the polynomial only has x^0
		    else
		    	return expression + poly[0];
    	}
    }

    /**
		Adds two polynomial objects together.
		@param p
			a Polynomial object to be added with the calling object
		@return
			a new Polynomial object that holds the addition of two other Polynomial objects
    */
    public Polynomial add(Polynomial p){
    	Polynomial new_obj = new Polynomial(this.coefficient(0) + p.coefficient(0));

    	if(poly.length >= p.poly.length){
    		for(int i = 1; i < poly.length; i++)
    			new_obj.assign_coef(this.coefficient(i) + p.coefficient(i), i);
    	}
    	else 
    		for(int i = 1; i < p.poly.length; i++)
    			new_obj.assign_coef(this.coefficient(i) + p.coefficient(i), i);

    	return new_obj;
    }

    /**
		Multiplies two polynomial objects together.
		@param p
			a Polynomial object to be multiplied with calling object
		@return
			a new Polynomial object that holds the product of two Polynomial objects
    */
    public Polynomial multiply(Polynomial p){
    	// calculate the product of the two objects
    	double[] product = new double[poly.length + p.poly.length];
    	for(int i = 0; i < poly.length; i++){
    		for(int j = 0; j < p.poly.length; j++)
    			product[i + j] += poly[i] * p.poly[j];
    	}

    	// create new polynomial object and return 
    	Polynomial new_obj	= new Polynomial(product[0]);

    	for(int i = 0; i < product.length; i++)
    		new_obj.assign_coef(product[i], i);

    	return new_obj;
    }

    /**
		Testing the Polynomial class methods.
		@param args
			Unused.
    */
    public static void main(String[] args){
    	Polynomial f1 = new Polynomial();
    	System.out.println("f1(x) = " + f1);
    	f1.add_to_coef(2, 0);
    	System.out.println("f1(x) + 2 = " + f1);
    	f1.assign_coef(4, 2);
    	System.out.println("Assign 4 to x^2 = " + f1);

    	System.out.println(' ');

    	Polynomial f2 = new Polynomial(3);
    	System.out.println("f2(x) = " + f2);
    	f2.assign_coef(1, 1);
    	System.out.println("Assign 1 to x^1 = " + f2);

    	System.out.println(' ');

    	Polynomial f3 = new Polynomial(f2);
    	System.out.println("Create f3(x) by copying f2(x) = " + f3);
    	f3.add_to_coef(4, 1);
    	System.out.println("Add 4 to x^1 of f3(x) = " + f3);

    	System.out.println(' ');

    	System.out.println("f1(x) = " + f1);
    	System.out.println("f2(x) = " + f2);
    	System.out.println("f3(x) = " + f3);

    	System.out.println(' ');

    	System.out.println("Coefficients of f1(x):");
    	System.out.println("x^2 = " + f1.coefficient(2));
    	System.out.println("x^1 = " + f1.coefficient(1));
    	System.out.println("x^0 = " + f1.coefficient(0));

    	System.out.println(' ');

    	System.out.println("f3(x) = " + f3);
    	System.out.println("f3(3) = " + f3.eval(3));

    	System.out.println(' ');

    	System.out.println("f1(x) + f2(x) = " + f1.add(f2));
    	System.out.println("f1(x) = " + f1);
    	System.out.println("f2(x) = " + f2);

    	System.out.println(' ');

    	System.out.println("f2(x) * f3(x) = " + f2.multiply(f3));
    	System.out.println("f2(x) = " + f2);
    	System.out.println("f3(x) = " + f3);

    	System.out.println("Create f4(x) from copying f2(X), add 3 to the coefficient of x^4, assign x^2 = 20, and add f1(x):");
    	Polynomial f4 = new Polynomial(f2);
    	f4.add_to_coef(3, 4);
    	f4.assign_coef(20, 2);
        System.out.println("f4+f1(2) = " + f4.add(f1).eval(2));

    	System.out.println(' ');

        Polynomial f5 = new Polynomial(f2);
        System.out.println("f5 created from f2: " + f5);
        f5.add_to_coef(2, 6);
        System.out.println("Add 3 to x^4: " + f5);
        f5.assign_coef(12, 1);
        System.out.println("Assign 20 to x^2: " + f5);
        System.out.println("f5(3) = " + f5.add(f1).eval(3));
        System.out.println("f5 * f4 = " + f5.multiply(f4));

        System.out.println(' ');

    	System.out.println("All polynomials:");
    	System.out.println("f1(x) = " + f1);
    	System.out.println("f2(x) = " + f2);
    	System.out.println("f3(x) = " + f3);
    	System.out.println("f4(x) = " + f4);
        System.out.println("f5(x) = " + f5);
    }
}


