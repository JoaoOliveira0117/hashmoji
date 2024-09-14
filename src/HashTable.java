import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashTable {
  private int baseEmojiNumber = 0x1F600;
  private int emojiRange = 0x1F64F - 0x1F600;
  private final int HASH_SIZE = baseEmojiNumber - emojiRange + 1;
  private Map<String, String> table = new HashMap<String, String>();
  private Set<String> generatedEmojis = new HashSet<>();

  public HashTable() {
    generateHashTable();
  }

  private String getRandomEmoji() {
    String emoji;

    do {
        int result = baseEmojiNumber + (int) (Math.random() * (emojiRange + 1));
        emoji = Character.toString(result);

        generatedEmojis.add(emoji);
    } while (!emojiExists(emoji));

    return emoji;
  }

  private boolean emojiExists(String emoji) {
    return generatedEmojis.contains(emoji);
  }

  private void generateHashTable() {
    for (int i = 0; i < HASH_SIZE; i++) {
      table.put(getRandomEmoji(), "");
    }
  }

  public void set(String key, String value) {
    table.put(key, value);
  }

  public String get(String key) {
    return table.get(key);
  }

  public void printTable() {
    for (Map.Entry<String, String> entry : table.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }
  }
}
