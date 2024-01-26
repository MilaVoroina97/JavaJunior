package org.example.seminarThree;

import java.io.*;

public class Solution implements Serializable {
    public class A {
        transient String name = "A";
        public A(String name) {
            this.name += name;
        }
        public A() {}
    }
    public class B extends A {
        transient String name = "B";
        public B(String name) {
            super(name);
            this.name += name;
        }
        public B() {}
    }

    public class C extends B implements Serializable {
         String Cname;
        private static final long serialVersionUID = 7829136421241571165L;
        public C(String name) {
            super(name);
            this.Cname = name;
        }

        public C(){}

        @Serial
        private void writeObject(ObjectOutputStream outputStream) throws IOException {
            outputStream.defaultWriteObject();
            outputStream.writeObject(Cname);
        }

        @Serial
        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            Cname = (String) objectInputStream.readObject();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution.C c = new Solution().new C("C");
        System.out.println(c.name);

        System.out.println("Сериализация");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ser.txt"));
        oos.writeObject(c);
        oos.close();

        System.out.println("Десериализация");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ser.txt"));
        Solution.C c1 = (Solution.C) ois.readObject();
        ois.close();
        System.out.println(c1.name);
    }
}

