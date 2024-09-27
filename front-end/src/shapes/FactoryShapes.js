import  Circle  from "./Circle.js";
import Rectangle from "./Rectangle.js";
import Diamond from "./Diamond.js";
import Ellipse  from "./Ellipse.js";
import  Pentagon from "./Pentagon.js";
import Triangle from "./Triangle.js";
import Square from "./Square.js";
import Star from "./Star.js";

export  default class FactoryShapes {
    createObject(shape){
            switch(shape){
                case "circle":
                    return new Circle  ;
                case "rectangle":
                    return new Rectangle;
                case "ellipse":
                    return new Ellipse;
                case "diamond":
                    return new Diamond;
            
                case "square":
                    return new Square;
                case "star":
                    return new Star;
                case "pentagon":
                    return new Pentagon;
                case "triangle":
                    return new Triangle;
            }
    }
}






