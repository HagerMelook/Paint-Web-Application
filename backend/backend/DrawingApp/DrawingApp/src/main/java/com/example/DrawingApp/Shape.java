package com.example.DrawingApp;

import java.awt.Point;

public abstract class Shape {
 protected int id;
 protected int length;
 protected int height;
 protected String color;
 protected String type;
 protected Point position;
 
public Shape(int id,int length, int height, String color,String type,Point position) {
	this.id = id;
	this.length = length;
	this.height = height;
	this.color = color;
	this.type = type;
	this.position = position;
}
public int getLength() {
	return length;
}
public void setLength(int length) {
	this.length = length;
}
public int getHeight() {
	return height;
}
public void setHeight(int height) {
	this.height = height;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Point getPosition() {
	return position;
}
public void setPosition(Point position) {
	this.position = position;
}



}

