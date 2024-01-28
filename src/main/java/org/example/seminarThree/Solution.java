package org.example.seminarThree;

import java.io.*;

public class Solution {

    public class A {
        String name = "A";

        public A(String name) {
            this.name += name;
        }

        public A(){}

    }
    public class B extends A {
        String name = "B";

        public B(String name) {
            super(name);
            this.name += name;
        }

        public B(){super();}

    }

    public class C extends B implements Externalizable {
        String Cname;
        private static final long serialVersionUID = 7829136421241571165L;

        public C(String name) {
            super(name);
            this.Cname = name;
        }

        public C(){super();}

        public String getCname() {
            return this.Cname;
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            //out.writeObject(super.name);
            out.writeObject(this.Cname);
        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            //super.name = (String) in.readObject();
            this.Cname = (String) in.readObject();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution.C c = new Solution().new C("C");
        System.out.println(c.name);

        System.out.println("Serialization");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ser.txt"));
        oos.writeObject(c);
        oos.close();

        System.out.println("Deserialization");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ser.txt"));
        Solution.C c1 = (Solution.C) ois.readObject();
        ois.close();
        System.out.println(c1.name);
    }
}

