import Shape from './Shape'
export default class Pentagon extends Shape {
    radius = 0;
    constructor(x, y ,color, drag ,radius){
        super(x,y,color,drag);
        this.radius=radius;
    }
    createShape(){
        return new Konva.RegularPolygon({
            x: this.xIndex,
            y: this.yIndex,
            sides: 5,
            radius: this.radius,
            fill: this.fillColor,
            draggable:this.draggable,
            stroke: 'black',
            strokeWidth: 2,
          });
    }

}