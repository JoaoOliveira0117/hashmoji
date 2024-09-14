import java.nio.file.Path;
import java.nio.file.Paths;

/**\
 * Emoji hasher implementation
 * 
 * How does it work?
 * It calculates the sum of the unicode characters in each word of the ../public/text.txt file
 * Then generates the corresponding emoji and maps it on the HashTable class
 * 
 * Limitations?
 * There are an limited amount of emojis, 80 to be exact.
 */

public class App {
    public static void main(String[] args) throws Exception {
        HashTable hashTable = new HashTable();

        Path publicPath = Paths.get("../public").toAbsolutePath().normalize();
        String filepath = publicPath.toString() + "/text.txt";

        String content = new ReadFile(filepath).getContent();
        String regex = "[,\\.\\s]";
        String[] words = content.split(regex);

        StringBuilder sb = new StringBuilder();

        int baseEmojiNumber = 0x1F600;
        int emojiRange = 0x1F64F - 0x1F600;

        for(String word : words) {
            if (!word.isBlank()) {
                int i = 0;
                int codePoint = baseEmojiNumber + (word.chars().sum() % (emojiRange + 1));
                String emoji = Character.toString(codePoint);
        
                if (hashTable.get(emoji).equals(word)) {
                    sb.append(emoji);
                    continue;
                }
                
                while (hashTable.get(emoji) != null && !hashTable.get(emoji).isEmpty() && !hashTable.get(emoji).isBlank()) {
                    i++;
                    if (i > emojiRange) {
                        i = 0;
                    }
                    codePoint = baseEmojiNumber + i;
                    emoji = Character.toString(codePoint);
                }

                hashTable.set(emoji, word);
                sb.append(emoji);
            }
        }

        hashTable.printTable();
        System.out.println("Emojified text: " + sb.toString());
    }
}
