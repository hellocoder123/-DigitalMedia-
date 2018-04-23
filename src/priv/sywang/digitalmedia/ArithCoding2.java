package priv.sywang.digitalmedia;
/**      
* @Description: TODO 
* @author sy.wang  
* @date 2018年4月8日 下午6:22:21  
* @version V2.0    
*/
 
public class ArithCoding2 {   
   private char[] syms; //信号源  A B C D
   private double[] rates; //频率 0.1,0.4,0.2,0.3 
 
   //构造函数初始化信源及其频率
   public ArithCoding2(char[] syms, double[] rates) {   
       this.syms = syms;   
       this.rates = rates;   
   }   
   //编码
   public double encoding(String s) {   
       char[] cs = s.toCharArray();   
       double symLow[] = new double[rates.length];
       //计算各信源区间的下限值
       for (int i = 0; i < symLow.length; i++) {   
           symLow[i] = 0;   
           for (int j = 0; j < i; j++) {   
               symLow[i] += rates[j];   
           } 
//         System.out.println(symLow[i]);
       }   
       int currentSymbol;  //当前信源位置 
       double low = 0.0;   //下限
       double high = 1.0;   //上限
       double range = 1.0; 
       //更新上下限
       for (int i = 0; i < cs.length; i++) {   
           currentSymbol = this.getIndex(syms, cs[i]);   
           low = low + range * symLow[currentSymbol];   
           high = low + range * rates[currentSymbol];   
           range = high - low;  
 //        System.out.println("currentSymbol:"+currentSymbol+" low:"+low+" high:"+high);
       }   
       // 取中间值   
       return low + (high - low)/2;   
   }   
   
   
   //解码
   public String decoding(double cNum) {   
       String s = "";   
       double symLow[] = new double[rates.length]; 
     //计算各信源区间的下限值
       for (int i = 0; i < symLow.length; i++) {   
           symLow[i] = 0;   
           for (int j = 0; j < i; j++) {   
               symLow[i] += rates[j];   
           }   
       }   
       double subsymLow[] = new double[symLow.length];   
       double subSymbolRange[] = new double[rates.length];   
       double subRangeLow = 0;   
       double subRange = 0;
       //初始化信源的上下界
       for (int i = 0; i < syms.length; i++) {   
           subsymLow[i] = symLow[i];   
           subSymbolRange[i] = rates[i];   
       }   
       int currentSymbol = 0;   
       do {   
           for (int i = 0; i < syms.length; i++) {   
               // 判别区间   
               if (cNum >= subsymLow[i]  && cNum < subsymLow[i] + subSymbolRange[i]) {   
                   // 区间赋初值   
                   subRangeLow = subsymLow[i];   
                   subRange = subSymbolRange[i];   
                   currentSymbol = i;   
               }   
           }   
           double subSymbolProbSum = subRangeLow;   
           for (int i = 0; i < syms.length; i++) {   
               // 区间赋值   
               subSymbolRange[i] = subRange * rates[i];   
               subsymLow[i] = subSymbolProbSum;   
               subSymbolProbSum = subSymbolProbSum + subSymbolRange[i];
//               System.out.println("subSymLow:"+subSymLow[i]+" "
//    	               + subSymbolProbSum		);
           }   
              
           s += syms[currentSymbol];   
       } while (syms[currentSymbol] != 'C');   
 
       return s;   
   }   
 
   public int getIndex(char[] cs, char c) {   
       for (int i = 0; i < cs.length; i++) {   
           if (cs[i] == c) {   
               return i;   
           }   
       }   
       return -1;   
   }   
  
   public static void main(String args[]) {   
       char[] syms = { 'A', 'B', 'C', 'D' };   
       double[] rates = { 0.1,0.4,0.2,0.3 };   
       ArithCoding2 test = new ArithCoding2(syms, rates);   
       double cNum = test.encoding("ABC");   
       System.out.println(cNum);   
       System.out.println(test.decoding(cNum));     
   }   
} 
