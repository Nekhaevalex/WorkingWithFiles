package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {

    private static byte[] readFromFile(String fileName) {
        byte[] fileData;
        try (FileInputStream fls = new FileInputStream(fileName)) {
            int lengthOfFile = fls.available();
            fileData = new byte[lengthOfFile];
            fls.read(fileData, 0, lengthOfFile);
        } catch (IOException ex) {
            throw new RuntimeException("Error reading file: "+fileName, ex);
        }
        return fileData;
    }

    public static void staticExample() {
        Person john = new Person("John");
        Person alex = new Person("Alex");

        System.out.println(john);
        System.out.println(alex);

        john.old();

        System.out.println(john);
        System.out.println(alex);
    }

    public static void listDemo() {
        List<String> list = new ArrayList();
        list.add("a"); list.add("b"); list.add("c");
        for (String s : list) {

        }
        System.out.println("list: "+list);
        list.remove("a");
        System.out.println("List after remove a: "+list);
        list.remove(0);
        System.out.println("List after removing 0 element: "+list);
        if (list.contains("a")) {
            System.out.println("List contains a");
        }
        int indexA = list.indexOf("a");
        int indexB = list.indexOf("b");
        System.out.println("index A: "+indexA+", index B: "+indexB);
        if (indexA > -1) {
            String a = list.get(indexA);
        }
        list.add("o");
        String[] array = list.toArray(new String[0]);
        System.out.println("array: "+ Arrays.toString(array));
        list.clear();
        System.out.println("List after clearing: "+list);
    }

    public static void setDemo()    {
        Set<String> set = new HashSet();
        set.add("a"); set.add("b"); set.add("a");
        for (String s: set) {

        }
        System.out.println("set: "+set);
        set.remove("a");
        System.out.println("set after removing a: "+set);
        if (set.contains("a")) {
            System.out.println("set contains a");
        }
        set.add("c");
        String[] array = set.toArray(new String[0]);
        System.out.println("array: "+Arrays.toString(array));
        set.clear();
    }

    public static void mapDemo() {
        Map<String, Integer> map = new HashMap();
        map.put("a", 0); map.put("a", 1);
        if (map.containsKey("a")){
            System.out.println("map contains a key");
        }
        if (map.containsValue(1)){
            System.out.println("map contains 1 value");
        }
        Integer value = map.get("a");
        System.out.println("a: "+value);
        int size = map.size();
        System.out.println("map size: "+size);
        Set<String> allKeys = map.keySet();
        System.out.println("map all keys: "+allKeys);
        Collection<Integer> values = map.values();
        System.out.println("map all values: "+values);
        for (Map.Entry<String, Integer> en : map.entrySet()) {
            System.out.println(en.getKey()+" "+en.getValue());
        }
        map.clear();
    }

    public static void hashEqualsDemo() {
        class C1 {
            String data = "default";

            @Override
            public String toString() {
                return "C1: "+data;
            }

            public C1 (String data) {
                this.data = data;
            }

            @Override
            public int hashCode() {
                return this.data.length();
            }

            @Override
            public boolean equals(Object obj) {
                C1 c = (C1)obj;
                return this.data.equals(c.data);
            }
        }
        Set<C1> set = new HashSet();
        C1 a1 = new C1("a");
        C1 b = new C1("b");
        C1 a2 = new C1("a");
        set.add(a1); set.add(b); set.add(a2);
        System.out.println(set);
    }

    public static void threadDemo1() throws InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("run in another thread");
            }
        };
        Thread t = new Thread(r);
        t.start();
        t.join();
    }

    public static void threadDemo2() throws InterruptedException {
        Runnable r = new ComplexCalculations();
        Thread t = new Thread(r);
        t.start();
        t.join();
    }

    public static void threadDemo3() throws InterruptedException {
        Runnable[] rs = new Runnable[2];
        Thread[] ts = new Thread[rs.length];
        for (int i = 0; i<rs.length; i++) {
            rs[i] = new ComplexCalculations();
            ts[i] = new Thread(rs[i]);
        }
        for (int i = 0; i<rs.length; i++) {
            ts[i].start();
        }
        for (int i = 0; i<rs.length; i++) {
            ts[i].join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        byte[] rawData = readFromFile("text.txt");
        String string = new String(rawData);
        System.out.println("File contence: "+string);

        String fileName = "text.txt";
        try (FileInputStream fls = new FileInputStream(fileName)){
            Scanner s = new Scanner(fls);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                System.out.println("Line: "+line);
            }
        } catch (IOException exception) {
            throw new RuntimeException("Error reading file: "+fileName);
        }
        listDemo();
        staticExample();
        setDemo();
        mapDemo();
        hashEqualsDemo();
        threadDemo1();
        threadDemo2();
        threadDemo3();
    }
}