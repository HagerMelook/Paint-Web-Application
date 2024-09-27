import Shape from './Shape'
export default class Circle extends Shape {
    radius = 0;
    constructor(x, y ,color, drag ,radius){
        super(x,y,color,drag);
        this.radius=radius;
    }
     createShape(){
        return new Konva.Circle({
            x: this.xIndex,
            y: this.yIndex,
            sides: 3,
            radius: this.radius,
            fill: this.fillColor,
            draggable:this.draggable,
            stroke: 'black',
            strokeWidth: 2,
          });
    }


}