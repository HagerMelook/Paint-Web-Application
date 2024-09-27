package com.example.DrawingApp;

import java.awt.Point;

public class ShapeFactory {
	
	   public static Shape createShape(int id,int length, int height, String color,String shapeType,Point position){
	      if(shapeType == null){
	         return null;
	      }		
	      if(shapeType.equalsIgnoreCase("Ellipse")){
		        return new Ellipse(id,length, height,color,"ellipse", position);
	         
	      } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
	         return new Rectangle(id,length, height,color,"rectangle", position);
	         
	      } else if(shapeType.equalsIgnoreCase("SQUARE")){
	         return new Square(id,length,color,"square", position);
	         
	      } else if (shapeType.equalsIgnoreCase("CIRCLE")) {
	    	  return new Circle(id,length,color,"circle", position);
	      
	      } else if(shapeType.equalsIgnoreCase("LINE")) {
	    	  return new Line(id,length,color,"line");
	    	  
	      } else if(shapeType.equalsIgnoreCase("TRIANGLE")) {
	    	  return new Triangle(id,length,height,color,"triangle", position);
	      }
	      
	      return null;
	   }

	}
