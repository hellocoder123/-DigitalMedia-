package priv.sywang.digitalmedia;


public class ArithCoding {   
	private char[] symbols;
	 private double[] rates;
	public ArithCoding(char[] symbols, double[] rates) {
		this.symbols = symbols;
		this.rates = rates;
	}
	
	
	public double coding(String s) {   
        char[] cs = s.toCharArray();   
        double symbolRangeLow[] = new double[rates.length];   
        for (int i = 0; i < symbolRangeLow.length; i++) {   
            symbolRangeLow[i] = 0;   
            for (int j = 0; j < i; j++) {   
                symbolRangeLow[i] += rates[j];   
            }
//            System.out.println(symbolRangeLow[i]);
        }   
        int currentSymbol;   
        double low = 0.0;   
        double high = 1.0;   
        double range = 1.0;   
        for (int i = 0; i < cs.length; i++) {
            currentSymbol = this.getIndex(symbols, cs[i]);   
            low = low + range * symbolRangeLow[currentSymbol];   
            high = low + range * rates[currentSymbol];   
            range = high - low;   
//            System.out.println("currentSymbol:"+currentSymbol+" low:"+low+" high:"+high);
        }   
        return low;   
    }   
	
	
	 public String deCoding(double codeNumber) {   
	        String sym = "";   
	        double symbolRangeLow[] = new double[rates.length];   
	        for (int i = 0; i < symbolRangeLow.length; i++) {   
	            symbolRangeLow[i] = 0;   
	            for (int j = 0; j < i; j++) {   
	                symbolRangeLow[i] += rates[j];   
	            }   
	        }   
	        double subSymbolRangeLow[] = new double[symbolRangeLow.length];   
	        double subSymbolRange[] = new double[rates.length];   
	        double subRangeLow = 0;   
	        double subRange = 0;   
	        for (int i = 0; i < symbols.length; i++) {   
	            subSymbolRangeLow[i] = symbolRangeLow[i];   
	            subSymbolRange[i] = rates[i];  
	           // System.out.println("subSymbolRangeLow["+i+"]:"+subSymbolRangeLow[i]+" "+subSymbolRange[i]);
	        }   
	        int currentSymbol = 0;  
	        do {   
	            for (int i = 0; i < symbols.length; i++) {   
	                // 判别区间   
	                if (codeNumber >= subSymbolRangeLow[i]   
	                        && codeNumber < subSymbolRangeLow[i]   
	                                + subSymbolRange[i]) {   
	                    // 区间赋初值   
	                    subRangeLow = subSymbolRangeLow[i];   
	                    subRange = subSymbolRange[i];   
	                    currentSymbol = i; 
	                    
	                    System.out.print(symbols[currentSymbol]);
	                }   
	            }   
	            double subSymbolProbSum = subRangeLow;   
	            for (int i = 0; i < symbols.length; i++) {   
	                // 区间赋值   
	                subSymbolRange[i] = subRange * rates[i];   
	                subSymbolRangeLow[i] = subSymbolProbSum;   
	                subSymbolProbSum = subSymbolProbSum + subSymbolRange[i]; 
	                System.out.println("subSymbolRangeLow:"+subSymbolRangeLow[i]+" "
	    	               + subSymbolProbSum		);
	            }   
	               
	            sym+= symbols[currentSymbol];   
	        } while (symbols[currentSymbol] != 'B');    
	        return sym;   
	    }  
	  
	public int getIndex(char[] cs, char c) {   
        for (int i = 0; i < cs.length; i++) {   
            if (cs[i] == c) {   
                return i;   
            }   
        }   
        return -1;   
    }   
	public static void main(String[] args) {
		char[] s = { 'A', 'B', 'C', 'D'};
		double[] b = {0.1,0.4,0.2,0.3}   ;
		System.out.println(new ArithCoding(s,b).coding("ACDDB"));
		System.out.println(new ArithCoding(s,b).deCoding(new ArithCoding(s,b).coding("ACDDB")));
		
	}
}