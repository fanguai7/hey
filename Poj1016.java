import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Poj1016 {
	//字符串变形函数
	public static String change(String str) {
		char[] chs=str.toCharArray();
		int[] helps=new int[10];
		StringBuilder result=new StringBuilder();
		int tmp=0;
		for(int i=0;i<chs.length;i++) {
			tmp=chs[i]-48;
			helps[tmp]+=1;//记录对应数字出现的次数
		}
		for(int j=0;j<10;j++) {//将两个数组拼接起来
			if(helps[j]!=0) {
				result.append(helps[j]).append(j);
			}
		}
		return result.toString();
	}
	public static void main(String[] args) {
		Map<String,Integer> saveMap=new HashMap<String, Integer>();
		Scanner in =new Scanner(System.in);
		String str=in.next();
		String chStr;		//记录变形后的数据
		String outStr=str;	//记录下输入的数据，输出的时候要用
		int i=1;
		if(str.equals(change(str))) {			//1、变形之后与原数相同直接输出
			System.out.println(outStr+" is self-inventorying");
		} else {
			str=change(str);
			saveMap.put(str, 0);
			while(i<=15) {
				chStr=change(str);				//记下变形后的数据
				if(str.equals(chStr)){			//2、变换n步之后与前一个数相同
					System.out.println(outStr+" is self-inventorying after "+i+"steps");
					break;
				}
				else if(saveMap.containsKey(chStr)) {	//3、变换到n时，查看前面时候已经出现过
					System.out.println(outStr+" entrs an inventory loop of length "+(i-saveMap.get(chStr)));
					break;
				}
				else {
					str=chStr;
					saveMap.put(chStr, i);
				}
				i++;
			}
		}
		//循环15，没有被break，i加到了16，则为情况4
		if(i==16) {
			System.out.println(outStr+" entrs can not be classified after 15 iterations");
		}
	}
}