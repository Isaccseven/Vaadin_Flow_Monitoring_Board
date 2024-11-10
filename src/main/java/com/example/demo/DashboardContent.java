package com.example.demo;

import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.config.builder.*;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.helper.Series;
import com.github.appreciated.apexcharts.config.stroke.Curve;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.*;

@Route("")
@CssImport("./styles/shared-styles.css")
public class DashboardContent extends AppLayout {


    public DashboardContent() {
        DashboardData data = DashboardData.getSampleData();

        // Main layout
        Div mainLayout = new Div();

        // Sidebar
        SideNav sidebar = createSidebar();
        DrawerToggle drawerToggle = new DrawerToggle();
        Scroller scroller = new Scroller(sidebar);

        // Main content
        Div mainContent = new Div();
        mainContent.addClassName("main-content");

        // Dashboard content
        Div dashboardMain = createDashboardMain(data);

        mainContent.add( dashboardMain);

        mainLayout.add(mainContent);

        setContent(mainLayout);
        addToDrawer(scroller);
        addToNavbar(drawerToggle);
    }

    private SideNav createSidebar() {
        SideNav sideNav = new SideNav();
        sideNav.addItem(
                new SideNavItem("Dashboard", "/dashboard",
                        VaadinIcon.DASHBOARD.create()),
                new SideNavItem("Analytics", "/analytics",
                        VaadinIcon.CHART.create()));
        return sideNav;
    }


    private Div createDashboardMain(DashboardData data) {
        Div dashboardMain = new Div();
        dashboardMain.addClassName("dashboard-main");

        H3 title = new H3("SaaS Dashboard");
        title.addClassName("dashboard-title");

        // Metrics grid
        Div metricsGrid = new Div();
        metricsGrid.addClassName("metrics-grid");

        metricsGrid.add(
                createMetricCard("Generated Diagrams", VaadinIcon.BAR_CHART, data.generatedDiagrams, data.diagramIncrease),
                createMetricCard("Registered Users", VaadinIcon.USERS, data.registeredUsers, data.userIncrease),
                createMetricCard("Logged In Users", VaadinIcon.USER_CHECK, data.loggedInUsers, data.loginIncrease),
                createMetricCard("Active Users", VaadinIcon.DASHBOARD, data.activeUsers, data.activeIncrease)
        );

        // Charts and tables
        Div chartsSection = new Div();
        chartsSection.addClassName("charts-section");

        // Weekly Metrics Chart
        Div weeklyChartCard = createChartCard("Weekly SaaS Metrics", createWeeklyChart(data));

        // Monthly Trend Chart
        Div monthlyChartCard = createChartCard("Monthly Trend", createMonthlyChart(data));

        // Diagram Types Chart
        Div diagramTypesCard = createChartCard("Diagram Types Distribution", createDiagramTypesChart(data));

        // User Retention Chart
        Div userRetentionCard = createChartCard("User Retention", createUserRetentionChart(data));

        // Top Users Card
        Div topUsersCard = createTopUsersCard(data);

        // Monthly Report Card
        Div monthlyReportCard = createMonthlyReportCard(data);

        chartsSection.add(weeklyChartCard, monthlyChartCard, diagramTypesCard, userRetentionCard, topUsersCard, monthlyReportCard);

        dashboardMain.add(title, metricsGrid, chartsSection);

        return dashboardMain;
    }

    private Div createMetricCard(String title, VaadinIcon icon, int value, int increase) {
        // Metric card code remains the same...
        // For brevity, I've omitted this code section.
        return new Div(); // Placeholder
    }

    private Div createChartCard(String titleText, Component chart) {
        Div card = new Div();
        card.addClassName("chart-card");

        H4 title = new H4(titleText);
        title.addClassName("chart-title");

        card.add(title, chart);

        return card;
    }

    // Updated methods to create charts using ApexCharts

    private Component createWeeklyChart(DashboardData data) {
        ApexCharts chart = new ApexCharts();
               chart.setSeries(
                        new Series<>("Generated Diagrams", data.weeklyData.diagrams.toArray(new Integer[0])),
                        new Series<>("Registered Users", data.weeklyData.registrations.toArray(new Integer[0])),
                        new Series<>("Logged In Users", data.weeklyData.logins.toArray(new Integer[0])),
                        new Series<>("Active Users", data.weeklyData.activeUsers.toArray(new Integer[0]))
                );
                chart.setChart(ChartBuilder.get()
                        .withType(Type.LINE)
                        .build());

                chart.setXaxis(XAxisBuilder.get()
                        .withCategories("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
                        .build());

                chart.setStroke(StrokeBuilder.get()
                        .withCurve(Curve.SMOOTH)
                        .build());

        return chart;
    }

    private Component createMonthlyChart(DashboardData data) {
        List<String> months = new ArrayList<>();
        List<Integer> diagrams = new ArrayList<>();
        List<Integer> registrations = new ArrayList<>();

        for (DashboardData.MonthlyData item : data.monthlyData) {
            months.add(item.month);
            diagrams.add(item.diagrams);
            registrations.add(item.registrations);
        }

        ApexCharts chart = new ApexCharts();
                chart.setChart(ChartBuilder.get()
                        .withType(Type.BAR)
                        .build());
                chart.setSeries(
                        new Series<>("Generated Diagrams", diagrams.toArray(new Integer[0])),
                        new Series<>("Registered Users", registrations.toArray(new Integer[0]))
                );
                chart.setXaxis(XAxisBuilder.get()
                        .withCategories(months.toArray(new String[0]))
                        .build());

        return chart;
    }

    private Component createDiagramTypesChart(DashboardData data) {
        List<String> types = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();

        for (DashboardData.DiagramType item : data.diagramTypes) {
            types.add(item.type);
            counts.add(item.count);
        }

        ApexCharts chart = new ApexCharts();
        chart.setChart(ChartBuilder.get()
                        .withType(Type.PIE)
                        .build());
        chart.setSeries(new Series<>(counts.toArray(new Integer[0])));
        chart.setLabels(types.toArray(new String[0]));


        return chart;
    }

    private Component createUserRetentionChart(DashboardData data) {
        List<String> months = new ArrayList<>();
        List<Integer> retentions = new ArrayList<>();

        for (DashboardData.UserRetention item : data.userRetention) {
            months.add(item.month);
            retentions.add(item.retention);
        }

        ApexCharts chart = new ApexCharts();
        chart.setChart(ChartBuilder.get()
                        .withType(Type.BAR)
                        .build());
        chart.setSeries(new Series<>("User Retention", retentions.toArray(new Integer[0])));
        chart.setXaxis(XAxisBuilder.get()
                        .withCategories(months.toArray(new String[0]))
                        .build());

        return chart;
    }

    private Div createTopUsersCard(DashboardData data) {
        // Top users card code remains the same...
        // For brevity, I've omitted this code section.
        return new Div(); // Placeholder
    }

    private Div createMonthlyReportCard(DashboardData data) {
        // Monthly report card code remains the same...
        // For brevity, I've omitted this code section.
        return new Div(); // Placeholder
    }

    // Other helper methods and data classes remain the same...
}
