package com.example.demo;


import java.util.Arrays;
import java.util.List;

public class DashboardData {
    public int generatedDiagrams;
    public int diagramIncrease;
    public int registeredUsers;
    public int userIncrease;
    public int loggedInUsers;
    public int loginIncrease;
    public int activeUsers;
    public int activeIncrease;
    public WeeklyData weeklyData;
    public List<MonthlyData> monthlyData;
    public List<TopUser> topUsers;
    public List<DiagramType> diagramTypes;
    public List<UserRetention> userRetention;

    public static class WeeklyData {
        public List<Number> diagrams;
        public List<Number> registrations;
        public List<Number> logins;
        public List<Number> activeUsers;
    }

    public static class MonthlyData {
        public String month;
        public int diagrams;
        public int registrations;
        public int logins;
        public int activeUsers;

        public MonthlyData(String month, int diagrams, int registrations, int logins, int activeUsers) {
            this.month = month;
            this.diagrams = diagrams;
            this.registrations = registrations;
            this.logins = logins;
            this.activeUsers = activeUsers;
        }
    }

    public static class TopUser {
        public String name;
        public int diagramsCreated;
        public String lastActive;

        public TopUser(String name, int diagramsCreated, String lastActive) {
            this.name = name;
            this.diagramsCreated = diagramsCreated;
            this.lastActive = lastActive;
        }
    }

    public static class DiagramType {
        public String type;
        public int count;

        public DiagramType(String type, int count) {
            this.type = type;
            this.count = count;
        }
    }

    public static class UserRetention {
        public String month;
        public int retention;

        public UserRetention(String month, int retention) {
            this.month = month;
            this.retention = retention;
        }
    }

    // Sample data initialization method (optional)
    public static DashboardData getSampleData() {
        DashboardData data = new DashboardData();
        data.generatedDiagrams = 15234;
        data.diagramIncrease = 12;
        data.registeredUsers = 8750;
        data.userIncrease = 5;
        data.loggedInUsers = 6500;
        data.loginIncrease = 8;
        data.activeUsers = 4200;
        data.activeIncrease = 3;

        // Weekly data
        data.weeklyData = new WeeklyData();
        data.weeklyData.diagrams = Arrays.asList(120, 132, 145, 160, 178, 190, 210);
        data.weeklyData.registrations = Arrays.asList(25, 30, 35, 40, 45, 50, 55);
        data.weeklyData.logins = Arrays.asList(80, 85, 90, 95, 100, 105, 110);
        data.weeklyData.activeUsers = Arrays.asList(60, 62, 65, 68, 70, 72, 75);

        // Monthly data
        data.monthlyData = Arrays.asList(
                new MonthlyData("Jan", 3200, 520, 2800, 1900),
                new MonthlyData("Feb", 3400, 540, 2900, 2000),
                new MonthlyData("Mar", 3600, 560, 3000, 2100),
                new MonthlyData("Apr", 3800, 580, 3100, 2200),
                new MonthlyData("May", 4000, 600, 3200, 2300),
                new MonthlyData("Jun", 4200, 620, 3300, 2400)
        );

        // Top users
        data.topUsers = Arrays.asList(
                new TopUser("Alice Johnson", 145, "2 hours ago"),
                new TopUser("Bob Smith", 132, "1 day ago"),
                new TopUser("Charlie Brown", 120, "3 hours ago"),
                new TopUser("Diana Prince", 118, "5 hours ago"),
                new TopUser("Ethan Hunt", 105, "1 hour ago")
        );

        // Diagram types
        data.diagramTypes = Arrays.asList(
                new DiagramType("Flowchart", 4500),
                new DiagramType("ER Diagram", 3200),
                new DiagramType("UML", 2800),
                new DiagramType("Mindmap", 2500),
                new DiagramType("Gantt Chart", 2200)
        );

        // User retention
        data.userRetention = Arrays.asList(
                new UserRetention("Jan", 85),
                new UserRetention("Feb", 82),
                new UserRetention("Mar", 80),
                new UserRetention("Apr", 83),
                new UserRetention("May", 85),
                new UserRetention("Jun", 87)
        );

        return data;
    }
}
