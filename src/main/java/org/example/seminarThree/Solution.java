package org.example.seminarThree;

import java.io.*;

public class Solution implements Externalizable {
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }

    public class A {
        transient String name = "A";

        public A() {super();}
        public A(String name) {
            this.name += name;
        }

    }
    public class B extends A {
        transient String name = "B";

        public B() {super();}
        public B(String name) {
            super(name);
            this.name += name;
        }

    }

    public class C extends B implements Externalizable {
        String Cname;
        private static final long serialVersionUID = 7829136421241571165L;

        public C() {
            super();
        }

        public C(String name) {
            super(name);
            this.Cname = name;
        }

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

