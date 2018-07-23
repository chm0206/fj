import com.chm.fj.other.DateUtil;

public class TestInt {
	public static void main(String[] args){
		int a =1000000000;
		Long s = System.currentTimeMillis();
		for(int i = 1;i<a;i++){
			double b = i;
			Double c = b;//自动装箱很废时间
			//int c = b/b*b;
		}
		Long e = System.currentTimeMillis();
		System.out.println(e-s);

		for(int i = 1;i<a;i++){
			Double b = new Double(i);
			double c = b;
			//Integer c = b/b*b;
		}
		Long i = System.currentTimeMillis();
		System.out.println(i-e);
	}
}
