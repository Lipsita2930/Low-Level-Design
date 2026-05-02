package Questions.MeetingScheduler.Entity;

public class User {
    
        private String id;
        private String name;
        private String email;
        private String phone;
        private UserCalender calender;
    
        public User(String id, String name, String email, String phone) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.calender = new UserCalender(id);

        }
    
        public String getEmail() {
            return email;
        }
    
        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public UserCalender getCalender() {
            return calender;
        }

        public void setCalender(UserCalender calender) {
            this.calender = calender;
        }
    }