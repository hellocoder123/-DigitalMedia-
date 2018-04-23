package priv.sywang.digitalmedia;

import java.util.HashMap;
import java.util.Map;

/**      
* @Description: TODO LWZ算法实现编解码
* @author sy.wang  
* @date 2018年4月23日 下午8:04:52  
* @version V1.0    
*/
public class LWZCoding {
	public static void encoding(String str) {
		//初始化编码字典
		int firstCode = 256;
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(int i=0;i<256;i++) {
			map.put((char)i+"", i);
		}
		
		String previous = "";//表示之前的字符
		String pc = "";//pc表示之前的字符+当前读取的字符
		/*
		 * core:1.依此读取待编译字符c，判断pc是否在编码字典中。
		 * 		2.若在编码字典中，令previous = pc,返回1;
		 * 		3.若不在编码字典中，将pc加入字典，令previous = c，输出previous，返回1.
		 */
		for(char c:str.toCharArray()) {
			pc = previous+c;
			if(map.containsKey(pc)) {
				previous = pc;
			}else {
				map.put(pc, firstCode++);
				System.out.print(map.get(previous)+"/");
				previous = c+"";
			}
		}
		//最后一次输出
		if(!previous.equals("")) {
			System.out.println(map.get(previous));
		}
		
	}
	//解码和编码类似，将解码字典改成map<Integer,String>
	public static void decoding(int[] arr) {
		//初始化解码字典。
		int firstCode = 256;
		Map<Integer,String> map = new HashMap<Integer,String>();
		for(int i=0;i<256;i++) {
			map.put(i, (char)i+"");
		}
		
		String previous = "";
		String  c = "";
		
		/*
		 * 1.依此读取待解码的编码集，判断该编码数字是否在字典中。
		 * 2.若在字典中，判断前一个字符是否为空，若不空，将previous+c[0]更新进字典。
		 * 3.若不在字典中，判断当前编码数字是否等于新增编码数字.若是，令c+=c[0];这种情况貌似只出现于[97,256,...
		 * 4.输出从，令previous= c;
		 */
		for(int code:arr) {
			if(map.containsKey(code)) {
				c=map.get(code);
			}else if(code==firstCode) {
				c = c+c.charAt(0);
			}else {
				System.out.println("bad encode!");
			}
			
			if(!previous.equals("")) {
				map.put(firstCode++, previous+c.charAt(0));
			}
			System.out.print(c);
			previous = c;
		}
	}
	public static void main(String[] args) {
		int []arr = {97,256,98,98,257};
		decoding(arr);
	}

}
