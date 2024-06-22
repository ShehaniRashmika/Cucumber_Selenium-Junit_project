package words;

import java.util.*;
import java.util.stream.Collectors;

public class Words {
    public static List<String> getUniqueWordsFromSentence(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(sentence.toLowerCase().split("\\W+"))
                .collect(Collectors.toList()) // Collect to mutable list first
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
