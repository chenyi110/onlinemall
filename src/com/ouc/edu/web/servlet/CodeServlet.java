package com.ouc.edu.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CodeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 使用java图形界面技术绘制一张图片

		int charNum = 4;
		int width = 20 * 4;
		int height = 28;

		// 1. 创建一张内存图片
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 2.获得绘图对象
		Graphics graphics = bufferedImage.getGraphics();

		// 3、绘制背景颜色
		//graphics.setColor(Color.YELLOW);
		graphics.fillRect(0, 0, width, height);

		// 4、绘制图片边框
		graphics.setColor(Color.GRAY);
		graphics.drawRect(0, 0, width - 1, height - 1);

		// 5、输出验证码内容
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("宋体", Font.BOLD, 22));
		
		// 随机输出4个字符
		String s = "ABCDEFGHGKLMNPQRSTUVWXYZ23456789";
		Random random = new Random();
		char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		// session中要用到
		String msg = "";
		StringBuffer sb = new StringBuffer(); //保存字符串
		
//		int x = 5;
//		for (int i = 0; i < charNum; i++) {
//			int index = random.nextInt(32);
//			String content = String.valueOf(s.charAt(index));
//			
//			msg += content;
//			graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
//			graphics.drawString(content, x, 22);
//			x += 20;
//		}
		int index;
		for(int i=0; i<4; i++){
			index = random.nextInt(ch.length);
			graphics.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
			Font font = new Font("宋体", 30, 20);
			graphics.setFont(font);
			graphics.drawString(ch[index]+"", (i*20)+2, 23);
			sb.append(ch[index]);
		}

		// 6、绘制干扰线
		//graphics.setColor(Color.GRAY);
		for (int i = 0; i < 5; i++) {
			int x1 = random.nextInt(width);
			int x2 = random.nextInt(width);

			int y1 = random.nextInt(height);
			int y2 = random.nextInt(height);
			graphics.setColor(interLine(1, 255));
			graphics.drawLine(x1, y1, x2, y2);
		}

		// 添加噪点
	    int area = (int) (0.02 * 80 * 25);
	    for(int i=0; i<area; ++i){
	        int x = (int)(Math.random() * 80);
	        int y = (int)(Math.random() * 25);
	        bufferedImage.setRGB(x, y, (int)(Math.random() * 255));
	    }
		// 释放资源
		graphics.dispose();

		HttpSession session = request.getSession();  //保存到session
		session.setAttribute("verificationCode", msg);
		// 图片输出 ImageIO
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	/**
	 * 获取随机的颜色值，r,g,b的取值在Low到High之间
	 * @param Low  左区间
	 * @param High 右区间
	 * @return 返回随机颜色值
	 */
	private static Color interLine(int Low, int High){
	    if(Low > 255)
	    	Low = 255;
	    if(High > 255)
	    	High = 255;
	    if(Low < 0)
	    	Low = 0;
	    if(High < 0)
	    	High = 0;
	    int interval = High - Low; 
	    int r = Low + (int)(Math.random() * interval);
	    int g = Low + (int)(Math.random() * interval);
	    int b = Low + (int)(Math.random() * interval);
	    return new Color(r, g, b);
	  }

}
