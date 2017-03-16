package dk.cphbusiness.airport.template;

import dk.cphbusiness.algorithm.examples.queues.HeapQueue;
import dk.cphbusiness.algorithm.examples.queues.NotPrioritisingPassengerArrayQueue;
import dk.cphbusiness.algorithm.examples.queues.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

public class Program {
  private static List<Plane> planes = new ArrayList<>();
  private static PassengerProducer producer;
  private static PassengerConsumer consumer;
  private static PriorityQueue<Passenger> queue;
  private static Clock clock;
  
  private static void setup() {
    for (int hour = 7; hour <= 22; hour++) {
      planes.add(new Plane(new Time(hour, 00, 00)));
      }
      queue = new HeapQueue(5);
    //queue = new NotPrioritisingPassengerArrayQueue(5);
    producer = new PassengerProducer(planes, queue);
    consumer = new PassengerConsumer(planes, queue);
    clock = new Clock(producer, consumer, new Time(05, 00, 00));
    }
  
  public static void main(String[] args) {
    setup();
    System.out.println("Welcome to the airport");
    new Thread(clock).start();
    
    }
  
  }