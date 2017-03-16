/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.algorithm.examples.queues;

import dk.cphbusiness.airport.template.Passenger;
import java.util.NoSuchElementException;

/**
 *
 * @author ehn94
 */
public class HeapQueue implements PriorityQueue<Passenger>
{

    private Passenger[] data;
    private int size = 0;

    public HeapQueue(int capacity)
    {
        this.data = new Passenger[capacity];
    }
    
    private void swap(int pointer, int parentPointer) {
        data[0] = data[pointer];
        data[pointer] = data[parentPointer];
        data[parentPointer] = data[0];
    }

    private int parentOf(int p)
    {
        return p / 2;
    }

    private int leftOf(int p)
    {
        return 2 * p;
    }

    private int rightOf(int p)
    {
        return 2 * p + 1;
    }

    @Override
    public void enqueue(Passenger passenger)
    {

        int p = ++size;
        data[p] = passenger;    
        int pp = parentOf(p); 

        while (true)
        {

            if (pp == 0 || data[p].compareTo(data[pp]) > 0)
            {
                return;
            }
            data[0] = data[pp];    
            data[pp] = data[p];
            data[p] = data[0];
            p = pp;
            pp = parentOf(p);
        }
    }

    @Override
    public Passenger dequeue()
    {
        if (size == 0)
        {
            throw new NoSuchElementException();
        }

        Passenger result = data[1];

        swap(1, size--);
        int child = 0;
        int pointer = 1;
        do {
            int leftOf = leftOf(pointer);
            int rightOf = rightOf(pointer);
            
            if (leftOf > size) {
                return result;
            } else if (rightOf > size) {
                child = leftOf;
            } else if (data[leftOf].compareTo(data[rightOf]) < 0) {
                child = leftOf;
            } else {
                child = rightOf;
            }
            if (data[pointer].compareTo(data[child]) > 0) {
                swap(pointer, child);
                pointer = child;
            } else {
                return result;
            }
        } while (true);
    }

    @Override
    public Passenger peek()
    {
        if (size == 0)
        {
            throw new NoSuchElementException();
        }

        Passenger result = data[1];
        return result;

    }

    @Override
    public int size()
    {
        return size;
    }

}