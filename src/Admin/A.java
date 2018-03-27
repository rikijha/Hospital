/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;


public class A {
    int x=5;
    A m1(){
    return new A();
    }
}
class B extends A{
 int x=7;
 B m1(){
    return new B();
    }
 
}

class C extends A{
 int x=8;
}
class D extends B{
 int x=9;
}
class Test{
    public static void main(String[] args) {
        A a=new B();
        B b=(B)a;
       // D d=(D)b;
        System.out.println(b.m1().x);
    }
}