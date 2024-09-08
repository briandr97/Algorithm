import java.io.*;
import java.util.*;

class Main {
    private static HashMap<Room, Integer> dp = new HashMap<Room, Integer>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int repeat = Integer.parseInt(br.readLine().trim());
        for(int i=0; i<repeat; i++) {
            int floor = Integer.parseInt(br.readLine().trim());
            int roomNumber = Integer.parseInt(br.readLine().trim());
        
            bw.write(Integer.toString(recursive(floor, roomNumber)) + "\n");
            bw.flush();
            dp.clear();
        }
        bw.close();
    }
    
    private static Integer recursive(int floor, int roomNumber) {
        if(floor==0) {
            return roomNumber;
        }
        if(roomNumber==1) {
            return 1;
        }
        Room room = new Room(floor, roomNumber);
        Integer peopleCount = dp.get(room);
        if(peopleCount == null) {
            int result = recursive(floor, roomNumber-1) + recursive(floor-1, roomNumber);
            dp.put(room, result);
            return result;
        }
        return peopleCount;
    }
}

class Room {
    private final int floor;
    private final int roomNumber;
    
    public Room(int floor, int roomNumber) {
        this.floor = floor;
        this.roomNumber = roomNumber;
    }
    
    public int getFloor() {
        return floor;
    }
    
    public int getRoomNumber() {
        return roomNumber;
    }
    
    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Room)) {
            return false;
        }
        Room other = (Room)object;
        if(floor != other.getFloor()) {
            return false;
        }
        if(roomNumber != other.getRoomNumber()) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(floor, roomNumber);
    }
}