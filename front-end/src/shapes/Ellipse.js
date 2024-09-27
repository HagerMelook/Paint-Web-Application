import Shape from './Shape'
export default class Ellipse extends Shape {
    radiusX = 0;
    radiusY = 0;
    constructor(x, y ,color, drag ,radiusX,radiusY){
        super(x,y,color,drag);
        this.radiusX=radiusX;
        this.radiusY=radiusY;
    }
    createShape(){
        return new Konva.Ellipse    ({
            x: this.xIndex,
            y: this.yIndex,
            sides: 3,
            radiusX: this.radiusX,
            radiusY: this.radiusY,
            fill: this.fillColor,
            draggable:this.draggable,
            stroke: 'black',
            strokeWidth: 2,
          });
    }

}