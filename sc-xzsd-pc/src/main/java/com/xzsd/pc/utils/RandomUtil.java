package com.xzsd.pc.utils;

public class RandomUtil {

    /*
     * @param count :需要产生随机数的个数
     */
    public static String radmonkey(int count){
        StringBuffer sbf=new StringBuffer();
        for (int i = 0; i <count; i++) {
            int num=(int)(Math.random()*10);
            sbf.append(num);
        }

        return sbf.toString();
    }

    /**
     * 生成随机数
     * @param num
     * @return
     */
	/*public static String randomKey(int count){
		StringBuffer sbf=new StringBuffer();
		for (int i = 0; i <count; i++) {
			int num=(int)(Math.random()*10);
			sbf.append(num);
		}
		return sbf.toString();
	}*/

    public static String randomLetter(int num){
        //先定义取值范围
        String chars = "0123456789QWERTYUIOPASDFGHJKLZXCVBNMabcdefghijklmnopqrstuvwxyz";
        StringBuffer value = new StringBuffer();
        for (int i = 0; i < num; i++) {
            value.append(chars.charAt((int)(Math.random() * 62)));
        }
        return value.toString();
    }

    public static void main(String[] args) {
        System.err.println(radmonkey(10));
        System.out.println(randomLetter(6));
    }

}
