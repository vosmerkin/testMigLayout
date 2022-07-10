package com.example.migLayout.services;

public class TestTask {

    public  void main(String[] args) {
        Parent p = new Child();
        ParentReceiver pr = new ChildReceiver();
        pr.receiver(p);
    }
    public class Parent {
        int a = 2;
    }
    public class Child extends Parent {
        int a = 3;
    }
    public class ParentReceiver {
        public void receiver(Parent p) {
            System.out.println("Parent");
            System.out.println(p.a);
        }
    }
    public class ChildReceiver extends ParentReceiver {
        public void receiver(Child c) {
            System.out.println("Parent");
            System.out.println(c.a);
        }
    }

}
