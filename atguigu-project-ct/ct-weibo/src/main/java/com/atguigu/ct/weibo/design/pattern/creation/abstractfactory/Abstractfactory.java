package com.atguigu.ct.weibo.design.pattern.creation.abstractfactory;

/**
 * 抽象工厂模式（Abstract Factory Pattern）是围绕一个超级工厂创建其他工厂。该超级工厂又称为其他工厂的工厂。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
 *
 * 在抽象工厂模式中，接口是负责创建一个相关对象的工厂，不需要显式指定它们的类。每个生成的工厂都能按照工厂模式提供对象。
 *
 *
 * 介绍
 * 意图：提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类。
 *
 * 主要解决：主要解决接口选择的问题。
 *
 * 何时使用：系统的产品有多于一个的产品族，而系统只消费其中某一族的产品。
 *
 * 如何解决：在一个产品族里面，定义多个产品。
 *
 * 关键代码：在一个工厂里聚合多个同类产品。
 *
 * 应用实例：工作了，为了参加一些聚会，肯定有两套或多套衣服吧，比如说有商务装（成套，一系列具体产品）、时尚装（成套，一系列具体产品），甚至对于一个家庭来说，可能有商务女装、商务男装、时尚女装、时尚男装，这些也都是成套的，即一系列具体产品。假设一种情况（现实中是不存在的，要不然，没法进入共产主义了，但有利于说明抽象工厂模式），在您的家中，某一个衣柜（具体工厂）只能存放某一种这样的衣服（成套，一系列具体产品），每次拿这种成套的衣服时也自然要从这个衣柜中取出了。用 OOP 的思想去理解，所有的衣柜（具体工厂）都是衣柜类的（抽象工厂）某一个，而每一件成套的衣服又包括具体的上衣（某一具体产品），裤子（某一具体产品），这些具体的上衣其实也都是上衣（抽象产品），具体的裤子也都是裤子（另一个抽象产品）。
 *
 * 优点：当一个产品族中的多个对象被设计成一起工作时，它能保证客户端始终只使用同一个产品族中的对象。
 *
 * 缺点：产品族扩展非常困难，要增加一个系列的某一产品，既要在抽象的 Creator 里加代码，又要在具体的里面加代码。
 *
 * 使用场景： 1、QQ 换皮肤，一整套一起换。 2、生成不同操作系统的程序。
 *
 * 注意事项：产品族难扩展，产品等级易扩展。
 */
public class Abstractfactory {
    /**
     * 定义一个接口
     */
    interface Shape {
        //画形状
        void draw();
    }


    /**
     * 矩形
     */
    public static   class Rectangle implements Shape {

        @Override
        public void draw() {
            System.out.println("Inside Rectangle::draw() method.");
        }
    }

    /**
     * 正方形
     */
    public static   class Square implements Shape {

        @Override
        public void draw() {
            System.out.println("Inside Square::draw() method.");
        }
    }

    /**
     * 圆形
     */
    public static   class Circle implements Shape {

        @Override
        public void draw() {
            System.out.println("Inside Circle::draw() method.");
        }
    }


    /**
     * 填充的颜色
     */
    public interface Color {
        void fill();
    }

    /**
     * 填充红色
     */
    public static class Red implements Color {

        @Override
        public void fill() {
            System.out.println("Inside Red::fill() method.");
        }
    }

    /**
     * 填充绿色
     */
    public static class Green implements Color {

        @Override
        public void fill() {
            System.out.println("Inside Green::fill() method.");
        }
    }

    /**
     * 填充蓝色
     */
    public static class Blue implements Color {

        @Override
        public void fill() {
            System.out.println("Inside Blue::fill() method.");
        }
    }

    /**
     * 工厂的抽象方法
     */
    public static abstract class AbstractFactory {
        public abstract Color getColor(String color);
        public abstract Shape getShape(String shapeType) ;
    }

    /**
     * 形状的抽象类
     */
    public static class ShapeFactory extends AbstractFactory{


        @Override
        public Color getColor(String color) {
            return null;
        }

        @Override
        public Shape getShape(String shapeType) {
            if(shapeType == null){
                return null;
            }
            if(shapeType.equalsIgnoreCase("CIRCLE")){
                return new Circle();
            } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
                return new Rectangle();
            } else if(shapeType.equalsIgnoreCase("SQUARE")){
                return new Square();
            }
            return null;
        }
    }

    /**
     * 颜色的抽象类
     */
    public static class ColorFactory extends AbstractFactory {

        @Override
        public Shape getShape(String shapeType){
            return null;
        }

        @Override
        public Color getColor(String color) {
            if(color == null){
                return null;
            }
            if(color.equalsIgnoreCase("RED")){
                return new Red();
            } else if(color.equalsIgnoreCase("GREEN")){
                return new Green();
            } else if(color.equalsIgnoreCase("BLUE")){
                return new Blue();
            }
            return null;
        }
    }

    /**
     *  所有工厂的父类工厂
     */
    public static class FactoryProducer {
        public static AbstractFactory getFactory(String choice){
            if(choice.equalsIgnoreCase("SHAPE")){
                return new ShapeFactory();
            } else if(choice.equalsIgnoreCase("COLOR")){
                return new ColorFactory();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        //获取形状工厂
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");

        //获取形状为 Circle 的对象
        Shape shape1 = shapeFactory.getShape("CIRCLE");

        //调用 Circle 的 draw 方法
        shape1.draw();

        //获取形状为 Rectangle 的对象
        Shape shape2 = shapeFactory.getShape("RECTANGLE");

        //调用 Rectangle 的 draw 方法
        shape2.draw();

        //获取形状为 Square 的对象
        Shape shape3 = shapeFactory.getShape("SQUARE");

        //调用 Square 的 draw 方法
        shape3.draw();

        //获取颜色工厂
        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");

        //获取颜色为 Red 的对象
        Color color1 = colorFactory.getColor("RED");

        //调用 Red 的 fill 方法
        color1.fill();

        //获取颜色为 Green 的对象
        Color color2 = colorFactory.getColor("Green");

        //调用 Green 的 fill 方法
        color2.fill();

        //获取颜色为 Blue 的对象
        Color color3 = colorFactory.getColor("BLUE");

        //调用 Blue 的 fill 方法
        color3.fill();
    }


}
