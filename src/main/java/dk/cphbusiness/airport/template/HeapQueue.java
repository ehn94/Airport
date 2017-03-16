/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.airport.template;

import dk.cphbusiness.algorithm.examples.queues.PriorityQueue;
import java.util.NoSuchElementException;
import javafx.scene.chart.PieChart;

/**
 *
 * @author ehn19
 */
public class HeapQueue implements PriorityQueue<Passenger> {

    Passenger[] passengers;
    int size = 0;

    public HeapQueue(int capacity) {
        passengers = new Passenger[capacity];
    }
    
    private void swap(int pointer, int parentP){
        passengers[0] = passengers[pointer];
        passengers[pointer] = passengers[parentP];
        passengers[parentP] = passengers[0];
        
    }

    @Override
    public void enqueue(Passenger person) {
        int pointer = ++size;

        passengers[pointer] = person;
        int parentP = parentOf(pointer);
        
        while(true)
        {
            
            if(parentP == 0 || passengers[pointer].compareTo(passengers[parentP])>0 )
            {
                return; 
            }
            swap(pointer, parentP);
            pointer = parentP;
            parentP = parentOf(pointer);
        }
    }

    @Override
    public Passenger dequeue() {
        if(size == 0) throw new NoSuchElementException();
        Passenger result = passengers[1];
        
        if(passengers[rightOf(1)].compareTo(passengers[leftOf(1)]) == 0)
        {
            
        }else
        {
            passengers[1] = passengers[rightOf(1)];
        }
        return result; 
        
        
    }

    @Override
    public Passenger peek() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int parentOf(int pointer) {
        return pointer / 2;
    }

    private int leftOf(int pointer)
    {
        return 2 * pointer;
    }

    private int rightOf(int pointer)
    {
        return 2 * pointer + 1;
    }
}
