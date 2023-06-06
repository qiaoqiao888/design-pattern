package com.cloud.pro.designpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 第一节:迭代器设计模式
 * @author: proloud.vip
 * @Date: 2023/6/6 15:38
 */
public class IteratorDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        Aggregate<String> aggregate = new ConcreteAggregate<String>(list);
        Iterator<String> iterator = aggregate.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

// 迭代器接口
interface Iterator<T> {
    boolean hasNext();
    T next();
}

// 具体迭代器类
class ConcreteIterator<T> implements Iterator<T> {
    private List<T> list;
    private int index;

    public ConcreteIterator(List<T> list) {
        this.list = list;
        this.index = 0;
    }

    public boolean hasNext() {
        return index < list.size();
    }

    public T next() {
        if (hasNext()) {
            return list.get(index++);
        }
        return null;
    }
}

// 聚合器接口
interface Aggregate<T> {
    Iterator<T> createIterator();
}

// 具体聚合器类
class ConcreteAggregate<T> implements Aggregate<T> {
    private List<T> list;

    public ConcreteAggregate(List<T> list) {
        this.list = list;
    }

    public Iterator<T> createIterator() {
        return new ConcreteIterator<T>(list);
    }
}