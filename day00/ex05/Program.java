import java.util.Arrays;
import java.util.Scanner;

public class Program {
    static final int STUDY_DAYS_IN_WEEK = 7;

    static final int WEEKS_IN_MONTH = 5;

    static final int MIN_START_TIME = 1;

    static final int MAX_END_TIME = 6;

    static final int MAX_LESSON_IN_WEEK = 10;

    static final int MAX_COUNT_STUDENTS = 10;

    private static String[] getStudents(Scanner scanner) {
        String[] students = new String[MAX_COUNT_STUDENTS];

        for (int i = 0; i < MAX_COUNT_STUDENTS; i++) {
            String line = scanner.nextLine();

            if (line.equals(".")) {
                break;
            }

            students[i] = line;
        }

        return students;
    }

    private static boolean[][] getLessonDays(Scanner scanner) {
        boolean[][] lessonDays = new boolean[STUDY_DAYS_IN_WEEK][MAX_END_TIME - MIN_START_TIME];

        for (int i = 0; i < MAX_LESSON_IN_WEEK; i++) {
            if (!scanner.hasNextInt()) {
                scanner.next();
                break;
            }

            int lessonTime = scanner.nextInt();

            String lessonDay = scanner.next();

            if (lessonDays[getDayNumberByName(lessonDay)][lessonTime - 1]) {
                System.err.println("This time is already in used in schedule");
                System.exit(-1);
            }

            lessonDays[getDayNumberByName(lessonDay)][lessonTime - 1] = true;
        }

        return lessonDays;
    }

    private static int getDayNumberByName(String lessonDay) {
        int dayNumber = 0;

        switch (lessonDay) {
            case "MO":
                dayNumber = 0;
                break;
            case "TU":
                dayNumber = 1;
                break;
            case "WE":
                dayNumber = 2;
                break;
            case "TH":
                dayNumber = 3;
                break;
            case "FR":
                dayNumber = 4;
                break;
            case "SA":
                dayNumber = 5;
                break;
            case "SU":
                dayNumber = 6;
        }

        return dayNumber;
    }

    private static String getDayNameByNumber(int number) {
        String day = "";

        switch (number) {
            case 0:
                day = "MO";
                break;
            case 1:
                day = "TU";
                break;
            case 2:
                day = "WE";
                break;
            case 3:
                day = "TH";
                break;
            case 4:
                day = "FR";
                break;
            case 5:
                day = "SA";
                break;
            case 6:
                day = "SU";
        }

        return day;
    }

    private static int getCountStudents(String[] students) {
        int count = 0;

        while (students[count] != null) {
            count++;
        }

        return count;
    }

    private static int getParticipantStatus(String isHere) {
        if (isHere.equals("HERE")) {
            return 1;
        }
        return -1;
    }

    private static boolean checkExistingLesson(int dayOfWeek, int time, boolean[][] lessonDays) {
        return lessonDays[dayOfWeek][time];
    }

    private static int getStudentIndexByName(String name, String[] students) {
        for (int i = 0; i < MAX_COUNT_STUDENTS; i++) {
            if (students[i].equals(name)) {
                return i;
            }
        }

        return -1;
    }

    private static void fillSchedule(Scanner scanner, int[][][][] schedule, String[] students, boolean[][] lessonDays) {
        for (int i = 0; i < MAX_COUNT_STUDENTS * MAX_LESSON_IN_WEEK; i++) {
            String name = scanner.next();

            if (name.equals(".")) {
                break;
            }

            int time = scanner.nextInt();

            int dayOfMonth = scanner.nextInt();

            String isHere = scanner.next();

            int studentIndex = getStudentIndexByName(name, students);

            if (studentIndex < 0) {
                System.err.println("There is no student with this name");
                System.exit(-1);
            }

            int dayOfWeek = dayOfMonth % 7;

            int numberOfWeek = dayOfMonth / 7;

            if (!checkExistingLesson(dayOfWeek, time - 1, lessonDays)) {
                System.err.println("There is no lesson at this time");
                System.exit(-1);
            }

            if (dayOfWeek == 0 || dayOfWeek == 1 || dayOfWeek == 2 || dayOfWeek == 4)
                numberOfWeek++;

            schedule[studentIndex][dayOfWeek][numberOfWeek - 1][time - 1] = getParticipantStatus(isHere);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] students = getStudents(scanner);

        boolean[][] lessonDays = getLessonDays(scanner);

        int[][][][] schedule = new int[getCountStudents(students)][STUDY_DAYS_IN_WEEK][WEEKS_IN_MONTH][MAX_END_TIME - MIN_START_TIME];

        fillSchedule(scanner, schedule, students, lessonDays);

        printSchedule(schedule, students, lessonDays);

        scanner.close();
    }

    private static void printSchedule(int[][][][] schedule, String[] students, boolean[][] lessonDays) {
        System.out.print("          ");

        for (int week = 0; week < WEEKS_IN_MONTH; week++) {
            for (int day = 0; day < STUDY_DAYS_IN_WEEK; day++) {
                for (int time = 0; time < MAX_END_TIME - MIN_START_TIME; time++) {
                    if (lessonDays[day][time] && (7 * week + day) > 0) {
                        System.out.printf("%1d:00%3s%3d|", time + 1, getDayNameByNumber(day), (7 * week + day));
                    }
                }
            }
        }

        System.out.println();

        for (int i = 0; i < MAX_COUNT_STUDENTS; i++) {
            if (students[i] == null) {
                break;
            }

            System.out.printf("%10s", students[i]);

            for (int week = 0; week < WEEKS_IN_MONTH; week++) {
                for (int day = 0; day < STUDY_DAYS_IN_WEEK; day++) {
                    for (int time = 0; time < MAX_END_TIME - MIN_START_TIME; time++) {
                        if (week == 0 && (day == 0 || day == 6))
                            continue;

                        if (week == 4 && (day > 2 & day < 5))
                            continue;

                        if (lessonDays[day][time]) {
                            String result = "";

                            if (schedule[i][day][week][time] == 1) {
                                result = "1";
                            } else if (schedule[i][day][week][time] == -1) {
                                result = "-1";
                            }

                            System.out.printf("%10s|", result);
                        }
                    }
                }
            }

            System.out.println();
        }
    }
}
