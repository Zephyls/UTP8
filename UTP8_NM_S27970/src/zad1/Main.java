/**
 * @author Nguyen Manh Tuan S27970
 */
package zad1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, List<String>> anagrams = new BufferedReader(
            new InputStreamReader(new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt").openStream())
        ).lines().collect(Collectors.groupingBy(word -> {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }));

        anagrams.values().stream()
            .filter(group -> group.size() > 1)
            .sorted((a, b) -> b.size() - a.size())
            .filter(group -> group.size() == anagrams.values().stream().mapToInt(List::size).max().orElse(0))
            .forEach(group -> System.out.println(String.join(" ", group)));
    }
}
