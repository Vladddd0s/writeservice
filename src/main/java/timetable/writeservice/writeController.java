package timetable.writeservice;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Qualifier;
import timetable.DB;
import timetable.Train;

import java.util.concurrent.BlockingQueue;

@org.springframework.stereotype.Controller
public class writeController {
    HazelcastInstance hazelcastInstance;
    BlockingQueue<Train> trains;
    public writeController(@Qualifier("hazelcastInstance") HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
        this.trains = hazelcastInstance.getQueue("queue");
        DB.connect();
         while(true) {
            if (!(trains.isEmpty())) {

                for (Train train : trains) {
                    DB.write(train.number, train.route, train.time);
                    trains.poll();
                }
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}

