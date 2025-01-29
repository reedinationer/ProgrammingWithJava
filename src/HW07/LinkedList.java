package HW07;

import java.lang.IndexOutOfBoundsException;

public class LinkedList<E> {
    private Node<E> head;
    private int size;

    public LinkedList(){
        this.head = null;
        this.size = 0;
    }

    public E get(int index){
        if (index >= size || index < 0 || head == null) throw new IndexOutOfBoundsException(String.format("Index out of bounds: %d", index));
        Node<E> curNode = head;
        int i = 0;
        while (i < index){
            curNode = curNode.getNext();
            i++;
        }
        return curNode.getData();
    }

    public E set(int index, E data){
        if (index > size - 1 || index < 0) throw new IndexOutOfBoundsException(String.format("Index out of bounds: %d", index));
        Node<E> curNode = head;
        for (int i=0; i < index; i++){
            curNode = curNode.getNext();
        }
        E result = curNode.getData();
        curNode.setData(data);
        return result;
    }

    private boolean compareObjs(Object o1, Object o2){
        if (o1 == o2) return true;
        if (o1 != null){ // Ensure we are not calling null.equals()
            return o1.equals(o2);
        }
        return false; // In this case o1 == null, but o1 != o2 so we know the values are not equal
    }

    public boolean remove(Object obj){
        if (head == null) return false;
        else if (compareObjs(head.getData(), obj)){
            size -= 1;
            head = head.getNext();
            return true;
        }
        Node<E> curNode = head;
        Node<E> nextNode;
        while (curNode.getNext() != null){
            nextNode = curNode.getNext();
            if (compareObjs(nextNode.getData(), obj)) {
                curNode.setNext(curNode.getNext().getNext());
                size -= 1;
                return true;
            }
            curNode = curNode.getNext();
        }
        return false;
    }

    public E remove(int index){
        if (index > size - 1 || index < 0) throw new IndexOutOfBoundsException(String.format("Index out of bounds: %d", index));
        size -= 1;
        if (index == 0){
            E result = head.getData(); // Cache value
            this.head = this.head.getNext();
            return result;
        }
        Node<E> curNode = head;
        for (int i=0; i < index - 1; i++){
            curNode = curNode.getNext();
        }
        E result = curNode.getNext().getData(); // Cache value
        curNode.setNext(curNode.getNext().getNext());
        return result;
    }

    public int indexOf(Object o){
        Node<E> curNode = head;
        int i = 0;
        while (curNode != null){
            if (compareObjs(curNode.getData(), o)){
                return i;
            }
            curNode = curNode.getNext();
            i++;
        }
        return -1;
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        String result = "";
        Node<E> curNode = head;
        int i = 0;
        while (i < size){
            if (i == 0){
                result = String.format("[%s", curNode.getData());
            } else {
                result = String.format("%s, %s", result, curNode.getData());
            }
            curNode = curNode.getNext();
            i++;
        }
        return String.format("%s]", result);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public boolean contains(Object obj){
        if (head == null) return false;
        Node<E> curNode = head;
        while (curNode != null){
            if (curNode.getData() == obj || curNode.getData().equals(obj)){
                return true;
            }
            curNode = curNode.getNext();
        }
        return false;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void clear(){
        this.head = null;
        this.size = 0;
    }

    public void add(int index, E data){
        if (index > this.size || index < 0) throw new IndexOutOfBoundsException(String.format("Index out of bounds: %d", index));
        if (index == 0){
            this.head = new Node<>(data, this.head);
        } else {
            Node<E> curNode = this.head;
            for (int i=1; i < index; i++){
                curNode = curNode.getNext();
            }
            Node<E> newNode = new Node<>(data, null);
            newNode.setNext(curNode.getNext());
            curNode.setNext(newNode);
            }
        this.size += 1;
        }

    public void add(E data){
        add(this.size, data);
    }

    public int size() {
        return size;
    }

    private class Node<E> {
        private E data;
        private Node<E> next;


        private Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        private E getData() {
            return data;
        }

        private void setData(E data) {
            this.data = data;
        }

        private Node<E> getNext() {
            return next;
        }

        private void setNext(Node<E> next) {
            this.next = next;
        }
    }
}


