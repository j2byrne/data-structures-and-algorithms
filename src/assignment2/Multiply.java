package assignment2;

import java.math.BigInteger;
import java.util.Random;

public class Multiply {
	public static long getHigher(long a, int m) {
		return (long) a / ((long) Math.pow(2, m));
	}

	public static long getLower(long a, int m) {
		return a % ((long) Math.pow(2, m));
	}

//		b. O(N^2)

//		c. 
	public static long multiplyLong(int a, int b) {
		int n = Math.max(String.valueOf(a).length(), String.valueOf(b).length());
		
		if (a == 0 || b == 0) {
			return 0;
		} else if (n < 2) {
			return a * b;
		}
		int m = n/2;
		long p = getHigher(a, m);
		long q = getLower(a, m);
		long r = getHigher(b, m);
		long s = getLower(b, m);
		
		return multiplyLong((int) p, (int) r) * (long) Math.pow(2, 2*m) + (multiplyLong((int) p, (int) s) + multiplyLong((int) q, (int) r)) * (int) Math.pow(2, m) + multiplyLong((int) q, (int) s);
	}
	
	public static void multiplyLongTime() {
		int n_min = 20;
		int n_max = 28;
		
		Random rand = new Random(20010);
		
		for(int n = n_min; n < n_max; n++) {
			System.out.println(n);
			int min = (int) Math.pow(2, n);
			long a = rand.nextInt(min) + min;
			long b = rand.nextInt(min) + min;
			long begin = System.currentTimeMillis();
//			long begin = System.nanoTime();
			Multiply.multiplyLong((int) a, (int) b);
			long end = System.currentTimeMillis();
//			long end = System.nanoTime();
			System.out.println(n + ", " + (end-begin)*1000L);
		}
	}
	
	public static long multiplyFast(int a, int b) {
		int n;
		if (String.valueOf(a).length() >= String.valueOf(b).length()) {
			n = String.valueOf(a).length();
		} else {
			n = String.valueOf(b).length();
		}
		
		if (a == 0 || b == 0) {
			return 0;
		} else if (n < 2) {
			return a * b;
		}
		
		int m = n/2;
		long p = getHigher(a, m);
		long q = getLower(a, m);
		long r = getHigher(b, m);
		long s = getLower(b, m);
		
		long u = multiplyFast((int) p, (int) r);
		long w = multiplyFast((int) q, (int) s);
		long v = multiplyFast((int) q + (int) p, (int) s + (int) r);
		
		return u * ((int) Math.pow(2, 2 * m)) + (v - u - w) * ((int) Math.pow(2, m)) + w;
	}
	
	public static void multiplyLongTest() {
		long a = 12345678L;
		long b = 87654321L;
		long res = Multiply.multiplyLong((int) a, (int)b);
		System.out.println(res);
		System.out.println(a + "x" + b + "\n" + (a*b));
	}
	
	public static BigInteger getLower(BigInteger a, int m) {
		BigInteger two = new BigInteger("2");
		BigInteger res = a.mod(two.pow(m));
		return res;
	}
	
	public static BigInteger getHigher(BigInteger a, int m) {
		BigInteger two = new BigInteger("2");
		BigInteger res = a.divide(two.pow(m));
		return res;
	}
	
	public static BigInteger multiplyLong(BigInteger a, BigInteger b) {
		int n = a.max(b).bitLength();
		
		if (a.compareTo(BigInteger.ZERO) == 0 || b.compareTo(BigInteger.ZERO) == 0) {
			return BigInteger.ZERO;
		} else if (n < 2) {
			return a.multiply(b);
		}
		int m = n/2;

		BigInteger p = getHigher(a, m);
		BigInteger q = getLower(a, m);
		BigInteger r = getHigher(b, m);
		BigInteger s = getLower(b, m);
		
		BigInteger two = new BigInteger("2");
		
		return multiplyLong(p, r).multiply(two.pow(2*m)).add(multiplyLong(p, s).add(multiplyLong(q, r)).multiply(two.pow(m))).add(multiplyLong(q, s));
	}

	public static BigInteger multiplyFast(BigInteger a, BigInteger b) {
		int n = a.max(b).bitLength();
		
		if (a.compareTo(BigInteger.ZERO) == 0 || b.compareTo(BigInteger.ZERO) == 0) {
			return BigInteger.ZERO;
		} else if (n < 2) {
			return a.multiply(b);
		}
		
		int m = n/2;
		
		BigInteger p = getHigher(a, m);
		BigInteger q = getLower(a, m);
		BigInteger r = getHigher(b, m);
		BigInteger s = getLower(b, m);
		
		BigInteger u = multiplyFast(p, r);
		BigInteger w = multiplyFast(q, s);
		BigInteger v = multiplyFast(q.add(p), s.add(r));
		
		BigInteger two = new BigInteger("2");
		
		return u.multiply(two.pow(2*m)).add((v.subtract(u).subtract(w)).multiply(two.pow(m))).add(w);
	}
	
	public static void test() {
		Random rand = new Random(20010);
		
		int n_min = 2;
		
		for(int n = n_min; n <= Integer.MAX_VALUE-2; n *= 2) {
			BigInteger a = new BigInteger(n, rand);
			BigInteger b = new BigInteger(n, rand);
			
//			long begin = System.currentTimeMillis();
			long begin = System.nanoTime();
			Multiply.multiplyLong(a, b);
//			long end = System.currentTimeMillis();
			long end = System.nanoTime();
			System.out.println(n + ", " + (end-begin));
		}
	}
	
	public static void main (String[] args)
	{
		//test();
		long a = 1234567812345678L; // 1001
		int b = (int) (a >> 32);
		int c = (int) a;
		
		System.out.println(b);
		System.out.println(c);
		System.out.println(Long.toBinaryString(a));
		System.out.println(Integer.toBinaryString(b));
		System.out.println(Integer.toBinaryString(c));
	}
}
