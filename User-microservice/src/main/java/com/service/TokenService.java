package com.service;

import org.springframework.stereotype.Service;

@Service
public class TokenService 
{
	public String generateToken() 
	{
		String data = "9876543210abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sb = new StringBuffer("");
		for(int i=1;i<=20;i++)
		{
			int index = (int)(Math.random()*data.length());
			sb.append(data.charAt(index));
		}
		return sb.toString();
	}
	
	public String generateOrder()
	{
		String data = "9876543210#";
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i<6;i++)
		{
			int index = (int)(Math.random()*data.length());
			sb.append(data.charAt(index));
		}
		return sb.toString();
	}
	public String otp()
	{
		String data = "9876543210";
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i<6;i++)
		{
			int index = (int)(Math.random()*data.length());
			sb.append(data.charAt(index));
		}
		return sb.toString();

	}
}
