package student;

import student.domain.Gender;
import student.domain.Student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentStatistics {

    private final Collection<Student> students;

    /**
     * Reads all students from given csv-File.
     *
     * @param path the file
     * @throws IOException
     */
    public StudentStatistics(Path path) throws IOException {
        students = Files.lines(path)
                .skip(1)
                .map(Student::of)
                .collect(Collectors.toSet());
    }

    /**
     * Counts all students of given gender
     *
     * @param gender the gender
     * @return number of students of the given gender
     */
    public long countGender(Gender gender) {
        return students.stream()
                .filter(student -> student.gender().equals(gender))
                .count();
    }

    /**
     * Returns all students classes sorted alphabetically.
     *
     * @return all students classes sorted alphabetically
     */
    public SortedSet<String> getClasses() {
        return students.stream()
                .map(Student::schoolClass)
                .sorted()
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Returns a map containing the number students of each gender in a given class.
     *
     * @param schoolClass the class
     * @return count of students of each gender
     */
    public Map<Gender, Long> getGenderCountForClass(String schoolClass) {

        return students.stream()
                .filter(student -> student.schoolClass().equals(schoolClass))
                .collect(Collectors.groupingBy(Student::gender,
                        TreeMap::new,
                        Collectors.counting()));
    }

    /**
     * Returns all students whose second name contains the given sequence.
     *
     * @param sequence the sequence to search for. Case sensitive
     * @return students named like %sequence%
     */
    public List<Student> getAllWithSecondNameLike(String sequence) {
        return students.stream()
                .filter(student -> student.secondName().contains(sequence))
                .toList();
    }

    /**
     * Finds a student by number and class
     *
     * @param number      students number
     * @param schoolClass students class
     * @return the student or Optional.empty if no student matches criteria
     */
    public Optional<Student> findByNumberAndClass(int number, String schoolClass) {
        return students.stream()
                .filter(student -> student.number() == number && student.schoolClass().equals(schoolClass))
                .peek(student -> System.out.println(student.schoolClass() + " " + student.firstName() + student.secondName()))
                .findAny();
    }

    /**
     * Returns the student with the longest name.
     *
     * @return any student whose name contains the highest number of letters in all names
     */
    public Student getStudentWithLongestName() {
        return students.stream()
                .reduce((student, student2) -> student.firstName().length() + student.secondName().length() > student2.firstName().length() + student2.secondName().length() ? student : student2)
                .get();
    }

    /**
     * Returns the most frequently found first names
     *
     * @param count how many should be returned
     * @return the topX most frequent first names
     */
    public Set<String> getMostFrequentFirstNames(int count) {
        if (count<=0){
            throw new IllegalArgumentException();
        }
       return students.stream()
                .collect(Collectors.groupingBy(Student::firstName,
                        TreeMap::new,
                        Collectors.counting()))
               .entrySet()
               .stream()
               .sorted((o1, o2) -> (int) (o2.getValue()-o1.getValue()))
               .limit(count)
               .peek(System.out::println)
               .map(Map.Entry::getKey)
               .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * Returns the number of students in each year.
     *
     * @return count of students of each year
     */
    public Map<Integer, Long> countStudentsByYear() {
        return students.stream()
                .collect(Collectors.groupingBy(o -> Integer.parseInt(o.schoolClass().substring(0,1)),
                        TreeMap::new,
                        Collectors.counting()));
    }
}
