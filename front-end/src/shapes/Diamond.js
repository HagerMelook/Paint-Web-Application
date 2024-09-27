import Shape from './Shape'
export default class Diamond extends Shape {
    side = 0;
    constructor(x, y ,color, drag ,side){
        super(x,y,color,drag);
        this.side=side;
    }
    createShape(){
        return new Konva.RegularPolygon({
            x: this.xIndex,
            y: this.yIndex,
            sides: 4,
            radius: this.radius,
            fill: this.fillColor,
            draggable:this.draggable,
            stroke: 'black',
            strokeWidth: 2,
          });
    }

}
