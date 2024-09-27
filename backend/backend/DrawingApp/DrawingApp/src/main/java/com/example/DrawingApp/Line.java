package com.example.DrawingApp;

import java.awt.Point;


public class Line extends Shape{
	private Point startPoint;
	private Point endPoint;
	public Line(int id,int length,String type,String color) {
		super(id,length,0,color,type,null);

	}
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}	
	

}
