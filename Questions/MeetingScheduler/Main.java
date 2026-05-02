package Questions.MeetingScheduler;

import java.time.*;
import java.util.List;

import Questions.MeetingScheduler.Entity.Meeting;
import Questions.MeetingScheduler.Entity.Room;
import Questions.MeetingScheduler.Entity.User;
import Questions.MeetingScheduler.NotificationService.EmailNotification;

public class Main {

 public static void main(String[] args) {

 // ── 1. Configure the scheduler ────────────────────────────────
 MeetingScheduler scheduler = MeetingScheduler.getInstance();

 // Add rooms to the room pool
 scheduler.addRoom(new Room("R1", "Board Room", 10));
 scheduler.addRoom(new Room("R2", "Zoom Room", 4));

 // Register notification observers (Observer pattern)
 scheduler.addListener(new EmailNotification());


 // ── 2. Create participants ────────────────────────────────────
 User alice = new User("u1", "Alice", "alice@co.com", "555-0101");
 User bob = new User("u2", "Bob", "bob@co.com", "555-0102");
 User carol = new User("u3", "Carol", "carol@co.com", "555-0103");

 // ── 3. Schedule a meeting ─────────────────────────────────────
 Meeting standup = scheduler.scheduleMeeting(
                "Daily Standup",
                "15-minute team sync",
                List.of(alice, bob, carol),
                Duration.ofMinutes(30),
                alice.getId() 
 );

 System.out.println("Scheduled: " + standup.getTitle() + " at " + standup.getTimeSlot());

 // ── 4. Schedule another meeting (auto finds next free slot) ───
 Meeting review = scheduler.scheduleMeeting(
                "Sprint Review",
                "End of sprint demo",
                List.of(alice, bob),
                Duration.ofHours(1),
                alice.getId()
 );

 System.out.println("Scheduled: " + review.getTitle() + " at " + review.getTimeSlot());

 // ── 5. Cancel the standup ─────────────────────────────────────
 scheduler.cancelMeeting(standup.getId(), alice.getId());
 System.out.println("Standup status: " + standup.getStatus()); // CANCELLED

 // ── 6. Undo the cancel (Command pattern undo) ─────────────────
 scheduler.undoLastAction();
 System.out.println("After undo: " + standup.getStatus()); // SCHEDULED again

 
}
}
