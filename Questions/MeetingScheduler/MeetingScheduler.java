package Questions.MeetingScheduler;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import Questions.MeetingScheduler.Entity.Meeting;
import Questions.MeetingScheduler.Entity.Room;
import Questions.MeetingScheduler.Entity.TimeSlot;
import Questions.MeetingScheduler.Entity.User;
import Questions.MeetingScheduler.MeetingCommand.CancelMeetingCommand;
import Questions.MeetingScheduler.MeetingCommand.MeetingCommand;
import Questions.MeetingScheduler.MeetingCommand.ScheduleMeetingCommand;
import Questions.MeetingScheduler.NotificationService.MeetingEventListener;
import Questions.MeetingScheduler.Repository.InMemoryMeetingRepository;
import Questions.MeetingScheduler.Repository.InMemoryRoomRepository;
import Questions.MeetingScheduler.Repository.MeetingRepository;
import Questions.MeetingScheduler.Repository.RoomRepository;
import Questions.MeetingScheduler.SchedulingStrategy.EarliestAvailableSlotStrategy;
import Questions.MeetingScheduler.SchedulingStrategy.SchedulingStrategy;

import java.time.*; import java.util.*;

public class MeetingScheduler {

 // volatile guarantees that all threads see the same reference after assignment
 private static volatile MeetingScheduler instance;

 private final List<MeetingEventListener> listeners = new ArrayList<>();
 private final Deque<MeetingCommand> commandHistory = new ArrayDeque<>();
 private final MeetingRepository meetingRepo;
 private final RoomRepository roomRepo;
 private SchedulingStrategy strategy;

 private MeetingScheduler() {
        this.meetingRepo = new InMemoryMeetingRepository();
        this.roomRepo = new InMemoryRoomRepository();
        this.strategy = new EarliestAvailableSlotStrategy();
 }

 // Double-checked locking — thread-safe, lazy, efficient

 public static MeetingScheduler getInstance() {
    
        if (instance == null) {
            synchronized (MeetingScheduler.class) {

                if (instance == null) {
                    instance = new MeetingScheduler();
                }
            }
 }
        return instance;
 }

 // ─── Configuration (called once at startup) ───────────────────
 public void addRoom(Room r) { 
    roomRepo.addRoom(r); 
}
 public void addListener(MeetingEventListener l) { 
    listeners.add(l); 
}
 public void setStrategy(SchedulingStrategy s) { 
    this.strategy = s; 
}

 // ─── Core Operations ──────────────────────────────────────────
 public Meeting scheduleMeeting(String title,String description,List<User> participants,Duration duration,String organizerId) {

                // Step 1: find a slot where everyone is free (Strategy pattern)
                TimeSlot slot = strategy.findAvailableSlot(participants, duration, LocalDateTime.now())
                                        .orElseThrow(() -> new NoAvailableSlotException("No free slot found in the next 14 days for all participants"));

                // Step 2: find a room that can fit everyone (Repository pattern)
                Room room = roomRepo.findAvailableRoom(slot, participants.size())
                .orElseThrow(() -> new NoAvailableRoomException(
                "No room available for " + participants.size() + " people at " + slot));

                // Step 3: build the immutable Meeting object (Builder pattern)
                Meeting meeting = new Meeting.Builder()
                                        .setTitle(title)
                                        .setDescription(description)
                                        .setTimeSlot(slot)
                                        .setParticipants(participants)
                                        .setRoom(room)
                                        .setOrganizerId(organizerId)
                                        .build();

                // Step 4: execute via command (Command pattern — enables undo)
                MeetingCommand cmd = new ScheduleMeetingCommand(meeting, meetingRepo, listeners);
                cmd.execute();
                commandHistory.push(cmd);

                return meeting;

 }

 public void cancelMeeting(String meetingId, String requesterId) {
        Meeting meeting = meetingRepo.findById(meetingId)
        .orElseThrow(() -> new MeetingNotFoundException("Meeting not found: " + meetingId));

        if (!meeting.getOrganizerId().equals(requesterId))
        throw new UnauthorizedException("Only the organizer can cancel this meeting");

        MeetingCommand cmd = new CancelMeetingCommand(meeting, meetingRepo, listeners);
        cmd.execute();
        commandHistory.push(cmd);
}


public void undoLastAction() {
    if (!commandHistory.isEmpty()) {
    commandHistory.pop().undo();
}
 }

 public List<Meeting> getAllMeetings() { return meetingRepo.findAll(); }
}

// Custom exceptions — meaningful error signals
class NoAvailableSlotException extends RuntimeException { public NoAvailableSlotException(String m) { super(m); } }
class NoAvailableRoomException extends RuntimeException { public NoAvailableRoomException(String m) { super(m); } }
class MeetingNotFoundException extends RuntimeException { public MeetingNotFoundException(String m) { super(m); } }
class UnauthorizedException extends RuntimeException { public UnauthorizedException(String m) { super(m); } }



