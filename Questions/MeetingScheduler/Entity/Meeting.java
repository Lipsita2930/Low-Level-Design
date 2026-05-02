package Questions.MeetingScheduler.Entity;

import java.util.List;
import java.util.UUID;

public class Meeting {
    
    private String id;
    private String title;
    private TimeSlot timeSlot;
    private List<User> participants;
    private Room room;
    private String organizerId;
    private Status status;

    private Meeting(Builder b) {
        this.id = UUID.randomUUID().toString();
        this.title = b.title;
        this.timeSlot = b.timeSlot;
        this.participants = b.participants;
        this.room = b.room;
        this.organizerId = b.organizerId;
        this.status = b.status;
    }

    public String getTitle() {
        return title;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void cancel() {
        this.status = Status.CANCELLED;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public Room getRoom() {
        return room;
    }

    public String getId() {
        return id;
    }

    public String getOrganizerId() {
        return organizerId;
    }

    public Status getStatus() {
        return status;
    }

    public static class Builder {
        private String title;
        private TimeSlot timeSlot;
        private List<User> participants;
        private Room room;
        private String organizerId;
        private Status status;


       
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTimeSlot(TimeSlot timeSlot) {
            this.timeSlot = timeSlot;
            return this;
        }

        public Builder setParticipants(List<User> participants) {
            this.participants = participants;
            return this;
        }

        public Builder setRoom(Room room) {
            this.room = room;
            return this;
        }

        public Builder setOrganizerId(String organizerId2) {
            this.organizerId = organizerId2;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Meeting build() {
            if (organizerId == null) {
                throw new IllegalArgumentException("Organizer ID is required");
            }
            return new Meeting(this);
        }

        public Builder setDescription(String description) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setDescription'");
        }
    }
}
